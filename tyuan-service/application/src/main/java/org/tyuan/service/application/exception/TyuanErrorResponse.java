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
package org.tyuan.service.application.exception;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.tyuan.service.data.exception.TyuanErrorCode;

import java.util.Date;

public class TyuanErrorResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Error code
    private final TyuanErrorCode errorCode;

    private final Date timestamp;

    protected TyuanErrorResponse(final String message, final TyuanErrorCode errorCode, HttpStatus status) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = new Date();
    }

    public static TyuanErrorResponse of(final String message, final TyuanErrorCode errorCode, HttpStatus status) {
        return new TyuanErrorResponse(message, errorCode, status);
    }

    @ApiModelProperty(position = 1, value = "HTTP Response Status Code", example = "401", readOnly = true)
    public Integer getStatus() {
        return status.value();
    }

    @ApiModelProperty(position = 2, value = "Error message", example = "Authentication failed", readOnly = true)
    public String getMessage() {
        return message;
    }

    @ApiModelProperty(position = 3, value = "Platform error code:" +
            "\n* `2` - General error (HTTP: 500 - Internal Server Error)" +
            "\n\n* `10` - Authentication failed (HTTP: 401 - Unauthorized)" +
            "\n\n* `11` - JWT token expired (HTTP: 401 - Unauthorized)" +
            "\n\n* `15` - Credentials expired (HTTP: 401 - Unauthorized)" +
            "\n\n* `20` - Permission denied (HTTP: 403 - Forbidden)" +
            "\n\n* `30` - Invalid arguments (HTTP: 400 - Bad Request)" +
            "\n\n* `31` - Bad request params (HTTP: 400 - Bad Request)" +
            "\n\n* `32` - Item not found (HTTP: 404 - Not Found)" +
            "\n\n* `33` - Too many requests (HTTP: 429 - Too Many Requests)" +
            "\n\n* `34` - Too many updates (Too many updates over Websocket session)" +
            "\n\n* `40` - Subscription violation (HTTP: 403 - Forbidden)",
            example = "10", dataType = "integer",
            readOnly = true)
    public TyuanErrorCode getErrorCode() {
        return errorCode;
    }

    @ApiModelProperty(position = 4, value = "Timestamp", readOnly = true)
    public Date getTimestamp() {
        return timestamp;
    }
}
