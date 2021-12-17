/**
 * @ClassName SysDictService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
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
package org.tyuan.service.application.service.impl;

import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.dao.mapper.customize.COrganizationInstitutionMapper;
import org.tyuan.service.application.service.OrganizationInstitutionService;
import org.tyuan.service.data.ErrorCodeConsts;
import org.tyuan.service.dao.model.OrganizationInstitution;
import org.tyuan.service.dao.model.OrganizationInstitutionExample;
import org.tyuan.service.dao.model.custom.COrganizationInstitution;
import org.tyuan.service.data.vo.DeleteVo;
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
