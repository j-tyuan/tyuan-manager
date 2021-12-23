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
import org.tyuan.service.data.model.SysUserCredentials;
import org.tyuan.service.data.model.SysUserCredentialsExample;

public interface SysUserCredentialsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysUserCredentialsSqlProvider.class, method="countByExample")
    long countByExample(SysUserCredentialsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @DeleteProvider(type=SysUserCredentialsSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysUserCredentialsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_user_credentials",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_user_credentials (create_time, update_time, ",
        "user_id, activate_token, ",
        "enabled, password, reset_token)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{userId,jdbcType=BIGINT}, #{activateToken,jdbcType=VARCHAR}, ",
        "#{enabled,jdbcType=BIT}, #{password,jdbcType=VARCHAR}, #{resetToken,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysUserCredentials record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @InsertProvider(type=SysUserCredentialsSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SysUserCredentials record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysUserCredentialsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="activate_token", property="activateToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="reset_token", property="resetToken", jdbcType=JdbcType.VARCHAR)
    })
    List<SysUserCredentials> selectByExample(SysUserCredentialsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, create_time, update_time, user_id, activate_token, enabled, password, reset_token",
        "from sys_user_credentials",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="activate_token", property="activateToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="reset_token", property="resetToken", jdbcType=JdbcType.VARCHAR)
    })
    SysUserCredentials selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserCredentialsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysUserCredentials record, @Param("example") SysUserCredentialsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserCredentialsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysUserCredentials record, @Param("example") SysUserCredentialsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserCredentialsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysUserCredentials record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_credentials
     *
     * @mbg.generated
     */
    @Update({
        "update sys_user_credentials",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "activate_token = #{activateToken,jdbcType=VARCHAR},",
          "enabled = #{enabled,jdbcType=BIT},",
          "password = #{password,jdbcType=VARCHAR},",
          "reset_token = #{resetToken,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUserCredentials record);
}