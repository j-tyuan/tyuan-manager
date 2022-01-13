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
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.application.service.manage.SysDictService;
import org.tyuan.service.common.UserInfoHolder;
import org.tyuan.service.dao.mapper.customize.manage.CSysDictMapper;
import org.tyuan.service.data.ErrorCodeConsts;
import org.tyuan.service.data.cache.CacheConstant;
import org.tyuan.service.data.model.SysDict;
import org.tyuan.service.data.model.SysDictExample;
import org.tyuan.service.data.vo.DeleteVo;
import org.tyuan.service.data.vo.sys.SysDictTableParamsVo;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SysDictServiceImpl implements SysDictService {

    Logger logger = LoggerFactory.getLogger(SysDictServiceImpl.class);

    @Resource
    CSysDictMapper csysDictMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public List<SysDict> getAll() {

        return csysDictMapper.selectByExample(null);
    }

    @Override
    public PageInfo getByParams(SysDictTableParamsVo param) {
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria();

        String like = "%{0}%";
        String label = param.getDictLabel();
        if (StringUtils.isNotBlank(label)) {
            criteria.andDictLabelLike(MessageFormat.format(like, label));
        }
        String type = param.getDictType();
        if (StringUtils.isNotBlank(type) && !"all".equals(type)) {
            criteria.andDictTypeEqualTo(param.getDictType());
        }
        if (null != param.getParentId()) {
            criteria.andParentIdEqualTo(param.getParentId());
        }
        PageHelper.offsetPage(param.getOffset(), param.getPageSize())
                .setOrderBy("update_time desc");
        List<SysDict> result = csysDictMapper.selectByExample(example);

        return new PageInfo(result);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysDict sysDict) throws ServiceException {

        String label = sysDict.getDictLabel();
        if (StringUtils.isBlank(label)) {

            logger.info("{} - 【添加字典失败】info：{}", UserInfoHolder.getUserName(), "label 为null");
            throw new ServiceException(ErrorCodeConsts.ERROR_INFO_LACK, "参数错误");
        }

        if (StringUtils.isBlank(sysDict.getDictValue())) {

            logger.info("{} - 【添加字典失败】info：{}", UserInfoHolder.getUserName(), "value 为null");
            throw new ServiceException(ErrorCodeConsts.ERROR_INFO_LACK, "参数错误");
        }

        sysDict.setCreateBy(UserInfoHolder.getUserName());
        sysDict.setUpdateBy(UserInfoHolder.getUserName());

        Date today = new Date();
        sysDict.setUpdateTime(today);
        sysDict.setCreateTime(today);

        sysDict.setId(UUID.randomUUID().toString());
        csysDictMapper.insertSelective(sysDict);
        logger.info("{} - 【添加字典成功】info：label - {}", UserInfoHolder.getUserName(), label);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysDict sysDict) throws ServiceException {

        String id = sysDict.getId();
        if (null == id) {

            logger.info("{} - 【修改字典失败】info： {}", UserInfoHolder.getUserName(), "id 不正确");
            throw new ServiceException(ErrorCodeConsts.ERROR_INFO_LACK, "参数错误");
        }

        sysDict.setUpdateBy(UserInfoHolder.getUserName());
        sysDict.setUpdateTime(new Date());
        csysDictMapper.updateByPrimaryKeySelective(sysDict);

        logger.info("{} - 【字典修改成功】info： {}", UserInfoHolder.getUserName(), id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<String> ids = deleteVo.getId();
        ids.forEach(id -> {
            csysDictMapper.deleteByPrimaryKey(id);
        });
    }

    @Override
    public SysDict getById(String id) {
        return csysDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysDict getByLabel(String label) {

        SysDictExample example = new SysDictExample();
        example.createCriteria().andDictLabelEqualTo(label);
        List<SysDict> dicts = csysDictMapper.selectByExample(example);

        if (CollectionUtils.isNotEmpty(dicts)) {

            return dicts.get(0);
        }

        return null;
    }

    @Override
    public Map<String, List<SysDict>> getByType(String type) {

        HashOperations hashOperations = redisTemplate.opsForHash();

        if (StringUtils.isBlank(type)) {
            return hashOperations.entries(CacheConstant.SYS_DICT_MAP);
        }
        Map result = Maps.newHashMap();
        result.put(type, hashOperations.get(CacheConstant.SYS_DICT_MAP, type));
        return result;
    }
}
