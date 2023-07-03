# CREATE DATABASE `sky_eye_system`;
# USE `sky_eye_system`;

CREATE TABLE base_entity
(
    id          BIGINT AUTO_INCREMENT COMMENT 'ID',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间',
    deleted     INT DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT ='模板';

CREATE TABLE user
(
    id                BIGINT AUTO_INCREMENT COMMENT '用户ID',
    create_time       DATETIME COMMENT '创建时间',
    update_time       DATETIME COMMENT '更新时间',
    deleted           INT          DEFAULT 0 COMMENT '删除标记',
    username          VARCHAR(255) COMMENT '用户名',
    password          VARCHAR(255) COMMENT '密码',
    email             VARCHAR(255) COMMENT '电子邮件',
    phone             VARCHAR(255) COMMENT '手机号码',
    nick_name         VARCHAR(255) COMMENT '昵称',
    registration_date DATETIME COMMENT '注册日期',
    last_login_date   DATETIME COMMENT '最后登录日期',
    last_login_ip     VARCHAR(255) DEFAULT '' COMMENT '最后登录IP',
    user_type         INT          DEFAULT 0 COMMENT '用户类型',
    status            INT          DEFAULT 0 COMMENT '用户状态',
    PRIMARY KEY (id)
) COMMENT ='用户表';

-- 角色表
CREATE TABLE role
(
    id          BIGINT AUTO_INCREMENT COMMENT 'ID',
    name        VARCHAR(255) NOT NULL COMMENT '角色名称',
    description TEXT COMMENT '描述',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间',
    deleted     INT DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT ='角色表';

-- 权限表
CREATE TABLE permission
(
    id          BIGINT AUTO_INCREMENT COMMENT 'ID',
    name        VARCHAR(255) NOT NULL COMMENT '权限名称',
    description TEXT COMMENT '描述',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间',
    deleted     INT DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT ='模板';

-- 角色权限关联表
CREATE TABLE role_permission
(
    id            BIGINT AUTO_INCREMENT COMMENT 'ID',
    role_id       BIGINT COMMENT '角色id',
    permission_id BIGINT comment '权限id',
    create_time   DATETIME COMMENT '创建时间',
    update_time   DATETIME COMMENT '更新时间',
    deleted       INT DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id),
    INDEX idx_permission_role_id (role_id) COMMENT '角色id (业务中通过角色id查权限)'
) COMMENT ='角色权限关联表';


-- 用户角色关联表
CREATE TABLE user_roles
(
    id          BIGINT AUTO_INCREMENT COMMENT 'ID',
    user_id     BIGINT COMMENT '用户id',
    role_id     BIGINT comment '角色id',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间',
    deleted     INT DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id),
    INDEX idx_user_roles_user_id (user_id) COMMENT '业务中通过userId查权限'
) COMMENT ='用户角色关联表';

CREATE TABLE hot_platform
(
    id          BIGINT AUTO_INCREMENT COMMENT 'ID',
    name        VARCHAR(255) NOT NULL COMMENT '平台名称',
    icon_url    VARCHAR(255) NOT NULL COMMENT '图标',
    description TEXT         NOT NULL COMMENT '平台描述',
    website     VARCHAR(255) NOT NULL COMMENT '官网',
    slogan      VARCHAR(255) NOT NULL COMMENT '口号',
    founder     VARCHAR(255) NOT NULL COMMENT '创始人',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间',
    deleted     INT DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT ='平台表';

INSERT INTO hot_platform(id, name, icon_url, description, website, slogan, founder)
values (4, '百度', '', '', '', '', ''),
       (5, 'CSDN', '', '', '', '', ''),
       (6, '头条', '', '', '', '', ''),
       (2, '微博', '', '', '', '', ''),
       (3, '知乎', '', '', '', '', ''),
       (7, '哔哩哔哩', '', '', '', '', ''),
       (10, '掘金', '', '', '', '', ''),
       (12, '36氪', '', '', '', '', ''),
       (11, '腾讯新闻', '', '', '', '', ''),
       (9, '少数派', '', '', '', '', '');

CREATE TABLE hot_spot
(
    id          BIGINT AUTO_INCREMENT COMMENT 'ID',
    platform_id BIGINT       NOT NULL COMMENT '平台id',
    `rank`      INT          NOT NULL COMMENT '排序',
    keyword     VARCHAR(255) NOT NULL COMMENT '关键字',
    url         TEXT         NOT NULL COMMENT '链接',
    image       VARCHAR(255) NOT NULL COMMENT '封面',
    hot_value   INT          NOT NULL COMMENT '热度',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间',
    deleted     INT DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) COMMENT ='热点表';

