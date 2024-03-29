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
import org.tyuan.service.dao.model.SysLoginLog;
import org.tyuan.service.dao.model.SysLoginLogExample;

public interface SysLoginLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysLoginLogSqlProvider.class, method="countByExample")
    long countByExample(SysLoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @DeleteProvider(type=SysLoginLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysLoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_login_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_login_log (user_id, user_no, ",
        "user_name, create_time, ",
        "login_ip, login_date, ",
        "avatar_id)",
        "values (#{userId,jdbcType=BIGINT}, #{userNo,jdbcType=VARCHAR}, ",
        "#{userName,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{loginIp,jdbcType=VARCHAR}, #{loginDate,jdbcType=TIMESTAMP}, ",
        "#{avatarId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysLoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @InsertProvider(type=SysLoginLogSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SysLoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysLoginLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_no", property="userNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="login_ip", property="loginIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_date", property="loginDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="avatar_id", property="avatarId", jdbcType=JdbcType.BIGINT)
    })
    List<SysLoginLog> selectByExample(SysLoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, user_id, user_no, user_name, create_time, login_ip, login_date, avatar_id",
        "from sys_login_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_no", property="userNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="login_ip", property="loginIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="login_date", property="loginDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="avatar_id", property="avatarId", jdbcType=JdbcType.BIGINT)
    })
    SysLoginLog selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysLoginLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysLoginLog record, @Param("example") SysLoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysLoginLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysLoginLog record, @Param("example") SysLoginLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysLoginLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysLoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbg.generated
     */
    @Update({
        "update sys_login_log",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "user_no = #{userNo,jdbcType=VARCHAR},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "login_ip = #{loginIp,jdbcType=VARCHAR},",
          "login_date = #{loginDate,jdbcType=TIMESTAMP},",
          "avatar_id = #{avatarId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysLoginLog record);
}
