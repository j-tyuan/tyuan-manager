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
package org.tyuan.service.application.cache;

import org.tyuan.service.dao.model.SysPermission;
import org.tyuan.service.dao.model.custom.COrganizationInstitution;
import org.tyuan.service.dao.model.custom.CSysSource;
import org.tyuan.service.data.cache.DataCache;

import java.util.List;

public class LocalCache {

    /**
     * 菜单缓存数据
     */
    public static DataCache<List<CSysSource>> SYS_SOURCE;

    /**
     * 权限缓存数据
     */
    public static DataCache<List<SysPermission>> SYS_PERMISSION;

    /**
     * 机构缓存数据
     */
    public static DataCache<List<COrganizationInstitution>> SYS_INSTITUTION = new DataCache<>();

}
