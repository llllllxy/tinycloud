/*
 Navicat MySQL Data Transfer

 Source Server         : 个人-本机-127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : nacos-tinycloud

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 04/06/2023 21:59:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'tinycloud-common-dev.yml', 'DEFAULT_GROUP', 'logging:\n  config: classpath:logback-config.xml\nlog:\n  # 日志输出格式\n  pattern: \"%d{yyyy-MM-dd} %d{HH:mm:ss.SSS} [%thread] %-5level [%X{traceId}]  %logger{20} - [%method,%line] - %msg%n\"\n  # 日志输出级别\n  root:\n    level: info\n  # 日志最大历史天数\n  max-history: 60\n  # 日志总文件大小\n  total-size-cap: 10GB\n\nspring:\n  # 设置上传文件大小\n  servlet:\n    multipart:\n      max-file-size: 20MB\n      max-request-size: 20MB\n  mvc:\n    throw-exception-if-no-handler-found: true\n  # 静态资源配置\n  resources:\n    add-mappings: false\n    static-locations: classpath:/static/\n  # 设置jackson的默认时区和默认格式\n  jackson:\n    time-zone: GMT+8\n    date-format: yyyy-MM-dd HH:mm:ss\n  # redis 配置\n  redis:\n    #默认数据库\n    database: 0\n    # 地址\n    host: 127.0.0.1\n    # 端口，默认为6379\n    port: 6379\n    # 密码\n    password: 123654\n    # 连接超时时间\n    timeout: 10s\n    lettuce:\n      pool:\n        # 连接池中的最小空闲连接\n        min-idle: 8\n        # 连接池中的最大空闲连接\n        max-idle: 8\n        # 连接池的最大数据库连接数\n        max-active: 12\n        # #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 5s\n  # 数据库配置\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/tinycloud?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8\n    username: root\n    password: 123654\n    # druid连接池\n    type: com.alibaba.druid.pool.DruidDataSource\n    # 数据库驱动\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    druid:\n      # 初始连接数\n      initial-size: 5\n      # 最小连接池数量\n      min-idle: 10\n      # 最大连接池数量\n      max-active: 20\n      # 配置获取连接等待超时的时间\n      max-wait: 60000\n      # 配置连接超时时间\n      connect-timeout: 30000\n      # 配置网络超时时间\n      socket-timeout: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      time-between-eviction-runs-millis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      min-evictable-idle-time-millis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      max-evictable-idle-time-millis: 900000\n      # 配置检测连接是否有效\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      web-stat-filter:\n        enabled: true\n      stat-view-servlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username: admin\n        login-password: 123456\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n\n# MyBatis-plus配置 映射xml文件\nmybatis-plus:\n  mapper-locations: classpath*:mapper/**/*Mapper.xml\n  # 起别名\n  type-aliases-package: org.liuxingyu.**.entity\n  # 去除打印\n  global-config:\n    banner: false\n  # 驼峰命名匹配\n  configuration:\n    map-underscore-to-camel-case: true\n    # 设置在控制台打印sql语句\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\n# Feign调用配置\nfeign:\n  client:\n    config:\n      default:\n        connectTimeout: 5000\n        readTimeout: 5000\n  hystrix:\n    enabled: true\n  httpclient:\n    enabled: true\n    max-connections: 200\n    max-connections-per-route: 50', 'd2e9bae4071a328c6b234159f5b8cf3d', '2023-06-04 02:35:28', '2023-06-04 02:35:28', NULL, '192.168.0.103', '', '', '', NULL, NULL, 'yaml', NULL, '');
INSERT INTO `config_info` VALUES (2, 'tinycloud-user-dev.yml', 'DEFAULT_GROUP', '# 项目相关配置\ntinycloud:\n  # 名称\n  name: tinycloud-user\n  # 版本\n  version: 1.0.0\n\n# Tomcat\nserver:\n  port: 8081\n  servlet:\n    context-path: /user-server', 'a4619617f27da878a6928ebb3d377e5d', '2023-06-04 02:35:28', '2023-06-04 02:52:25', 'nacos', '192.168.0.103', '', '', '', '', '', 'yaml', '', '');
INSERT INTO `config_info` VALUES (5, 'tinycloud-message-dev.yml', 'DEFAULT_GROUP', '# 项目相关配置\ntinycloud:\n  # 名称\n  name: tinycloud-message\n  # 版本\n  version: 1.0.0\n\n# Tomcat\nserver:\n  port: 8083\n  servlet:\n    context-path: /message-server', 'ad6d74d6634cc35fce91638823d68bf0', '2023-06-04 02:54:15', '2023-06-04 02:56:08', 'nacos', '192.168.0.103', '', '', '', '', '', 'yaml', '', '');

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '秘钥',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 1, 'tinycloud-common-dev.yml', 'DEFAULT_GROUP', '', 'logging:\n  config: classpath:logback-config.xml\nlog:\n  # 日志输出格式\n  pattern: \"%d{yyyy-MM-dd} %d{HH:mm:ss.SSS} [%thread] %-5level [%X{traceId}]  %logger{20} - [%method,%line] - %msg%n\"\n  # 日志输出级别\n  root:\n    level: info\n  # 日志最大历史天数\n  max-history: 60\n  # 日志总文件大小\n  total-size-cap: 10GB\n\nspring:\n  # 设置上传文件大小\n  servlet:\n    multipart:\n      max-file-size: 20MB\n      max-request-size: 20MB\n  mvc:\n    throw-exception-if-no-handler-found: true\n  # 静态资源配置\n  resources:\n    add-mappings: false\n    static-locations: classpath:/static/\n  # 设置jackson的默认时区和默认格式\n  jackson:\n    time-zone: GMT+8\n    date-format: yyyy-MM-dd HH:mm:ss\n  # redis 配置\n  redis:\n    #默认数据库\n    database: 0\n    # 地址\n    host: 127.0.0.1\n    # 端口，默认为6379\n    port: 6379\n    # 密码\n    password: 123654\n    # 连接超时时间\n    timeout: 10s\n    lettuce:\n      pool:\n        # 连接池中的最小空闲连接\n        min-idle: 8\n        # 连接池中的最大空闲连接\n        max-idle: 8\n        # 连接池的最大数据库连接数\n        max-active: 12\n        # #连接池最大阻塞等待时间（使用负值表示没有限制）\n        max-wait: 5s\n  # 数据库配置\n  datasource:\n    url: jdbc:mysql://127.0.0.1:3306/tinycloud?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8\n    username: root\n    password: 123654\n    # druid连接池\n    type: com.alibaba.druid.pool.DruidDataSource\n    # 数据库驱动\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    druid:\n      # 初始连接数\n      initial-size: 5\n      # 最小连接池数量\n      min-idle: 10\n      # 最大连接池数量\n      max-active: 20\n      # 配置获取连接等待超时的时间\n      max-wait: 60000\n      # 配置连接超时时间\n      connect-timeout: 30000\n      # 配置网络超时时间\n      socket-timeout: 60000\n      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒\n      time-between-eviction-runs-millis: 60000\n      # 配置一个连接在池中最小生存的时间，单位是毫秒\n      min-evictable-idle-time-millis: 300000\n      # 配置一个连接在池中最大生存的时间，单位是毫秒\n      max-evictable-idle-time-millis: 900000\n      # 配置检测连接是否有效\n      validation-query: SELECT 1 FROM DUAL\n      test-while-idle: true\n      test-on-borrow: false\n      test-on-return: false\n      web-stat-filter:\n        enabled: true\n      stat-view-servlet:\n        enabled: true\n        # 设置白名单，不填则允许所有访问\n        allow:\n        url-pattern: /druid/*\n        # 控制台管理用户名和密码\n        login-username: admin\n        login-password: 123456\n      filter:\n        stat:\n          enabled: true\n          # 慢SQL记录\n          log-slow-sql: true\n          slow-sql-millis: 1000\n          merge-sql: true\n        wall:\n          config:\n            multi-statement-allow: true\n\n# MyBatis-plus配置 映射xml文件\nmybatis-plus:\n  mapper-locations: classpath*:mapper/**/*Mapper.xml\n  # 起别名\n  type-aliases-package: org.liuxingyu.**.entity\n  # 去除打印\n  global-config:\n    banner: false\n  # 驼峰命名匹配\n  configuration:\n    map-underscore-to-camel-case: true\n    # 设置在控制台打印sql语句\n    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl\n\n# Feign调用配置\nfeign:\n  client:\n    config:\n      default:\n        connectTimeout: 5000\n        readTimeout: 5000\n  hystrix:\n    enabled: true\n  httpclient:\n    enabled: true\n    max-connections: 200\n    max-connections-per-route: 50', 'd2e9bae4071a328c6b234159f5b8cf3d', '2023-06-04 10:35:27', '2023-06-04 02:35:28', NULL, '192.168.0.103', 'I', '', '');
INSERT INTO `his_config_info` VALUES (0, 2, 'tinycloud-user-dev.yml', 'DEFAULT_GROUP', '', '# 项目相关配置\ntinycloud:\n  # 名称\n  name: tinycloud-user\n  # 版本\n  version: 1.0.0\n\n# Tomcat\nserver:\n  port: 8081\n  servlet:\n    context-path: /user-server', 'a4619617f27da878a6928ebb3d377e5d', '2023-06-04 10:35:27', '2023-06-04 02:35:28', NULL, '192.168.0.103', 'I', '', '');
INSERT INTO `his_config_info` VALUES (2, 3, 'tinycloud-user-dev.yml', 'DEFAULT_GROUP', '', '# 项目相关配置\ntinycloud:\n  # 名称\n  name: tinycloud-user\n  # 版本\n  version: 1.0.0\n\n# Tomcat\nserver:\n  port: 8081\n  servlet:\n    context-path: /user-server', 'a4619617f27da878a6928ebb3d377e5d', '2023-06-04 10:51:39', '2023-06-04 02:51:40', 'nacos', '192.168.0.103', 'U', '', '');
INSERT INTO `his_config_info` VALUES (2, 4, 'tinycloud-user-dev.yml', 'DEFAULT_GROUP', '', '# 项目相关配置\ntinycloud:\n  # 名称\n  name: tinycloud-user\n  # 版本\n  version: 1.0.0\n\n# Tomcat\nserver:\n  port: 8091\n  servlet:\n    context-path: /user-server', 'b10ad471b31ba8eb2c2fe20c35bc41b6', '2023-06-04 10:52:24', '2023-06-04 02:52:25', 'nacos', '192.168.0.103', 'U', '', '');
INSERT INTO `his_config_info` VALUES (0, 5, 'tinycloud-message-dev.yml', 'DEFAULT_GROUP', '', '# 项目相关配置\r\ntinycloud:\r\n  # 名称\r\n  name: tinycloud-message\r\n  # 版本\r\n  version: 1.0.0\r\n\r\n# Tomcat\r\nserver:\r\n  port: 8083\r\n  servlet:\r\n    context-path: /message-server', 'dedae34d8e9222c1d9d6e255c5e71a0b', '2023-06-04 10:54:14', '2023-06-04 02:54:15', 'nacos', '192.168.0.103', 'I', '', '');
INSERT INTO `his_config_info` VALUES (5, 6, 'tinycloud-message-dev.yml', 'DEFAULT_GROUP', '', '# 项目相关配置\r\ntinycloud:\r\n  # 名称\r\n  name: tinycloud-message\r\n  # 版本\r\n  version: 1.0.0\r\n\r\n# Tomcat\r\nserver:\r\n  port: 8083\r\n  servlet:\r\n    context-path: /message-server', 'dedae34d8e9222c1d9d6e255c5e71a0b', '2023-06-04 10:55:26', '2023-06-04 02:55:27', 'nacos', '192.168.0.103', 'U', '', '');
INSERT INTO `his_config_info` VALUES (5, 7, 'tinycloud-message-dev.yml', 'DEFAULT_GROUP', '', '# 项目相关配置\ntinycloud:\n  # 名称\n  name: tinycloud-message\n  # 版本\n  version: 1.0.0\n\n# Tomcat\nserver:\n  port: 8093\n  servlet:\n    context-path: /message-server', '9231ff242be374861a8f9fe1f39bd0bb', '2023-06-04 10:56:08', '2023-06-04 02:56:08', 'nacos', '192.168.0.103', 'U', '', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
