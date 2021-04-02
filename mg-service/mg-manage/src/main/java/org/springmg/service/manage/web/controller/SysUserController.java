package org.springmg.service.manage.web.controller;

import com.github.pagehelper.PageInfo;
import org.springmg.common.exception.ServiceException;
import org.springmg.service.manage.annotation.Log;
import org.springmg.service.manage.aop.LogAspect;
import org.springmg.service.manage.cache.UserInfoCacheService;
import org.springmg.service.manage.web.PermissionConstant;
import org.springmg.service.manage.web.RouteConstant;
import org.springmg.service.manage.service.SysUserAvatarService;
import org.springmg.service.manage.service.SysUserService;
import org.springmg.service.model.ErrorCodeConsts;
import org.springmg.service.model.ResultData;
import org.springmg.service.model.ResultTable;
import org.springmg.service.model.vo.DeleteVo;
import org.springmg.service.model.vo.sys.SysUserTableParamsVo;
import org.springmg.service.model.vo.sys.SysUserVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/6/29 17:28
 */
@RestController
public class SysUserController {


    @Resource
    SysUserService sysUserService;

    @Resource
    SysUserAvatarService sysUserAvatarService;

    @Resource
    private UserInfoCacheService userInfoCacheService;


    @RequiresPermissions(PermissionConstant.SYS_USER_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_USER)
    @Log(type = LogAspect.LogType.SELECT, value = "查看用户列表")
    public ResultTable list(@RequestBody SysUserTableParamsVo requestParam) {
        ResultTable resultTable = new ResultTable();
        try {
            PageInfo pageInfo = sysUserService.getByParams(requestParam);
            resultTable.setPageInfo(pageInfo);
            return resultTable;
        } catch (Exception e) {
            return resultTable;
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_USER)
    public ResultData get(@RequestParam("id") Long id) {

        return new ResultData().setData(sysUserService.getById(id));
    }


    @RequiresPermissions(PermissionConstant.SYS_USER_DEL)
    @PostMapping(RouteConstant.ROUTER_SYS_USER_DEL)
    @Log(type = LogAspect.LogType.DEL, value = "删除用户")
    public ResultData del(@RequestBody DeleteVo deleteVo) {
        try {
            sysUserService.del(deleteVo);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_ADD)
    @PostMapping(RouteConstant.ROUTER_SYS_USER_ADD)
    @Log(type = LogAspect.LogType.ADD, value = "添加用户")
    public ResultData add(@RequestBody @Validated SysUserVo k) {
        try {
            sysUserService.add(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_USER_EDIT)
    @Log(type = LogAspect.LogType.EDIT, value = "修改用户")
    public ResultData edit(@RequestBody @Validated SysUserVo k) {
        try {
            sysUserService.edit(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_DISABLE)
    @PostMapping(RouteConstant.ROUTER_SYS_USER_DISABLE)
    @Log(type = LogAspect.LogType.EDIT, value = "修改用户状态")
    public ResultData disable(@PathVariable(value = "uid") Long userId,
                              @PathVariable(value = "disable", required = false) Integer disable) {
        try {
            sysUserService.disable(userId, disable);
            userInfoCacheService.leaveMessage(userId, "你已经被禁止登陆");
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(ErrorCodeConsts.ERROR)
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_USER_FETCH)
    public ResultData fetch(@PathVariable(value = "value") String value) {
        try {
            ResultData resultData = new ResultData();
            List list = sysUserService.fetch(value);
            resultData.setData(list);
            return resultData;
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(ErrorCodeConsts.ERROR)
                    .setErrorMessage(e.getMessage());
        }
    }


    @RequiresPermissions(PermissionConstant.SYS_USER_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_USER_AVATAR)
    public ResultData updateAvatar(@RequestParam("avatar") MultipartFile multipartFile) {
        ResultData result = new ResultData();
        try {
            Long id = sysUserAvatarService.updateAvatar(multipartFile);
            result.setData(id);
            return result;
        } catch (ServiceException e) {
            result.setErrorCode(e.getStatus());
            result.setErrorMessage(e.getMessage());
            return result;
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_USER_ROLE_GET_BY_ID)
    public ResultData getRoleIdsByUserId(@PathVariable(value = "userId") Long id) {
        ResultData resultData = new ResultData();
        List roles = sysUserService.getRoleIdsByUserId(id);
        resultData.setData(roles);
        return resultData;
    }
}
