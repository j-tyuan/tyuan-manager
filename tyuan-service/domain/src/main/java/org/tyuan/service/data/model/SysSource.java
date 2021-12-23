package org.tyuan.service.data.model;

import java.io.Serializable;
import java.util.Date;

public class SysSource implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.parent_id
     *
     * @mbg.generated
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.source_name
     *
     * @mbg.generated
     */
    private String sourceName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.source_sort
     *
     * @mbg.generated
     */
    private Long sourceSort;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.source_href
     *
     * @mbg.generated
     */
    private String sourceHref;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.source_target
     *
     * @mbg.generated
     */
    private String sourceTarget;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.source_icon
     *
     * @mbg.generated
     */
    private String sourceIcon;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.is_leaf
     *
     * @mbg.generated
     */
    private Boolean isLeaf;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.is_show
     *
     * @mbg.generated
     */
    private Boolean isShow;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.permission_id
     *
     * @mbg.generated
     */
    private Long permissionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.create_by
     *
     * @mbg.generated
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.update_by
     *
     * @mbg.generated
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.remarks
     *
     * @mbg.generated
     */
    private String remarks;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_source.del_flag
     *
     * @mbg.generated
     */
    private Boolean delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_source
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.id
     *
     * @return the value of sys_source.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.id
     *
     * @param id the value for sys_source.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.create_time
     *
     * @return the value of sys_source.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.create_time
     *
     * @param createTime the value for sys_source.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.update_time
     *
     * @return the value of sys_source.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.update_time
     *
     * @param updateTime the value for sys_source.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.parent_id
     *
     * @return the value of sys_source.parent_id
     *
     * @mbg.generated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.parent_id
     *
     * @param parentId the value for sys_source.parent_id
     *
     * @mbg.generated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.source_name
     *
     * @return the value of sys_source.source_name
     *
     * @mbg.generated
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.source_name
     *
     * @param sourceName the value for sys_source.source_name
     *
     * @mbg.generated
     */
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.source_sort
     *
     * @return the value of sys_source.source_sort
     *
     * @mbg.generated
     */
    public Long getSourceSort() {
        return sourceSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.source_sort
     *
     * @param sourceSort the value for sys_source.source_sort
     *
     * @mbg.generated
     */
    public void setSourceSort(Long sourceSort) {
        this.sourceSort = sourceSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.source_href
     *
     * @return the value of sys_source.source_href
     *
     * @mbg.generated
     */
    public String getSourceHref() {
        return sourceHref;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.source_href
     *
     * @param sourceHref the value for sys_source.source_href
     *
     * @mbg.generated
     */
    public void setSourceHref(String sourceHref) {
        this.sourceHref = sourceHref == null ? null : sourceHref.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.source_target
     *
     * @return the value of sys_source.source_target
     *
     * @mbg.generated
     */
    public String getSourceTarget() {
        return sourceTarget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.source_target
     *
     * @param sourceTarget the value for sys_source.source_target
     *
     * @mbg.generated
     */
    public void setSourceTarget(String sourceTarget) {
        this.sourceTarget = sourceTarget == null ? null : sourceTarget.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.source_icon
     *
     * @return the value of sys_source.source_icon
     *
     * @mbg.generated
     */
    public String getSourceIcon() {
        return sourceIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.source_icon
     *
     * @param sourceIcon the value for sys_source.source_icon
     *
     * @mbg.generated
     */
    public void setSourceIcon(String sourceIcon) {
        this.sourceIcon = sourceIcon == null ? null : sourceIcon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.is_leaf
     *
     * @return the value of sys_source.is_leaf
     *
     * @mbg.generated
     */
    public Boolean getIsLeaf() {
        return isLeaf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.is_leaf
     *
     * @param isLeaf the value for sys_source.is_leaf
     *
     * @mbg.generated
     */
    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.is_show
     *
     * @return the value of sys_source.is_show
     *
     * @mbg.generated
     */
    public Boolean getIsShow() {
        return isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.is_show
     *
     * @param isShow the value for sys_source.is_show
     *
     * @mbg.generated
     */
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.permission_id
     *
     * @return the value of sys_source.permission_id
     *
     * @mbg.generated
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.permission_id
     *
     * @param permissionId the value for sys_source.permission_id
     *
     * @mbg.generated
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.create_by
     *
     * @return the value of sys_source.create_by
     *
     * @mbg.generated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.create_by
     *
     * @param createBy the value for sys_source.create_by
     *
     * @mbg.generated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.update_by
     *
     * @return the value of sys_source.update_by
     *
     * @mbg.generated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.update_by
     *
     * @param updateBy the value for sys_source.update_by
     *
     * @mbg.generated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.remarks
     *
     * @return the value of sys_source.remarks
     *
     * @mbg.generated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.remarks
     *
     * @param remarks the value for sys_source.remarks
     *
     * @mbg.generated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_source.del_flag
     *
     * @return the value of sys_source.del_flag
     *
     * @mbg.generated
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_source.del_flag
     *
     * @param delFlag the value for sys_source.del_flag
     *
     * @mbg.generated
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}