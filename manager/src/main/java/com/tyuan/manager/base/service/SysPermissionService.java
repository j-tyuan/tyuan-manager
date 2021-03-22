package com.tyuan.manager.base.service;

import com.tyuan.model.base.pojo.SysPermission;
import com.tyuan.model.base.vo.sys.PermissionTreeVo;

import java.util.List;


public interface SysPermissionService extends BaseService<SysPermission> {

    /**
     * 根据ID获取数据
     *
     * @param Ids
     * @return
     */
    List<SysPermission> getByIds(List<Long> Ids);

    /**
     * 根据角色获取绑定的权限
     *
     * @param roleId
     * @return
     */
    List<SysPermission> getByRoleId(Long roleId);

    /**
     * 获取权限的树形结构
     *
     * @return
     */
    List<PermissionTreeVo> getByFormat();

    /**
     * @param id
     * @return
     */
    SysPermission getById(Long id);

    /**
     * 此列表中数据为全局数据，禁止对里面数据做任何操作。否则与导致意外情况
     * 只允许读取
     *
     * @return
     */
    List<SysPermission> getAll();
}
