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

import org.tyuan.service.dao.model.SysPermission;

import java.util.List;


public interface SysPermissionService extends BaseService<SysPermission> {

    /**
     * 根据ID获取数据
     *
     * @param Ids
     * @return
     */
    List<SysPermission> getByIds(List<Long> Ids);

    /**
     * 根据角色获取绑定的权限
     *
     * @param roleId
     * @return
     */
    List<SysPermission> getByRoleId(Long roleId);


    /**
     * @param id
     * @return
     */
    SysPermission getById(Long id);

    /**
     * 此列表中数据为全局数据，禁止对里面数据做任何操作。否则与导致意外情况
     * 只允许读取
     *
     * @return
     */
    List<SysPermission> getAll();
}
