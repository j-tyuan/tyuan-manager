/**
 * @ClassName SysMenuServiceImpl
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:54
 */
package com.tyuan.manager.base.service.impl;

import com.google.common.collect.Lists;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.dao.base.mapper.SysSourceMapper;
import com.tyuan.manager.base.service.SysPermissionService;
import com.tyuan.manager.base.service.SysSourceService;
import com.tyuan.manager.base.utils.UserInfoHolder;
import com.tyuan.model.base.ErrorCodeConsts;
import com.tyuan.model.base.pojo.SysPermission;
import com.tyuan.model.base.pojo.SysSource;
import com.tyuan.model.base.pojo.SysSourceExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyuan.model.base.vo.DeleteVo;
import com.tyuan.model.base.vo.sys.SysSourceTableParamsVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;

@Service
public class SysSourceServiceImpl implements SysSourceService {

    @Resource
    SysSourceMapper sysSourceMapper;

    @Autowired
    SysPermissionService sysPermissionService;

    @Override
    public List<SysSource> getAll() {

        List<SysSource> list = sysSourceMapper.selectByExample(null);
        Collections.sort(list, Comparator.comparingLong(o -> o.getSort()));
        return list;
    }


    @Override
    public PageInfo<SysSource> getByParams(SysSourceTableParamsVo param) {

        SysSourceExample example = new SysSourceExample();
        SysSourceExample.Criteria criteria = example.createCriteria();
        String like = "%{0}%";
        if (StringUtils.isNotBlank(param.getName())) {
            criteria.andNameLike(MessageFormat.format(like, param.getName()));
        } else {

            criteria.andParentIdEqualTo(0L);
        }

        PageHelper.offsetPage(param.getOffset(), param.getPageSize())
                .setOrderBy("sort asc");

        List<SysSource> result = sysSourceMapper.selectByExample(example);
        return new PageInfo<>(result);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysSource sysSource) throws ServiceException {
        String userName = UserInfoHolder.getUserName();
        sysSource.setUpdateBy(userName);
        sysSource.setCreateBy(userName);
        sysSourceMapper.insertSelective(sysSource);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysSource sysSource) throws ServiceException {
        Long id = sysSource.getId();

        SysSource val = sysSourceMapper.selectByPrimaryKey(id);
        if (null == val) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "未找到数据，修改失败");
        }

        sysSource.setUpdateBy(UserInfoHolder.getUserName());
        sysSource.setUpdateDate(new Date());

        sysSourceMapper.updateByPrimaryKeySelective(sysSource);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<Long> ids = deleteVo.getId();
        ids.forEach(id -> {
            sysSourceMapper.deleteByPrimaryKey(id);
        });
    }

    @Override
    public SysSource getById(Long id) {
        return sysSourceMapper.selectByPrimaryKey(id);
    }

    /**
     * 权限过滤
     *
     * @return
     */
    public List<SysSource> authority(List<SysSource> list) {

        List<SysPermission> permissions = sysPermissionService.getAll();

        //最终返回结果
        List<SysSource> result = Lists.newArrayList();

        Iterator<SysSource> sysSourceIterator = list.iterator();
        while (sysSourceIterator.hasNext()) {
            SysSource sysSource = sysSourceIterator.next();

            Iterator<SysPermission> permIterator = permissions.iterator();
            while (permIterator.hasNext()) {
                SysPermission sysPower = permIterator.next();
                if (sysPower.getId() == sysSource.getPermissionId()) {
                    try {

                        //有权限
                        SecurityUtils.getSubject().checkPermission(sysPower.getPermission());
                        result.add(sysSource);
                        break;
                    } catch (AuthorizationException ex) {
                    }
                    permIterator.remove();
                }
            }
        }

        return result;
    }

    @Override
    public List<SysSource> getByParentId(long parentId) {
        SysSourceExample example = new SysSourceExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<SysSource> sources = sysSourceMapper.selectByExample(example);
        return sources;
    }
}
