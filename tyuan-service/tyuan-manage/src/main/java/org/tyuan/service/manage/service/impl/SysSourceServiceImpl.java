/**
 * @ClassName SysMenuServiceImpl
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:54
 */
package org.tyuan.service.manage.service.impl;

import com.google.common.collect.Lists;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.dao.customize.CSysSourceMapper;
import org.tyuan.service.manage.service.SysPermissionService;
import org.tyuan.service.manage.service.SysSourceService;
import org.tyuan.service.manage.utils.UserInfoHolder;
import org.tyuan.service.model.ErrorCodeConsts;
import org.tyuan.service.model.pojo.SysPermission;
import org.tyuan.service.model.pojo.SysSource;
import org.tyuan.service.model.pojo.SysSourceExample;
import org.tyuan.service.model.pojo.custom.CSysSource;
import org.tyuan.service.model.vo.DeleteVo;
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
