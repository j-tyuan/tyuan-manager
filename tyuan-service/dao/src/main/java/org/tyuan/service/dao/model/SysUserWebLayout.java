package org.tyuan.service.dao.model;

import java.util.Date;

public class SysUserWebLayout {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_web_layout.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_web_layout.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_web_layout.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_web_layout.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_web_layout.layout_structure
     *
     * @mbg.generated
     */
    private String layoutStructure;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_web_layout.id
     *
     * @return the value of sys_user_web_layout.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_web_layout.id
     *
     * @param id the value for sys_user_web_layout.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_web_layout.create_time
     *
     * @return the value of sys_user_web_layout.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_web_layout.create_time
     *
     * @param createTime the value for sys_user_web_layout.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_web_layout.update_time
     *
     * @return the value of sys_user_web_layout.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_web_layout.update_time
     *
     * @param updateTime the value for sys_user_web_layout.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_web_layout.user_id
     *
     * @return the value of sys_user_web_layout.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_web_layout.user_id
     *
     * @param userId the value for sys_user_web_layout.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_web_layout.layout_structure
     *
     * @return the value of sys_user_web_layout.layout_structure
     *
     * @mbg.generated
     */
    public String getLayoutStructure() {
        return layoutStructure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_web_layout.layout_structure
     *
     * @param layoutStructure the value for sys_user_web_layout.layout_structure
     *
     * @mbg.generated
     */
    public void setLayoutStructure(String layoutStructure) {
        this.layoutStructure = layoutStructure == null ? null : layoutStructure.trim();
    }
}
