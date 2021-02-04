/**
 * @ClassName RoleService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
 */
package com.tyuan.manager.service;

import com.github.pagehelper.PageInfo;
import com.tyuan.model.pojo.SysRole;
import com.tyuan.model.vo.sys.SysRoleTableParamsVo;
import com.tyuan.model.vo.sys.SysRoleVo;

import java.util.List;

public interface SysRoleService extends BaseService<SysRoleVo> {

    PageInfo<SysRole> getByParams(SysRoleTableParamsVo param);

    /**
     * 根据用户ID 获取全部的角色信息
     *
     * @param uid
     * @return
     */
    List<SysRole> getRoleByUserId(Long uid);


    /**
     * 过滤角色
     *
     * @param roles
     */
    void filterRoles(List<SysRole> roles);

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
     *
     * @return
     */
    List<SysRole> getAll();

    /**
     * 根据角色ID获取
     * @param id
     * @return
     */
    List<Long> getBindUserById(Long id);

    /**
     * 绑定用户
     * @param roleId
     * @param userId
     * @return
     */
    void bindUser(Long roleId, Long userId);

    /**
     * 解绑用户
     * @param roleId
     * @param userId
     */
    void unbindUser(Long roleId, Long userId);
}
