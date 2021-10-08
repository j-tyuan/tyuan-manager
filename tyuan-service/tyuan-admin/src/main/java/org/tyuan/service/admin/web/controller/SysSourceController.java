/**
 * @ClassName MavMenuController
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:53
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

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tyuan.common.ITree;
import org.tyuan.service.system.model.ResultData;
import org.tyuan.common.annotation.Log;
import org.tyuan.common.enums.LogType;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.common.utils.TreeUtils;
import org.tyuan.service.admin.web.PermissionConstant;
import org.tyuan.service.admin.web.RouteConstant;
import org.tyuan.service.system.model.pojo.custom.CSysSource;
import org.tyuan.service.system.model.vo.DeleteVo;
import org.tyuan.service.system.model.vo.sys.SysUrlVo;
import org.tyuan.service.system.service.SysSourceService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class SysSourceController {

    @Resource
    SysSourceService sysSourceService;

    @RequiresPermissions(PermissionConstant.SYS_SOURCE_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_SOURCE)
    public ResultData getAll() {
        ResultData resultData = new ResultData();
        List<CSysSource> list = sysSourceService.getAll();
        List<ITree> newList = TreeUtils.tree(list, 0L);
        // 排序
        Collections.sort(newList, Comparator.comparingLong(o -> o.getSort()));
        resultData.setData(newList);
        return resultData;
    }

    @RequiresPermissions(PermissionConstant.SYS_SOURCE_DEL)
    @PostMapping(RouteConstant.ROUTER_SYS_SOURCE_DEL)
    @Log(type = LogType.DEL, value = "删除资源")
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

    @RequiresPermissions(PermissionConstant.SYS_SOURCE_ADD)
    @PostMapping(RouteConstant.ROUTER_SYS_SOURCE_ADD)
    @Log(type =LogType.ADD, value = "添加资源")
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

    @RequiresPermissions(PermissionConstant.SYS_SOURCE_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_SOURCE_EDIT)
    @Log(type =LogType.EDIT, value = "修改资源")
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
