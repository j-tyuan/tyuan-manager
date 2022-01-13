/**
 * @ClassName RoleServiceImpl
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:58
 */
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
package org.tyuan.service.application.service.manage.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.application.service.manage.SysRoleService;
import org.tyuan.service.common.UserInfoHolder;
import org.tyuan.service.dao.mapper.SysRoleMapper;
import org.tyuan.service.dao.mapper.customize.manage.CSysRolePermissionMapper;
import org.tyuan.service.data.ErrorCodeConsts;
import org.tyuan.service.data.model.SysRole;
import org.tyuan.service.data.model.SysRoleExample;
import org.tyuan.service.data.model.SysRolePermission;
import org.tyuan.service.data.model.SysRolePermissionExample;
import org.tyuan.service.data.vo.DeleteVo;
import org.tyuan.service.data.vo.sys.SysRoleTableParamsVo;
import org.tyuan.service.data.vo.sys.SysRoleVo;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    SysRoleMapper sysRoleMapper;

    @Resource
    CSysRolePermissionMapper cSysRolePermissionMapper;

    @Override
    public PageInfo<SysRole> getByParams(SysRoleTableParamsVo param) {

        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        String like = "%{0}%";
        if (StringUtils.isNotBlank(param.getRoleCode())) {
            criteria.andRoleCodeLike(MessageFormat.format(like, param.getRoleCode()));
        }
        if (StringUtils.isNotBlank(param.getRoleName())) {
            criteria.andRoleNameLike(MessageFormat.format(like, param.getRoleName()));
        }

        PageHelper.offsetPage(param.getOffset(), param.getPageSize())
                .setOrderBy("update_time desc");
        List<SysRole> result = sysRoleMapper.selectByExample(example);

        return new PageInfo(result);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysRoleVo sysRole) throws ServiceException {
        sysRole.setCreateBy(UserInfoHolder.getUserName());
        sysRole.setUpdateBy(UserInfoHolder.getUserName());
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andRoleNameEqualTo(sysRole.getRoleName());

        List list = sysRoleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {

            throw new ServiceException(ErrorCodeConsts.ERROR, "名称已存在");
        }

        sysRole.setId(UUID.randomUUID().toString());
        sysRoleMapper.insertSelective(sysRole);
        if (CollectionUtils.isNotEmpty(sysRole.getPermissionIds())) {
            cSysRolePermissionMapper.batchBind(sysRole.getId(), sysRole.getPermissionIds());
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysRoleVo sysRole) throws ServiceException {
        String id = sysRole.getId();
        SysRole val = sysRoleMapper.selectByPrimaryKey(id);
        if (null == val) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "未找到数据，修改失败");
        }
        sysRole.setUpdateBy(UserInfoHolder.getUserName());
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);

        cSysRolePermissionMapper.unBindByRoleId(sysRole.getId());
        if (CollectionUtils.isNotEmpty(sysRole.getPermissionIds())) {
            cSysRolePermissionMapper.batchBind(sysRole.getId(), sysRole.getPermissionIds());
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<String> ids = deleteVo.getId();
        ids.forEach(id -> {
            sysRoleMapper.deleteByPrimaryKey(id);
            //批量解除绑定
            cSysRolePermissionMapper.unBindByRoleId(id);
        });
    }

    @Override
    public SysRole getById(String id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRole> getAll() {
        List<SysRole> all = sysRoleMapper.selectByExample(null);
        return all;
    }

    public void filterRoles(List<SysRole> sysRoles) {
        Iterator<SysRole> iterator = sysRoles.iterator();
        while (iterator.hasNext()) {
            try {

                SysRole item = iterator.next();
                SecurityUtils.getSubject().checkRole(item.getRoleName());
            } catch (AuthorizationException e) {

                iterator.remove();
            }
        }
    }

    @Override
    public boolean hasRoleById(String id) {

        SysRole sysRole = this.getById(id);
        return SecurityUtils.getSubject().hasRole(sysRole.getRoleName());
    }

    @Override
    public List<String> getAuthIdByRoleId(String roleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<SysRolePermission> sysRolePermissions = cSysRolePermissionMapper.selectByExample(example);
        List<String> permissionIds = sysRolePermissions.stream().map(e -> e.getPermissionId()).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(permissionIds)) {

            return Lists.newArrayList();
        }
        return permissionIds;
    }

}
