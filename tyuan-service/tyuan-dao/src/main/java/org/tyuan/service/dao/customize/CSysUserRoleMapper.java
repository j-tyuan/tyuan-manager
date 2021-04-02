package org.tyuan.service.dao.customize;

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
    List<Long> getRoleIdsByUid(Long userId);

    /**
     * 批量绑定用户
     */
    @SelectProvider(type = CSysUserRoleSqlProvider.class, method = "batchInsert")
    void batchInsert(@Param("roleId") Long roleId, @Param("userIds") List<Long> userIds);
}
