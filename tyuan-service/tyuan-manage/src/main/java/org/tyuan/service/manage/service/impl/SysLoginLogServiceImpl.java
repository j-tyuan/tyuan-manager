/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/2/4 6:51 下午
 */
package org.tyuan.service.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.tyuan.common.utils.IPUtils;
import org.tyuan.service.dao.mapper.SysLoginLogMapper;
import org.tyuan.service.manage.service.SysLogService;
import org.tyuan.service.manage.service.SysLoginLogService;
import org.tyuan.service.model.pojo.SysLoginLog;
import org.tyuan.service.model.pojo.SysLoginLogExample;
import org.tyuan.service.model.pojo.SysUser;
import org.tyuan.service.model.vo.sys.SysLoginLogTableParamsVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {

    Logger logger = LoggerFactory.getLogger(SysLogService.class);

    @Resource
    SysLoginLogMapper sysLoginLogMapper;

    @Override
    public PageInfo getByParams(SysLoginLogTableParamsVo param) {

        SysLoginLogExample example = new SysLoginLogExample();
        SysLoginLogExample.Criteria criteria = example.createCriteria();
        String userName = param.getUserName();
        if (StringUtils.isNotBlank(userName)) {
            criteria.andUserNameEqualTo(userName);
        }
        String userNo = param.getUserNo();
        if (StringUtils.isNotBlank(userNo)) {
            criteria.andUserNoEqualTo(userNo);
        }
        PageHelper.offsetPage(param.getOffset(), param.getPageSize())
                .setOrderBy("create_date desc");
        List<SysLoginLog> result = sysLoginLogMapper.selectByExample(example);
        return new PageInfo(result);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysUser sysUser, HttpServletRequest request) {
        SysLoginLog loginLog = new SysLoginLog();

        loginLog.setAvatarId(sysUser.getAvatarId());
        loginLog.setCreateDate(new Date());
        loginLog.setUserId(sysUser.getId());
        loginLog.setUserName(sysUser.getName());
        loginLog.setUserNo(sysUser.getUserNo());
        String ipAddr = IPUtils.getIpAddr(request);
        loginLog.setLoginIp(ipAddr);
        sysLoginLogMapper.insertSelective(loginLog);
    }
}
