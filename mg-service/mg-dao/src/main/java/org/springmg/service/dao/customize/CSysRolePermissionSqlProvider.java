package org.springmg.service.dao.customize;

import org.springmg.service.dao.mapper.SysRolePermissionSqlProvider;
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
