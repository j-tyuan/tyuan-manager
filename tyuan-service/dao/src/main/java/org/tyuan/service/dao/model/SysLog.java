package org.tyuan.service.dao.model;

import java.util.Date;

public class SysLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.request_id
     *
     * @mbg.generated
     */
    private String requestId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.log_type
     *
     * @mbg.generated
     */
    private Integer logType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.log_title
     *
     * @mbg.generated
     */
    private String logTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.remote_addr
     *
     * @mbg.generated
     */
    private String remoteAddr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.user_agent
     *
     * @mbg.generated
     */
    private String userAgent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.request_uri
     *
     * @mbg.generated
     */
    private String requestUri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.method
     *
     * @mbg.generated
     */
    private String method;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.duration
     *
     * @mbg.generated
     */
    private Integer duration;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.id
     *
     * @return the value of sys_log.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.id
     *
     * @param id the value for sys_log.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.request_id
     *
     * @return the value of sys_log.request_id
     *
     * @mbg.generated
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.request_id
     *
     * @param requestId the value for sys_log.request_id
     *
     * @mbg.generated
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.create_time
     *
     * @return the value of sys_log.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.create_time
     *
     * @param createTime the value for sys_log.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.log_type
     *
     * @return the value of sys_log.log_type
     *
     * @mbg.generated
     */
    public Integer getLogType() {
        return logType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.log_type
     *
     * @param logType the value for sys_log.log_type
     *
     * @mbg.generated
     */
    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.log_title
     *
     * @return the value of sys_log.log_title
     *
     * @mbg.generated
     */
    public String getLogTitle() {
        return logTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.log_title
     *
     * @param logTitle the value for sys_log.log_title
     *
     * @mbg.generated
     */
    public void setLogTitle(String logTitle) {
        this.logTitle = logTitle == null ? null : logTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.user_id
     *
     * @return the value of sys_log.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.user_id
     *
     * @param userId the value for sys_log.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.user_name
     *
     * @return the value of sys_log.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.user_name
     *
     * @param userName the value for sys_log.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.remote_addr
     *
     * @return the value of sys_log.remote_addr
     *
     * @mbg.generated
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.remote_addr
     *
     * @param remoteAddr the value for sys_log.remote_addr
     *
     * @mbg.generated
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.user_agent
     *
     * @return the value of sys_log.user_agent
     *
     * @mbg.generated
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.user_agent
     *
     * @param userAgent the value for sys_log.user_agent
     *
     * @mbg.generated
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.request_uri
     *
     * @return the value of sys_log.request_uri
     *
     * @mbg.generated
     */
    public String getRequestUri() {
        return requestUri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.request_uri
     *
     * @param requestUri the value for sys_log.request_uri
     *
     * @mbg.generated
     */
    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri == null ? null : requestUri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.method
     *
     * @return the value of sys_log.method
     *
     * @mbg.generated
     */
    public String getMethod() {
        return method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.method
     *
     * @param method the value for sys_log.method
     *
     * @mbg.generated
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.duration
     *
     * @return the value of sys_log.duration
     *
     * @mbg.generated
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.duration
     *
     * @param duration the value for sys_log.duration
     *
     * @mbg.generated
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}