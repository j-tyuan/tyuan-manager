/**
 * @ClassName RoleService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
 */
package org.springmg.service.manage.service;

import com.github.pagehelper.PageInfo;
import org.springmg.service.model.vo.sys.RoleUserTableParamsVo;
import org.springmg.service.model.vo.sys.SysRoleUserVo;

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
