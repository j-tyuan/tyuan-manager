/**
 * @ClassName SysController
 * @Author dev@tyuan.design
 * @Date 2020/6/22 11:08
 * <p>
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.admin.web.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.tyuan.service.system.model.ResultData;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.common.utils.UserInfoHolder;
import org.tyuan.service.admin.web.RouteConstant;
import org.tyuan.service.admin.web.WebConstant;
import org.tyuan.service.framework.cache.UserInfoCacheService;
import org.tyuan.service.system.model.ErrorCodeConsts;
import org.tyuan.service.system.model.LoginResult;
import org.tyuan.service.system.model.SysParamConsts;
import org.tyuan.service.system.model.cache.CacheConstant;
import org.tyuan.service.system.model.pojo.SysPermission;
import org.tyuan.service.system.model.pojo.SysRole;
import org.tyuan.service.system.model.pojo.SysUser;
import org.tyuan.service.system.model.vo.LoginVo;
import org.tyuan.service.system.service.SysLoginLogService;
import org.tyuan.service.system.service.SysPermissionService;
import org.tyuan.service.system.service.SysUserService;

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
    private SysPermissionService sysPermissionService;

    @Resource
    private UserInfoCacheService userInfoCacheService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    SysLoginLogService sysLoginLogService;

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
        if (!sysUser.getUserPwd().equals(pwdMd5)) {
            return loginResult;
        }
        if (sysUser.getDisabled()) {
            loginResult.setStatus("disable");
            return loginResult;
        }

        String token = UUID.randomUUID().toString();
        response.setHeader("Access-Control-Allow-Credentials", "true");
        StringBuilder builder = new StringBuilder();
        builder.append(sysUser.getUserAccount())
                .append(":")
                .append(token);
        Cookie cookie = new Cookie(WebConstant.TOKEN, builder.toString());
        cookie.setPath("/");
        cookie.setMaxAge(5 * 365 * 24 * 60 * 60);
        response.addCookie(cookie);

        // 更新登陆信息
        sysUserService.updateUserLoginInfo(request, sysUser.getId());
        // 更新用户的缓存信息
        updateCache(request, sysUser, token);
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

    @GetMapping(RouteConstant.ROUTER_WATER_MARK)
    public ResultData watermark() {
        HashOperations operations = redisTemplate.opsForHash();
        Object o = operations.get(CacheConstant.SYS_PARAM_MAP, SysParamConsts.SYS_WATER_MARK);
        return new ResultData().setData(o);
    }

    /**
     * 更新用户缓存信息
     *
     * @param sysUser
     * @param userToken
     */
    private void updateCache(HttpServletRequest request, SysUser sysUser, String userToken) {

        String expStr = (String) redisTemplate.opsForHash().get(CacheConstant.SYS_PARAM_MAP, SysParamConsts.LOGIN_EXPIRES);
        Long exp = Long.valueOf(expStr);

        Map map = Maps.newHashMap();
        map.put("id", sysUser.getId());
        map.put("account", sysUser.getUserAccount());
        map.put("name", sysUser.getUserName());

        userInfoCacheService.leaveMessage(sysUser.getId(), "你的账号已在其它地方登陆");

        // 添加登陆日志
        sysLoginLogService.add(sysUser, request);

        userTokenCacheService.put(sysUser.getId(), userToken, map, exp);
        // 超级用户
        if (sysUser.getUserType() == 1) {
            userInfoCacheService.putRole(userToken, Lists.newArrayList("sys"), exp);
            userInfoCacheService.putPerm(userToken, Lists.newArrayList("sys"), exp);
            return;
        }
        List<SysRole> roles = sysUserService.getRoleByUserId(sysUser.getId());
        List<String> roleCodes = Lists.newArrayList();
        List<String> perms = Lists.newArrayList();

        for (SysRole role : roles) {
            roleCodes.add(role.getRoleCode());
            List<SysPermission> list = sysPermissionService.getByRoleId(role.getId());
            for (SysPermission item : list) {
                perms.add(item.getPermission());
            }
        }

        userInfoCacheService.putRole(userToken, roleCodes, exp);
        userInfoCacheService.putPerm(userToken, perms, exp);

    }

}
