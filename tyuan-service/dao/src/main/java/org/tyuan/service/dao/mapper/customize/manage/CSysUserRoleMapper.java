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
package org.tyuan.service.dao.mapper.customize.manage;

import org.tyuan.service.dao.mapper.SysUserRoleMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CSysUserRoleMapper extends SysUserRoleMapper {

    @Select({
            "select",
            "role_id",
            "from sys_user_role",
            "where user_id = #{userId,jdbcType=BIGINT}"
    })
    List<String> getRoleIdsByUid(String userId);

    /**
     * 批量绑定用户
     */
    @SelectProvider(type = CSysUserRoleSqlProvider.class, method = "batchInsert")
    void batchInsert(@Param("roleId") String roleId, @Param("userIds") List<String> userIds);
}
