/*
* 库名称：base_manager 需要自己创建
*
*
*
*/


use base_manager;

DROP TABLE IF EXISTS `organization_institution`;
DROP TABLE IF EXISTS `sys_area`;
DROP TABLE IF EXISTS `sys_dict`;
DROP TABLE IF EXISTS `sys_log`;
DROP TABLE IF EXISTS `sys_login_log`;
DROP TABLE IF EXISTS `sys_mdict`;
DROP TABLE IF EXISTS `sys_oss_file`;
DROP TABLE IF EXISTS `sys_param`;
DROP TABLE IF EXISTS `sys_permission`;
DROP TABLE IF EXISTS `sys_role`;
DROP TABLE IF EXISTS `sys_role_permission`;
DROP TABLE IF EXISTS `sys_source`;
DROP TABLE IF EXISTS `sys_user`;
DROP TABLE IF EXISTS `sys_user_avatar`;
DROP TABLE IF EXISTS `sys_user_role`;
DROP TABLE IF EXISTS `sys_user_web_layout`;
DROP TABLE IF EXISTS `sys_user_credentials`;
DROP TABLE IF EXISTS `audit_log`;

# oauth2
DROP TABLE IF EXISTS `oauth2_domain`;
DROP TABLE IF EXISTS `oauth2_mobile`;
DROP TABLE IF EXISTS `oauth2_params`;
DROP TABLE IF EXISTS `oauth2_registration`;

