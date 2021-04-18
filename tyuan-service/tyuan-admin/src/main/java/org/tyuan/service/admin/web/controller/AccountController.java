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

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tyuan.common.ResultData;
import org.tyuan.common.annotation.Log;
import org.tyuan.common.enums.LogType;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.admin.web.RouteConstant;
import org.tyuan.service.system.model.ErrorCodeConsts;
import org.tyuan.service.system.model.pojo.SysUser;
import org.tyuan.service.system.service.AccountService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/6/29 17:28
 */
@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    @PostMapping(RouteConstant.ROUTER_ACCOUNT_CUSTOM_LAYOUT)
    @Log(type = LogType.EDIT, value = "用户修改自定义布局")
    public ResultData customLayout(@RequestBody Map customLayoutVo) {
        accountService.customLayout(customLayoutVo);
        return new ResultData();
    }

    @PostMapping(RouteConstant.ROUTER_ACCOUNT_AVATAR)
    @Log(type =LogType.EDIT, value = "用户修改头像")
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

    @PostMapping(RouteConstant.ROUTER_ACCOUNT_SETTING)
    @Log(type =LogType.EDIT, value = "用户修改设置")
    public ResultData setting(@RequestBody SysUser sysUser) {
        accountService.setting(sysUser);
        return new ResultData();
    }

    @GetMapping(RouteConstant.ROUTER_ACCOUNT)
    @Log(type =LogType.EDIT, value = "获取账号信息")
    public ResultData account() {
        ResultData resultData = new ResultData();
        Map data = accountService.account();
        resultData.setData(data);
        return resultData;
    }
}
