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

import com.google.common.collect.Lists;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tyuan.service.application.service.SysPermissionService;
import org.tyuan.service.application.service.SysRoleService;
import org.tyuan.service.data.ResultData;

import javax.annotation.Resource;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/7/1 15:59
 */
@RestController
@RequestMapping("/api/sys/permission")
public class SysPermissionController {

    @Resource
    SysPermissionService sysPowerService;

    @Resource
    SysRoleService sysRoleService;

    @GetMapping({"", "/"})
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:permission')")
    public ResultData getAll() {
        ResultData resultData = new ResultData();
        resultData.setData(sysPowerService.getAll());
        return resultData;
    }

    @GetMapping("/getByRoleId")
    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:permission')")
    public ResultData byRoleId(@RequestParam("id") Long id) {
        ResultData resultData = new ResultData();

        boolean b = sysRoleService.hasRoleById(id);
        if (!b) {

            resultData.setData(Lists.newArrayList());
            return resultData;
        }

        resultData.setData(sysPowerService.getByRoleId(id));
        return resultData;
    }

}
