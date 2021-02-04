/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/2/4 6:51 下午
 */
package com.tyuan.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tyuan.dao.mapper.SysLogMapper;
import com.tyuan.manager.service.ServiceException;
import com.tyuan.manager.service.SysLogService;
import com.tyuan.model.pojo.SysLog;
import com.tyuan.model.pojo.SysLogExample;
import com.tyuan.model.pojo.SysLogWithBLOBs;
import com.tyuan.model.vo.sys.SysLogTableVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

        PageHelper.offsetPage(param.getOffset(), param.getPageSize())
                .setOrderBy("create_date desc");
        List<SysLogWithBLOBs> result = sysLogMapper.selectByExampleWithBLOBs(example);
        return new PageInfo(result);
    }

    @Override
    public void saveExceptionInfo(String requestId, String exception) {
        SysLogExample sysLogExample = new SysLogExample();
        sysLogExample.createCriteria().andRequestIdEqualTo(requestId);
        List<SysLog> list = sysLogMapper.selectByExample(sysLogExample);
        if (CollectionUtils.isEmpty(list)) {
            logger.error("保存操作异常时，找不到用户");
            return;
        }
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setException(exception);
        sysLogMapper.updateByExampleSelective(sysLog, sysLogExample);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysLogWithBLOBs sysLog) throws ServiceException {
        sysLogMapper.insertSelective(sysLog);
    }
}
