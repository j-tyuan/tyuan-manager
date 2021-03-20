package com.tyuan.dao.base.customize;

import com.tyuan.dao.base.mapper.SysUserRoleMapper;
import com.tyuan.dao.base.mapper.SysUserRoleSqlProvider;
import com.tyuan.model.base.pojo.SysUserRole;
import com.tyuan.model.base.pojo.SysUserRoleExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface CSysUserRoleMapper extends SysUserRoleMapper {

    @Select({
            "select",
            "role_id",
            "from sys_user_role",
            "where user_id = #{userId,jdbcType=BIGINT}"
    })
    List<Long> getRoleIdsByUid(Long userId);
}
