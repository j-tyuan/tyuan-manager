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
package org.tyuan.service.dao.customize;

import org.tyuan.service.dao.mapper.SysRolePermissionMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CSysRolePermissionMapper extends SysRolePermissionMapper {


    /**
     * 批量绑定数据
     *
     * @param roleId
     * @param permissionIds
     */
    @SelectProvider(type = CSysRolePermissionSqlProvider.class, method = "batchBind")
    void batchBind(@Param("roleId") Long roleId,
                   @Param("permissionIds") List<Long> permissionIds);

    /**
     * 根据角色ID 删除数据
     *
     * @param roleId
     */
    @Delete({
            "delete from sys_role_permission",
            "where role_id = #{roleId,jdbcType=BIGINT}"
    })
    void unBindByRoleId(Long roleId);
}
