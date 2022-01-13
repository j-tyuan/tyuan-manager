package org.tyuan.service.data.model;

import java.io.Serializable;
import java.util.Date;

public class OAuth2Mobile implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_mobile.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_mobile.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_mobile.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_mobile.oauth2_params_id
     *
     * @mbg.generated
     */
    private String oauth2ParamsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_mobile.pkg_name
     *
     * @mbg.generated
     */
    private String pkgName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column oauth2_mobile.app_secret
     *
     * @mbg.generated
     */
    private String appSecret;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table oauth2_mobile
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_mobile.id
     *
     * @return the value of oauth2_mobile.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_mobile.id
     *
     * @param id the value for oauth2_mobile.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_mobile.create_time
     *
     * @return the value of oauth2_mobile.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_mobile.create_time
     *
     * @param createTime the value for oauth2_mobile.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_mobile.update_time
     *
     * @return the value of oauth2_mobile.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_mobile.update_time
     *
     * @param updateTime the value for oauth2_mobile.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_mobile.oauth2_params_id
     *
     * @return the value of oauth2_mobile.oauth2_params_id
     *
     * @mbg.generated
     */
    public String getOauth2ParamsId() {
        return oauth2ParamsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_mobile.oauth2_params_id
     *
     * @param oauth2ParamsId the value for oauth2_mobile.oauth2_params_id
     *
     * @mbg.generated
     */
    public void setOauth2ParamsId(String oauth2ParamsId) {
        this.oauth2ParamsId = oauth2ParamsId == null ? null : oauth2ParamsId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_mobile.pkg_name
     *
     * @return the value of oauth2_mobile.pkg_name
     *
     * @mbg.generated
     */
    public String getPkgName() {
        return pkgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_mobile.pkg_name
     *
     * @param pkgName the value for oauth2_mobile.pkg_name
     *
     * @mbg.generated
     */
    public void setPkgName(String pkgName) {
        this.pkgName = pkgName == null ? null : pkgName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column oauth2_mobile.app_secret
     *
     * @return the value of oauth2_mobile.app_secret
     *
     * @mbg.generated
     */
    public String getAppSecret() {
        return appSecret;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column oauth2_mobile.app_secret
     *
     * @param appSecret the value for oauth2_mobile.app_secret
     *
     * @mbg.generated
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }
}