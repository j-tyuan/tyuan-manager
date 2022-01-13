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
package org.tyuan.service.application.service.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class ResetPasswordRequest {

    @ApiModelProperty(position = 1, value = "The reset token to verify", example = "AAB254FF67D..")
    private String resetToken;
    @ApiModelProperty(position = 2, value = "The new password to set", example = "secret")
    private String password;
}
