/**
 * Copyright © 2016-2021 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.data.audit;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thingsboard.server.common.data.BaseData;
import org.thingsboard.server.common.data.id.*;

@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
public class AuditLog extends BaseData<AuditLogId> {

    @ApiModelProperty(position = 3, value = "JSON object with Tenant Id", readOnly = true)
    private TenantId tenantId;
    @ApiModelProperty(position = 4, value = "JSON object with Customer Id", readOnly = true)
    private CustomerId customerId;
    @ApiModelProperty(position = 5, value = "JSON object with Entity id", readOnly = true)
    private EntityId entityId;
    @ApiModelProperty(position = 6, value = "Name of the logged entity", example = "Thermometer", readOnly = true)
    private String entityName;
    @ApiModelProperty(position = 7, value = "JSON object with User id.", readOnly = true)
    private UserId userId;
    @ApiModelProperty(position = 8, value = "Unique user name(email) of the user that performed some action on logged entity", example = "tenant@thingsboard.org", readOnly = true)
    private String userName;
    @ApiModelProperty(position = 9, value = "String represented Action type", example = "ADDED", readOnly = true)
    private ActionType actionType;
    @ApiModelProperty(position = 10, value = "JsonNode represented action data", readOnly = true)
    private JsonNode actionData;
    @ApiModelProperty(position = 11, value = "String represented Action status", example = "SUCCESS", allowableValues = "SUCCESS,FAILURE", readOnly = true)
    private ActionStatus actionStatus;
    @ApiModelProperty(position = 12, value = "Failure action details info. An empty string in case of action status type 'SUCCESS', otherwise includes stack trace of the caused exception.", readOnly = true)
    private String actionFailureDetails;

    public AuditLog() {
        super();
    }

    public AuditLog(AuditLogId id) {
        super(id);
    }

    public AuditLog(AuditLog auditLog) {
        super(auditLog);
        this.tenantId = auditLog.getTenantId();
        this.customerId = auditLog.getCustomerId();
        this.entityId = auditLog.getEntityId();
        this.entityName = auditLog.getEntityName();
        this.userId = auditLog.getUserId();
        this.userName = auditLog.getUserName();
        this.actionType = auditLog.getActionType();
        this.actionData = auditLog.getActionData();
        this.actionStatus = auditLog.getActionStatus();
        this.actionFailureDetails = auditLog.getActionFailureDetails();
    }

    @ApiModelProperty(position = 2, value = "Timestamp of the auditLog creation, in milliseconds", example = "1609459200000", readOnly = true)
    @Override
    public long getCreatedTime() {
        return super.getCreatedTime();
    }

    @ApiModelProperty(position = 1, value = "JSON object with the auditLog Id")
    @Override
    public AuditLogId getId() {
        return super.getId();
    }

}
