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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.application.service.manage.SysParamService;
import org.tyuan.service.common.UserInfoHolder;
import org.tyuan.service.dao.mapper.customize.manage.CSysParamMapper;
import org.tyuan.service.data.ErrorCodeConsts;
import org.tyuan.service.data.cache.CacheConstant;
import org.tyuan.service.data.model.SysParam;
import org.tyuan.service.data.model.SysParamExample;
import org.tyuan.service.data.vo.DeleteVo;
import org.tyuan.service.data.vo.sys.SysParamTableVo;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
public class SysParamServerImpl implements SysParamService {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    CSysParamMapper cSysParamMapper;

    @Override
    public PageInfo getByParams(SysParamTableVo param) {

        SysParamExample example = new SysParamExample();
        SysParamExample.Criteria criteria = example.createCriteria();
        String like = "%{0}%";
        String name = param.getParamName();

        if (StringUtils.isNotBlank(name)) {
            criteria.andParamNameLike(MessageFormat.format(like, name));
        }
        if (StringUtils.isNotBlank(param.getParamKey())) {
            criteria.andParamKeyLike(MessageFormat.format(like, param.getParamKey()));
        }
        if (StringUtils.isNoneBlank(param.getRemarks())) {
            criteria.andRemarksLike(MessageFormat.format(like, param.getRemarks()));
        }

        PageHelper.offsetPage(param.getOffset(), param.getPageSize())
                .setOrderBy("update_time desc");
        List<SysParam> result = cSysParamMapper.selectByExampleWithBLOBs(example);

        return new PageInfo(result);
    }

    /**
     * 在缓存中获取所有数据
     *
     * @return
     */
    @Override
    public List<SysParam> getAll() {
        HashOperations hashoperations = redisTemplate.opsForHash();
        List<SysParam> sysParams = (List<SysParam>) hashoperations.entries(CacheConstant.SYS_PARAM_INFO);
        return sysParams;
    }

    @Override
    public SysParam getByKey(String key) {
        SysParamExample example = new SysParamExample();
        example.createCriteria().andParamKeyEqualTo(key);
        List<SysParam> sysParams = cSysParamMapper.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isNotEmpty(sysParams)) {
            return sysParams.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<String> ids = deleteVo.getId();
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        ids.forEach(id -> {
            cSysParamMapper.deleteByPrimaryKey(id);
        });
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysParam sysParam) throws ServiceException {
        String key = sysParam.getParamKey();
        if (StringUtils.isBlank(key)) {

            throw new ServiceException(ErrorCodeConsts.ERROR_NOT_EXIST, "添加失败，缺少关键信息");
        }

        SysParamExample example = new SysParamExample();
        example.createCriteria().andParamKeyEqualTo(key);
        List<SysParam> list = cSysParamMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {

            throw new ServiceException(ErrorCodeConsts.ERROR_EXIST, "添加失败，key重复");
        }

        sysParam.setCreateBy(UserInfoHolder.getUserName());
        sysParam.setUpdateBy(UserInfoHolder.getUserName());
        sysParam.setId(UUID.randomUUID().toString());
        cSysParamMapper.insertSelective(sysParam);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysParam sysParam) throws ServiceException {
        if (null == sysParam.getId()) {
            add(sysParam);
            return;
        }
        sysParam.setUpdateBy(UserInfoHolder.getUserName());
        sysParam.setParamKey(null);
        sysParam.setCreateBy(null);
        cSysParamMapper.updateByPrimaryKeySelective(sysParam);

    }

}
