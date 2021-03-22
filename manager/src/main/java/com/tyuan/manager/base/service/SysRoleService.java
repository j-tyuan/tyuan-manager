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
import com.tyuan.model.base.vo.sys.SysRoleVo;

import java.util.List;

public interface SysRoleService extends BaseService<SysRoleVo> {

    PageInfo<SysRole> getByParams(SysRoleTableParamsVo param);

    /**
     * 是否包涵角色
     *
     * @return
     */
    boolean hasRoleById(Long id);

    /**
     * @param roleId
     * @return
     */
    List<Long> getAuthIdByRoleId(Long roleId);

    /**
     * @param id
     * @return
     */
    SysRole getById(Long id);

    /**
     * @return
     */
    List<SysRole> getAll();


    /**
     * 获取用户
     *
     * @return
     */
    PageInfo getUser(RoleUserTableParamsVo paramsVo);

    /**
     * 绑定用户
     *
     * @param roleId
     * @param userId
     * @return
     */
    void bindUser(Long roleId, Long userId);

    /**
     * 解绑用户
     *
     * @param roleId
     * @param userId
     */
    void unbindUser(Long roleId, Long userId);
}
