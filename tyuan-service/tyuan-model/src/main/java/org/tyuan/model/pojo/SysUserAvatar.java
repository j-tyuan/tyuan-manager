package org.tyuan.model.pojo;

import java.util.Date;

public class SysUserAvatar {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_avatar.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_avatar.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_avatar.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_user_avatar.user_avatar
     *
     * @mbg.generated
     */
    private String userAvatar;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_avatar.id
     *
     * @return the value of sys_user_avatar.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_avatar.id
     *
     * @param id the value for sys_user_avatar.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_avatar.create_date
     *
     * @return the value of sys_user_avatar.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_avatar.create_date
     *
     * @param createDate the value for sys_user_avatar.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_avatar.update_date
     *
     * @return the value of sys_user_avatar.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_avatar.update_date
     *
     * @param updateDate the value for sys_user_avatar.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_user_avatar.user_avatar
     *
     * @return the value of sys_user_avatar.user_avatar
     *
     * @mbg.generated
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_user_avatar.user_avatar
     *
     * @param userAvatar the value for sys_user_avatar.user_avatar
     *
     * @mbg.generated
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }
}