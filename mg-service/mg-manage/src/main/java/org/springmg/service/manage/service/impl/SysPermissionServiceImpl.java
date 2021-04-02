package org.springmg.service.manage.service.impl;

import com.google.common.collect.Lists;
import org.springmg.service.dao.customize.CSysRolePermissionMapper;
import org.springmg.service.dao.mapper.SysPermissionMapper;
import org.springmg.service.manage.cache.LocalCache;
import org.springmg.service.manage.service.SysPermissionService;
import org.springmg.service.model.pojo.SysPermission;
import org.springmg.service.model.pojo.SysPermissionExample;
import org.springmg.service.model.pojo.SysRolePermission;
import org.springmg.service.model.pojo.SysRolePermissionExample;
import org.springmg.service.model.vo.sys.PermissionTreeVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Resource
    private CSysRolePermissionMapper cSysRolePermissionMapper;

    @Override
    public SysPermission getById(Long id) {
        return sysPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysPermission> getAll() {
        return LocalCache.SYS_PERMISSION.getData();
    }

    @Override
    public List<SysPermission> getByIds(List<Long> ids) {

        if (CollectionUtils.isEmpty(ids)) {

            return Lists.newArrayList();
        }
        SysPermissionExample example = new SysPermissionExample();
        example.createCriteria().andIdIn(ids);

        return sysPermissionMapper.selectByExample(example);
    }

    @Override
    public List<SysPermission> getByRoleId(Long roleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<SysRolePermission> sysRolePermissions = cSysRolePermissionMapper.selectByExample(example);
        List<Long> permissionIds = sysRolePermissions.stream().map(e -> e.getPermissionId()).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(permissionIds)) {

            return Lists.newArrayList();
        }
        SysPermissionExample sysPermissionExample = new SysPermissionExample();
        sysPermissionExample.createCriteria().andIdIn(permissionIds);
        List<SysPermission> permissions = sysPermissionMapper.selectByExample(sysPermissionExample);
        return permissions;
    }


    @Override
    public List<PermissionTreeVo> getByFormat() {

        List<SysPermission> list = this.getAll();
        return PermissionTreeVo.format(0L, list);
    }
}
