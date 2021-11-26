/**
 * @ClassName RoleController
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
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
package org.tyuan.service.admin.web.controller;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tyuan.service.system.model.ResultData;
import org.tyuan.common.annotation.Log;
import org.tyuan.common.enums.LogType;
import org.tyuan.service.admin.web.PermissionConstant;
import org.tyuan.service.admin.web.RouteConstant;
import org.tyuan.service.system.cache.UserInfoCacheService;
import org.tyuan.service.system.model.ResultTable;
import org.tyuan.service.system.model.vo.sys.RoleUserTableParamsVo;
import org.tyuan.service.system.model.vo.sys.SysRoleUserVo;
import org.tyuan.service.system.service.SysRoleUserService;

import javax.annotation.Resource;

@RestController
public class SysRoleUserController {

    @Resource
    SysRoleUserService service;

    @Resource
    UserInfoCacheService userInfoCacheService;

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
    @Log(type = LogType.EDIT, value = "绑定用户")
    public ResultData bindUser(@RequestBody SysRoleUserVo vo) {
        ResultData resultData = new ResultData();
        service.bindUser(vo);
        for (Long l : vo.getUserIds()) {
            userInfoCacheService.leaveMessage(l, "你的权限发生改变，请重新登陆");
        }
        return resultData;
    }

    @RequiresPermissions(PermissionConstant.SYS_ROLE_USER_UN_BIND)
    @PostMapping(RouteConstant.ROUTER_SYS_ROLE_USER_UNBIND)
    @Log(type =LogType.EDIT, value = "解绑用户")
    public ResultData unbindUser(@RequestBody SysRoleUserVo vo) {
        ResultData resultData = new ResultData();
        service.unbindUser(vo);
        for (Long l : vo.getUserIds()) {
            userInfoCacheService.leaveMessage(l, "你的权限发生改变，请重新登陆");
        }
        return resultData;
    }
}
