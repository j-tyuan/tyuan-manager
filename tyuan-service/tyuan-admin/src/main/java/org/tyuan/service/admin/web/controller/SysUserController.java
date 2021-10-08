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
import org.springframework.web.multipart.MultipartFile;
import org.tyuan.service.system.model.ResultData;
import org.tyuan.common.annotation.Log;
import org.tyuan.common.enums.LogType;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.admin.web.PermissionConstant;
import org.tyuan.service.admin.web.RouteConstant;
import org.tyuan.service.framework.cache.LocalCache;
import org.tyuan.service.framework.cache.UserInfoCacheService;
import org.tyuan.service.system.model.ErrorCodeConsts;
import org.tyuan.service.system.model.ResultTable;
import org.tyuan.service.system.model.pojo.custom.COrganizationInstitution;
import org.tyuan.service.system.model.vo.DeleteVo;
import org.tyuan.service.system.model.vo.sys.SysUserTableParamsVo;
import org.tyuan.service.system.model.vo.sys.SysUserVo;
import org.tyuan.service.system.service.SysUserAvatarService;
import org.tyuan.service.system.service.SysUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/6/29 17:28
 */
@RestController
public class SysUserController {


    @Resource
    SysUserService sysUserService;

    @Resource
    SysUserAvatarService sysUserAvatarService;

    @Resource
    private UserInfoCacheService userInfoCacheService;


    @RequiresPermissions(PermissionConstant.SYS_USER_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_USER)
    @Log(type = LogType.SELECT, value = "查看用户列表")
    public ResultTable list(@RequestBody SysUserTableParamsVo requestParam) {
        ResultTable resultTable = new ResultTable();
        try {
            List<COrganizationInstitution> list = LocalCache.SYS_INSTITUTION.getData();
            PageInfo pageInfo = sysUserService.getByParams(requestParam, list);
            resultTable.setPageInfo(pageInfo);
            return resultTable;
        } catch (Exception e) {
            return resultTable;
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_USER)
    public ResultData get(@RequestParam("id") Long id) {

        return new ResultData().setData(sysUserService.getById(id));
    }


    @RequiresPermissions(PermissionConstant.SYS_USER_DEL)
    @PostMapping(RouteConstant.ROUTER_SYS_USER_DEL)
    @Log(type = LogType.DEL, value = "删除用户")
    public ResultData del(@RequestBody DeleteVo deleteVo) {
        try {
            sysUserService.del(deleteVo);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_ADD)
    @PostMapping(RouteConstant.ROUTER_SYS_USER_ADD)
    @Log(type = LogType.ADD, value = "添加用户")
    public ResultData add(@RequestBody @Validated SysUserVo k) {
        try {
            sysUserService.add(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_USER_EDIT)
    @Log(type = LogType.EDIT, value = "修改用户")
    public ResultData edit(@RequestBody @Validated SysUserVo k) {
        try {
            sysUserService.edit(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_DISABLE)
    @PostMapping(RouteConstant.ROUTER_SYS_USER_DISABLE)
    @Log(type = LogType.EDIT, value = "修改用户状态")
    public ResultData disable(@PathVariable(value = "uid") Long userId,
                              @PathVariable(value = "disable", required = false) Integer disable) {
        try {
            sysUserService.disable(userId, disable);
            userInfoCacheService.leaveMessage(userId, "你已经被禁止登陆");
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(ErrorCodeConsts.ERROR)
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_USER_FETCH)
    public ResultData fetch(@PathVariable(value = "value") String value) {
        try {
            ResultData resultData = new ResultData();
            List list = sysUserService.fetch(value);
            resultData.setData(list);
            return resultData;
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(ErrorCodeConsts.ERROR)
                    .setErrorMessage(e.getMessage());
        }
    }


    @RequiresPermissions(PermissionConstant.SYS_USER_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_USER_AVATAR)
    public ResultData updateAvatar(@RequestParam("avatar") MultipartFile multipartFile) {
        ResultData result = new ResultData();
        try {
            Long id = sysUserAvatarService.updateAvatar(multipartFile);
            result.setData(id);
            return result;
        } catch (ServiceException e) {
            result.setErrorCode(e.getStatus());
            result.setErrorMessage(e.getMessage());
            return result;
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_USER_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_USER_ROLE_GET_BY_ID)
    public ResultData getRoleIdsByUserId(@PathVariable(value = "userId") Long id) {
        ResultData resultData = new ResultData();
        List roles = sysUserService.getRoleIdsByUserId(id);
        resultData.setData(roles);
        return resultData;
    }
}
