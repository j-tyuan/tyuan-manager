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
import org.tyuan.service.data.model.SysParam;
import org.tyuan.service.data.model.SysParamExample;

public interface SysParamMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysParamSqlProvider.class, method="countByExample")
    long countByExample(SysParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @DeleteProvider(type=SysParamSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_param",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_param (create_time, update_time, ",
        "param_name, param_key, ",
        "is_sys, create_by, update_by, ",
        "remarks, param_val)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{paramName,jdbcType=VARCHAR}, #{paramKey,jdbcType=VARCHAR}, ",
        "#{isSys,jdbcType=BIT}, #{createBy,jdbcType=VARCHAR}, #{updateBy,jdbcType=VARCHAR}, ",
        "#{remarks,jdbcType=VARCHAR}, #{paramVal,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @InsertProvider(type=SysParamSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SysParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysParamSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="param_name", property="paramName", jdbcType=JdbcType.VARCHAR),
        @Result(column="param_key", property="paramKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_sys", property="isSys", jdbcType=JdbcType.BIT),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="param_val", property="paramVal", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SysParam> selectByExampleWithBLOBs(SysParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysParamSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="param_name", property="paramName", jdbcType=JdbcType.VARCHAR),
        @Result(column="param_key", property="paramKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_sys", property="isSys", jdbcType=JdbcType.BIT),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    List<SysParam> selectByExample(SysParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, create_time, update_time, param_name, param_key, is_sys, create_by, update_by, ",
        "remarks, param_val",
        "from sys_param",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="param_name", property="paramName", jdbcType=JdbcType.VARCHAR),
        @Result(column="param_key", property="paramKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_sys", property="isSys", jdbcType=JdbcType.BIT),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="param_val", property="paramVal", jdbcType=JdbcType.LONGVARCHAR)
    })
    SysParam selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysParamSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysParam record, @Param("example") SysParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysParamSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") SysParam record, @Param("example") SysParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysParamSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysParam record, @Param("example") SysParamExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysParamSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @Update({
        "update sys_param",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "param_name = #{paramName,jdbcType=VARCHAR},",
          "param_key = #{paramKey,jdbcType=VARCHAR},",
          "is_sys = #{isSys,jdbcType=BIT},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "remarks = #{remarks,jdbcType=VARCHAR},",
          "param_val = #{paramVal,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(SysParam record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_param
     *
     * @mbg.generated
     */
    @Update({
        "update sys_param",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "param_name = #{paramName,jdbcType=VARCHAR},",
          "param_key = #{paramKey,jdbcType=VARCHAR},",
          "is_sys = #{isSys,jdbcType=BIT},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "remarks = #{remarks,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysParam record);
}