/**
 * @ClassName RoleService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
 */
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
package org.tyuan.service.application.service;

import com.github.pagehelper.PageInfo;
import org.tyuan.service.data.model.SysRole;
import org.tyuan.service.data.vo.sys.RoleUserTableParamsVo;
import org.tyuan.service.data.vo.sys.SysRoleUserVo;

import java.util.List;

public interface SysRoleUserService {


    /**
     * 获取用户
     *
     * @return
     */
    PageInfo getUser(RoleUserTableParamsVo paramsVo);

    /**
     * 绑定用户
     *
     * @return
     */
    void bindUser(SysRoleUserVo sysRoleUserVo);

    /**
     * 解绑用户
     */
    void unbindUser(SysRoleUserVo sysRoleUserVo);

    /**
     * 根据用户ID 获取全部的角色信息
     *
     * @param uid
     * @return
     */
    List<Long> getRoleIdsByUserId(Long uid);

    /**
     * 根据用户ID 获取全部的角色信息
     *
     * @param uid
     * @return
     */
    List<SysRole> getRoleByUserId(Long uid);
}
