/**
 * @ClassName SysDictService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
 */
package com.tyuan.manager.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.dao.base.customize.COrganizationInstitutionMapper;
import com.tyuan.manager.base.cache.LocalCache;
import com.tyuan.manager.base.service.OrganizationInstitutionService;
import com.tyuan.manager.base.task.SysScheduledTask;
import com.tyuan.model.base.ErrorCodeConsts;
import com.tyuan.model.base.pojo.*;
import com.tyuan.model.base.pojo.custom.COrganizationInstitution;
import com.tyuan.model.base.vo.DeleteVo;
import com.tyuan.model.base.vo.sys.OrganizeInstitutionTableVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Service
public class OrganizationInstitutionServiceImpl implements OrganizationInstitutionService {

    @Resource
    COrganizationInstitutionMapper corganizeInstitutionMapper;

    @Resource
    SysScheduledTask sysScheduledTask;

    @Override
    public List<COrganizationInstitution> getAll() {
        List<COrganizationInstitution> list = LocalCache.SYS_INSTITUTION.getData();
        if (CollectionUtils.isEmpty(list)) {
            sysScheduledTask.refreshInstitution();
            return LocalCache.SYS_INSTITUTION.getData();
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(OrganizationInstitution sysInstitution) throws ServiceException {
        String code = sysInstitution.getInstCode();
        OrganizationInstitution item = getByCode(code);
        if (null != item) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "机构编码已存在");
        }
        Object o = corganizeInstitutionMapper.selectByPrimaryKey(sysInstitution.getParentId());
        if (null == o) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "上级节点不存在，请刷新页面后尝试");
        }
        corganizeInstitutionMapper.insertSelective(sysInstitution);
        // 更新缓存，不要异步
        sysScheduledTask.refreshInstitution();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(OrganizationInstitution sysInstitution) throws ServiceException {
        OrganizationInstitution item = corganizeInstitutionMapper.selectByPrimaryKey(sysInstitution.getId());
        if (null == item) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "机构不存在");
        }
        Object o = corganizeInstitutionMapper.selectByPrimaryKey(sysInstitution.getParentId());
        if (null == o) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "上级节点不存在，请刷新页面后尝试");
        }
        sysInstitution.setInstCode(null);
        corganizeInstitutionMapper.updateByPrimaryKeySelective(sysInstitution);
        // 更新缓存，不要异步
        sysScheduledTask.refreshInstitution();
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<Long> ids = deleteVo.getId();
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        for (Long id : ids) {
            OrganizationInstitutionExample example = new OrganizationInstitutionExample();
            example.createCriteria().andParentIdEqualTo(id);
            List list = corganizeInstitutionMapper.selectByExample(example);
            if (CollectionUtils.isNotEmpty(list)) {
                throw new ServiceException(ErrorCodeConsts.ERROR, "请先解除下级机构");
            }
            corganizeInstitutionMapper.deleteByPrimaryKey(id);
        }
        // 更新缓存，不要异步
        sysScheduledTask.refreshInstitution();
    }

    private OrganizationInstitution getByCode(String code) {
        OrganizationInstitutionExample example = new OrganizationInstitutionExample();
        example.createCriteria().andInstCodeEqualTo(code);
        List<OrganizationInstitution> list = corganizeInstitutionMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
}
