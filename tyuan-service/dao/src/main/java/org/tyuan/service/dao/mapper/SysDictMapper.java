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
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.tyuan.service.data.model.SysDict;
import org.tyuan.service.data.model.SysDictExample;

public interface SysDictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysDictSqlProvider.class, method="countByExample")
    long countByExample(SysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @DeleteProvider(type=SysDictSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_dict",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_dict (id, create_time, ",
        "update_time, dict_value, ",
        "dict_label, dict_type, ",
        "dict_sort, parent_id, ",
        "create_by, update_by, ",
        "remarks, del_flag)",
        "values (#{id,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{dictValue,jdbcType=VARCHAR}, ",
        "#{dictLabel,jdbcType=VARCHAR}, #{dictType,jdbcType=VARCHAR}, ",
        "#{dictSort,jdbcType=DECIMAL}, #{parentId,jdbcType=VARCHAR}, ",
        "#{createBy,jdbcType=VARCHAR}, #{updateBy,jdbcType=VARCHAR}, ",
        "#{remarks,jdbcType=VARCHAR}, #{delFlag,jdbcType=BIT})"
    })
    int insert(SysDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @InsertProvider(type=SysDictSqlProvider.class, method="insertSelective")
    int insertSelective(SysDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysDictSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="dict_value", property="dictValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="dict_label", property="dictLabel", jdbcType=JdbcType.VARCHAR),
        @Result(column="dict_type", property="dictType", jdbcType=JdbcType.VARCHAR),
        @Result(column="dict_sort", property="dictSort", jdbcType=JdbcType.DECIMAL),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.BIT)
    })
    List<SysDict> selectByExample(SysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, create_time, update_time, dict_value, dict_label, dict_type, dict_sort, ",
        "parent_id, create_by, update_by, remarks, del_flag",
        "from sys_dict",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="dict_value", property="dictValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="dict_label", property="dictLabel", jdbcType=JdbcType.VARCHAR),
        @Result(column="dict_type", property="dictType", jdbcType=JdbcType.VARCHAR),
        @Result(column="dict_sort", property="dictSort", jdbcType=JdbcType.DECIMAL),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.BIT)
    })
    SysDict selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysDictSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysDict record, @Param("example") SysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysDictSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysDict record, @Param("example") SysDictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysDictSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict
     *
     * @mbg.generated
     */
    @Update({
        "update sys_dict",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "dict_value = #{dictValue,jdbcType=VARCHAR},",
          "dict_label = #{dictLabel,jdbcType=VARCHAR},",
          "dict_type = #{dictType,jdbcType=VARCHAR},",
          "dict_sort = #{dictSort,jdbcType=DECIMAL},",
          "parent_id = #{parentId,jdbcType=VARCHAR},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "remarks = #{remarks,jdbcType=VARCHAR},",
          "del_flag = #{delFlag,jdbcType=BIT}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SysDict record);
}