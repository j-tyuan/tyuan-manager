package org.tyuan.service.dao.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.tyuan.service.dao.model.SysArea;
import org.tyuan.service.dao.model.SysAreaExample.Criteria;
import org.tyuan.service.dao.model.SysAreaExample.Criterion;
import org.tyuan.service.dao.model.SysAreaExample;

public class SysAreaSqlProvider {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbg.generated
     */
    public String countByExample(SysAreaExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sys_area");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbg.generated
     */
    public String deleteByExample(SysAreaExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sys_area");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbg.generated
     */
    public String insertSelective(SysArea record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sys_area");
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=BIGINT}");
        }
        
        if (record.getAreaName() != null) {
            sql.VALUES("area_name", "#{areaName,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaSort() != null) {
            sql.VALUES("area_sort", "#{areaSort,jdbcType=DECIMAL}");
        }
        
        if (record.getAreaCode() != null) {
            sql.VALUES("area_code", "#{areaCode,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaType() != null) {
            sql.VALUES("area_type", "#{areaType,jdbcType=BIT}");
        }
        
        if (record.getCreateBy() != null) {
            sql.VALUES("create_by", "#{createBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.VALUES("update_by", "#{updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getRemarks() != null) {
            sql.VALUES("remarks", "#{remarks,jdbcType=VARCHAR}");
        }
        
        if (record.getDelFlag() != null) {
            sql.VALUES("del_flag", "#{delFlag,jdbcType=BIT}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbg.generated
     */
    public String selectByExample(SysAreaExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("parent_id");
        sql.SELECT("area_name");
        sql.SELECT("area_sort");
        sql.SELECT("area_code");
        sql.SELECT("area_type");
        sql.SELECT("create_by");
        sql.SELECT("update_by");
        sql.SELECT("remarks");
        sql.SELECT("del_flag");
        sql.FROM("sys_area");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        SysArea record = (SysArea) parameter.get("record");
        SysAreaExample example = (SysAreaExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sys_area");
        
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
        
        if (record.getAreaName() != null) {
            sql.SET("area_name = #{record.areaName,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaSort() != null) {
            sql.SET("area_sort = #{record.areaSort,jdbcType=DECIMAL}");
        }
        
        if (record.getAreaCode() != null) {
            sql.SET("area_code = #{record.areaCode,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaType() != null) {
            sql.SET("area_type = #{record.areaType,jdbcType=BIT}");
        }
        
        if (record.getCreateBy() != null) {
            sql.SET("create_by = #{record.createBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getRemarks() != null) {
            sql.SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        }
        
        if (record.getDelFlag() != null) {
            sql.SET("del_flag = #{record.delFlag,jdbcType=BIT}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sys_area");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("parent_id = #{record.parentId,jdbcType=BIGINT}");
        sql.SET("area_name = #{record.areaName,jdbcType=VARCHAR}");
        sql.SET("area_sort = #{record.areaSort,jdbcType=DECIMAL}");
        sql.SET("area_code = #{record.areaCode,jdbcType=VARCHAR}");
        sql.SET("area_type = #{record.areaType,jdbcType=BIT}");
        sql.SET("create_by = #{record.createBy,jdbcType=VARCHAR}");
        sql.SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        sql.SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        sql.SET("del_flag = #{record.delFlag,jdbcType=BIT}");
        
        SysAreaExample example = (SysAreaExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(SysArea record) {
        SQL sql = new SQL();
        sql.UPDATE("sys_area");
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=BIGINT}");
        }
        
        if (record.getAreaName() != null) {
            sql.SET("area_name = #{areaName,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaSort() != null) {
            sql.SET("area_sort = #{areaSort,jdbcType=DECIMAL}");
        }
        
        if (record.getAreaCode() != null) {
            sql.SET("area_code = #{areaCode,jdbcType=VARCHAR}");
        }
        
        if (record.getAreaType() != null) {
            sql.SET("area_type = #{areaType,jdbcType=BIT}");
        }
        
        if (record.getCreateBy() != null) {
            sql.SET("create_by = #{createBy,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateBy() != null) {
            sql.SET("update_by = #{updateBy,jdbcType=VARCHAR}");
        }
        
        if (record.getRemarks() != null) {
            sql.SET("remarks = #{remarks,jdbcType=VARCHAR}");
        }
        
        if (record.getDelFlag() != null) {
            sql.SET("del_flag = #{delFlag,jdbcType=BIT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, SysAreaExample example, boolean includeExamplePhrase) {
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