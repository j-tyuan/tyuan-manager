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
import org.tyuan.service.data.model.SysUserAvatar;
import org.tyuan.service.data.model.SysUserAvatarExample;

public interface SysUserAvatarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysUserAvatarSqlProvider.class, method="countByExample")
    long countByExample(SysUserAvatarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @DeleteProvider(type=SysUserAvatarSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysUserAvatarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @Delete({
        "delete from sys_user_avatar",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @Insert({
        "insert into sys_user_avatar (create_time, update_time, ",
        "user_avatar)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{userAvatar,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SysUserAvatar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @InsertProvider(type=SysUserAvatarSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SysUserAvatar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysUserAvatarSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_avatar", property="userAvatar", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<SysUserAvatar> selectByExampleWithBLOBs(SysUserAvatarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @SelectProvider(type=SysUserAvatarSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysUserAvatar> selectByExample(SysUserAvatarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, create_time, update_time, user_avatar",
        "from sys_user_avatar",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_avatar", property="userAvatar", jdbcType=JdbcType.LONGVARCHAR)
    })
    SysUserAvatar selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserAvatarSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysUserAvatar record, @Param("example") SysUserAvatarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserAvatarSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") SysUserAvatar record, @Param("example") SysUserAvatarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserAvatarSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysUserAvatar record, @Param("example") SysUserAvatarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @UpdateProvider(type=SysUserAvatarSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysUserAvatar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @Update({
        "update sys_user_avatar",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "user_avatar = #{userAvatar,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(SysUserAvatar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_avatar
     *
     * @mbg.generated
     */
    @Update({
        "update sys_user_avatar",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUserAvatar record);
}