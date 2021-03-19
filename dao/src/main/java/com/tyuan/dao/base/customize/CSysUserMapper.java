package com.tyuan.dao.base.customize;

import com.tyuan.dao.base.mapper.SysUserMapper;
import com.tyuan.model.base.pojo.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

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
