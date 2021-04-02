package org.springmg.service.dao.customize;

import org.springmg.service.dao.mapper.SysUserMapper;
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
