package org.tyuan.service.system.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.tyuan.service.system.model.pojo.OrganizationInstitution;
import org.tyuan.service.system.model.pojo.OrganizationInstitutionExample.Criteria;
import org.tyuan.service.system.model.pojo.OrganizationInstitutionExample.Criterion;
import org.tyuan.service.system.model.pojo.OrganizationInstitutionExample;

public class OrganizationInstitutionSqlProvider {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public String countByExample(OrganizationInstitutionExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("organization_institution");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public String deleteByExample(OrganizationInstitutionExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("organization_institution");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public String insertSelective(OrganizationInstitution record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("organization_institution");
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=BIGINT}");
        }
        
        if (record.getInstCode() != null) {
            sql.VALUES("inst_code", "#{instCode,jdbcType=VARCHAR}");
        }
        
        if (record.getInstName() != null) {
            sql.VALUES("inst_name", "#{instName,jdbcType=VARCHAR}");
        }
        
        if (record.getInstType() != null) {
            sql.VALUES("inst_type", "#{instType,jdbcType=INTEGER}");
        }
        
        if (record.getOwnerUserId() != null) {
            sql.VALUES("owner_user_id", "#{ownerUserId,jdbcType=BIGINT}");
        }
        
        if (record.getInstSort() != null) {
            sql.VALUES("inst_sort", "#{instSort,jdbcType=INTEGER}");
        }
        
        if (record.getInstStatus() != null) {
            sql.VALUES("inst_status", "#{instStatus,jdbcType=INTEGER}");
        }
        
        if (record.getRemarks() != null) {
            sql.VALUES("remarks", "#{remarks,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public String selectByExample(OrganizationInstitutionExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("parent_id");
        sql.SELECT("inst_code");
        sql.SELECT("inst_name");
        sql.SELECT("inst_type");
        sql.SELECT("owner_user_id");
        sql.SELECT("inst_sort");
        sql.SELECT("inst_status");
        sql.SELECT("remarks");
        sql.FROM("organization_institution");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        OrganizationInstitution record = (OrganizationInstitution) parameter.get("record");
        OrganizationInstitutionExample example = (OrganizationInstitutionExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("organization_institution");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{record.parentId,jdbcType=BIGINT}");
        }
        
        if (record.getInstCode() != null) {
            sql.SET("inst_code = #{record.instCode,jdbcType=VARCHAR}");
        }
        
        if (record.getInstName() != null) {
            sql.SET("inst_name = #{record.instName,jdbcType=VARCHAR}");
        }
        
        if (record.getInstType() != null) {
            sql.SET("inst_type = #{record.instType,jdbcType=INTEGER}");
        }
        
        if (record.getOwnerUserId() != null) {
            sql.SET("owner_user_id = #{record.ownerUserId,jdbcType=BIGINT}");
        }
        
        if (record.getInstSort() != null) {
            sql.SET("inst_sort = #{record.instSort,jdbcType=INTEGER}");
        }
        
        if (record.getInstStatus() != null) {
            sql.SET("inst_status = #{record.instStatus,jdbcType=INTEGER}");
        }
        
        if (record.getRemarks() != null) {
            sql.SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("organization_institution");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("parent_id = #{record.parentId,jdbcType=BIGINT}");
        sql.SET("inst_code = #{record.instCode,jdbcType=VARCHAR}");
        sql.SET("inst_name = #{record.instName,jdbcType=VARCHAR}");
        sql.SET("inst_type = #{record.instType,jdbcType=INTEGER}");
        sql.SET("owner_user_id = #{record.ownerUserId,jdbcType=BIGINT}");
        sql.SET("inst_sort = #{record.instSort,jdbcType=INTEGER}");
        sql.SET("inst_status = #{record.instStatus,jdbcType=INTEGER}");
        sql.SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        
        OrganizationInstitutionExample example = (OrganizationInstitutionExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(OrganizationInstitution record) {
        SQL sql = new SQL();
        sql.UPDATE("organization_institution");
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=BIGINT}");
        }
        
        if (record.getInstCode() != null) {
            sql.SET("inst_code = #{instCode,jdbcType=VARCHAR}");
        }
        
        if (record.getInstName() != null) {
            sql.SET("inst_name = #{instName,jdbcType=VARCHAR}");
        }
        
        if (record.getInstType() != null) {
            sql.SET("inst_type = #{instType,jdbcType=INTEGER}");
        }
        
        if (record.getOwnerUserId() != null) {
            sql.SET("owner_user_id = #{ownerUserId,jdbcType=BIGINT}");
        }
        
        if (record.getInstSort() != null) {
            sql.SET("inst_sort = #{instSort,jdbcType=INTEGER}");
        }
        
        if (record.getInstStatus() != null) {
            sql.SET("inst_status = #{instStatus,jdbcType=INTEGER}");
        }
        
        if (record.getRemarks() != null) {
            sql.SET("remarks = #{remarks,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, OrganizationInstitutionExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}