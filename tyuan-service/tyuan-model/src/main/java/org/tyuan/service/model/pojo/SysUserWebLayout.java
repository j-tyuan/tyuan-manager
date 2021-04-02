/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.model.pojo;

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
