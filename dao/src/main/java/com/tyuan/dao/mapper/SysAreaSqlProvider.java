package com.tyuan.dao.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.tyuan.model.pojo.SysArea;
import com.tyuan.model.pojo.SysAreaExample.Criteria;
import com.tyuan.model.pojo.SysAreaExample.Criterion;
import com.tyuan.model.pojo.SysAreaExample;
import java.util.List;
import java.util.Map;

public class SysAreaSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbggenerated
     */
    public String countByExample(SysAreaExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("sys_area");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbggenerated
     */
    public String deleteByExample(SysAreaExample example) {
        BEGIN();
        DELETE_FROM("sys_area");
        applyWhere(example, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbggenerated
     */
    public String insertSelective(SysArea record) {
        BEGIN();
        INSERT_INTO("sys_area");

        if (record.getCreateDate() != null) {
            VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateDate() != null) {
            VALUES("update_date", "#{updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getParentId() != null) {
            VALUES("parent_id", "#{parentId,jdbcType=BIGINT}");
        }

        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getSort() != null) {
            VALUES("sort", "#{sort,jdbcType=DECIMAL}");
        }

        if (record.getCode() != null) {
            VALUES("code", "#{code,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            VALUES("type", "#{type,jdbcType=BIT}");
        }

        if (record.getCreateBy() != null) {
            VALUES("create_by", "#{createBy,jdbcType=VARCHAR}");
        }

        if (record.getUpdateBy() != null) {
            VALUES("update_by", "#{updateBy,jdbcType=VARCHAR}");
        }

        if (record.getRemarks() != null) {
            VALUES("remarks", "#{remarks,jdbcType=VARCHAR}");
        }

        if (record.getDelFlag() != null) {
            VALUES("del_flag", "#{delFlag,jdbcType=BIT}");
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbggenerated
     */
    public String selectByExample(SysAreaExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("create_date");
        SELECT("update_date");
        SELECT("parent_id");
        SELECT("name");
        SELECT("sort");
        SELECT("code");
        SELECT("type");
        SELECT("create_by");
        SELECT("update_by");
        SELECT("remarks");
        SELECT("del_flag");
        FROM("sys_area");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbggenerated
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        SysArea record = (SysArea) parameter.get("record");
        SysAreaExample example = (SysAreaExample) parameter.get("example");

        BEGIN();
        UPDATE("sys_area");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=BIGINT}");
        }

        if (record.getCreateDate() != null) {
            SET("create_date = #{record.createDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateDate() != null) {
            SET("update_date = #{record.updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getParentId() != null) {
            SET("parent_id = #{record.parentId,jdbcType=BIGINT}");
        }

        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }

        if (record.getSort() != null) {
            SET("sort = #{record.sort,jdbcType=DECIMAL}");
        }

        if (record.getCode() != null) {
            SET("code = #{record.code,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            SET("type = #{record.type,jdbcType=BIT}");
        }

        if (record.getCreateBy() != null) {
            SET("create_by = #{record.createBy,jdbcType=VARCHAR}");
        }

        if (record.getUpdateBy() != null) {
            SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        }

        if (record.getRemarks() != null) {
            SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        }

        if (record.getDelFlag() != null) {
            SET("del_flag = #{record.delFlag,jdbcType=BIT}");
        }

        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbggenerated
     */
    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("sys_area");

        SET("id = #{record.id,jdbcType=BIGINT}");
        SET("create_date = #{record.createDate,jdbcType=TIMESTAMP}");
        SET("update_date = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("parent_id = #{record.parentId,jdbcType=BIGINT}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("sort = #{record.sort,jdbcType=DECIMAL}");
        SET("code = #{record.code,jdbcType=VARCHAR}");
        SET("type = #{record.type,jdbcType=BIT}");
        SET("create_by = #{record.createBy,jdbcType=VARCHAR}");
        SET("update_by = #{record.updateBy,jdbcType=VARCHAR}");
        SET("remarks = #{record.remarks,jdbcType=VARCHAR}");
        SET("del_flag = #{record.delFlag,jdbcType=BIT}");

        SysAreaExample example = (SysAreaExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbggenerated
     */
    public String updateByPrimaryKeySelective(SysArea record) {
        BEGIN();
        UPDATE("sys_area");

        if (record.getCreateDate() != null) {
            SET("create_date = #{createDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateDate() != null) {
            SET("update_date = #{updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getParentId() != null) {
            SET("parent_id = #{parentId,jdbcType=BIGINT}");
        }

        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getSort() != null) {
            SET("sort = #{sort,jdbcType=DECIMAL}");
        }

        if (record.getCode() != null) {
            SET("code = #{code,jdbcType=VARCHAR}");
        }

        if (record.getType() != null) {
            SET("type = #{type,jdbcType=BIT}");
        }

        if (record.getCreateBy() != null) {
            SET("create_by = #{createBy,jdbcType=VARCHAR}");
        }

        if (record.getUpdateBy() != null) {
            SET("update_by = #{updateBy,jdbcType=VARCHAR}");
        }

        if (record.getRemarks() != null) {
            SET("remarks = #{remarks,jdbcType=VARCHAR}");
        }

        if (record.getDelFlag() != null) {
            SET("del_flag = #{delFlag,jdbcType=BIT}");
        }

        WHERE("id = #{id,jdbcType=BIGINT}");

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_area
     *
     * @mbggenerated
     */
    protected void applyWhere(SysAreaExample example, boolean includeExamplePhrase) {
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
            WHERE(sb.toString());
        }
    }
}
