/**
 * @ClassName RoleController
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
 */
package com.tyuan.manager.base.web.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.manager.base.annotation.Log;
import com.tyuan.manager.base.aop.LogAspect;
import com.tyuan.manager.base.web.PermissionConstant;
import com.tyuan.manager.base.web.RouteConstant;
import com.tyuan.manager.base.service.SysRoleService;
import com.tyuan.model.base.ErrorCodeConsts;
import com.tyuan.model.base.ResultData;
import com.tyuan.model.base.ResultTable;
import com.tyuan.model.base.pojo.SysRole;
import com.tyuan.model.base.vo.DeleteVo;
import com.tyuan.model.base.vo.sys.RoleUserTableParamsVo;
import com.tyuan.model.base.vo.sys.SysRoleTableParamsVo;
import com.tyuan.model.base.vo.sys.SysRoleVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SysRoleController {

    @Resource
    SysRoleService service;

    @RequiresPermissions(PermissionConstant.SYS_ROLE_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE)
    @Log(type = LogAspect.LogType.SELECT, value = "查看角色列表")
    public ResultTable list(@RequestBody SysRoleTableParamsVo requestParam) {
        ResultTable resultTable = new ResultTable();
        try {
            PageInfo<SysRole> pageInfo = service.getByParams(requestParam);
            resultTable.setPageInfo(pageInfo);
            return resultTable;
        } catch (Exception e) {
            return resultTable;
        }
    }


    @RequiresPermissions(PermissionConstant.SYS_ROLE_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_ROLE)
    @Log(type = LogAspect.LogType.SELECT, value = "查看角色")
    public ResultData get(@RequestParam(value = "id", required = false) Long id) {
        try {

            ResultData resultData = new ResultData();
            if (null != id) {
                SysRole sysRole = service.getById(id);
                resultData.setData(Lists.newArrayList(sysRole));
            } else {
                List<SysRole> roles = service.getAll();
                resultData.setData(roles);
            }
            return resultData;
        } catch (Exception e) {
            return new ResultData()
                    .setErrorCode(ErrorCodeConsts.ERROR)
                    .setErrorMessage(e.getMessage());
        }
    }


    @RequiresPermissions(PermissionConstant.SYS_ROLE_DEL)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE_DEL)
    @Log(type = LogAspect.LogType.DEL, value = "删除角色")
    public ResultData del(@RequestBody DeleteVo deleteVo) {
        try {
            service.del(deleteVo);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_ROLE_ADD)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE_ADD)
    @Log(type = LogAspect.LogType.ADD, value = "添加角色")
    public ResultData add(@RequestBody @Validated SysRoleVo k) {
        try {
            service.add(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_ROLE_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE_EDIT)
    @Log(type = LogAspect.LogType.EDIT, value = "修改角色")
    public ResultData edit(@RequestBody @Validated SysRoleVo k) {
        try {
            service.edit(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_ROLE_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_ROLE_AUTH)
    public ResultData getAuthByRoleId(@PathVariable("id") Long id) {
        ResultData resultData = new ResultData();
        List<Long> longs = service.getAuthIdByRoleId(id);
        resultData.setData(longs);
        return resultData;
    }
}
