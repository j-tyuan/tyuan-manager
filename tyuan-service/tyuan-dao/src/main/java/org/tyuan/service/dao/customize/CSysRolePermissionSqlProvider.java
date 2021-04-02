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

import org.tyuan.service.dao.mapper.SysRolePermissionSqlProvider;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class CSysRolePermissionSqlProvider extends SysRolePermissionSqlProvider {

    public String batchBind(Map<String, Object> parameter) {

        List<Long> permissionIds = (List<Long>) parameter.get("permissionIds");
        if (CollectionUtils.isEmpty(permissionIds)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("insert into sys_role_permission(`role_id`,`permission_id`) ");
        builder.append("values");

        for (int i = 0; i < permissionIds.size(); i++) {
            builder.append("(")
                    .append("#{roleId},")
                    .append("#{permissionIds[" + i + "]}),");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
