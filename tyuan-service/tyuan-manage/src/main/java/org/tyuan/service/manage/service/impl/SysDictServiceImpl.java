package org.tyuan.service.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.dao.customize.CSysDictMapper;
import org.tyuan.service.manage.service.SysDictService;
import org.tyuan.service.manage.utils.UserInfoHolder;
import org.tyuan.service.model.ErrorCodeConsts;
import org.tyuan.service.model.cache.CacheConstant;
import org.tyuan.service.model.pojo.SysDict;
import org.tyuan.service.model.pojo.SysDictExample;
import org.tyuan.service.model.vo.DeleteVo;
import org.tyuan.service.model.vo.sys.SysDictTableParamsVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        String label = param.getLabel();
        if (StringUtils.isNotBlank(label)) {
            criteria.andLabelLike(MessageFormat.format(like, label));
        }
        String type = param.getType();
        if (StringUtils.isNotBlank(type) && !"all".equals(type)) {
            criteria.andTypeEqualTo(param.getType());
        }
        if (null != param.getParentId()) {
            criteria.andParentIdEqualTo(param.getParentId());
        }
        PageHelper.offsetPage(param.getOffset(), param.getPageSize())
                .setOrderBy("update_date desc");
        List<SysDict> result = csysDictMapper.selectByExample(example);

        return new PageInfo(result);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysDict sysDict) throws ServiceException {

        String label = sysDict.getLabel();
        if (StringUtils.isBlank(label)) {

            logger.info("{} - 【添加字典失败】info：{}", UserInfoHolder.getUserName(), "label 为null");
            throw new ServiceException(ErrorCodeConsts.ERROR_INFO_LACK, "参数错误");
        }

        if (StringUtils.isBlank(sysDict.getValue())) {

            logger.info("{} - 【添加字典失败】info：{}", UserInfoHolder.getUserName(), "value 为null");
            throw new ServiceException(ErrorCodeConsts.ERROR_INFO_LACK, "参数错误");
        }

        sysDict.setCreateBy(UserInfoHolder.getUserName());
        sysDict.setUpdateBy(UserInfoHolder.getUserName());

        Date today = new Date();
        sysDict.setUpdateDate(today);
        sysDict.setCreateDate(today);

        csysDictMapper.insertSelective(sysDict);
        logger.info("{} - 【添加字典成功】info：label - {}", UserInfoHolder.getUserName(), label);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysDict sysDict) throws ServiceException {

        Long id = sysDict.getId();
        if (null == id) {

            logger.info("{} - 【修改字典失败】info： {}", UserInfoHolder.getUserName(), "id 不正确");
            throw new ServiceException(ErrorCodeConsts.ERROR_INFO_LACK, "参数错误");
        }

        sysDict.setUpdateBy(UserInfoHolder.getUserName());
        sysDict.setUpdateDate(new Date());
        csysDictMapper.updateByPrimaryKeySelective(sysDict);

        logger.info("{} - 【字典修改成功】info： {}", UserInfoHolder.getUserName(), id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<Long> ids = deleteVo.getId();
        ids.forEach(id -> {
            csysDictMapper.deleteByPrimaryKey(id);
        });
    }

    @Override
    public SysDict getById(Long id) {
        return csysDictMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysDict getByLabel(String label) {

        SysDictExample example = new SysDictExample();
        example.createCriteria().andLabelEqualTo(label);
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
