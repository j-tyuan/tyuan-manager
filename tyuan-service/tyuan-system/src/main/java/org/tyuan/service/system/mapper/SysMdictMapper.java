package org.tyuan.service.system.mapper;

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
import org.tyuan.service.system.model.pojo.SysMdict;
import org.tyuan.service.system.model.pojo.SysMdictExample;

public interface SysMdictMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysMdictSqlProvider.class, method="countByExample")
    long countByExample(SysMdictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @DeleteProvider(type=SysMdictSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysMdictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_mdict",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_mdict (create_date, update_date, ",
        "parent_id, name, sort, ",
        "description, create_by, ",
        "update_by, remarks, ",
        "del_flag)",
        "values (#{createDate,jdbcType=TIMESTAMP}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{parentId,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, #{sort,jdbcType=DECIMAL}, ",
        "#{description,jdbcType=VARCHAR}, #{createBy,jdbcType=VARCHAR}, ",
        "#{updateBy,jdbcType=VARCHAR}, #{remarks,jdbcType=VARCHAR}, ",
        "#{delFlag,jdbcType=BIT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysMdict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @InsertProvider(type=SysMdictSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SysMdict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysMdictSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_date", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.DECIMAL),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.BIT)
    })
    List<SysMdict> selectByExample(SysMdictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, create_date, update_date, parent_id, name, sort, description, create_by, ",
        "update_by, remarks, del_flag",
        "from sys_mdict",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_date", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.DECIMAL),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.BIT)
    })
    SysMdict selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysMdictSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysMdict record, @Param("example") SysMdictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysMdictSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysMdict record, @Param("example") SysMdictExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysMdictSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysMdict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_mdict
     *
     * @mbg.generated
     */
    @Update({
        "update sys_mdict",
        "set create_date = #{createDate,jdbcType=TIMESTAMP},",
          "update_date = #{updateDate,jdbcType=TIMESTAMP},",
          "parent_id = #{parentId,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=DECIMAL},",
          "description = #{description,jdbcType=VARCHAR},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "remarks = #{remarks,jdbcType=VARCHAR},",
          "del_flag = #{delFlag,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysMdict record);
}