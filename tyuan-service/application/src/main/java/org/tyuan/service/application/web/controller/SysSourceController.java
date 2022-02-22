/**
 * @ClassName MavMenuController
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:53
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
import org.tyuan.common.ITree;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.common.utils.TreeUtils;
import org.tyuan.service.application.service.manage.SysSourceService;
import org.tyuan.service.common.annotation.AuditLog;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.audit.ActionType;
import org.tyuan.service.data.model.custom.CSysSource;
import org.tyuan.service.data.vo.DeleteVo;
import org.tyuan.service.data.vo.sys.SysUrlVo;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/sys/source")
public class SysSourceController {

    @Resource
    SysSourceService sysSourceService;

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:source:list')")
    @GetMapping({"", "/"})
    public ResultData getAll() {
        ResultData resultData = new ResultData();
        List<CSysSource> list = sysSourceService.getAll();
        List<ITree> newList = TreeUtils.tree(list, "0");
        // 排序
        Collections.sort(newList, Comparator.comparingLong(o -> o.getSort()));
        resultData.setData(newList);
        return resultData;
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:source:del')")
    @PostMapping("/del")
    @AuditLog(type = ActionType.DELETED, value = "删除资源")
    public ResultData del(@RequestBody DeleteVo deleteVo) {
        try {
            sysSourceService.del(deleteVo);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:source:add')")
    @PostMapping("/add")
    @AuditLog(type = ActionType.ADDED, value = "添加资源")
    public ResultData add(@RequestBody @Validated SysUrlVo k) {
        try {
            sysSourceService.add(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:source:edit')")
    @PostMapping("/edit")
    @AuditLog(type = ActionType.UPDATED, value = "修改资源")
    public ResultData edit(@RequestBody @Validated SysUrlVo k) {
        try {
            sysSourceService.edit(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

}
