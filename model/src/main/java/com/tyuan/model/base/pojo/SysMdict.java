package com.tyuan.model.base.pojo;

import java.util.Date;

public class SysMdict {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.parent_id
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.sort
     *
     * @mbggenerated
     */
    private Long sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.remarks
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_mdict.del_flag
     *
     * @mbggenerated
     */
    private Boolean delFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.id
     *
     * @return the value of sys_mdict.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.id
     *
     * @param id the value for sys_mdict.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.create_date
     *
     * @return the value of sys_mdict.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.create_date
     *
     * @param createDate the value for sys_mdict.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.update_date
     *
     * @return the value of sys_mdict.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.update_date
     *
     * @param updateDate the value for sys_mdict.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.parent_id
     *
     * @return the value of sys_mdict.parent_id
     *
     * @mbggenerated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.parent_id
     *
     * @param parentId the value for sys_mdict.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.name
     *
     * @return the value of sys_mdict.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.name
     *
     * @param name the value for sys_mdict.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.sort
     *
     * @return the value of sys_mdict.sort
     *
     * @mbggenerated
     */
    public Long getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.sort
     *
     * @param sort the value for sys_mdict.sort
     *
     * @mbggenerated
     */
    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.description
     *
     * @return the value of sys_mdict.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.description
     *
     * @param description the value for sys_mdict.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.create_by
     *
     * @return the value of sys_mdict.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.create_by
     *
     * @param createBy the value for sys_mdict.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.update_by
     *
     * @return the value of sys_mdict.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.update_by
     *
     * @param updateBy the value for sys_mdict.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.remarks
     *
     * @return the value of sys_mdict.remarks
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.remarks
     *
     * @param remarks the value for sys_mdict.remarks
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_mdict.del_flag
     *
     * @return the value of sys_mdict.del_flag
     *
     * @mbggenerated
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_mdict.del_flag
     *
     * @param delFlag the value for sys_mdict.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}