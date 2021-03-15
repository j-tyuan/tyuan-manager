/**
 * @ClassName RoleController
 * @Description 字典管理
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
 */
package com.tyuan.manager.base.web.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.tyuan.manager.base.aop.LogAspect;
import com.tyuan.manager.base.service.SysLogService;
import com.tyuan.manager.base.web.PermissionConstant;
import com.tyuan.manager.base.web.RouteConstant;
import com.tyuan.model.base.ResultData;
import com.tyuan.model.base.ResultTable;
import com.tyuan.model.base.pojo.SysDict;
import com.tyuan.model.base.vo.sys.SysLogTableVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

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

    @RequiresPermissions(PermissionConstant.SYS_LOG_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_LOG_TYPE)
    public ResultData getLogType() {
        ResultData resultData = new ResultData();
        LogAspect.LogType[] vals = LogAspect.LogType.values();
        Map tMap = Maps.newHashMap();
        for (LogAspect.LogType item : vals) {
            tMap.put(item.getType(), item.getName());
        }
        resultData.setData(tMap);
        return resultData;
    }
}
