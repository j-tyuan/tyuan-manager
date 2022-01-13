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

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.tyuan.service.application.service.manage.AuditLogService;
import org.tyuan.service.dao.mapper.AuditLogMapper;
import org.tyuan.service.data.audit.ActionStatus;
import org.tyuan.service.data.audit.ActionType;
import org.tyuan.service.data.model.AuditLogWithBLOBs;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

/**
 * @author jiangguiqi@aliyun.com
 * @version 1.0
 * @date 2021/12/22 1:28 下午
 */
@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Resource
    AuditLogMapper auditLogMapper;

    @Override
    public void logAction(String userId, String userName, ActionType actionType, Exception e, Object... additionalInfo) {

        AuditLogWithBLOBs record = new AuditLogWithBLOBs();
        record.setUserId(userId);
        record.setUserName(userName);
        record.setActionType(actionType.name());
        record.setActionStatus(ActionStatus.SUCCESS.name());
        record.setActionData(JSONObject.toJSONString(additionalInfo));
        if (e != null) {
            record.setActionStatus(ActionStatus.FAILURE.name());
            String failureDetails = getFailureStack(e);
            record.setActionFailureDetails(failureDetails);
        }else {
            record.setActionFailureDetails("");
        }
        record.setId(UUID.randomUUID().toString());
        auditLogMapper.insertSelective(record);
    }

    private String getFailureStack(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
