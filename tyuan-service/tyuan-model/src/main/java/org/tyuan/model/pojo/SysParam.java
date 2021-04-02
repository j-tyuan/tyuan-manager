package org.tyuan.model.pojo;

import java.util.Date;

public class SysParam {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.param_name
     *
     * @mbg.generated
     */
    private String paramName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.param_key
     *
     * @mbg.generated
     */
    private String paramKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.is_sys
     *
     * @mbg.generated
     */
    private Boolean isSys;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.create_by
     *
     * @mbg.generated
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.update_by
     *
     * @mbg.generated
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.remarks
     *
     * @mbg.generated
     */
    private String remarks;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_param.param_val
     *
     * @mbg.generated
     */
    private String paramVal;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_param.id
     *
     * @return the value of sys_param.id
     *
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
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
     * @mbg.generated
     */
    public void setParamVal(String paramVal) {
        this.paramVal = paramVal == null ? null : paramVal.trim();
    }
}