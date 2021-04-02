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
package org.tyuan.service.manage.web;

public class PermissionConstant {

    /**
     * 用户权限
     */
    public final static String SYS_USER = "sys:user";
    public final static String SYS_USER_LIST = "sys:user:list";
    public final static String SYS_USER_ADD = "sys:user:add";
    public final static String SYS_USER_EDIT = "sys:user:edit";
    public final static String SYS_USER_DEL = "sys:user:del";
    public final static String SYS_USER_AUTH = "sys:user:auth";
    public final static String SYS_USER_DISABLE = "sys:user:disable";

    /**
     * 角色权限
     */
    public final static String SYS_ROLE = "sys:role";
    public final static String SYS_ROLE_LIST = "sys:role:list";
    public final static String SYS_ROLE_ADD = "sys:role:add";
    public final static String SYS_ROLE_EDIT = "sys:role:edit";
    public final static String SYS_ROLE_DEL = "sys:role:del";

    /**
     * 用户角色
     */
    public final static String SYS_ROLE_USER_LIST = "sys:role:user:list";
    public final static String SYS_ROLE_USER_BIND = "sys:role:user:bind";
    public final static String SYS_ROLE_USER_UN_BIND = "sys:role:user:unbind";


    /**
     * 权限
     */
    public final static String SYS_PERMISSION = "sys:permission";

    /**
     * 资源权限
     */
    public final static String SYS_SOURCE = "sys:source";
    public final static String SYS_SOURCE_LIST = "sys:source:list";
    public final static String SYS_SOURCE_ADD = "sys:source:add";
    public final static String SYS_SOURCE_EDIT = "sys:source:edit";
    public final static String SYS_SOURCE_DEL = "sys:source:del";


    /**
     * 字典权限
     */
    public final static String SYS_DICT = "sys:dict";
    public final static String SYS_DICT_LIST = "sys:dict:list";
    public final static String SYS_DICT_ADD = "sys:dict:add";
    public final static String SYS_DICT_EDIT = "sys:dict:edit";
    public final static String SYS_DICT_DEL = "sys:dict:del";

    /**
     * 参数权限
     */
    public final static String SYS_PARAM = "sys:param";
    public final static String SYS_PARAM_LIST = "sys:param:list";
    public final static String SYS_PARAM_ADD = "sys:param:add";
    public final static String SYS_PARAM_EDIT = "sys:param:edit";
    public final static String SYS_PARAM_DEL = "sys:param:del";


    /**
     * 监控 - redis keys
     */
    public final static String MONITOR_REDIS_KEYS = "monitor:redis:keys";
    public final static String MONITOR_REDIS_GET_VALUE = "monitor:redis:getval";

    /**
     * 字典权限
     */
    public final static String SYS_LOG_LIST = "sys:log:list";

    /**
     *
     */
    public final static String SYS_LOG_LOGIN_LIST = "sys:log:login";

    /**
     * 参数权限
     */
    public final static String SYS_ORGANIZATION_INSTITUTION = "sys:organization:institution";
    public final static String SYS_ORGANIZATION_INSTITUTION_ADD = "sys:organization:institution:add";
    public final static String SYS_ORGANIZATION_INSTITUTION_EDIT = "sys:organization:institution:edit";
    public final static String SYS_ORGANIZATION_INSTITUTION_DEL = "sys:organization:institution:del";
}
