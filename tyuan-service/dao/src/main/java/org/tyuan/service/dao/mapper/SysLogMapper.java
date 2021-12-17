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
import org.tyuan.service.dao.model.SysLog;
import org.tyuan.service.dao.model.SysLogExample;
import org.tyuan.service.dao.model.SysLogWithBLOBs;

public interface SysLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysLogSqlProvider.class, method="countByExample")
    long countByExample(SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @DeleteProvider(type=SysLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_log (request_id, create_time, ",
        "log_type, log_title, ",
        "user_id, user_name, ",
        "remote_addr, user_agent, ",
        "request_uri, method, ",
        "duration, params, ",
        "exception)",
        "values (#{requestId,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{logType,jdbcType=INTEGER}, #{logTitle,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=BIGINT}, #{userName,jdbcType=VARCHAR}, ",
        "#{remoteAddr,jdbcType=VARCHAR}, #{userAgent,jdbcType=VARCHAR}, ",
        "#{requestUri,jdbcType=VARCHAR}, #{method,jdbcType=VARCHAR}, ",
        "#{duration,jdbcType=INTEGER}, #{params,jdbcType=LONGVARCHAR}, ",
        "#{exception,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @InsertProvider(type=SysLogSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SysLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysLogSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="request_id", property="requestId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="log_type", property="logType", jdbcType=JdbcType.INTEGER),
        @Result(column="log_title", property="logTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="remote_addr", property="remoteAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_agent", property="userAgent", jdbcType=JdbcType.VARCHAR),
        @Result(column="request_uri", property="requestUri", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="duration", property="duration", jdbcType=JdbcType.INTEGER),
        @Result(column="params", property="params", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="exception", property="exception", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SysLogWithBLOBs> selectByExampleWithBLOBs(SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="request_id", property="requestId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="log_type", property="logType", jdbcType=JdbcType.INTEGER),
        @Result(column="log_title", property="logTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="remote_addr", property="remoteAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_agent", property="userAgent", jdbcType=JdbcType.VARCHAR),
        @Result(column="request_uri", property="requestUri", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="duration", property="duration", jdbcType=JdbcType.INTEGER)
    })
    List<SysLog> selectByExample(SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, request_id, create_time, log_type, log_title, user_id, user_name, remote_addr, ",
        "user_agent, request_uri, method, duration, params, exception",
        "from sys_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="request_id", property="requestId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="log_type", property="logType", jdbcType=JdbcType.INTEGER),
        @Result(column="log_title", property="logTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="remote_addr", property="remoteAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_agent", property="userAgent", jdbcType=JdbcType.VARCHAR),
        @Result(column="request_uri", property="requestUri", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="duration", property="duration", jdbcType=JdbcType.INTEGER),
        @Result(column="params", property="params", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="exception", property="exception", jdbcType=JdbcType.LONGVARCHAR)
    })
    SysLogWithBLOBs selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysLogWithBLOBs record, @Param("example") SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysLogSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") SysLogWithBLOBs record, @Param("example") SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @Update({
        "update sys_log",
        "set request_id = #{requestId,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "log_type = #{logType,jdbcType=INTEGER},",
          "log_title = #{logTitle,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "remote_addr = #{remoteAddr,jdbcType=VARCHAR},",
          "user_agent = #{userAgent,jdbcType=VARCHAR},",
          "request_uri = #{requestUri,jdbcType=VARCHAR},",
          "method = #{method,jdbcType=VARCHAR},",
          "duration = #{duration,jdbcType=INTEGER},",
          "params = #{params,jdbcType=LONGVARCHAR},",
          "exception = #{exception,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(SysLogWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_log
     *
     * @mbg.generated
     */
    @Update({
        "update sys_log",
        "set request_id = #{requestId,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "log_type = #{logType,jdbcType=INTEGER},",
          "log_title = #{logTitle,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "remote_addr = #{remoteAddr,jdbcType=VARCHAR},",
          "user_agent = #{userAgent,jdbcType=VARCHAR},",
          "request_uri = #{requestUri,jdbcType=VARCHAR},",
          "method = #{method,jdbcType=VARCHAR},",
          "duration = #{duration,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysLog record);
}