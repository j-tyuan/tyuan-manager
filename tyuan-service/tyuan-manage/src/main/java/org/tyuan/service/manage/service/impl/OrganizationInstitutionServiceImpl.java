/**
 * @ClassName SysDictService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
 */
package org.tyuan.service.manage.service.impl;

import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.dao.customize.COrganizationInstitutionMapper;
import org.tyuan.service.manage.service.OrganizationInstitutionService;
import org.tyuan.service.model.ErrorCodeConsts;
import org.tyuan.service.model.pojo.OrganizationInstitution;
import org.tyuan.service.model.pojo.OrganizationInstitutionExample;
import org.tyuan.service.model.pojo.custom.COrganizationInstitution;
import org.tyuan.service.model.vo.DeleteVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrganizationInstitutionServiceImpl implements OrganizationInstitutionService {

    @Resource
    COrganizationInstitutionMapper corganizeInstitutionMapper;

    @Override
    public List<COrganizationInstitution> getAll() {
        return corganizeInstitutionMapper.getAll();
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
