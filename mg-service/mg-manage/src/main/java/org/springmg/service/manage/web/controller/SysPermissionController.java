package org.springmg.service.manage.web.controller;

import com.google.common.collect.Lists;
import org.springmg.service.manage.web.PermissionConstant;
import org.springmg.service.manage.web.RouteConstant;
import org.springmg.service.manage.service.SysRoleService;
import org.springmg.service.manage.service.SysPermissionService;
import org.springmg.service.model.ResultData;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/7/1 15:59
 */
@RestController
public class SysPermissionController {

    @Resource
    SysPermissionService sysPowerService;

    @Resource
    SysRoleService sysRoleService;

    @GetMapping(RouteConstant.ROUTER_SYS_PERMISSION_BY_ROLE_ID)
    @RequiresPermissions(PermissionConstant.SYS_PERMISSION)
    public ResultData byRoleId(@RequestParam("id") Long id) {
        ResultData resultData = new ResultData();

        boolean b = sysRoleService.hasRoleById(id);
        if (!b) {

            resultData.setData(Lists.newArrayList());
            return resultData;
        }

        resultData.setData(sysPowerService.getByRoleId(id));
        return resultData;
    }

/*    @GetMapping(RouteConstant.ROUTER_SYS_PERMISSION)
    @RequiresPermissions(PermissionConstant.SYS_PERMISSION)
    public ResultData getByFormat() {
        ResultData resultData = new ResultData();
        resultData.setData(sysPowerService.getByFormat());
        return resultData;
    }*/

    @GetMapping(RouteConstant.ROUTER_SYS_PERMISSION)
    @RequiresPermissions(PermissionConstant.SYS_PERMISSION)
    public ResultData getAll() {
        ResultData resultData = new ResultData();
        resultData.setData(sysPowerService.getAll());
        return resultData;
    }
}
