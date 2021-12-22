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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tyuan.service.application.service.SysLogService;
import org.tyuan.service.application.service.SysLoginLogService;
import org.tyuan.service.dao.mapper.SysLoginLogMapper;
import org.tyuan.service.data.model.SysLoginLog;
import org.tyuan.service.data.model.SysLoginLogExample;
import org.tyuan.service.data.vo.sys.SysLoginLogTableParamsVo;

import javax.annotation.Resource;
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
                .setOrderBy("create_time desc");
        List<SysLoginLog> result = sysLoginLogMapper.selectByExample(example);
        return new PageInfo(result);
    }

    @Override
    public void add(SysLoginLog sysLoginLog) {
        SysLoginLog loginLog = new SysLoginLog();
        sysLoginLogMapper.insertSelective(loginLog);
    }
}
