package com.tyuan.model.pojo;

import java.util.Date;

public class SysParam {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.param_name
     *
     * @mbggenerated
     */
    private String paramName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.param_key
     *
     * @mbggenerated
     */
    private String paramKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.is_sys
     *
     * @mbggenerated
     */
    private Boolean isSys;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.remarks
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.param_val
     *
     * @mbggenerated
     */
    private String paramVal;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.id
     *
     * @return the value of sys_param.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_param.id
     *
     * @param id the value for sys_param.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.create_date
     *
     * @return the value of sys_param.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_param.create_date
     *
     * @param createDate the value for sys_param.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.update_date
     *
     * @return the value of sys_param.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_param.update_date
     *
     * @param updateDate the value for sys_param.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.param_name
     *
     * @return the value of sys_param.param_name
     *
     * @mbggenerated
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_param.param_name
     *
     * @param paramName the value for sys_param.param_name
     *
     * @mbggenerated
     */
    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.param_key
     *
     * @return the value of sys_param.param_key
     *
     * @mbggenerated
     */
    public String getParamKey() {
        return paramKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_param.param_key
     *
     * @param paramKey the value for sys_param.param_key
     *
     * @mbggenerated
     */
    public void setParamKey(String paramKey) {
        this.paramKey = paramKey == null ? null : paramKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.is_sys
     *
     * @return the value of sys_param.is_sys
     *
     * @mbggenerated
     */
    public Boolean getIsSys() {
        return isSys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_param.is_sys
     *
     * @param isSys the value for sys_param.is_sys
     *
     * @mbggenerated
     */
    public void setIsSys(Boolean isSys) {
        this.isSys = isSys;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.create_by
     *
     * @return the value of sys_param.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_param.create_by
     *
     * @param createBy the value for sys_param.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.update_by
     *
     * @return the value of sys_param.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_param.update_by
     *
     * @param updateBy the value for sys_param.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.remarks
     *
     * @return the value of sys_param.remarks
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_param.remarks
     *
     * @param remarks the value for sys_param.remarks
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.param_val
     *
     * @return the value of sys_param.param_val
     *
     * @mbggenerated
     */
    public String getParamVal() {
        return paramVal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_param.param_val
     *
     * @param paramVal the value for sys_param.param_val
     *
     * @mbggenerated
     */
    public void setParamVal(String paramVal) {
        this.paramVal = paramVal == null ? null : paramVal.trim();
    }
}
