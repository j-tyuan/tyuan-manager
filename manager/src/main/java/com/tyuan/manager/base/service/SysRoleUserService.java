/**
 * @ClassName RoleService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
 */
package com.tyuan.manager.base.service;

import com.github.pagehelper.PageInfo;
import com.tyuan.model.base.pojo.SysRole;
import com.tyuan.model.base.vo.sys.RoleUserTableParamsVo;
import com.tyuan.model.base.vo.sys.SysRoleTableParamsVo;
import com.tyuan.model.base.vo.sys.SysRoleUserVo;
import com.tyuan.model.base.vo.sys.SysRoleVo;

import java.util.List;

public interface SysRoleUserService {


    /**
     * 获取用户
     *
     * @return
     */
    PageInfo getUser(RoleUserTableParamsVo paramsVo);

    /**
     * 绑定用户
     *
     * @return
     */
    void bindUser(SysRoleUserVo sysRoleUserVo);

    /**
     * 解绑用户
     */
    void unbindUser(SysRoleUserVo sysRoleUserVo);
}
