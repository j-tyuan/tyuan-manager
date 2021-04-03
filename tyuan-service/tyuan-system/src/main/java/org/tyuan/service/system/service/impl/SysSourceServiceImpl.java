/**
 * @ClassName SysMenuServiceImpl
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:54
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
package org.tyuan.service.system.service.impl;

import com.google.common.collect.Lists;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.system.mapper.customize.CSysSourceMapper;
import org.tyuan.service.system.service.SysPermissionService;
import org.tyuan.service.system.service.SysSourceService;
import org.tyuan.common.utils.UserInfoHolder;
import org.tyuan.service.system.model.ErrorCodeConsts;
import org.tyuan.service.system.model.pojo.SysPermission;
import org.tyuan.service.system.model.pojo.SysSource;
import org.tyuan.service.system.model.pojo.SysSourceExample;
import org.tyuan.service.system.model.pojo.custom.CSysSource;
import org.tyuan.service.system.model.vo.DeleteVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SysSourceServiceImpl implements SysSourceService {

    @Resource
    CSysSourceMapper csysSourceMapper;

    @Autowired
    SysPermissionService sysPermissionService;

    @Override
    public List<CSysSource> getAll() {
        return csysSourceMapper.getAll();
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysSource sysSource) throws ServiceException {
        String userName = UserInfoHolder.getUserName();
        sysSource.setUpdateBy(userName);
        sysSource.setCreateBy(userName);
        csysSourceMapper.insertSelective(sysSource);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysSource sysSource) throws ServiceException {
        Long id = sysSource.getId();

        SysSource val = csysSourceMapper.selectByPrimaryKey(id);
        if (null == val) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "未找到数据，修改失败");
        }

        sysSource.setUpdateBy(UserInfoHolder.getUserName());
        sysSource.setUpdateDate(new Date());

        csysSourceMapper.updateByPrimaryKeySelective(sysSource);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<Long> ids = deleteVo.getId();
        ids.forEach(id -> {
            csysSourceMapper.deleteByPrimaryKey(id);
        });

    }

    @Override
    public SysSource getById(Long id) {
        return csysSourceMapper.selectByPrimaryKey(id);
    }

    /**
     * 权限过滤
     *
     * @return
     */
    @Override
    public List<CSysSource> authorityFilter(List<CSysSource> list) {
        List<CSysSource> newList = Lists.newArrayList();
        List<SysPermission> permissions = sysPermissionService.getAll();
        Iterator<CSysSource> sysSourceIterator = list.iterator();

        while (sysSourceIterator.hasNext()) {
            CSysSource sysSource = sysSourceIterator.next();
            Iterator<SysPermission> permIterator = permissions.iterator();
            while (permIterator.hasNext()) {
                SysPermission sysPower = permIterator.next();
                if (sysPower.getId().equals(sysSource.getPermissionId())) {
                    try {
                        //有权限
                        SecurityUtils.getSubject().checkPermission(sysPower.getPermission());
                        newList.add(sysSource);
                    } catch (AuthorizationException ex) {
                    }
                }
            }
        }
        return newList;
    }

    @Override
    public List<SysSource> getByParentId(long parentId) {
        SysSourceExample example = new SysSourceExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<SysSource> sources = csysSourceMapper.selectByExample(example);
        return sources;
    }
}
