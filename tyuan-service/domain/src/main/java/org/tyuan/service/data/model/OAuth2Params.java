package org.tyuan.service.data.model;

import java.io.Serializable;
import java.util.Date;

public class OAuth2Params implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_params.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_params.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_params.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_params.enabled
     *
     * @mbg.generated
     */
    private Boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_params.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oauth2_params
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_params.id
     *
     * @return the value of oauth2_params.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_params.id
     *
     * @param id the value for oauth2_params.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_params.create_time
     *
     * @return the value of oauth2_params.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_params.create_time
     *
     * @param createTime the value for oauth2_params.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_params.update_time
     *
     * @return the value of oauth2_params.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_params.update_time
     *
     * @param updateTime the value for oauth2_params.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_params.enabled
     *
     * @return the value of oauth2_params.enabled
     *
     * @mbg.generated
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_params.enabled
     *
     * @param enabled the value for oauth2_params.enabled
     *
     * @mbg.generated
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_params.user_id
     *
     * @return the value of oauth2_params.user_id
     *
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_params.user_id
     *
     * @param userId the value for oauth2_params.user_id
     *
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}