package com.tyuan.manager.base.service;

import com.github.pagehelper.PageInfo;
import com.tyuan.model.base.pojo.SysLoginLog;
import com.tyuan.model.base.pojo.SysUser;
import com.tyuan.model.base.vo.sys.SysLoginLogTableParamsVo;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author jiangguiqi@aliyun.com
 * @version 1.0
 * @date 2021/3/23 1:50 下午
 */
public interface SysLoginLogService extends BaseService<SysLoginLog> {

    PageInfo getByParams(SysLoginLogTableParamsVo param);

    /**
     * 记录登陆日志
     * @param sysUser
     * @param request
     */
    void add(SysUser sysUser, HttpServletRequest request);
}
