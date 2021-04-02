package org.springmg.service.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springmg.common.exception.ServiceException;
import org.springmg.service.dao.customize.CSysParamMapper;
import org.springmg.service.manage.service.SysParamService;
import org.springmg.service.manage.task.SysScheduledTask;
import org.springmg.service.manage.utils.UserInfoHolder;
import org.springmg.service.model.ErrorCodeConsts;
import org.springmg.service.model.cache.CacheConstant;
import org.springmg.service.model.pojo.SysParam;
import org.springmg.service.model.pojo.SysParamExample;
import org.springmg.service.model.vo.DeleteVo;
import org.springmg.service.model.vo.sys.SysParamTableVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;

@Service
public class SysParamServerImpl implements SysParamService {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    SysScheduledTask sysScheduledTask;

    @Resource
    CSysParamMapper cSysParamMapper;

    /**
     * 在缓存中拿取数据
     *
     * @param key
     * @return
     * @see SysScheduledTask
     */
    @Override
    public SysParam getByKey(String key) {
        HashOperations hashoperations = redisTemplate.opsForHash();
        SysParam sysParam = (SysParam) hashoperations.get(CacheConstant.SYS_PARAM_INFO, key);
        return sysParam;
    }

    @Override
    public void updateCache() {

        sysScheduledTask.refreshParam();
    }

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
                .setOrderBy("update_date desc");
        List<SysParam> result = cSysParamMapper.selectByExampleWithBLOBs(example);

        return new PageInfo(result);
    }

    /**
     * 在缓存中获取所有数据
     *
     * @return
     * @see SysScheduledTask
     */
    @Override
    public List<SysParam> getAll() {
        HashOperations hashoperations = redisTemplate.opsForHash();
        List<SysParam> sysParams = (List<SysParam>) hashoperations.entries(CacheConstant.SYS_PARAM_INFO);

        return sysParams;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<Long> ids = deleteVo.getId();
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
        cSysParamMapper.insertSelective(sysParam);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysParam sysParam) throws ServiceException {
        sysParam.setUpdateBy(UserInfoHolder.getUserName());
        sysParam.setParamKey(null);
        sysParam.setCreateBy(null);
        cSysParamMapper.updateByPrimaryKeySelective(sysParam);

    }

}
