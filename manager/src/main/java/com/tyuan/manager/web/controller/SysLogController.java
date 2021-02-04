/**
 * @ClassName RoleController
 * @Description 字典管理
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
 */
package com.tyuan.manager.web.controller;

import com.github.pagehelper.PageInfo;
import com.tyuan.manager.service.SysLogService;
import com.tyuan.manager.web.PermissionConstant;
import com.tyuan.manager.web.RouteConstant;
import com.tyuan.model.ResultTable;
import com.tyuan.model.pojo.SysDict;
import com.tyuan.model.vo.sys.SysLogTableVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SysLogController {

    @Resource
    SysLogService service;

    @RequiresPermissions(PermissionConstant.SYS_LOG_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_LOG)
    public ResultTable list(@RequestBody SysLogTableVo requestParam) {
        ResultTable table = new ResultTable();
        try {
            PageInfo<SysDict> pageInfo = service.getByParams(requestParam);
            table.setPageInfo(pageInfo);
            return table;
        } catch (Exception e) {
            return table;
        }
    }
}
