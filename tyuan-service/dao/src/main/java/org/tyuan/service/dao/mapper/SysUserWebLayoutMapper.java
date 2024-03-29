package org.tyuan.service.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.tyuan.service.dao.model.SysUserWebLayout;
import org.tyuan.service.dao.model.SysUserWebLayoutExample;

public interface SysUserWebLayoutMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysUserWebLayoutSqlProvider.class, method="countByExample")
    long countByExample(SysUserWebLayoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @DeleteProvider(type=SysUserWebLayoutSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysUserWebLayoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_user_web_layout",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_user_web_layout (create_time, update_time, ",
        "user_id, layout_structure)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{userId,jdbcType=BIGINT}, #{layoutStructure,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysUserWebLayout record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @InsertProvider(type=SysUserWebLayoutSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SysUserWebLayout record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysUserWebLayoutSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="layout_structure", property="layoutStructure", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SysUserWebLayout> selectByExampleWithBLOBs(SysUserWebLayoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysUserWebLayoutSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT)
    })
    List<SysUserWebLayout> selectByExample(SysUserWebLayoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, create_time, update_time, user_id, layout_structure",
        "from sys_user_web_layout",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="layout_structure", property="layoutStructure", jdbcType=JdbcType.LONGVARCHAR)
    })
    SysUserWebLayout selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserWebLayoutSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysUserWebLayout record, @Param("example") SysUserWebLayoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserWebLayoutSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") SysUserWebLayout record, @Param("example") SysUserWebLayoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserWebLayoutSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysUserWebLayout record, @Param("example") SysUserWebLayoutExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserWebLayoutSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysUserWebLayout record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @Update({
        "update sys_user_web_layout",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "layout_structure = #{layoutStructure,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(SysUserWebLayout record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_web_layout
     *
     * @mbg.generated
     */
    @Update({
        "update sys_user_web_layout",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUserWebLayout record);
}
