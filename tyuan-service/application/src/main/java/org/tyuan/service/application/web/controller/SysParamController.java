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
import org.springframework.web.bind.annotation.*;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.application.service.SysParamService;
import org.tyuan.service.application.web.RouteConstant;
import org.tyuan.service.common.annotation.AuditLog;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.ResultTable;
import org.tyuan.service.data.audit.ActionType;
import org.tyuan.service.data.model.SysParam;
import org.tyuan.service.data.vo.DeleteVo;
import org.tyuan.service.data.vo.sys.SysParamTableVo;
import org.tyuan.service.data.vo.sys.SysParamVo;

import javax.annotation.Resource;

@RestController
public class SysParamController {


    @Resource
    SysParamService paramService;

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:param:list')")
    @PostMapping(RouteConstant.ROUTER_SYS_PARAM)
    @AuditLog(type = ActionType.QUERY, value = "查看系统参数")
    public ResultTable list(@RequestBody SysParamTableVo param) {

        ResultTable resultData = new ResultTable();
        try {
            PageInfo pageInfo = paramService.getByParams(param);
            resultData.setPageInfo(pageInfo);
            return resultData;
        } catch (Exception e) {
            return resultData;
        }
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:param:del')")
    @PostMapping(RouteConstant.ROUTER_SYS_PARAM_DEL)
    @AuditLog(type = ActionType.DELETED, value = "删除系统参数")
    public ResultData del(@RequestBody DeleteVo deleteVo) {
        try {
            paramService.del(deleteVo);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:param:add')")
    @PostMapping(RouteConstant.ROUTER_SYS_PARAM_ADD)
    @AuditLog(type = ActionType.ADDED, value = "添加系统参数")
    public ResultData add(@RequestBody SysParamVo k) throws ServiceException {

        paramService.add(k);
        return new ResultData();
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:param:edit','SYS_ADMIN')")
    @PostMapping(RouteConstant.ROUTER_SYS_PARAM_EDIT)
    @AuditLog(type = ActionType.UPDATED, value = "修改系统参数")
    public ResultData edit(@RequestBody SysParamVo k) throws ServiceException {
        paramService.edit(k);
        return new ResultData();

    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:param:edit')")
    @GetMapping(RouteConstant.ROUTER_SYS_PARAM_GET_BY_KEY)
    public ResultData getByKey(@PathVariable("key") String key) throws ServiceException {
        SysParam record = paramService.getByKey(key);
        return new ResultData().setData(record);

    }
}
