package org.tyuan.service.dao.customize;

import org.tyuan.service.dao.mapper.SysRolePermissionMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface CSysRolePermissionMapper extends SysRolePermissionMapper {


    /**
     * 批量绑定数据
     *
     * @param roleId
     * @param permissionIds
     */
    @SelectProvider(type = CSysRolePermissionSqlProvider.class, method = "batchBind")
    void batchBind(@Param("roleId") Long roleId,
                   @Param("permissionIds") List<Long> permissionIds);

    /**
     * 根据角色ID 删除数据
     *
     * @param roleId
     */
    @Delete({
            "delete from sys_role_permission",
            "where role_id = #{roleId,jdbcType=BIGINT}"
    })
    void unBindByRoleId(Long roleId);
}
