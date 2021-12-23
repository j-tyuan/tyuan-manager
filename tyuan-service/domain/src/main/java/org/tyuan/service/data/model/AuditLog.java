package org.tyuan.service.data.model;

import java.io.Serializable;
import java.util.Date;

public class AuditLog implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_log.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_log.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_log.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_log.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_log.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_log.action_type
     *
     * @mbg.generated
     */
    private String actionType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column audit_log.action_status
     *
     * @mbg.generated
     */
    private String actionStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table audit_log
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_log.id
     *
     * @return the value of audit_log.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_log.id
     *
     * @param id the value for audit_log.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_log.create_time
     *
     * @return the value of audit_log.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_log.create_time
     *
     * @param createTime the value for audit_log.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_log.update_time
     *
     * @return the value of audit_log.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_log.update_time
     *
     * @param updateTime the value for audit_log.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_log.user_id
     *
     * @return the value of audit_log.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_log.user_id
     *
     * @param userId the value for audit_log.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_log.user_name
     *
     * @return the value of audit_log.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_log.user_name
     *
     * @param userName the value for audit_log.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_log.action_type
     *
     * @return the value of audit_log.action_type
     *
     * @mbg.generated
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_log.action_type
     *
     * @param actionType the value for audit_log.action_type
     *
     * @mbg.generated
     */
    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column audit_log.action_status
     *
     * @return the value of audit_log.action_status
     *
     * @mbg.generated
     */
    public String getActionStatus() {
        return actionStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column audit_log.action_status
     *
     * @param actionStatus the value for audit_log.action_status
     *
     * @mbg.generated
     */
    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus == null ? null : actionStatus.trim();
    }
}