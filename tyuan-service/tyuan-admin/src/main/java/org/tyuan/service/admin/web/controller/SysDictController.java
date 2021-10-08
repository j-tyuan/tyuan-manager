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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tyuan.service.system.model.ResultData;
import org.tyuan.common.annotation.Log;
import org.tyuan.common.enums.LogType;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.admin.web.PermissionConstant;
import org.tyuan.service.admin.web.RouteConstant;
import org.tyuan.service.system.model.DictTypeEnum;
import org.tyuan.service.system.model.ResultTable;
import org.tyuan.service.system.model.pojo.SysDict;
import org.tyuan.service.system.model.vo.DeleteVo;
import org.tyuan.service.system.model.vo.sys.SysDictTableParamsVo;
import org.tyuan.service.system.model.vo.sys.SysDictVo;
import org.tyuan.service.system.service.SysDictService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class SysDictController {


    @Resource
    SysDictService service;

    @RequiresPermissions(PermissionConstant.SYS_DICT_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_DICT)
    @Log(type = LogType.SELECT, value = "查看字典")
    public ResultTable list(@RequestBody SysDictTableParamsVo requestParam) {
        ResultTable table = new ResultTable();
        try {
            PageInfo<SysDict> pageInfo = service.getByParams(requestParam);
            table.setPageInfo(pageInfo);
            return table;
        } catch (Exception e) {
            return table;
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_DICT_DEL)
    @PostMapping(RouteConstant.ROUTER_SYS_DICT_DEL)
    @Log(type = LogType.DEL, value = "删除字典")
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

    @RequiresPermissions(PermissionConstant.SYS_DICT_ADD)
    @PostMapping(RouteConstant.ROUTER_SYS_DICT_ADD)
    @Log(type = LogType.ADD, value = "添加字典")
    public ResultData add(@RequestBody @Validated SysDictVo k) throws ServiceException {

        service.add(k);
        return new ResultData();
    }

    @RequiresPermissions(PermissionConstant.SYS_DICT_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_DICT_EDIT)
    @Log(type = LogType.EDIT, value = "修改字典")
    public ResultData edit(@RequestBody @Validated SysDictVo k) {
        try {
            service.edit(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_DICT_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_DICT_GET_TYPES)
    public ResultData getTypes() {

        ResultData resultData = new ResultData();
        resultData.setData(DictTypeEnum.getToList());

        return resultData;
    }

    @RequiresPermissions(PermissionConstant.SYS_DICT_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_DICT_GET_BY_TYPE)
    public ResultData getByType(@PathVariable(value = "type") String type) {
        Map<String, List<SysDict>> maps = service.getByType(type);
        ResultData resultData = new ResultData();
        resultData.setData(maps);
        return resultData;
    }

}