/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = organization_institution   */
/******************************************/
CREATE TABLE `organization_institution`
(
    `id`            varchar(64)  NOT NULL COMMENT 'ID',
    `create_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    `parent_id`     varchar(64)  NOT NULL DEFAULT '0' COMMENT '父ID',
    `inst_code`     varchar(512) NOT NULL COMMENT '机构编码',
    `inst_name`     varchar(512) NOT NULL COMMENT '机构名称',
    `inst_type`     int(2)       NOT NULL COMMENT '机构类型：0：公司，1:部门',
    `owner_user_id` varchar(64)  NOT NULL COMMENT '负责人ID',
    `inst_sort`     int(2)       NOT NULL COMMENT '排序',
    `inst_status`   int(2)       NOT NULL COMMENT '状态：0:启用，1:停用',
    `remarks`       varchar(2048)         DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (`id`)
) COMMENT = '机构表';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_area   */
/******************************************/

CREATE TABLE `sys_area`
(
    `id`          varchar(64)    NOT NULL COMMENT 'ID',
    `create_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    `parent_id`   varchar(64)    NOT NULL COMMENT '父级编号',
    `area_name`   varchar(100)   NOT NULL COMMENT '名称',
    `area_sort`   decimal(10, 0) NOT NULL COMMENT '排序',
    `area_code`   varchar(100)            DEFAULT NULL COMMENT '区域编码',
    `area_type`   tinyint(1)              DEFAULT NULL COMMENT '区域类型',
    `create_by`   varchar(64)    NOT NULL COMMENT '创建者',
    `update_by`   varchar(64)    NOT NULL COMMENT '更新者',
    `remarks`     varchar(255)            DEFAULT NULL COMMENT '备注信息',
    `del_flag`    tinyint(1)     NOT NULL DEFAULT '0' COMMENT '删除标记',
    PRIMARY KEY (`id`)
) COMMENT = '区域表';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_dict   */
/******************************************/
CREATE TABLE `sys_dict`
(
    `id`          varchar(64)    NOT NULL COMMENT 'ID',
    `create_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    `dict_value`  varchar(100)   NOT NULL COMMENT '数据值',
    `dict_label`  varchar(100)   NOT NULL COMMENT '标签名',
    `dict_type`   varchar(100)   NOT NULL COMMENT '类型',
    `dict_sort`   decimal(10, 0) NOT NULL COMMENT '排序（升序）',
    `parent_id`   varchar(64)             DEFAULT '-' COMMENT '父级编号',
    `create_by`   varchar(64)    NOT NULL COMMENT '创建者',
    `update_by`   varchar(64)    NOT NULL COMMENT '更新者',
    `remarks`     varchar(255)            DEFAULT NULL COMMENT '备注信息',
    `del_flag`    tinyint(1)     NOT NULL DEFAULT '0' COMMENT '删除标记',
    PRIMARY KEY (`id`)
) COMMENT = '字典表';

/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_mdict   */
/******************************************/
CREATE TABLE `sys_mdict`
(
    `id`          varchar(64)    NOT NULL COMMENT 'ID',
    `create_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `parent_id`   varchar(64)    NOT NULL COMMENT '父级编号',
    `mdict_name`  varchar(100)   NOT NULL COMMENT '名称',
    `mdict_sort`  decimal(10, 0) NOT NULL COMMENT '排序',
    `create_by`   varchar(64)    NOT NULL COMMENT '创建者',
    `update_by`   varchar(64)    NOT NULL COMMENT '更新者',
    `remarks`     varchar(255)            DEFAULT NULL COMMENT '备注信息',
    `del_flag`    tinyint(1)     NOT NULL DEFAULT '0' COMMENT '删除标记',
    PRIMARY KEY (`id`)
) COMMENT = '多级字典表';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_oss_file   */
/******************************************/
CREATE TABLE `sys_oss_file`
(
    `id`           varchar(64)      NOT NULL COMMENT 'ID',
    `create_time`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `bucket`       varchar(124)     NOT NULL COMMENT 'oss空间',
    `file_uid`     varchar(128)     NOT NULL COMMENT '文件Key(id)',
    `file_name`    varchar(128)     NOT NULL COMMENT '文件名称',
    `size`         int(10) unsigned NOT NULL COMMENT '文件大小',
    `file_type`    varchar(128)     NOT NULL COMMENT '文件类型',
    `channel_code` varchar(128)     NOT NULL COMMENT '渠道编码:qiniu、aliyun、.....',
    `create_by`    varchar(64)      NOT NULL COMMENT '创建者',
    `update_by`    varchar(64)      NOT NULL COMMENT '更新者',
    PRIMARY KEY (`id`)
) COMMENT = '文件表';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_param   */
/******************************************/
CREATE TABLE `sys_param`
(
    `id`          varchar(64) NOT NULL COMMENT 'ID',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `param_name`  varchar(255)         DEFAULT NULL COMMENT '参数名称',
    `param_key`   varchar(255)         DEFAULT NULL COMMENT '参数key',
    `param_val`   text COMMENT '参数值',
    `is_sys`      tinyint(1)  NOT NULL DEFAULT '0' COMMENT '是否系统参数',
    `create_by`   varchar(64) NOT NULL COMMENT '创建者',
    `update_by`   varchar(64) NOT NULL COMMENT '更新者',
    `remarks`     varchar(1024)        DEFAULT NULL COMMENT '备注信息',
    PRIMARY KEY (`id`)
) COMMENT = '参数表 - 用于动态配置一些项目';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_permission   */
/******************************************/
CREATE TABLE `sys_permission`
(
    `id`              varchar(64)  NOT NULL COMMENT 'ID',
    `parent_id`       bigint(20)   NOT NULL COMMENT '父级编号',
    `permission`      varchar(100) NOT NULL COMMENT '权限',
    `permission_sort` decimal(10, 0) DEFAULT '0' COMMENT '排序',
    `permission_name` varchar(100) NOT NULL COMMENT '权限',
    `remarks`         varchar(255)   DEFAULT NULL COMMENT '备注信息',
    PRIMARY KEY (`id`)
) COMMENT = '权限表';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_role   */
/******************************************/
CREATE TABLE `sys_role`
(
    `id`          varchar(64)  NOT NULL COMMENT 'ID',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `role_code`   varchar(100) NOT NULL COMMENT '角色名称',
    `role_name`   varchar(255)          DEFAULT NULL COMMENT '英文名称',
    `is_sys`      tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否系统数据',
    `useable`     tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否可用',
    `create_by`   varchar(64)  NOT NULL COMMENT '创建者',
    `update_by`   varchar(64)  NOT NULL COMMENT '更新者',
    `remarks`     varchar(255)          DEFAULT NULL COMMENT '备注信息',
    `del_flag`    tinyint(1)   NOT NULL DEFAULT '0' COMMENT '删除标记',
    PRIMARY KEY (`id`)
) COMMENT = '角色表';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_role_permission   */
/******************************************/
CREATE TABLE `sys_role_permission`
(
    `id`            varchar(64) NOT NULL COMMENT 'ID',
    `role_id`       varchar(64) NOT NULL COMMENT '角色编号',
    `permission_id` varchar(64) NOT NULL COMMENT '权限ID',
    PRIMARY KEY (`id`)
) COMMENT = '角色-权限';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_source   */
/******************************************/
CREATE TABLE `sys_source`
(
    `id`            varchar(64)  NOT NULL COMMENT 'ID',
    `create_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `parent_id`     varchar(64)  NOT NULL COMMENT '父级编号',
    `source_name`   varchar(100) NOT NULL COMMENT '名称',
    `source_sort`   decimal(10, 0)        DEFAULT '0' COMMENT '排序',
    `source_href`   varchar(2048)         DEFAULT '' COMMENT '链接',
    `source_target` varchar(20)           DEFAULT '' COMMENT '目标',
    `source_icon`   varchar(100)          DEFAULT NULL COMMENT '图标',
    `is_leaf`       tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否叶子节点',
    `is_show`       tinyint(1)   NOT NULL COMMENT '是否在菜单中显示',
    `permission_id` varchar(64)           DEFAULT '0' COMMENT '权限标识',
    `create_by`     varchar(64)  NOT NULL COMMENT '创建者',
    `update_by`     varchar(64)  NOT NULL COMMENT '更新者',
    `remarks`       varchar(255)          DEFAULT NULL COMMENT '备注信息',
    `del_flag`      tinyint(1)   NOT NULL DEFAULT '0' COMMENT '删除标记',
    PRIMARY KEY (`id`)
) COMMENT = '菜单表';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_user   */
/******************************************/
CREATE TABLE `sys_user`
(
    `id`              varchar(64)  NOT NULL COMMENT 'ID',
    `create_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `user_account`    varchar(100) NOT NULL COMMENT '登录名',
    `additional_info` TEXT         NOT NULL COMMENT '用户详细信息',
    `authority`       varchar(64)  NOT NULL COMMENT '用户类型',
    `user_name`       varchar(100) NOT NULL COMMENT '姓名',
    `user_email`      varchar(200)          DEFAULT NULL COMMENT '邮箱',
    `user_phone`      varchar(200)          DEFAULT NULL COMMENT '电话',
    `inst_id`         varchar(64)           DEFAULT NULL COMMENT '机构ID',
    `inst_name`       varchar(100)          DEFAULT NULL COMMENT '机构名称',
    `mobile`          varchar(200)          DEFAULT NULL COMMENT '手机',
    `login_ip`        varchar(100)          DEFAULT NULL COMMENT '最后登陆IP',
    `login_date`      datetime              DEFAULT NULL COMMENT '最后登陆时间',
    `login_flag`      varchar(64)           DEFAULT NULL COMMENT '是否可登录',
    `create_by`       varchar(64)  NOT NULL COMMENT '创建者',
    `update_by`       varchar(64)  NOT NULL COMMENT '更新者',
    `remarks`         varchar(255)          DEFAULT NULL COMMENT '备注信息',
    `del_flag`        tinyint(1)   NOT NULL DEFAULT '0' COMMENT '删除标记',
    `disabled`        tinyint(1)            DEFAULT '0' COMMENT '是否禁用',
    `user_no`         varchar(512)          DEFAULT '' COMMENT '用户工号',
    `avatar_id`       varchar(64)           DEFAULT '-' COMMENT '用户头像',
    PRIMARY KEY (`id`)
) COMMENT = '用户表';

