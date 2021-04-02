package org.tyuan.model.pojo;

import java.util.Date;

public class OrganizationInstitution {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.parent_id
     *
     * @mbg.generated
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.inst_code
     *
     * @mbg.generated
     */
    private String instCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.inst_name
     *
     * @mbg.generated
     */
    private String instName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.inst_type
     *
     * @mbg.generated
     */
    private Integer instType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.owner_user_id
     *
     * @mbg.generated
     */
    private Long ownerUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.inst_sort
     *
     * @mbg.generated
     */
    private Integer instSort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.inst_status
     *
     * @mbg.generated
     */
    private Integer instStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.inst_desc
     *
     * @mbg.generated
     */
    private String instDesc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column organization_institution.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.id
     *
     * @return the value of organization_institution.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.id
     *
     * @param id the value for organization_institution.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.parent_id
     *
     * @return the value of organization_institution.parent_id
     *
     * @mbg.generated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.parent_id
     *
     * @param parentId the value for organization_institution.parent_id
     *
     * @mbg.generated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.inst_code
     *
     * @return the value of organization_institution.inst_code
     *
     * @mbg.generated
     */
    public String getInstCode() {
        return instCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.inst_code
     *
     * @param instCode the value for organization_institution.inst_code
     *
     * @mbg.generated
     */
    public void setInstCode(String instCode) {
        this.instCode = instCode == null ? null : instCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.inst_name
     *
     * @return the value of organization_institution.inst_name
     *
     * @mbg.generated
     */
    public String getInstName() {
        return instName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.inst_name
     *
     * @param instName the value for organization_institution.inst_name
     *
     * @mbg.generated
     */
    public void setInstName(String instName) {
        this.instName = instName == null ? null : instName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.inst_type
     *
     * @return the value of organization_institution.inst_type
     *
     * @mbg.generated
     */
    public Integer getInstType() {
        return instType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.inst_type
     *
     * @param instType the value for organization_institution.inst_type
     *
     * @mbg.generated
     */
    public void setInstType(Integer instType) {
        this.instType = instType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.owner_user_id
     *
     * @return the value of organization_institution.owner_user_id
     *
     * @mbg.generated
     */
    public Long getOwnerUserId() {
        return ownerUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.owner_user_id
     *
     * @param ownerUserId the value for organization_institution.owner_user_id
     *
     * @mbg.generated
     */
    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.inst_sort
     *
     * @return the value of organization_institution.inst_sort
     *
     * @mbg.generated
     */
    public Integer getInstSort() {
        return instSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.inst_sort
     *
     * @param instSort the value for organization_institution.inst_sort
     *
     * @mbg.generated
     */
    public void setInstSort(Integer instSort) {
        this.instSort = instSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.inst_status
     *
     * @return the value of organization_institution.inst_status
     *
     * @mbg.generated
     */
    public Integer getInstStatus() {
        return instStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.inst_status
     *
     * @param instStatus the value for organization_institution.inst_status
     *
     * @mbg.generated
     */
    public void setInstStatus(Integer instStatus) {
        this.instStatus = instStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.inst_desc
     *
     * @return the value of organization_institution.inst_desc
     *
     * @mbg.generated
     */
    public String getInstDesc() {
        return instDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.inst_desc
     *
     * @param instDesc the value for organization_institution.inst_desc
     *
     * @mbg.generated
     */
    public void setInstDesc(String instDesc) {
        this.instDesc = instDesc == null ? null : instDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.create_date
     *
     * @return the value of organization_institution.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.create_date
     *
     * @param createDate the value for organization_institution.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column organization_institution.update_date
     *
     * @return the value of organization_institution.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column organization_institution.update_date
     *
     * @param updateDate the value for organization_institution.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}