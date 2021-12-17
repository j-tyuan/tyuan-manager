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
package org.tyuan.service.application.service.impl;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.tyuan.service.dao.mapper.SysPermissionMapper;
import org.tyuan.service.dao.mapper.customize.CSysRolePermissionMapper;
import org.tyuan.service.dao.model.SysPermission;
import org.tyuan.service.dao.model.SysPermissionExample;
import org.tyuan.service.dao.model.SysRolePermission;
import org.tyuan.service.dao.model.SysRolePermissionExample;
import org.tyuan.service.application.service.SysPermissionService;

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
        return sysPermissionMapper.selectByExample(null);
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
}
