/**
 * @ClassName SysController
 * @Author dev@tyuan.design
 * @Date 2020/6/22 11:08
 */
package com.tyuan.manager.web.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tyuan.manager.annotation.Log;
import com.tyuan.manager.service.ServiceException;
import com.tyuan.manager.service.SysPermissionService;
import com.tyuan.manager.service.SysRoleService;
import com.tyuan.manager.service.SysUserService;
import com.tyuan.manager.utils.UserInfoHolder;
import com.tyuan.manager.web.RouteConstant;
import com.tyuan.manager.web.WebConstant;
import com.tyuan.manager.cache.UserInfoCacheService;
import com.tyuan.model.ErrorCodeConsts;
import com.tyuan.model.LoginResult;
import com.tyuan.model.ResultData;
import com.tyuan.model.SysParamConsts;
import com.tyuan.model.cache.CacheConstant;
import com.tyuan.model.pojo.SysPermission;
import com.tyuan.model.pojo.SysRole;
import com.tyuan.model.pojo.SysUser;
import com.tyuan.model.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class SysController {

    @Autowired
    UserInfoCacheService userTokenCacheService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private UserInfoCacheService userInfoCacheService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping(RouteConstant.ROUTER_SYS_LOGIN_ACCOUNT)
    public LoginResult loginAccount(@RequestBody LoginVo loginVo,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws ServiceException {
        LoginResult loginResult = new LoginResult();
        loginResult.setStatus("error");
        loginResult.setType(loginVo.getType());

        String account = loginVo.getUsername();
        String pwd = loginVo.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(pwd)) {
            return loginResult;
        }

        SysUser sysUser = sysUserService.getByAccount(account);
        if (null == sysUser) {
            return loginResult;
        }

        String pwdMd5 = DigestUtils.md5DigestAsHex(pwd.getBytes());
        if (!sysUser.getPassword().equals(pwdMd5)) {
            return loginResult;
        }
        if (sysUser.getDisabled()) {
            loginResult.setStatus("disable");
            return loginResult;
        }

        String token = UUID.randomUUID().toString();
        response.setHeader("Access-Control-Allow-Credentials", "true");
        StringBuilder builder = new StringBuilder();
        builder.append(sysUser.getAccount())
                .append(":")
                .append(token);
        Cookie cookie = new Cookie(WebConstant.TOKEN, builder.toString());
        cookie.setPath("/");
        cookie.setMaxAge(5 * 365 * 24 * 60 * 60);
        response.addCookie(cookie);

        // 更新登陆信息
        sysUserService.updateUserLoginInfo(request, sysUser.getId());

        // 更新用户的缓存信息
        updateCache(sysUser, token);
        loginResult.setStatus("ok");

        return loginResult;
    }

    @RequestMapping(RouteConstant.ROUTER_SYS_LOGOUT)
    public ResultData logout() {
        userTokenCacheService.clear(UserInfoHolder.getUserId());
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        ResultData resultData = new ResultData();
        resultData.setSuccess(true);
        return resultData;
    }

    @GetMapping(RouteConstant.ROUTER_CURRENT_USER_INFO)
    public ResultData getUserInfo(@ModelAttribute("userToken") String userToken) {
        Map user = (Map) userTokenCacheService.get(userToken);
        String userId = String.valueOf(user.get("id"));
        SysUser u = sysUserService.getById(Long.valueOf(userId));
        ResultData resultData = new ResultData();
        resultData.setData(u);
        resultData.setErrorCode(ErrorCodeConsts.SUCCESS);
        return resultData;
    }

    @GetMapping(RouteConstant.ROUTER_PERMISSION)
    public ResultData permission(@ModelAttribute("userToken") String userToken) {
        ResultData resultData = new ResultData();
        Map map = Maps.newHashMap();
        map.put("permission", userInfoCacheService.getPerm(userToken));
        map.put("role", userInfoCacheService.getRole(userToken));
        resultData.setData(map);
        resultData.setErrorCode(ErrorCodeConsts.SUCCESS);
        return resultData;
    }

    /**
     * 更新用户缓存信息
     *
     * @param sysUser
     * @param userToken
     */
    private void updateCache(SysUser sysUser, String userToken) {

        String expStr = (String) redisTemplate.opsForHash().get(CacheConstant.SYS_PARAM_MAP, SysParamConsts.LOGIN_EXPIRES);
        Long exp = Long.valueOf(expStr);

        Map map = Maps.newHashMap();
        map.put("id", sysUser.getId());
        map.put("account", sysUser.getAccount());
        map.put("name", sysUser.getName());

        userInfoCacheService.leaveMessage(sysUser.getId(), "你的账号已在其它地方登陆");

        userTokenCacheService.put(sysUser.getId(), userToken, map, exp);
        if (sysUser.getUserType() == 1) {
            userInfoCacheService.putRole(userToken, Lists.newArrayList("sys"), exp);
            userInfoCacheService.putPerm(userToken, Lists.newArrayList("sys"), exp);
            return;
        }
        List<SysRole> roles = sysRoleService.getRoleByUserId(sysUser.getId());
        List<String> roleCodes = Lists.newArrayList();
        List<String> perms = Lists.newArrayList();

        for (SysRole role : roles) {
            roleCodes.add(role.getCode());
            List<SysPermission> list = sysPermissionService.getByRoleId(role.getId());
            for (SysPermission item : list) {
                perms.add(item.getPermission());
            }
        }

        userInfoCacheService.putRole(userToken, roleCodes, exp);
        userInfoCacheService.putPerm(userToken, perms, exp);

    }

}
