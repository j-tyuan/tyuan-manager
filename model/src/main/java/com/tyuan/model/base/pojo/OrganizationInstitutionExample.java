package com.tyuan.model.base.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrganizationInstitutionExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public OrganizationInstitutionExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
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
     * This method corresponds to the database table organization_institution
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
     * This method corresponds to the database table organization_institution
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table organization_institution
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
     * This class corresponds to the database table organization_institution
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andInstCodeIsNull() {
            addCriterion("inst_code is null");
            return (Criteria) this;
        }

        public Criteria andInstCodeIsNotNull() {
            addCriterion("inst_code is not null");
            return (Criteria) this;
        }

        public Criteria andInstCodeEqualTo(String value) {
            addCriterion("inst_code =", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotEqualTo(String value) {
            addCriterion("inst_code <>", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeGreaterThan(String value) {
            addCriterion("inst_code >", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeGreaterThanOrEqualTo(String value) {
            addCriterion("inst_code >=", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeLessThan(String value) {
            addCriterion("inst_code <", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeLessThanOrEqualTo(String value) {
            addCriterion("inst_code <=", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeLike(String value) {
            addCriterion("inst_code like", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotLike(String value) {
            addCriterion("inst_code not like", value, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeIn(List<String> values) {
            addCriterion("inst_code in", values, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotIn(List<String> values) {
            addCriterion("inst_code not in", values, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeBetween(String value1, String value2) {
            addCriterion("inst_code between", value1, value2, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstCodeNotBetween(String value1, String value2) {
            addCriterion("inst_code not between", value1, value2, "instCode");
            return (Criteria) this;
        }

        public Criteria andInstNameIsNull() {
            addCriterion("inst_name is null");
            return (Criteria) this;
        }

        public Criteria andInstNameIsNotNull() {
            addCriterion("inst_name is not null");
            return (Criteria) this;
        }

        public Criteria andInstNameEqualTo(String value) {
            addCriterion("inst_name =", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameNotEqualTo(String value) {
            addCriterion("inst_name <>", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameGreaterThan(String value) {
            addCriterion("inst_name >", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameGreaterThanOrEqualTo(String value) {
            addCriterion("inst_name >=", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameLessThan(String value) {
            addCriterion("inst_name <", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameLessThanOrEqualTo(String value) {
            addCriterion("inst_name <=", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameLike(String value) {
            addCriterion("inst_name like", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameNotLike(String value) {
            addCriterion("inst_name not like", value, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameIn(List<String> values) {
            addCriterion("inst_name in", values, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameNotIn(List<String> values) {
            addCriterion("inst_name not in", values, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameBetween(String value1, String value2) {
            addCriterion("inst_name between", value1, value2, "instName");
            return (Criteria) this;
        }

        public Criteria andInstNameNotBetween(String value1, String value2) {
            addCriterion("inst_name not between", value1, value2, "instName");
            return (Criteria) this;
        }

        public Criteria andInstTypeIsNull() {
            addCriterion("inst_type is null");
            return (Criteria) this;
        }

        public Criteria andInstTypeIsNotNull() {
            addCriterion("inst_type is not null");
            return (Criteria) this;
        }

        public Criteria andInstTypeEqualTo(Integer value) {
            addCriterion("inst_type =", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeNotEqualTo(Integer value) {
            addCriterion("inst_type <>", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeGreaterThan(Integer value) {
            addCriterion("inst_type >", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("inst_type >=", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeLessThan(Integer value) {
            addCriterion("inst_type <", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeLessThanOrEqualTo(Integer value) {
            addCriterion("inst_type <=", value, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeIn(List<Integer> values) {
            addCriterion("inst_type in", values, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeNotIn(List<Integer> values) {
            addCriterion("inst_type not in", values, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeBetween(Integer value1, Integer value2) {
            addCriterion("inst_type between", value1, value2, "instType");
            return (Criteria) this;
        }

        public Criteria andInstTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("inst_type not between", value1, value2, "instType");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdIsNull() {
            addCriterion("owner_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdIsNotNull() {
            addCriterion("owner_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdEqualTo(Long value) {
            addCriterion("owner_user_id =", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdNotEqualTo(Long value) {
            addCriterion("owner_user_id <>", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdGreaterThan(Long value) {
            addCriterion("owner_user_id >", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("owner_user_id >=", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdLessThan(Long value) {
            addCriterion("owner_user_id <", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdLessThanOrEqualTo(Long value) {
            addCriterion("owner_user_id <=", value, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdIn(List<Long> values) {
            addCriterion("owner_user_id in", values, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdNotIn(List<Long> values) {
            addCriterion("owner_user_id not in", values, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdBetween(Long value1, Long value2) {
            addCriterion("owner_user_id between", value1, value2, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andOwnerUserIdNotBetween(Long value1, Long value2) {
            addCriterion("owner_user_id not between", value1, value2, "ownerUserId");
            return (Criteria) this;
        }

        public Criteria andInstSortIsNull() {
            addCriterion("inst_sort is null");
            return (Criteria) this;
        }

        public Criteria andInstSortIsNotNull() {
            addCriterion("inst_sort is not null");
            return (Criteria) this;
        }

        public Criteria andInstSortEqualTo(Integer value) {
            addCriterion("inst_sort =", value, "instSort");
            return (Criteria) this;
        }

        public Criteria andInstSortNotEqualTo(Integer value) {
            addCriterion("inst_sort <>", value, "instSort");
            return (Criteria) this;
        }

        public Criteria andInstSortGreaterThan(Integer value) {
            addCriterion("inst_sort >", value, "instSort");
            return (Criteria) this;
        }

        public Criteria andInstSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("inst_sort >=", value, "instSort");
            return (Criteria) this;
        }

        public Criteria andInstSortLessThan(Integer value) {
            addCriterion("inst_sort <", value, "instSort");
            return (Criteria) this;
        }

        public Criteria andInstSortLessThanOrEqualTo(Integer value) {
            addCriterion("inst_sort <=", value, "instSort");
            return (Criteria) this;
        }

        public Criteria andInstSortIn(List<Integer> values) {
            addCriterion("inst_sort in", values, "instSort");
            return (Criteria) this;
        }

        public Criteria andInstSortNotIn(List<Integer> values) {
            addCriterion("inst_sort not in", values, "instSort");
            return (Criteria) this;
        }

        public Criteria andInstSortBetween(Integer value1, Integer value2) {
            addCriterion("inst_sort between", value1, value2, "instSort");
            return (Criteria) this;
        }

        public Criteria andInstSortNotBetween(Integer value1, Integer value2) {
            addCriterion("inst_sort not between", value1, value2, "instSort");
            return (Criteria) this;
        }

        public Criteria andInstStatusIsNull() {
            addCriterion("inst_status is null");
            return (Criteria) this;
        }

        public Criteria andInstStatusIsNotNull() {
            addCriterion("inst_status is not null");
            return (Criteria) this;
        }

        public Criteria andInstStatusEqualTo(Integer value) {
            addCriterion("inst_status =", value, "instStatus");
            return (Criteria) this;
        }

        public Criteria andInstStatusNotEqualTo(Integer value) {
            addCriterion("inst_status <>", value, "instStatus");
            return (Criteria) this;
        }

        public Criteria andInstStatusGreaterThan(Integer value) {
            addCriterion("inst_status >", value, "instStatus");
            return (Criteria) this;
        }

        public Criteria andInstStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("inst_status >=", value, "instStatus");
            return (Criteria) this;
        }

        public Criteria andInstStatusLessThan(Integer value) {
            addCriterion("inst_status <", value, "instStatus");
            return (Criteria) this;
        }

        public Criteria andInstStatusLessThanOrEqualTo(Integer value) {
            addCriterion("inst_status <=", value, "instStatus");
            return (Criteria) this;
        }

        public Criteria andInstStatusIn(List<Integer> values) {
            addCriterion("inst_status in", values, "instStatus");
            return (Criteria) this;
        }

        public Criteria andInstStatusNotIn(List<Integer> values) {
            addCriterion("inst_status not in", values, "instStatus");
            return (Criteria) this;
        }

        public Criteria andInstStatusBetween(Integer value1, Integer value2) {
            addCriterion("inst_status between", value1, value2, "instStatus");
            return (Criteria) this;
        }

        public Criteria andInstStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("inst_status not between", value1, value2, "instStatus");
            return (Criteria) this;
        }

        public Criteria andInstDescIsNull() {
            addCriterion("inst_desc is null");
            return (Criteria) this;
        }

        public Criteria andInstDescIsNotNull() {
            addCriterion("inst_desc is not null");
            return (Criteria) this;
        }

        public Criteria andInstDescEqualTo(String value) {
            addCriterion("inst_desc =", value, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescNotEqualTo(String value) {
            addCriterion("inst_desc <>", value, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescGreaterThan(String value) {
            addCriterion("inst_desc >", value, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescGreaterThanOrEqualTo(String value) {
            addCriterion("inst_desc >=", value, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescLessThan(String value) {
            addCriterion("inst_desc <", value, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescLessThanOrEqualTo(String value) {
            addCriterion("inst_desc <=", value, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescLike(String value) {
            addCriterion("inst_desc like", value, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescNotLike(String value) {
            addCriterion("inst_desc not like", value, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescIn(List<String> values) {
            addCriterion("inst_desc in", values, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescNotIn(List<String> values) {
            addCriterion("inst_desc not in", values, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescBetween(String value1, String value2) {
            addCriterion("inst_desc between", value1, value2, "instDesc");
            return (Criteria) this;
        }

        public Criteria andInstDescNotBetween(String value1, String value2) {
            addCriterion("inst_desc not between", value1, value2, "instDesc");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table organization_institution
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
     * This class corresponds to the database table organization_institution
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