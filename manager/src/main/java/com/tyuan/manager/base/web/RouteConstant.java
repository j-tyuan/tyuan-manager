/**
 * @ClassName RouteConstant
 * @Author dev@tyuan.design
 * @Date 2020/6/22 10:20
 */
package com.tyuan.manager.base.web;

public class RouteConstant {

    /**
     * 登陆
     */
    public static final String ROUTER_SYS_LOGIN_ACCOUNT = "/api/login/account";

    /**
     * 退出
     */
    public static final String ROUTER_SYS_LOGOUT = "/api/logout";

    /**
     * 获取用户信息
     */
    public static final String ROUTER_CURRENT_USER_INFO = "/api/currentUser";

    /**
     * 权限
     */
    public static final String ROUTER_PERMISSION = "/api/permission";

    /**
     * 获取全部
     */
    public static final String ROUTER_ALL = "/api/all";

    /**
     * 菜单
     */
    public static final String ROUTER_SYS_NAV = "/api/sys/nav";
    public static final String ROUTER_SYS_SOURCE = "/api/sys/source";
    public static final String ROUTER_SYS_SOURCE_ADD = "/api/sys/source/add";
    public static final String ROUTER_SYS_SOURCE_DEL = "/api/sys/source/del";
    public static final String ROUTER_SYS_SOURCE_EDIT = "/api/sys/source/edit";

    /**
     * 账号
     */
    public static final String ROUTER_ACCOUNT_CUSTOM_LAYOUT = "/api/account/custom/layout";
    public static final String ROUTER_ACCOUNT_SETTING = "/api/account/setting";
    public static final String ROUTER_ACCOUNT_AVATAR = "/api/account/avatar";
    public static final String ROUTER_ACCOUNT = "/api/account";

    /**
     * 用户
     */
    public static final String ROUTER_SYS_USER_ALL = "/api/sys/users";
    public static final String ROUTER_SYS_USER = "/api/sys/user";
    public static final String ROUTER_SYS_USER_ADD = "/api/sys/user/add";
    public static final String ROUTER_SYS_USER_EDIT = "/api/sys/user/edit";
    public static final String ROUTER_SYS_USER_DEL = "/api/sys/user/del";
    public static final String ROUTER_SYS_USER_DISABLE = "/api/sys/user/disable/{uid}/{disable}";
    public static final String ROUTER_SYS_USER_FETCH = "/api/sys/user/fetch/{value}";
    public static final String ROUTER_SYS_USER_AVATAR = "/api/sys/user/avatar";
    public static final String ROUTER_SYS_USER_ROLE_GET_BY_ID = "/api/sys/user/{userId}/role";

    /**
     * 角色
     */
    public static final String ROUTER_SYS_ROLE = "/api/sys/role";
    public static final String ROUTER_SYS_ROLE_ADD = "/api/sys/role/add";
    public static final String ROUTER_SYS_ROLE_EDIT = "/api/sys/role/edit";
    public static final String ROUTER_SYS_ROLE_DEL = "/api/sys/role/del";
    public static final String ROUTER_SYS_ROLE_AUTH = "/api/sys/role/auth/{id}";

    /**
     * 用户角色
     */
    public static final String ROUTER_SYS_ROLE_USER = "/api/sys/role/user";
    public static final String ROUTER_SYS_ROLE_USER_BIND = "/api/sys/role/user/bind";
    public static final String ROUTER_SYS_ROLE_USER_UNBIND = "/api/sys/role/user/unbind";


    /**
     * 权限
     */
    public static final String ROUTER_SYS_PERMISSION = "/api/sys/permission";
    public static final String ROUTER_SYS_PERMISSION_BY_ROLE_ID = "/api/sys/permission/getByRoleId";


    /**
     * 字典
     */
    public static final String ROUTER_SYS_DICT = "/api/sys/dict";
    public static final String ROUTER_SYS_DICT_ADD = "/api/sys/dict/add";
    public static final String ROUTER_SYS_DICT_EDIT = "/api/sys/dict/edit";
    public static final String ROUTER_SYS_DICT_DEL = "/api/sys/dict/del";
    public static final String ROUTER_SYS_DICT_GET_BY_TYPE = "/api/sys/dict/{type}";
    public static final String ROUTER_SYS_DICT_GET_TYPES = "/api/sys/dict/getTypes";

    /**
     * 参数
     */
    public static final String ROUTER_SYS_PARAM = "/api/sys/param";
    public static final String ROUTER_SYS_PARAM_ADD = "/api/sys/param/add";
    public static final String ROUTER_SYS_PARAM_EDIT = "/api/sys/param/edit";
    public static final String ROUTER_SYS_PARAM_DEL = "/api/sys/param/del";


    /**
     * 服务监控 - redis
     */
    public static final String ROUTER_MONITOR_REDIS_KEYS = "/api/monitor/redis/keys";
    public static final String ROUTER_MONITOR_REDIS_GET_VALUE = "/api/monitor/redis/getVal";

    /**
     * 日志
     */
    public static final String ROUTER_SYS_LOG = "/api/sys/log";
    public static final String ROUTER_SYS_LOG_TYPE = "/api/sys/log/type";

    /**
     * 机构
     */
    public static final String ROUTER_ORGANIZATION_INSTITUTION = "/api/org/inst";
    public static final String ROUTER_ORGANIZATION_INSTITUTION_ADD = "/api/org/inst/add";
    public static final String ROUTER_ORGANIZATION_INSTITUTION_EDIT = "/api/org/inst/edit";
    public static final String ROUTER_ORGANIZATION_INSTITUTION_DEL = "/api/org/inst/del";

    /**
     * 测试接口
     */
    public static final String ROUTER_TEST_VISIT = "/api/test/visit";
}
