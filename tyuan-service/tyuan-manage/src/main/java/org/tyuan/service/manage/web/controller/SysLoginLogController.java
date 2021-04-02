/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/3/14 5:03 下午
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
package org.tyuan.service.manage.web.controller;

import com.github.pagehelper.PageInfo;
import org.tyuan.service.manage.annotation.Log;
import org.tyuan.service.manage.aop.LogAspect;
import org.tyuan.service.manage.web.PermissionConstant;
import org.tyuan.service.manage.web.RouteConstant;
import org.tyuan.service.manage.service.SysLoginLogService;
import org.tyuan.service.model.ResultTable;
import org.tyuan.service.model.vo.sys.SysLoginLogTableParamsVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
