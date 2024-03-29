package org.tyuan.service.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.tyuan.service.dao.model.SysSource;
import org.tyuan.service.dao.model.SysSourceExample;

import java.util.List;

public interface SysSourceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysSourceSqlProvider.class, method="countByExample")
    long countByExample(SysSourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @DeleteProvider(type=SysSourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysSourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_source",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_source (create_time, update_time, ",
        "parent_id, source_name, ",
        "source_sort, source_href, ",
        "source_target, source_icon, ",
        "is_leaf, is_show, permission_id, ",
        "create_by, update_by, ",
        "remarks, del_flag)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{parentId,jdbcType=BIGINT}, #{sourceName,jdbcType=VARCHAR}, ",
        "#{sourceSort,jdbcType=DECIMAL}, #{sourceHref,jdbcType=VARCHAR}, ",
        "#{sourceTarget,jdbcType=VARCHAR}, #{sourceIcon,jdbcType=VARCHAR}, ",
        "#{isLeaf,jdbcType=BIT}, #{isShow,jdbcType=BIT}, #{permissionId,jdbcType=BIGINT}, ",
        "#{createBy,jdbcType=VARCHAR}, #{updateBy,jdbcType=VARCHAR}, ",
        "#{remarks,jdbcType=VARCHAR}, #{delFlag,jdbcType=BIT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysSource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @InsertProvider(type=SysSourceSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SysSource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysSourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="source_name", property="sourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_sort", property="sourceSort", jdbcType=JdbcType.DECIMAL),
        @Result(column="source_href", property="sourceHref", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_target", property="sourceTarget", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_icon", property="sourceIcon", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_leaf", property="isLeaf", jdbcType=JdbcType.BIT),
        @Result(column="is_show", property="isShow", jdbcType=JdbcType.BIT),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.BIT)
    })
    List<SysSource> selectByExample(SysSourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, create_time, update_time, parent_id, source_name, source_sort, source_href, ",
        "source_target, source_icon, is_leaf, is_show, permission_id, create_by, update_by, ",
        "remarks, del_flag",
        "from sys_source",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="source_name", property="sourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_sort", property="sourceSort", jdbcType=JdbcType.DECIMAL),
        @Result(column="source_href", property="sourceHref", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_target", property="sourceTarget", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_icon", property="sourceIcon", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_leaf", property="isLeaf", jdbcType=JdbcType.BIT),
        @Result(column="is_show", property="isShow", jdbcType=JdbcType.BIT),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.BIT)
    })
    SysSource selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysSourceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysSource record, @Param("example") SysSourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysSourceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysSource record, @Param("example") SysSourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysSourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysSource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    @Update({
        "update sys_source",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "parent_id = #{parentId,jdbcType=BIGINT},",
          "source_name = #{sourceName,jdbcType=VARCHAR},",
          "source_sort = #{sourceSort,jdbcType=DECIMAL},",
          "source_href = #{sourceHref,jdbcType=VARCHAR},",
          "source_target = #{sourceTarget,jdbcType=VARCHAR},",
          "source_icon = #{sourceIcon,jdbcType=VARCHAR},",
          "is_leaf = #{isLeaf,jdbcType=BIT},",
          "is_show = #{isShow,jdbcType=BIT},",
          "permission_id = #{permissionId,jdbcType=BIGINT},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "remarks = #{remarks,jdbcType=VARCHAR},",
          "del_flag = #{delFlag,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysSource record);
}
