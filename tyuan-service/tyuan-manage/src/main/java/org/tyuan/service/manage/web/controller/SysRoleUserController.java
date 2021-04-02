/**
 * @ClassName RoleController
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
 */
package org.tyuan.service.manage.web.controller;

import com.github.pagehelper.PageInfo;
import org.tyuan.service.manage.annotation.Log;
import org.tyuan.service.manage.aop.LogAspect;
import org.tyuan.service.manage.service.SysRoleUserService;
import org.tyuan.service.manage.web.PermissionConstant;
import org.tyuan.service.manage.web.RouteConstant;
import org.tyuan.service.model.ResultData;
import org.tyuan.service.model.ResultTable;
import org.tyuan.service.model.vo.sys.RoleUserTableParamsVo;
import org.tyuan.service.model.vo.sys.SysRoleUserVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class SysRoleUserController {

    @Resource
    SysRoleUserService service;


    @RequiresPermissions(PermissionConstant.SYS_ROLE_USER_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE_USER)
    public ResultTable getUser(@RequestBody RoleUserTableParamsVo paramsVo) {
        ResultTable resultTable = new ResultTable();
        try {
            PageInfo pageInfo = service.getUser(paramsVo);
            resultTable.setPageInfo(pageInfo);
            return resultTable;
        } catch (Exception e) {
            return resultTable;
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_ROLE_USER_BIND)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE_USER_BIND)
    @Log(type = LogAspect.LogType.EDIT, value = "绑定用户")
    public ResultData bindUser(@RequestBody SysRoleUserVo vo) {
        ResultData resultData = new ResultData();
        service.bindUser(vo);
        return resultData;
    }

    @RequiresPermissions(PermissionConstant.SYS_ROLE_USER_UN_BIND)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE_USER_UNBIND)
    @Log(type = LogAspect.LogType.EDIT, value = "解绑用户")
    public ResultData unbindUser(@RequestBody SysRoleUserVo vo) {
        ResultData resultData = new ResultData();
        service.unbindUser(vo);
        return resultData;
    }
}
