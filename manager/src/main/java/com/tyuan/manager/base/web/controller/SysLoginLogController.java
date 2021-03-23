/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/3/14 5:03 下午
 */
package com.tyuan.manager.base.web.controller;

import com.github.pagehelper.PageInfo;
import com.tyuan.manager.base.annotation.Log;
import com.tyuan.manager.base.aop.LogAspect;
import com.tyuan.manager.base.service.SysLoginLogService;
import com.tyuan.manager.base.web.PermissionConstant;
import com.tyuan.manager.base.web.RouteConstant;
import com.tyuan.model.base.ResultTable;
import com.tyuan.model.base.vo.sys.SysLoginLogTableParamsVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SysLoginLogController {

    @Resource
    SysLoginLogService loginLogService;

    @RequiresPermissions(PermissionConstant.SYS_LOG_LOGIN_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_LOG_LOGIN_LIST)
    @Log(type = LogAspect.LogType.SELECT, value = "查看登陆日志")
    public ResultTable list(@RequestBody SysLoginLogTableParamsVo param) {
        ResultTable resultData = new ResultTable();
        try {
            PageInfo pageInfo = loginLogService.getByParams(param);
            resultData.setPageInfo(pageInfo);
            return resultData;
        } catch (Exception e) {
            return resultData;
        }
    }

}
