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
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.application.cache.LocalCache;
import org.tyuan.service.application.cache.UserInfoCacheService;
import org.tyuan.service.application.service.SysRoleUserService;
import org.tyuan.service.application.service.SysUserAvatarService;
import org.tyuan.service.application.service.SysUserService;
import org.tyuan.service.common.annotation.AuditLog;
import org.tyuan.service.data.ErrorCodeConsts;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.ResultTable;
import org.tyuan.service.data.SysParamConsts;
import org.tyuan.service.data.audit.ActionType;
import org.tyuan.service.data.cache.CacheConstant;
import org.tyuan.service.data.model.custom.COrganizationInstitution;
import org.tyuan.service.data.vo.DeleteVo;
import org.tyuan.service.data.vo.sys.SysUserTableParamsVo;
import org.tyuan.service.data.vo.sys.SysUserVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/6/29 17:28
 */
@RestController
@RequestMapping("/api/sys/user")
public class SysUserController {


    @Resource
    SysUserService sysUserService;

    @Resource
    SysUserAvatarService sysUserAvatarService;

    @Resource
    private UserInfoCacheService userInfoCacheService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SysRoleUserService sysRoleUserService;


    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:user:list')")
    @PostMapping({"", "/"})
    @AuditLog(type = ActionType.QUERY, value = "查看用户列表")
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

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:user:list')")
    @GetMapping({"", "/"})
    public ResultData get(@RequestParam("id") Long id) {

        return new ResultData().setData(sysUserService.getById(id));
    }


    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:user:del')")
    @PostMapping("/del")
    @AuditLog(type = ActionType.DELETED, value = "删除用户")
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

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:user:add')")
    @PostMapping("/add")
    @AuditLog(type = ActionType.ADDED, value = "添加用户")
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

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:user:edit')")
    @PostMapping("edit")
    @AuditLog(type = ActionType.UPDATED, value = "修改用户")
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

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:user:disable')")
    @PostMapping("/disable/{uid}/{disable}")
    @AuditLog(type = ActionType.UPDATED, value = "修改用户状态")
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

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:user:list')")
    @GetMapping("/fetch/{value}")
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


    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:user:edit')")
    @PostMapping("/avatar")
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

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','sys:user:list')")
    @GetMapping("/{userId}/role")
    public ResultData getRoleIdsByUserId(@PathVariable(value = "userId") Long id) {
        ResultData resultData = new ResultData();
        List roles = sysRoleUserService.getRoleIdsByUserId(id);
        resultData.setData(roles);
        return resultData;
    }

    @GetMapping("/watermark")
    public ResultData watermark() {
        HashOperations operations = redisTemplate.opsForHash();
        Object o = operations.get(CacheConstant.SYS_PARAM_MAP, SysParamConsts.SYS_WATER_MARK);
        return new ResultData().setData(o);
    }

}