CREATE TABLE `sys_user_credentials`
(
    `id`             varchar(64)   NOT NULL COMMENT 'ID',
    `create_time`    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `user_id`        varchar(64)            DEFAULT '-' COMMENT '用户ID',

    `activate_token` varchar(1024) NOT NULL COMMENT 'token',
    `enabled`        tinyint(1)             DEFAULT '0' COMMENT '是否禁用',
    `password`       varchar(1024) NOT NULL COMMENT '密码',
    `reset_token`    varchar(1024)          DEFAULT NULL COMMENT 'token',
    PRIMARY KEY (`id`)
) COMMENT = '用户证书表';


CREATE TABLE `audit_log`
(
    `id`                     varchar(64)  NOT NULL COMMENT 'ID',
    `create_time`            datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`            datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `user_id`                varchar(64)           DEFAULT '0' COMMENT '用户ID',
    `user_name`              varchar(255) NOT NULL COMMENT '用户名称',
    `action_type`            varchar(255) NOT NULL COMMENT '操作类型',
    `action_data`            LONGTEXT COMMENT '操作数据',
    `action_status`          varchar(255) NOT NULL COMMENT '操作状态',
    `action_failure_details` text         NOT NULL COMMENT '失败详情',
    PRIMARY KEY (`id`)
) COMMENT = '审计日志';

/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_user_avatar   */
/******************************************/
CREATE TABLE `sys_user_avatar`
(
    `id`          varchar(64) NOT NULL COMMENT 'ID',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `user_avatar` longtext    NOT NULL COMMENT '头像地址',
    PRIMARY KEY (`id`),
    KEY `idx_sys_user_avatar_id` (`id`)
) COMMENT = '用户头像';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_user_role   */
/******************************************/
CREATE TABLE `sys_user_role`
(
    `id`          varchar(64) NOT NULL COMMENT 'ID',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `user_id`     varchar(64) NOT NULL COMMENT '用户编号',
    `role_id`     varchar(64) NOT NULL COMMENT '角色编号',
    PRIMARY KEY (`id`)
) COMMENT = '用户-角色';
/******************************************/
/*   DatabaseName = base_manager   */
/*   TableName = sys_user_web_layout   */
/******************************************/
CREATE TABLE `sys_user_web_layout`
(
    `id`               varchar(64) NOT NULL COMMENT 'ID',
    `create_time`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `user_id`          varchar(64) NOT NULL COMMENT '用户ID',
    `layout_structure` text COMMENT '布局结构',
    PRIMARY KEY (`id`)
) COMMENT = '用户自定义PC Web端义布局';

CREATE TABLE `oauth2_domain`
(
    `id`               varchar(64)  NOT NULL COMMENT 'ID',
    `create_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    `oauth2_params_id` varchar(64)  NOT NULL,
    `domain_name`      varchar(255) NOT NULL,
    `domain_scheme`    varchar(31)  NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT = '';

CREATE TABLE `oauth2_mobile`
(
    `id`               varchar(64)   NOT NULL COMMENT 'ID',
    `create_time`      datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    `oauth2_params_id` varchar(64)   NOT NULL,
    `pkg_name`         varchar(255)  NOT NULL,
    `app_secret`       varchar(2048) NOT NULL,

    PRIMARY KEY (`id`)
) COMMENT = '';

CREATE TABLE `oauth2_params`
(
    `id`          varchar(64) NOT NULL COMMENT 'ID',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    `enabled`     tinyint(1)  NOT NULL DEFAULT 0,
    `user_id`     varchar(64) NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT = '';


CREATE TABLE `oauth2_params`
(
    `id`          varchar(64) NOT NULL COMMENT 'ID',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    `enabled`     tinyint(1)  NOT NULL DEFAULT 0,
    `user_id`     varchar(64) NOT NULL,
    PRIMARY KEY (`id`)
) COMMENT = '';


CREATE TABLE `oauth2_registration`
(
    `id`                             VARCHAR(64) NOT NULL COMMENT 'ID',
    `create_time`                    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`                    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `additional_info`                TEXT,
    `client_id`                      VARCHAR(255)         DEFAULT '-',
    `client_secret`                  VARCHAR(2048)        DEFAULT '-',
    `authorization_uri`              VARCHAR(255)         DEFAULT '-',
    `token_uri`                      VARCHAR(255)         DEFAULT '-',
    `scope`                          VARCHAR(255)         DEFAULT '-',
    `platforms`                      VARCHAR(255)         DEFAULT '-',
    `user_info_uri`                  VARCHAR(255)         DEFAULT '-',
    `user_name_attribute_name`       VARCHAR(255)         DEFAULT '-',
    `jwk_set_uri`                    VARCHAR(255)         DEFAULT '-',
    `client_authentication_method`   VARCHAR(255)         DEFAULT '-',
    `login_button_label`             VARCHAR(255)         DEFAULT '-',
    `login_button_icon`              VARCHAR(255)         DEFAULT '-',
    `allow_user_creation`            TINYINT(1)           DEFAULT 0,
    `activate_user`                  TINYINT(1)           DEFAULT 0,
    `type`                           VARCHAR(31)          DEFAULT '-',
    `basic_email_attribute_key`      VARCHAR(31)          DEFAULT '-',
    `basic_first_name_attribute_key` VARCHAR(31)          DEFAULT '-',
    `basic_last_name_attribute_key`  VARCHAR(31)          DEFAULT '-',
    `basic_tenant_name_strategy`     VARCHAR(31)          DEFAULT '-',
    `basic_tenant_name_pattern`      VARCHAR(255)         DEFAULT '-',
    `basic_customer_name_pattern`    VARCHAR(255)         DEFAULT '-',
    `basic_default_dashboard_name`   VARCHAR(255)         DEFAULT '-',
    `basic_always_full_screen`       TINYINT(1)           DEFAULT 0,
    `custom_url`                     VARCHAR(255)         DEFAULT '-',
    `custom_username`                VARCHAR(255)         DEFAULT '-',
    `custom_password`                VARCHAR(255)         DEFAULT '-',
    `custom_send_token`              tinyint(1),
    PRIMARY KEY (`id`)
) COMMENT = '';


-- 字典初始化数据
INSERT INTO `sys_dict` (`create_by`, `create_time`, `del_flag`, `remarks`, `id`, `dict_label`, `parent_id`,
                        `dict_sort`, `dict_type`, `update_by`, `update_time`, `dict_value`)
VALUES ('admin', '2020-11-08 10:31:00', '0', '明文密码', '1', '明文密码', '0', '0', 'rad_user_check_attribute', 'admin',
        '2020-11-08 18:21:13', 'Cleartext-Password');

-- 初始化管理员
INSERT INTO `sys_user` (`user_account`, `avatar_id`, `create_by`, `create_time`, `del_flag`, `disabled`, `user_email`,
                        `id`, `inst_id`, `inst_name`, `login_date`, `login_flag`, `login_ip`, `mobile`, `user_name`,
                        `user_phone`, `remarks`, `update_by`, `update_time`, `user_no`, `authority`, `additional_info`)
VALUES ('admin', '11', 'sys', '2020-10-18 15:26:26', '0', '0', 'tyuan.design', '1', null, null, '2021-04-06 20:50:46',
        null, '127.0.0.1', '15101626387', '超级系统管理员', '15101626387', 'sss1111',
        'admin', '2021-04-06 20:50:46', '', 'SYS_ADMIN', '{}');

INSERT INTO `sys_user_credentials` (`id`, activate_token, `password`, user_id)
VALUES ('1', '', MD5('tyuan'), '1');

select MD5('123');

-- 初始化用户布局
INSERT INTO `sys_user_web_layout` (`id`, `layout_structure`, `user_id`)
VALUES ('1', '{"layout":"mix","navTheme":"light"}', '1');

INSERT INTO `sys_param` (`create_by`, `create_time`, `id`, `is_sys`, `param_key`, `param_name`, `param_val`, `remarks`,
                         `update_by`, `update_time`)
VALUES ('admin', '2020-09-19 23:11:55', '1', '1', 'login.expires', '登陆有效时间', '20000', '秒为单位1', '超级系统管理员',
        '2021-03-19 16:35:09');
INSERT INTO `sys_param` (`create_by`, `create_time`, `id`, `is_sys`, `param_key`, `param_name`, `param_val`, `remarks`,
                         `update_by`, `update_time`)
VALUES ('超级系统管理员', '2021-04-04 14:13:12', '18', '1', 'sys.watermark', '水印管理',
        '{}', null,
        '超级系统管理员', '2021-04-06 19:40:42');

-- 初始化权限
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('1', '系统管理', '0', 'sys', null, '0');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('2', '用户管理', '1', 'sys:user', null, '1001');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('3', '用户列表', '2', 'sys:user:list', null, '10011');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('4', '添加用户', '2', 'sys:user:add', null, '10012');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('5', '修改用户', '2', 'sys:user:edit', null, '10013');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('6', '删除用户', '2', 'sys:user:del', null, '10014');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('7', '角色管理', '1', 'sys:role', null, '1002');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('8', '角色列表', '7', 'sys:role:list', null, '10021');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('9', '添加角色', '7', 'sys:role:add', null, '10022');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('10', '修改角色', '7', 'sys:role:edit', null, '10023');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('11', '删除角色', '7', 'sys:role:del', null, '10024');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('12', '资源管理', '1', 'sys:source', null, '1003');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('13', '资源列表', '12', 'sys:source:list', null, '10031');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('14', '添加资源', '12', 'sys:source:add', null, '10032');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('15', '修改资源', '12', 'sys:source:edit', null, '10033');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('16', '删除资源', '12', 'sys:source:del', null, '10034');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('17', '验证用户', '-1', 'authc', null, '10');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('18', '权限列表', '1', 'sys:permission', null, '1004');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('19', '字典管理', '1', 'sys:dict', null, '1005');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('20', '字典列表', '19', 'sys:dict:list', null, '10051');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('21', '添加字典', '19', 'sys:dict:add', null, '10052');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('22', '修改字典', '19', 'sys:dict:edit', null, '10053');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('23', '删除字典', '19', 'sys:dict:del', null, '10054');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('24', '系统监控', '0', 'monitor', null, '1006');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('25', 'redis监控', '24', 'monitor:redis', null, '10061');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('26', 'redis-key列表', '25', 'monitor:redis:keys', null, '100611');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('27', '编辑角色权限', '7', 'sys:role:permission:edit', null, '10025');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('28', 'l2tp管理', '0', 'rad', null, '101');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('29', 'l2tp用户列表', '28', 'rad:user:list', null, '1007');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('30', '用户组管理', '28', 'rad:group', null, '1002');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('31', '用户组列表', '28', 'rad:group:list', null, '1003');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('32', '添加用户组', '28', 'rad:group:add', null, '1004');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('33', '修改用户组', '28', 'rad:group:edit', null, '1005');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('34', '删除用户组', '28', 'rad:group:del', null, '1006');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('35', '添加用户', '28', 'rad:user:add', null, '1008');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('36', '修改用户', '28', 'rad:user:edit', null, '1009');
INSERT INTO `sys_permission` (`id`, `permission_name`, `parent_id`, `permission`, `remarks`, `permission_sort`)
VALUES ('37', '删除用户', '28', 'rad:user:del', null, '1010');

-- 初始化角色
INSERT INTO `sys_role` (`role_code`, `create_by`, `create_time`, `del_flag`, `id`, `is_sys`, `role_name`, `remarks`,
                        `update_by`, `update_time`, `useable`)
VALUES ('admin', 'sys', '2020-10-18 15:26:25', '0', '1', '1', '超级系统管理', '123211123', '超级系统管理员', '2021-03-19 13:20:11',
        '1');
INSERT INTO `sys_role` (`role_code`, `create_by`, `create_time`, `del_flag`, `id`, `is_sys`, `role_name`, `remarks`,
                        `update_by`, `update_time`, `useable`)
VALUES ('DEFAULTROLE', '超级系统管理员', '2021-03-20 14:10:56', '0', '19', '0', '默认角色', null, '超级系统管理员', '2021-03-20 14:10:56',
        '1');
INSERT INTO `sys_role` (`role_code`, `create_by`, `create_time`, `del_flag`, `id`, `is_sys`, `role_name`, `remarks`,
                        `update_by`, `update_time`, `useable`)
VALUES ('BOMEIZHUGUAN', '超级系统管理员', '2021-03-20 14:11:10', '0', '20', '0', '部门主管', null, '超级系统管理员',
        '2021-03-20 14:11:10', '1');
INSERT INTO `sys_role` (`role_code`, `create_by`, `create_time`, `del_flag`, `id`, `is_sys`, `role_name`, `remarks`,
                        `update_by`, `update_time`, `useable`)
VALUES ('QUYUJINGLI', '超级系统管理员', '2021-03-20 14:11:35', '0', '21', '0', '区域负责人', null, '超级系统管理员', '2021-03-20 14:11:35',
        '1');
INSERT INTO `sys_role` (`role_code`, `create_by`, `create_time`, `del_flag`, `id`, `is_sys`, `role_name`, `remarks`,
                        `update_by`, `update_time`, `useable`)
VALUES ('ZHONGGONGSHIFUZHE', '超级系统管理员', '2021-03-20 14:12:03', '0', '22', '0', '总公司负责人', null, '超级系统管理员',
        '2021-03-20 14:12:03', '1');

-- 初始化角色权限
INSERT INTO `sys_role_permission` (`id`, `permission_id`, `role_id`)
VALUES ('315', '29', '22');

-- 初始化菜单
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('sys', '2020-10-18 15:26:25', '0', '/', 'DashboardOutlined', '1', '1', '1', '控制台', '0', '17', '', '0', null,
        '超级系统管理员', '2021-04-05 12:48:43');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('sys', '2020-10-18 15:26:25', '0', '/', 'KeyOutlined', '2', '0', '1', '权限管理', '0', '17', '0', '800001', null,
        '超级系统管理员', '2021-03-16 20:35:36');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('sys', '2020-10-18 15:26:25', '0', '/auth/user', null, '3', '1', '1', '用户管理', '2', '3', null, '800011', null,
        '超级系统管理员', '2021-03-19 00:55:10');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('sys', '2020-10-18 15:26:25', '0', '/auth/role', null, '4', '1', '1', '角色管理', '2', '8', null, '800012', null,
        'admin', '2020-12-22 18:07:01');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('sys', '2020-10-18 15:26:25', '0', '/monitor', null, '5', '0', '1', '系统监控', '0', '17', null, '900001', null,
        'sys', '2020-10-18 15:26:25');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('sys', '2020-10-18 15:26:25', '0', '/', 'SettingOutlined', '7', '0', '1', '系统配置', '0', '17', null, '1000001',
        null, '超级系统管理员', '2021-03-16 20:35:43');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('sys', '2020-10-18 15:26:25', '0', '/sys/dict', null, '8', '1', '1', '字典管理', '7', '20', null, '1000011', null,
        'admin', '2020-10-18 16:28:38');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('sys', '2020-10-18 15:26:25', '0', '/sys/source', null, '9', '1', '1', '资源管理', '7', '13', null, '1000012', null,
        'admin', '2020-10-18 16:28:46');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('sys', '2020-10-18 15:26:25', '0', '/sys/param', null, '10', '1', '1', '参数管理', '7', '13', null, '1000013', null,
        'test', '2020-12-23 00:56:00');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('admin', '2021-02-04 19:23:22', '0', '/sys/log', null, '20', '1', '1', '系统日志', '31', '17', null, '1000014', '',
        '超级系统管理员', '2021-04-04 10:17:56');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('超级系统管理员', '2021-03-15 21:30:04', '0', '/', 'ApartmentOutlined', '25', '0', '1', '组织机构', '0', '17', null, '1',
        '', '超级系统管理员', '2021-04-05 12:51:22');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('超级系统管理员', '2021-03-15 21:32:55', '0', '/organization/institution', null, '26', '1', '1', '机构管理', '25', '17',
        null, '0', '', '超级系统管理员', '2021-03-16 18:05:57');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('超级系统管理员', '2021-03-23 14:28:27', '0', '/sys/login/log', null, '30', '1', '1', '登陆日志', '31', '17', null,
        '1000015', '', '超级系统管理员', '2021-04-04 10:18:03');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('超级系统管理员', '2021-04-04 10:17:38', '0', '/', null, '31', '0', '1', '日志管理', '7', '17', null, '1000099', '',
        '超级系统管理员', '2021-04-04 10:17:38');
INSERT INTO `sys_source` (`create_by`, `create_time`, `del_flag`, `source_href`, `source_icon`, `id`, `is_leaf`,
                          `is_show`, `source_name`, `parent_id`, `permission_id`, `remarks`, `source_sort`,
                          `source_target`, `update_by`, `update_time`)
VALUES ('超级系统管理员', '2021-04-04 11:03:57', '0', '/sys/watermark', null, '32', '1', '1', '水印管理', '7', '17', null,
        '1000011', '', '超级系统管理员', '2021-04-04 11:03:57');

-- 初始化机构
INSERT INTO `organization_institution` (`create_time`, `id`, `inst_code`, `remarks`, `inst_name`, `inst_sort`,
                                        `inst_status`, `inst_type`, `owner_user_id`, `parent_id`, `update_time`)
VALUES ('2021-03-16 18:05:01', '9', 'H0001', null, '总公司', '1000', '0', '0', '1', '0', '2021-03-16 18:05:01');
