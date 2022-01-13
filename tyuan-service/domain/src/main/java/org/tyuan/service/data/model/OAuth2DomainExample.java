package org.tyuan.service.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OAuth2DomainExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public OAuth2DomainExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdIsNull() {
            addCriterion("oauth2_params_id is null");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdIsNotNull() {
            addCriterion("oauth2_params_id is not null");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdEqualTo(String value) {
            addCriterion("oauth2_params_id =", value, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdNotEqualTo(String value) {
            addCriterion("oauth2_params_id <>", value, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdGreaterThan(String value) {
            addCriterion("oauth2_params_id >", value, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdGreaterThanOrEqualTo(String value) {
            addCriterion("oauth2_params_id >=", value, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdLessThan(String value) {
            addCriterion("oauth2_params_id <", value, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdLessThanOrEqualTo(String value) {
            addCriterion("oauth2_params_id <=", value, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdLike(String value) {
            addCriterion("oauth2_params_id like", value, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdNotLike(String value) {
            addCriterion("oauth2_params_id not like", value, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdIn(List<String> values) {
            addCriterion("oauth2_params_id in", values, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdNotIn(List<String> values) {
            addCriterion("oauth2_params_id not in", values, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdBetween(String value1, String value2) {
            addCriterion("oauth2_params_id between", value1, value2, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andOauth2ParamsIdNotBetween(String value1, String value2) {
            addCriterion("oauth2_params_id not between", value1, value2, "oauth2ParamsId");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNull() {
            addCriterion("domain_name is null");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNotNull() {
            addCriterion("domain_name is not null");
            return (Criteria) this;
        }

        public Criteria andDomainNameEqualTo(String value) {
            addCriterion("domain_name =", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotEqualTo(String value) {
            addCriterion("domain_name <>", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThan(String value) {
            addCriterion("domain_name >", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThanOrEqualTo(String value) {
            addCriterion("domain_name >=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThan(String value) {
            addCriterion("domain_name <", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThanOrEqualTo(String value) {
            addCriterion("domain_name <=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLike(String value) {
            addCriterion("domain_name like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotLike(String value) {
            addCriterion("domain_name not like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameIn(List<String> values) {
            addCriterion("domain_name in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotIn(List<String> values) {
            addCriterion("domain_name not in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameBetween(String value1, String value2) {
            addCriterion("domain_name between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotBetween(String value1, String value2) {
            addCriterion("domain_name not between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeIsNull() {
            addCriterion("domain_scheme is null");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeIsNotNull() {
            addCriterion("domain_scheme is not null");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeEqualTo(String value) {
            addCriterion("domain_scheme =", value, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeNotEqualTo(String value) {
            addCriterion("domain_scheme <>", value, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeGreaterThan(String value) {
            addCriterion("domain_scheme >", value, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeGreaterThanOrEqualTo(String value) {
            addCriterion("domain_scheme >=", value, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeLessThan(String value) {
            addCriterion("domain_scheme <", value, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeLessThanOrEqualTo(String value) {
            addCriterion("domain_scheme <=", value, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeLike(String value) {
            addCriterion("domain_scheme like", value, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeNotLike(String value) {
            addCriterion("domain_scheme not like", value, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeIn(List<String> values) {
            addCriterion("domain_scheme in", values, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeNotIn(List<String> values) {
            addCriterion("domain_scheme not in", values, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeBetween(String value1, String value2) {
            addCriterion("domain_scheme between", value1, value2, "domainScheme");
            return (Criteria) this;
        }

        public Criteria andDomainSchemeNotBetween(String value1, String value2) {
            addCriterion("domain_scheme not between", value1, value2, "domainScheme");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table oauth2_domain
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table oauth2_domain
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}