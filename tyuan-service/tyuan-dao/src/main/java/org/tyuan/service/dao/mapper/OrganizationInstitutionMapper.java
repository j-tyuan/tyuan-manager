/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.dao.mapper;

import org.tyuan.service.model.pojo.OrganizationInstitution;
import org.tyuan.service.model.pojo.OrganizationInstitutionExample;
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

public interface OrganizationInstitutionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @SelectProvider(type=OrganizationInstitutionSqlProvider.class, method="countByExample")
    long countByExample(OrganizationInstitutionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @DeleteProvider(type=OrganizationInstitutionSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrganizationInstitutionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @Delete({
        "delete from organization_institution",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @Insert({
        "insert into organization_institution (parent_id, inst_code, ",
        "inst_name, inst_type, ",
        "owner_user_id, inst_sort, ",
        "inst_status, inst_desc, ",
        "create_date, update_date)",
        "values (#{parentId,jdbcType=BIGINT}, #{instCode,jdbcType=VARCHAR}, ",
        "#{instName,jdbcType=VARCHAR}, #{instType,jdbcType=INTEGER}, ",
        "#{ownerUserId,jdbcType=BIGINT}, #{instSort,jdbcType=INTEGER}, ",
        "#{instStatus,jdbcType=INTEGER}, #{instDesc,jdbcType=VARCHAR}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{updateDate,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(OrganizationInstitution record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @InsertProvider(type=OrganizationInstitutionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(OrganizationInstitution record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @SelectProvider(type=OrganizationInstitutionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="inst_code", property="instCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="inst_name", property="instName", jdbcType=JdbcType.VARCHAR),
        @Result(column="inst_type", property="instType", jdbcType=JdbcType.INTEGER),
        @Result(column="owner_user_id", property="ownerUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="inst_sort", property="instSort", jdbcType=JdbcType.INTEGER),
        @Result(column="inst_status", property="instStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="inst_desc", property="instDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_date", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<OrganizationInstitution> selectByExample(OrganizationInstitutionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, parent_id, inst_code, inst_name, inst_type, owner_user_id, inst_sort, inst_status, ",
        "inst_desc, create_date, update_date",
        "from organization_institution",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="inst_code", property="instCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="inst_name", property="instName", jdbcType=JdbcType.VARCHAR),
        @Result(column="inst_type", property="instType", jdbcType=JdbcType.INTEGER),
        @Result(column="owner_user_id", property="ownerUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="inst_sort", property="instSort", jdbcType=JdbcType.INTEGER),
        @Result(column="inst_status", property="instStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="inst_desc", property="instDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_date", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    OrganizationInstitution selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @UpdateProvider(type=OrganizationInstitutionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrganizationInstitution record, @Param("example") OrganizationInstitutionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @UpdateProvider(type=OrganizationInstitutionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OrganizationInstitution record, @Param("example") OrganizationInstitutionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @UpdateProvider(type=OrganizationInstitutionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrganizationInstitution record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    @Update({
        "update organization_institution",
        "set parent_id = #{parentId,jdbcType=BIGINT},",
          "inst_code = #{instCode,jdbcType=VARCHAR},",
          "inst_name = #{instName,jdbcType=VARCHAR},",
          "inst_type = #{instType,jdbcType=INTEGER},",
          "owner_user_id = #{ownerUserId,jdbcType=BIGINT},",
          "inst_sort = #{instSort,jdbcType=INTEGER},",
          "inst_status = #{instStatus,jdbcType=INTEGER},",
          "inst_desc = #{instDesc,jdbcType=VARCHAR},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "update_date = #{updateDate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(OrganizationInstitution record);
}
