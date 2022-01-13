/**
 * @ClassName RoleController
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
 * <p>
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
 * <p>
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
package org.tyuan.service.application.web.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyuan.service.application.cache.UserInfoCacheService;
import org.tyuan.service.application.service.manage.SysRoleUserService;
import org.tyuan.service.common.annotation.AuditLog;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.ResultTable;
import org.tyuan.service.data.audit.ActionType;
import org.tyuan.service.data.vo.sys.RoleUserTableParamsVo;
import org.tyuan.service.data.vo.sys.SysRoleUserVo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/sys/role")
public class SysRoleUserController {

    @Resource
    SysRoleUserService service;

    @Resource
    UserInfoCacheService userInfoCacheService;

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:role:user:list')")
    @PostMapping({"/user"})
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

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:role:user:bind')")
    @PostMapping("/user/bind")
    @AuditLog(type = ActionType.UPDATED, value = "绑定用户")
    public ResultData bindUser(@RequestBody SysRoleUserVo vo) {
        ResultData resultData = new ResultData();
        service.bindUser(vo);
        for (String l : vo.getUserIds()) {
            userInfoCacheService.leaveMessage(l, "你的权限发生改变，请重新登陆");
        }
        return resultData;
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:role:user:unbind')")
    @PostMapping("/user/unbind")
    @AuditLog(type = ActionType.UPDATED, value = "解绑用户")
    public ResultData unbindUser(@RequestBody SysRoleUserVo vo) {
        ResultData resultData = new ResultData();
        service.unbindUser(vo);
        for (String l : vo.getUserIds()) {
            userInfoCacheService.leaveMessage(l, "你的权限发生改变，请重新登陆");
        }
        return resultData;
    }
}
