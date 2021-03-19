/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/2/4 6:51 下午
 */
package com.tyuan.manager.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.dao.base.mapper.SysLogMapper;
import com.tyuan.manager.base.service.SysLogService;
import com.tyuan.model.base.pojo.SysLogExample;
import com.tyuan.model.base.pojo.SysLogWithBLOBs;
import com.tyuan.model.base.vo.sys.SysLogTableVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    Logger logger = LoggerFactory.getLogger(SysLogService.class);

    @Resource
    SysLogMapper sysLogMapper;

    @Override
    public PageInfo getByParams(SysLogTableVo param) {

        SysLogExample example = new SysLogExample();
        SysLogExample.Criteria criteria = example.createCriteria();
        String like = "%{0}%";

        String title = param.getTitle();
        if (StringUtils.isNotBlank(title)) {
            criteria.andTitleLike(MessageFormat.format(like, title));
        }
        if (StringUtils.isNotBlank(param.getMethod())) {
            criteria.andMethodLike(MessageFormat.format(like, param.getMethod()));
        }
        if (StringUtils.isNoneBlank(param.getUserName())) {
            criteria.andUserNameLike(MessageFormat.format(like, param.getUserName()));
        }
        if (null != param.getUserId()) {
            criteria.andUserIdEqualTo(param.getUserId());
        }
        if (null != param.getType()) {
            criteria.andTypeEqualTo(param.getType());
        }
        if (StringUtils.isNotBlank(param.getRequestId())) {
            criteria.andRequestIdEqualTo(param.getRequestId());
        }
        PageHelper.offsetPage(param.getOffset(), param.getPageSize())
                .setOrderBy("create_date desc");
        List<SysLogWithBLOBs> result = sysLogMapper.selectByExampleWithBLOBs(example);
        return new PageInfo(result);
    }

    @Override
    public void updateByRequestId(String requestId, SysLogWithBLOBs withBLOBs) {
        SysLogExample sysLogExample = new SysLogExample();
        sysLogExample.createCriteria().andRequestIdEqualTo(requestId);
        sysLogMapper.updateByExampleSelective(withBLOBs, sysLogExample);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysLogWithBLOBs sysLog) throws ServiceException {
        sysLogMapper.insertSelective(sysLog);
    }
}