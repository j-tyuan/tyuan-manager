package com.tyuan.manager.web.controller;

import com.tyuan.manager.annotation.Log;
import com.tyuan.manager.aop.LogAspect;
import com.tyuan.manager.service.AccountService;
import com.tyuan.manager.service.ServiceException;
import com.tyuan.manager.web.RouteConstant;
import com.tyuan.model.ErrorCodeConsts;
import com.tyuan.model.ResultData;
import com.tyuan.model.pojo.SysUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Log(type = LogAspect.LogType.EDIT, value = "用户修改自定义布局")
    public ResultData customLayout(@RequestBody Map customLayoutVo) {
        accountService.customLayout(customLayoutVo);
        return new ResultData();
    }

    @PostMapping(RouteConstant.ROUTER_ACCOUNT_PHOTO)
    @Log(type = LogAspect.LogType.EDIT, value = "用户修改头像")
    public ResultData accountPhoto(@RequestParam("avatar") MultipartFile multipartFile) {
        ResultData result = new ResultData();
        try {
            long size = multipartFile.getSize();
            // 2m
            if (2 * 1024 * 1024 < size) {
                result.setErrorCode(ErrorCodeConsts.ERROR);
                result.setErrorMessage("超出限制");
                return result;
            }
            accountService.accountPhoto(multipartFile);
            return result;
        } catch (ServiceException e) {
            result.setErrorCode(e.getStatus());
            result.setErrorMessage(e.getMessage());
            return result;
        }
    }

    @PostMapping(RouteConstant.ROUTER_ACCOUNT_SETTING)
    @Log(type = LogAspect.LogType.EDIT, value = "用户修改设置")
    public ResultData setting(@RequestBody SysUser sysUser) {
        accountService.setting(sysUser);
        return new ResultData();
    }

    @GetMapping(RouteConstant.ROUTER_ACCOUNT)
    @Log(type = LogAspect.LogType.EDIT, value = "获取账号信息")
    public ResultData account() {
        ResultData resultData = new ResultData();
        Map data = accountService.account();
        resultData.setData(data);
        return resultData;
    }
}
