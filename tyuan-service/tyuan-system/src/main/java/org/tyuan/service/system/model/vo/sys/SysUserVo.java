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
package org.tyuan.service.system.model.vo.sys;

import lombok.Getter;
import lombok.Setter;
import org.tyuan.service.system.model.pojo.SysUser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/7/1 9:59
 */
@Getter
@Setter
public class SysUserVo extends SysUser implements Serializable {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.account
     *
     * @mbggenerated
     */
    @NotEmpty(message = "账号不允许为空")
    private String userAccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.name
     *
     * @mbggenerated
     */
    @NotEmpty(message = "用户名称不允许为空")
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.email
     *
     * @mbggenerated
     */
    @Email(message = "邮箱地址错误")
    private String userEmail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.phone
     *
     * @mbggenerated
     */
    private String userPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user.mobile
     *
     * @mbggenerated
     */
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "移动号码格式错误")
    private String mobile;


    // 角色ID
    private List<Long> roleIds;

}
