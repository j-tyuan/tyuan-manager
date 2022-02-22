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
import org.tyuan.service.data.model.OAuth2Domain;
import org.tyuan.service.data.model.OAuth2DomainExample;

public interface OAuth2DomainMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @SelectProvider(type=OAuth2DomainSqlProvider.class, method="countByExample")
    long countByExample(OAuth2DomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @DeleteProvider(type=OAuth2DomainSqlProvider.class, method="deleteByExample")
    int deleteByExample(OAuth2DomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @Delete({
        "delete from oauth2_domain",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @Insert({
        "insert into oauth2_domain (id, create_time, ",
        "update_time, oauth2_params_id, ",
        "domain_name, domain_scheme)",
        "values (#{id,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{oauth2ParamsId,jdbcType=VARCHAR}, ",
        "#{domainName,jdbcType=VARCHAR}, #{domainScheme,jdbcType=VARCHAR})"
    })
    int insert(OAuth2Domain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @InsertProvider(type=OAuth2DomainSqlProvider.class, method="insertSelective")
    int insertSelective(OAuth2Domain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @SelectProvider(type=OAuth2DomainSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="oauth2_params_id", property="oauth2ParamsId", jdbcType=JdbcType.VARCHAR),
        @Result(column="domain_name", property="domainName", jdbcType=JdbcType.VARCHAR),
        @Result(column="domain_scheme", property="domainScheme", jdbcType=JdbcType.VARCHAR)
    })
    List<OAuth2Domain> selectByExample(OAuth2DomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, create_time, update_time, oauth2_params_id, domain_name, domain_scheme",
        "from oauth2_domain",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="oauth2_params_id", property="oauth2ParamsId", jdbcType=JdbcType.VARCHAR),
        @Result(column="domain_name", property="domainName", jdbcType=JdbcType.VARCHAR),
        @Result(column="domain_scheme", property="domainScheme", jdbcType=JdbcType.VARCHAR)
    })
    OAuth2Domain selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @UpdateProvider(type=OAuth2DomainSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OAuth2Domain record, @Param("example") OAuth2DomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @UpdateProvider(type=OAuth2DomainSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OAuth2Domain record, @Param("example") OAuth2DomainExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @UpdateProvider(type=OAuth2DomainSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OAuth2Domain record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    @Update({
        "update oauth2_domain",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "oauth2_params_id = #{oauth2ParamsId,jdbcType=VARCHAR},",
          "domain_name = #{domainName,jdbcType=VARCHAR},",
          "domain_scheme = #{domainScheme,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(OAuth2Domain record);
}