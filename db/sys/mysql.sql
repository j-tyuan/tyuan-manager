use vpn_manage;
/* Drop Tables */

DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_role;

DROP TABLE IF EXISTS sys_permission;
DROP TABLE IF EXISTS sys_role_permission;

DROP TABLE IF EXISTS sys_source;

DROP TABLE IF EXISTS sys_mdict;
DROP TABLE IF EXISTS sys_area;
DROP TABLE IF EXISTS sys_dict;
DROP TABLE IF EXISTS sys_log;

DROP TABLE IF EXISTS sys_param;

/* Create Tables */
CREATE TABLE sys_area
(
    id          bigint(20) unsigned  NOT NULL AUTO_INCREMENT COMMENT 'id',
    create_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    parent_id   bigint(20) unsigned  NOT NULL COMMENT '父级编号',
    name        varchar(100)         NOT NULL COMMENT '名称',
    sort        decimal(10, 0)       NOT NULL COMMENT '排序',
    code        varchar(100) COMMENT '区域编码',
    type        TINYINT(1) COMMENT '区域类型',
    create_by   varchar(64)          NOT NULL COMMENT '创建者',
    update_by   varchar(64)          NOT NULL COMMENT '更新者',
    remarks     varchar(255) COMMENT '备注信息',
    del_flag    TINYINT(1) DEFAULT 0 NOT NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '区域表';


CREATE TABLE sys_dict
(
    id          bigint(20) unsigned           NOT NULL AUTO_INCREMENT COMMENT 'id',
    create_date TIMESTAMP           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_date TIMESTAMP           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    value       varchar(100)                  NOT NULL COMMENT '数据值',
    label       varchar(100)                  NOT NULL COMMENT '标签名',
    type        varchar(100)                  NOT NULL COMMENT '类型',
    description varchar(100)                  NOT NULL COMMENT '描述',
    sort        decimal(10, 0)                NOT NULL COMMENT '排序（升序）',
    parent_id   bigint(20) unsigned DEFAULT 0 COMMENT '父级编号',
    create_by   varchar(64)                   NOT NULL COMMENT '创建者',
    update_by   varchar(64)                   NOT NULL COMMENT '更新者',
    remarks     varchar(255) COMMENT '备注信息',
    del_flag    TINYINT(1)          DEFAULT 0 NOT NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '字典表';


CREATE TABLE sys_log
(
    id          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    create_date TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    type        TINYINT(1)   DEFAULT '1' COMMENT '日志类型',
    title       varchar(255) DEFAULT '' COMMENT '日志标题',
    create_by   varchar(64)         NOT NULL COMMENT '创建者',
    remote_addr varchar(255) COMMENT '操作IP地址',
    user_agent  varchar(255) COMMENT '用户代理',
    request_uri varchar(255) COMMENT '请求URI',
    method      varchar(5) COMMENT '操作方式',
    params      text COMMENT '操作提交的数据',
    exception   text COMMENT '异常信息',
    PRIMARY KEY (id)
) COMMENT = '日志表';


CREATE TABLE sys_mdict
(
    id          bigint(20) unsigned  NOT NULL AUTO_INCREMENT COMMENT 'id',
    create_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    parent_id   bigint(20) unsigned  NOT NULL COMMENT '父级编号',
    name        varchar(100)         NOT NULL COMMENT '名称',
    sort        decimal(10, 0)       NOT NULL COMMENT '排序',
    description varchar(100) COMMENT '描述',
    create_by   varchar(64)          NOT NULL COMMENT '创建者',
    update_by   varchar(64)          NOT NULL COMMENT '更新者',
    remarks     varchar(255) COMMENT '备注信息',
    del_flag    TINYINT(1) DEFAULT 0 NOT NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '多级字典表';



CREATE TABLE sys_source
(
    id            bigint(20) unsigned           NOT NULL AUTO_INCREMENT COMMENT 'id',
    create_date   TIMESTAMP           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_date   TIMESTAMP           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    parent_id     bigint(20) unsigned           NOT NULL COMMENT '父级编号',
    name          varchar(100)                  NOT NULL COMMENT '名称',
    sort          decimal(10, 0)      DEFAULT 0 COMMENT '排序',
    href          varchar(2000) COMMENT '链接',
    target        varchar(20) COMMENT '目标',
    icon          varchar(100) COMMENT '图标',
    is_leaf       TINYINT(1)          DEFAULT 0 NOT NULL COMMENT '是否叶子节点',
    is_show       TINYINT(1)                    NOT NULL COMMENT '是否在菜单中显示',
    permission_id bigint(20) unsigned default 0 COMMENT '权限标识',
    create_by     varchar(64)                   NOT NULL COMMENT '创建者',
    update_by     varchar(64)                   NOT NULL COMMENT '更新者',
    remarks       varchar(255) COMMENT '备注信息',
    del_flag      TINYINT(1)          DEFAULT 0 NOT NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '菜单表';

CREATE TABLE sys_role
(
    id          bigint(20) unsigned  NOT NULL AUTO_INCREMENT COMMENT 'id',
    create_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    name        varchar(100)         NOT NULL COMMENT '角色名称',
    en_name     varchar(255) COMMENT '英文名称',
    is_sys      TINYINT(1) default 0 not null COMMENT '是否系统数据',
    useable     TINYINT(1) default 0 not null COMMENT '是否可用',
    create_by   varchar(64)          NOT NULL COMMENT '创建者',
    update_by   varchar(64)          NOT NULL COMMENT '更新者',
    remarks     varchar(255) COMMENT '备注信息',
    del_flag    TINYINT(1) DEFAULT 0 NOT NULL COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT = '角色表';


create table sys_permission
(
    id         bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    parent_id  bigint(20)          NOT NULL COMMENT '父级编号',
    permission varchar(100)        NOT NULL COMMENT '权限',
    sort       decimal(10, 0) DEFAULT 0 COMMENT '排序',
    name       varchar(100)        NOT NULL COMMENT '权限',
    remarks    varchar(255) COMMENT '备注信息',
    PRIMARY KEY (id)
);

CREATE TABLE sys_role_permission
(
    id            bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    role_id       bigint(20) unsigned NOT NULL COMMENT '角色编号',
    permission_id bigint(20) unsigned NOT NULL COMMENT '权限ID',
    PRIMARY KEY (id)
) COMMENT = '角色-权限';


CREATE TABLE sys_user
(
    id          bigint(20) unsigned  NOT NULL AUTO_INCREMENT COMMENT 'id',
    create_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    account     varchar(100)         NOT NULL COMMENT '登录名',
    password    varchar(100)         NOT NULL COMMENT '密码',
    no          varchar(100) COMMENT '工号',
    name        varchar(100)         NOT NULL COMMENT '姓名',
    email       varchar(200) COMMENT '邮箱',
    phone       varchar(200) COMMENT '电话',
    mobile      varchar(200) COMMENT '手机',
    user_type   TINYINT(1) COMMENT '用户类型',
    photo       varchar(1000) COMMENT '用户头像',
    login_ip    varchar(100) COMMENT '最后登陆IP',
    login_date  datetime COMMENT '最后登陆时间',
    login_flag  varchar(64) COMMENT '是否可登录',
    create_by   varchar(64)          NOT NULL COMMENT '创建者',
    update_by   varchar(64)          NOT NULL COMMENT '更新者',
    remarks     varchar(255) COMMENT '备注信息',
    del_flag    TINYINT(1) DEFAULT 0 NOT NULL COMMENT '删除标记',
    disable     TINYINT(1) DEFAULT 0 NOT NULL COMMENT '是否启用',
    PRIMARY KEY (id)
) COMMENT = '用户表';


CREATE TABLE sys_user_role
(
    id      bigint(20) unsigned not null AUTO_INCREMENT COMMENT 'id',
    user_id bigint(20) unsigned NOT NULL COMMENT '用户编号',
    role_id bigint(20) unsigned NOT NULL COMMENT '角色编号',
    PRIMARY KEY (id)
) COMMENT = '用户-角色';


CREATE TABLE sys_param
(
    id          bigint(20) unsigned  not null AUTO_INCREMENT COMMENT 'id',
    create_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    param_name  varchar(255) COMMENT '参数名称',
    param_key   varchar(255) COMMENT '参数key',
    param_val   text COMMENT '参数值',
    is_sys      TINYINT(1) DEFAULT 0 NOT NULL COMMENT '是否系统参数',
    create_by   varchar(64)          NOT NULL COMMENT '创建者',
    update_by   varchar(64)          NOT NULL COMMENT '更新者',
    remarks     varchar(1024) COMMENT '备注信息',
    PRIMARY KEY (id)
) COMMENT = '参数表 - 用于动态配置一些项目';

