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

import org.tyuan.service.dao.mapper.SysUserRoleSqlProvider;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class CSysUserRoleSqlProvider extends SysUserRoleSqlProvider {

    public String batchInsert(Map<String, Object> parameter) {
        List<Long> userIds = (List<Long>) parameter.get("userIds");
        if (CollectionUtils.isEmpty(userIds)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("insert into sys_user_role(`role_id`,`user_id`) ");
        builder.append("values");

        for (int i = 0; i < userIds.size(); i++) {
            builder.append("(")
                    .append("#{roleId},")
                    .append("#{userIds[" + i + "]}),");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
