/**
 * @ClassName RoleServiceImpl
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:58
 */
package com.tyuan.manager.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.dao.base.customize.CSysRolePermissionMapper;
import com.tyuan.dao.base.mapper.SysRoleMapper;
import com.tyuan.dao.base.mapper.SysUserRoleMapper;
import com.tyuan.manager.base.cache.UserInfoCacheService;
import com.tyuan.manager.base.service.SysRoleService;
import com.tyuan.manager.base.utils.UserInfoHolder;
import com.tyuan.model.base.ErrorCodeConsts;
import com.tyuan.model.base.pojo.*;
import com.tyuan.model.base.vo.DeleteVo;
import com.tyuan.model.base.vo.sys.SysRoleTableParamsVo;
import com.tyuan.model.base.vo.sys.SysRoleVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    SysRoleMapper sysRoleMapper;

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    @Resource
    CSysRolePermissionMapper cSysRolePermissionMapper;

    @Resource
    UserInfoCacheService userInfoCacheService;

    @Override
    public PageInfo<SysRole> getByParams(SysRoleTableParamsVo param) {

        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        String like = "%{0}%";
        if (StringUtils.isNotBlank(param.getCode())) {
            criteria.andCodeLike(MessageFormat.format(like, param.getCode()));
        }
        if (StringUtils.isNotBlank(param.getName())) {
            criteria.andNameLike(MessageFormat.format(like, param.getName()));
        }

        PageHelper.offsetPage(param.getOffset(), param.getPageSize())
                .setOrderBy("update_date desc");
        List<SysRole> result = sysRoleMapper.selectByExample(example);

        return new PageInfo(result);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysRoleVo sysRole) throws ServiceException {
        sysRole.setCreateBy(UserInfoHolder.getUserName());
        sysRole.setUpdateBy(UserInfoHolder.getUserName());
        SysRoleExample example = new SysRoleExample();
        example.createCriteria().andNameEqualTo(sysRole.getName());

        List list = sysRoleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {

            throw new ServiceException(ErrorCodeConsts.ERROR, "名称已存在");
        }

        sysRoleMapper.insertSelective(sysRole);
        cSysRolePermissionMapper.batchBind(sysRole.getId(), sysRole.getPermissionIds());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysRoleVo sysRole) throws ServiceException {
        Long id = sysRole.getId();
        SysRole val = sysRoleMapper.selectByPrimaryKey(id);
        if (null == val) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "未找到数据，修改失败");
        }
        sysRole.setUpdateBy(UserInfoHolder.getUserName());
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);

        cSysRolePermissionMapper.unBindByRoleId(sysRole.getId());
        if (CollectionUtils.isNotEmpty(sysRole.getPermissionIds())){
            cSysRolePermissionMapper.batchBind(sysRole.getId(), sysRole.getPermissionIds());
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<Long> ids = deleteVo.getId();
        ids.forEach(id -> {
            sysRoleMapper.deleteByPrimaryKey(id);
            //批量解除绑定
            cSysRolePermissionMapper.unBindByRoleId(id);
        });
    }

    @Override
    public List<SysRole> getRoleByUserId(Long uid) {

        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(uid);

        List<SysUserRole> roleUsers = sysUserRoleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(roleUsers)) {
            return new ArrayList<>();
        }

        List<Long> ids = new ArrayList<>();
        for (SysUserRole item : roleUsers) {
            ids.add(item.getRoleId() + 0L);
        }

        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdIn(ids);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        return sysRoles;
    }

    @Override
    public SysRole getById(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRole> getAll() {
        List<SysRole> all = sysRoleMapper.selectByExample(null);
        return all;
    }

    @Override
    public List<Long> getBindUserById(Long id) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(id);
        List<SysUserRole> roles = sysUserRoleMapper.selectByExample(example);
        List<Long> longs = roles.stream().map(e -> e.getUserId()).collect(Collectors.toList());
        return longs;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void bindUser(Long roleId, Long userId) {
        SysUserRole record = new SysUserRole();
        record.setRoleId(roleId);
        record.setUserId(userId);
        sysUserRoleMapper.insertSelective(record);
        userInfoCacheService.leaveMessage(userId, "你的权限发生改变，请重新登陆");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void unbindUser(Long roleId, Long userId) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria()
                .andRoleIdEqualTo(roleId)
                .andUserIdEqualTo(userId);
        List<SysUserRole> list = sysUserRoleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Long id = list.get(0).getId();
        sysUserRoleMapper.deleteByPrimaryKey(id);
        userInfoCacheService.leaveMessage(userId, "你的权限发生改变，请重新登陆");
    }

    public void filterRoles(List<SysRole> sysRoles) {
        Iterator<SysRole> iterator = sysRoles.iterator();
        while (iterator.hasNext()) {
            try {

                SysRole item = iterator.next();
                SecurityUtils.getSubject().checkRole(item.getName());
            } catch (AuthorizationException e) {

                iterator.remove();
            }
        }
    }

    @Override
    public boolean hasRoleById(Long id) {

        SysRole sysRole = this.getById(id);
        return SecurityUtils.getSubject().hasRole(sysRole.getName());
    }

    @Override
    public List<Long> getAuthIdByRoleId(Long roleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<SysRolePermission> sysRolePermissions = cSysRolePermissionMapper.selectByExample(example);
        List<Long> permissionIds = sysRolePermissions.stream().map(e -> e.getPermissionId()).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(permissionIds)) {

            return Lists.newArrayList();
        }
        return permissionIds;
    }
}
