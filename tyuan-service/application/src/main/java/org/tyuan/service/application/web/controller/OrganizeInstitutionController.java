/**
 * @ClassName RoleController
 * @Description 字典管理
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

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.common.utils.TreeUtils;
import org.tyuan.service.application.service.manage.OrganizationInstitutionService;
import org.tyuan.service.common.annotation.AuditLog;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.audit.ActionType;
import org.tyuan.service.data.vo.DeleteVo;
import org.tyuan.service.data.vo.sys.OrganizeInstitutionVo;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/org/inst")
public class OrganizeInstitutionController {

    @Resource
    OrganizationInstitutionService sysInstitutionService;


    @GetMapping({"", "/"})
    public ResultData getAll() throws ServiceException {
        ResultData resultData = new ResultData();
        List list = sysInstitutionService.getAll();
        List newList = TreeUtils.tree(list, "-");
        resultData.setData(newList);
        return resultData;

    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:organization:institution:del')")
    @PostMapping("/del")
    @AuditLog(type = ActionType.DELETED, value = "删除机构")
    public ResultData del(@RequestBody DeleteVo deleteVo) {
        try {
            sysInstitutionService.del(deleteVo);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:organization:institution:add')")
    @PostMapping("/add")
    @AuditLog(type = ActionType.ADDED, value = "添加机构")
    public ResultData add(@RequestBody @Validated OrganizeInstitutionVo k) throws ServiceException {
        sysInstitutionService.add(k);
        return new ResultData();
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:organization:institution:edit')")
    @PostMapping("/edit")
    @AuditLog(type = ActionType.UPDATED, value = "修改机构")
    public ResultData edit(@RequestBody OrganizeInstitutionVo k) throws ServiceException {
        sysInstitutionService.edit(k);
        return new ResultData();

    }

}
