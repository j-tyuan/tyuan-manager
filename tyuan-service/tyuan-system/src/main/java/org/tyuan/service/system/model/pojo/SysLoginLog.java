package org.tyuan.service.system.model.pojo;

import java.util.Date;

public class SysLoginLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.user_no
     *
     * @mbg.generated
     */
    private String userNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.login_ip
     *
     * @mbg.generated
     */
    private String loginIp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.login_date
     *
     * @mbg.generated
     */
    private Date loginDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.avatar_id
     *
     * @mbg.generated
     */
    private Long avatarId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.id
     *
     * @return the value of sys_login_log.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.id
     *
     * @param id the value for sys_login_log.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.user_id
     *
     * @return the value of sys_login_log.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.user_id
     *
     * @param userId the value for sys_login_log.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.user_no
     *
     * @return the value of sys_login_log.user_no
     *
     * @mbg.generated
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.user_no
     *
     * @param userNo the value for sys_login_log.user_no
     *
     * @mbg.generated
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.user_name
     *
     * @return the value of sys_login_log.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.user_name
     *
     * @param userName the value for sys_login_log.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.create_time
     *
     * @return the value of sys_login_log.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.create_time
     *
     * @param createTime the value for sys_login_log.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.login_ip
     *
     * @return the value of sys_login_log.login_ip
     *
     * @mbg.generated
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.login_ip
     *
     * @param loginIp the value for sys_login_log.login_ip
     *
     * @mbg.generated
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.login_date
     *
     * @return the value of sys_login_log.login_date
     *
     * @mbg.generated
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.login_date
     *
     * @param loginDate the value for sys_login_log.login_date
     *
     * @mbg.generated
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.avatar_id
     *
     * @return the value of sys_login_log.avatar_id
     *
     * @mbg.generated
     */
    public Long getAvatarId() {
        return avatarId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.avatar_id
     *
     * @param avatarId the value for sys_login_log.avatar_id
     *
     * @mbg.generated
     */
    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }
}