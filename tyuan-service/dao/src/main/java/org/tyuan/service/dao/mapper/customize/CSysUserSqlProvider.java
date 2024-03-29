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
package org.tyuan.service.dao.mapper.customize;


import org.tyuan.service.dao.mapper.SysUserSqlProvider;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class CSysUserSqlProvider extends SysUserSqlProvider {

    /**
     * 绑定角色权限
     *
     * @return
     */
    public String bindRole(Map<String, Object> parameter) {

        List<Long> roleIds = (List<Long>) parameter.get("roleIds");
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("insert into sys_user_role(`user_id`,`role_id`) ");
        builder.append("values ");
        for (int i = 0; i < roleIds.size(); i++) {
            builder.append("(")
                    .append("#{userId},")
                    .append("#{roleIds[" + i + "]}),");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
