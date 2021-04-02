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

import org.tyuan.service.dao.mapper.SysUserMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CSysUserMapper extends SysUserMapper {

    /**
     * 绑定角色
     *
     * @param userId
     * @param roleIds
     */
    @InsertProvider(type = CSysUserSqlProvider.class, method = "bindRole")
    void bindRole(@Param("userId") Long userId,
                  @Param("roleIds") List<Long> roleIds);

    /**
     * 根据用户ID 删除数据
     *
     * @param userId
     */
    @Delete({
            "delete from sys_user_role",
            "where user_id = #{userId,jdbcType=BIGINT}"
    })
    void unBindByUserId(Long userId);

}
