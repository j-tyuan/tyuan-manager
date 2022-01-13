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
package org.tyuan.service.data.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class UserPasswordPolicy implements Serializable {

    @ApiModelProperty(position = 1, value = "Minimum number of symbols in the password." )
    private Integer minimumLength;
    @ApiModelProperty(position = 1, value = "Minimum number of uppercase letters in the password." )
    private Integer minimumUppercaseLetters;
    @ApiModelProperty(position = 1, value = "Minimum number of lowercase letters in the password." )
    private Integer minimumLowercaseLetters;
    @ApiModelProperty(position = 1, value = "Minimum number of digits in the password." )
    private Integer minimumDigits;
    @ApiModelProperty(position = 1, value = "Minimum number of special in the password." )
    private Integer minimumSpecialCharacters;

    @ApiModelProperty(position = 1, value = "Password expiration period (days). Force expiration of the password." )
    private Integer passwordExpirationPeriodDays;
    @ApiModelProperty(position = 1, value = "Password reuse frequency (days). Disallow to use the same password for the defined number of days" )
    private Integer passwordReuseFrequencyDays;

}
