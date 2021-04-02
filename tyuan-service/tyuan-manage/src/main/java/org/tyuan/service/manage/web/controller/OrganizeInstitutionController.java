/**
 * @ClassName RoleController
 * @Description 字典管理
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
package org.tyuan.service.manage.web.controller;

import org.tyuan.common.exception.ServiceException;
import org.tyuan.common.utils.TreeUtils;
import org.tyuan.service.manage.annotation.Log;
import org.tyuan.service.manage.aop.LogAspect;
import org.tyuan.service.manage.web.PermissionConstant;
import org.tyuan.service.manage.web.RouteConstant;
import org.tyuan.service.manage.service.OrganizationInstitutionService;
import org.tyuan.service.model.ResultData;
import org.tyuan.service.model.vo.DeleteVo;
import org.tyuan.service.model.vo.sys.OrganizeInstitutionVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrganizeInstitutionController {

    @Resource
    OrganizationInstitutionService sysInstitutionService;

    @RequiresPermissions(PermissionConstant.SYS_ORGANIZATION_INSTITUTION_DEL)
    @PostMapping(RouteConstant.ROUTER_ORGANIZATION_INSTITUTION_DEL)
    @Log(type = LogAspect.LogType.DEL, value = "删除机构")
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

    @RequiresPermissions(PermissionConstant.SYS_ORGANIZATION_INSTITUTION_ADD)
    @PostMapping(RouteConstant.ROUTER_ORGANIZATION_INSTITUTION_ADD)
    @Log(type = LogAspect.LogType.ADD, value = "添加机构")
    public ResultData add(@RequestBody @Validated OrganizeInstitutionVo k) throws ServiceException {
        sysInstitutionService.add(k);
        return new ResultData();
    }

    @RequiresPermissions(PermissionConstant.SYS_ORGANIZATION_INSTITUTION_EDIT)
    @PostMapping(RouteConstant.ROUTER_ORGANIZATION_INSTITUTION_EDIT)
    @Log(type = LogAspect.LogType.EDIT, value = "修改机构")
    public ResultData edit(@RequestBody OrganizeInstitutionVo k) throws ServiceException {
        sysInstitutionService.edit(k);
        return new ResultData();

    }

    @GetMapping(RouteConstant.ROUTER_ORGANIZATION_INSTITUTION)
    public ResultData getAll() throws ServiceException {
        ResultData resultData = new ResultData();
        List list = sysInstitutionService.getAll();
        List newList = TreeUtils.tree(list, 0L);
        resultData.setData(newList);
        return resultData;

    }
}
