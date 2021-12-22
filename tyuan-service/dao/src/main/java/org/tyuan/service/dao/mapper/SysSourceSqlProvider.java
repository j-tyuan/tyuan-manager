package org.tyuan.service.dao.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.tyuan.service.data.model.SysSource;
import org.tyuan.service.data.model.SysSourceExample.Criteria;
import org.tyuan.service.data.model.SysSourceExample.Criterion;
import org.tyuan.service.data.model.SysSourceExample;

public class SysSourceSqlProvider {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    public String countByExample(SysSourceExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("sys_source");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    public String deleteByExample(SysSourceExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("sys_source");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    public String insertSelective(SysSource record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sys_source");
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getParentId() != null) {
            sql.VALUES("parent_id", "#{parentId,jdbcType=BIGINT}");
        }
        
        if (record.getSourceName() != null) {
            sql.VALUES("source_name", "#{sourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceSort() != null) {
            sql.VALUES("source_sort", "#{sourceSort,jdbcType=DECIMAL}");
        }
        
        if (record.getSourceHref() != null) {
            sql.VALUES("source_href", "#{sourceHref,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceTarget() != null) {
            sql.VALUES("source_target", "#{sourceTarget,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceIcon() != null) {
            sql.VALUES("source_icon", "#{sourceIcon,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLeaf() != null) {
            sql.VALUES("is_leaf", "#{isLeaf,jdbcType=BIT}");
        }
        
        if (record.getIsShow() != null) {
            sql.VALUES("is_show", "#{isShow,jdbcType=BIT}");
        }
        
        if (record.getPermissionId() != null) {
            sql.VALUES("permission_id", "#{permissionId,jdbcType=BIGINT}");
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
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    public String selectByExample(SysSourceExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("parent_id");
        sql.SELECT("source_name");
        sql.SELECT("source_sort");
        sql.SELECT("source_href");
        sql.SELECT("source_target");
        sql.SELECT("source_icon");
        sql.SELECT("is_leaf");
        sql.SELECT("is_show");
        sql.SELECT("permission_id");
        sql.SELECT("create_by");
        sql.SELECT("update_by");
        sql.SELECT("remarks");
        sql.SELECT("del_flag");
        sql.FROM("sys_source");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        SysSource record = (SysSource) parameter.get("record");
        SysSourceExample example = (SysSourceExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("sys_source");
        
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
        
        if (record.getSourceName() != null) {
            sql.SET("source_name = #{record.sourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceSort() != null) {
            sql.SET("source_sort = #{record.sourceSort,jdbcType=DECIMAL}");
        }
        
        if (record.getSourceHref() != null) {
            sql.SET("source_href = #{record.sourceHref,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceTarget() != null) {
            sql.SET("source_target = #{record.sourceTarget,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceIcon() != null) {
            sql.SET("source_icon = #{record.sourceIcon,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLeaf() != null) {
            sql.SET("is_leaf = #{record.isLeaf,jdbcType=BIT}");
        }
        
        if (record.getIsShow() != null) {
            sql.SET("is_show = #{record.isShow,jdbcType=BIT}");
        }
        
        if (record.getPermissionId() != null) {
            sql.SET("permission_id = #{record.permissionId,jdbcType=BIGINT}");
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
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("sys_source");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("parent_id = #{record.parentId,jdbcType=BIGINT}");
        sql.SET("source_name = #{record.sourceName,jdbcType=VARCHAR}");
        sql.SET("source_sort = #{record.sourceSort,jdbcType=DECIMAL}");
        sql.SET("source_href = #{record.sourceHref,jdbcType=VARCHAR}");
        sql.SET("source_target = #{record.sourceTarget,jdbcType=VARCHAR}");
        sql.SET("source_icon = #{record.sourceIcon,jdbcType=VARCHAR}");
        sql.SET("is_leaf = #{record.isLeaf,jdbcType=BIT}");
        sql.SET("is_show = #{record.isShow,jdbcType=BIT}");
        sql.SET("permission_id = #{record.permissionId,jdbcType=BIGINT}");
        sql.SET("create_by = #{record.createBy,jdbcType=VARCHAR}");
        sql.SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        sql.SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        sql.SET("del_flag = #{record.delFlag,jdbcType=BIT}");
        
        SysSourceExample example = (SysSourceExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(SysSource record) {
        SQL sql = new SQL();
        sql.UPDATE("sys_source");
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getParentId() != null) {
            sql.SET("parent_id = #{parentId,jdbcType=BIGINT}");
        }
        
        if (record.getSourceName() != null) {
            sql.SET("source_name = #{sourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceSort() != null) {
            sql.SET("source_sort = #{sourceSort,jdbcType=DECIMAL}");
        }
        
        if (record.getSourceHref() != null) {
            sql.SET("source_href = #{sourceHref,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceTarget() != null) {
            sql.SET("source_target = #{sourceTarget,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceIcon() != null) {
            sql.SET("source_icon = #{sourceIcon,jdbcType=VARCHAR}");
        }
        
        if (record.getIsLeaf() != null) {
            sql.SET("is_leaf = #{isLeaf,jdbcType=BIT}");
        }
        
        if (record.getIsShow() != null) {
            sql.SET("is_show = #{isShow,jdbcType=BIT}");
        }
        
        if (record.getPermissionId() != null) {
            sql.SET("permission_id = #{permissionId,jdbcType=BIGINT}");
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
     * This method corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    protected void applyWhere(SQL sql, SysSourceExample example, boolean includeExamplePhrase) {
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