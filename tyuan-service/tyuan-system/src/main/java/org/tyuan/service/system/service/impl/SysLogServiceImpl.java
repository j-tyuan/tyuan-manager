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
package org.tyuan.service.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.system.mapper.SysLogMapper;
import org.tyuan.service.system.service.SysLogService;
import org.tyuan.service.system.model.pojo.SysLogExample;
import org.tyuan.service.system.model.pojo.SysLogWithBLOBs;
import org.tyuan.service.system.model.vo.sys.SysLogTableVo;
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
