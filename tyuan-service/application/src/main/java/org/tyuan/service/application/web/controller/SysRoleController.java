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
import com.google.common.collect.Lists;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.application.service.manage.SysRoleService;
import org.tyuan.service.common.annotation.AuditLog;
import org.tyuan.service.data.ErrorCodeConsts;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.ResultTable;
import org.tyuan.service.data.audit.ActionType;
import org.tyuan.service.data.model.SysRole;
import org.tyuan.service.data.vo.DeleteVo;
import org.tyuan.service.data.vo.sys.SysRoleTableParamsVo;
import org.tyuan.service.data.vo.sys.SysRoleVo;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sys/role")
public class SysRoleController {

    @Resource
    SysRoleService service;

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:role:list')")
    @PostMapping({"", "/"})
    @AuditLog(type = ActionType.QUERY, value = "查看角色列表")
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


    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:role:list')")
    @GetMapping({"", "/"})
    @AuditLog(type = ActionType.QUERY, value = "查看角色")
    public ResultData get(@RequestParam(value = "id", required = false) String id) {
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


    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:role:del')")
    @PostMapping("/del")
    @AuditLog(type = ActionType.DELETED, value = "删除角色")
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

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:role:add')")
    @PostMapping("/add")
    @AuditLog(type = ActionType.ADDED, value = "添加角色")
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

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:role:edit')")
    @PostMapping("/edit")
    @AuditLog(type = ActionType.UPDATED, value = "修改角色")
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

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:role:list')")
    @GetMapping("/auth/{id}")
    public ResultData getAuthByRoleId(@PathVariable("id") String id) {
        ResultData resultData = new ResultData();
        List<String> longs = service.getAuthIdByRoleId(id);
        resultData.setData(longs);
        return resultData;
    }
}
