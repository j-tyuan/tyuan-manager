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
