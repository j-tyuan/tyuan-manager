/**
 * @ClassName RoleController
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
 */
package com.tyuan.manager.web.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tyuan.manager.service.ServiceException;
import com.tyuan.manager.web.PermissionConstant;
import com.tyuan.manager.web.RouteConstant;
import com.tyuan.manager.service.SysRoleService;
import com.tyuan.model.ErrorCodeConsts;
import com.tyuan.model.ResultData;
import com.tyuan.model.ResultTable;
import com.tyuan.model.pojo.SysRole;
import com.tyuan.model.vo.DeleteVo;
import com.tyuan.model.vo.sys.SysRoleTableParamsVo;
import com.tyuan.model.vo.sys.SysRoleVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class SysRoleController {

    @Resource
    SysRoleService service;

    @RequiresPermissions(PermissionConstant.SYS_ROLE_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE)
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
    @GetMapping(RouteConstant.ROUTER_SYS_ROLE_BY_USER_ID)
    public ResultData getByUserId(@RequestParam(value = "id") Long id) {
        ResultData resultData = new ResultData();

        Map m = Maps.newHashMap();
        List<SysRole> roles = service.getRoleByUserId(id);
        service.filterRoles(roles);
        m.put("roles", roles);
        resultData.setData(m);

        return resultData;
    }

    @RequiresPermissions(PermissionConstant.SYS_ROLE_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_ROLE)
    public ResultData get(@RequestParam(value = "id", required = false) Long id) {
        try {

            ResultData resultData = new ResultData();
            if (null != id) {
                SysRole sysRole = service.getById(id);

                List list = Lists.newArrayList(sysRole);
                service.filterRoles(list);
                if (CollectionUtils.isNotEmpty(list)) {

                    resultData.setData(list.get(0));
                }
            } else {
                List<SysRole> roles = service.getAll();
                service.filterRoles(roles);
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

    @RequiresPermissions(PermissionConstant.SYS_ROLE_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_ROLE_USER)
    public ResultData getBindUserById(@PathVariable("roleId") Long id) {
        ResultData resultData = new ResultData();
        List<Long> longs = service.getBindUserById(id);
        resultData.setData(longs);
        return resultData;
    }

    @RequiresPermissions(PermissionConstant.SYS_ROLE_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE_BIND_USER)
    public ResultData bindUser(@PathVariable("roleId") Long roleId,
                               @PathVariable("userId") Long userId) {
        ResultData resultData = new ResultData();
        service.bindUser(roleId, userId);
        return resultData;
    }

    @RequiresPermissions(PermissionConstant.SYS_ROLE_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE_UNBIND_USER)
    public ResultData unbindUser(@PathVariable("roleId") Long roleId,
                                 @PathVariable("userId") Long userId) {
        ResultData resultData = new ResultData();
        service.unbindUser(roleId, userId);
        return resultData;
    }
}
