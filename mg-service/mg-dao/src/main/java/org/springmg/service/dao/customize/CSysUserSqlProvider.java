package org.springmg.service.dao.customize;


import org.springmg.service.dao.mapper.SysUserSqlProvider;
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
