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
package org.tyuan.service.application.exception;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.tyuan.service.data.exception.TyuanErrorCode;

public class TyuanCredentialsExpiredResponse extends TyuanErrorResponse {

    private final String resetToken;

    protected TyuanCredentialsExpiredResponse(String message, String resetToken) {
        super(message, TyuanErrorCode.CREDENTIALS_EXPIRED, HttpStatus.UNAUTHORIZED);
        this.resetToken = resetToken;
    }

    public static TyuanCredentialsExpiredResponse of(final String message, final String resetToken) {
        return new TyuanCredentialsExpiredResponse(message, resetToken);
    }

    @ApiModelProperty(position = 5, value = "Password reset token", readOnly = true)
    public String getResetToken() {
        return resetToken;
    }
}
