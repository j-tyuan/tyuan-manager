package com.tyuan.manager.web.controller;

import com.github.pagehelper.PageInfo;
import com.tyuan.manager.annotation.Log;
import com.tyuan.manager.cache.UserInfoCacheService;
import com.tyuan.manager.service.ServiceException;
import com.tyuan.manager.web.PermissionConstant;
import com.tyuan.manager.web.RouteConstant;
import com.tyuan.manager.service.SysUserService;
import com.tyuan.model.ErrorCodeConsts;
import com.tyuan.model.ResultData;
import com.tyuan.model.ResultTable;
import com.tyuan.model.vo.DeleteVo;
import com.tyuan.model.vo.sys.SysUserTableParamsVo;
import com.tyuan.model.vo.sys.SysUserVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    private UserInfoCacheService userInfoCacheService;

    @RequiresPermissions(PermissionConstant.SYS_USER_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_USER_ALL)
    @Log(value = "查看用户列表")
    public ResultData list(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "phone", required = false) String phone) {
        ResultData resultData = new ResultData();
        try {
            List list = sysUserService.getAll(name, phone);
            resultData.setData(list);
            return resultData;
        } catch (Exception e) {
            return resultData;
        }
    }


    @RequiresPermissions(PermissionConstant.SYS_USER_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_USER)
    @Log(value = "查看用户列表")
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
    @Log(value = "删除用户")
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
    @Log(value = "添加用户")
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
    @Log(value = "修改用户")
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
    @PostMapping(RouteConstant.ROUTER_SYS_DISABLE)
    @Log(value = "修改用户状态")
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
}
