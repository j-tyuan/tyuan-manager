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

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.application.service.AccountService;
import org.tyuan.service.common.annotation.AuditLog;
import org.tyuan.service.data.ErrorCodeConsts;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.audit.ActionType;
import org.tyuan.service.data.model.SysUser;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/6/29 17:28
 */
@RestController
@RequestMapping("/api/account/")
public class AccountController {

    @Resource
    AccountService accountService;

    @PostMapping("/custom/layout")
    @AuditLog(type = ActionType.UPDATED, value = "用户修改自定义布局")
    public ResultData customLayout(@RequestBody Map customLayoutVo) {
        accountService.customLayout(customLayoutVo);
        return new ResultData();
    }

    @PostMapping("/avatar")
    @AuditLog(type = ActionType.UPDATED, value = "用户修改头像")
    public ResultData accountAvatar(@RequestParam("avatar") MultipartFile multipartFile) {
        ResultData result = new ResultData();
        try {
            long size = multipartFile.getSize();
            // 2m
            if (2 * 1024 * 1024 < size) {
                result.setErrorCode(ErrorCodeConsts.ERROR);
                result.setErrorMessage("超出限制");
                return result;
            }
            accountService.accountAvatar(multipartFile);
            return result;
        } catch (ServiceException e) {
            result.setErrorCode(e.getStatus());
            result.setErrorMessage(e.getMessage());
            return result;
        }
    }

    @PostMapping("/setting")
    @AuditLog(type = ActionType.UPDATED, value = "用户修改设置")
    public ResultData setting(@RequestBody SysUser sysUser) {
        accountService.setting(sysUser);
        return new ResultData();
    }

    @GetMapping({"", "/"})
    @AuditLog(type = ActionType.UPDATED, value = "获取账号信息")
    public ResultData account() {
        ResultData resultData = new ResultData();
        Map data = accountService.account();
        resultData.setData(data);
        return resultData;
    }
}
