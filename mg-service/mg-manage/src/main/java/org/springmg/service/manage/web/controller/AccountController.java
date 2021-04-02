package org.springmg.service.manage.web.controller;

import org.springmg.common.exception.ServiceException;
import org.springmg.service.manage.annotation.Log;
import org.springmg.service.manage.aop.LogAspect;
import org.springmg.service.manage.web.RouteConstant;
import org.springmg.service.manage.service.AccountService;
import org.springmg.service.model.ErrorCodeConsts;
import org.springmg.service.model.ResultData;
import org.springmg.service.model.pojo.SysUser;
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

    @PostMapping(RouteConstant.ROUTER_ACCOUNT_AVATAR)
    @Log(type = LogAspect.LogType.EDIT, value = "用户修改头像")
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
