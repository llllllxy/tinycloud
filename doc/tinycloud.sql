/*
 Navicat MySQL Data Transfer

 Source Server         : 个人-本机-127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : tinycloud

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 04/06/2023 21:59:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_ac_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_ac_dict`;
CREATE TABLE `t_ac_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `dict_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典ID',
  `dict_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典标识',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `dict_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典码',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
  `descript` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `order_num` tinyint(4) NULL DEFAULT 0 COMMENT '排序',
  `system_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '是否系统内置，0是，1否',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0--正常1--停用）',
  `del_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除状态（0--正常，1--已删除）',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统数据字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ac_dict
-- ----------------------------
INSERT INTO `t_ac_dict` VALUES (1, '1515144385559932928', 'test_code', '测试字典', '1', '1', '测试字典', 1, '1', '0', 0, '2022-04-16 09:45:46', '2022-04-16 09:45:46', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (2, '1515144385580904448', 'test_code', '测试字典', '3', '3', '测试字典', 2, '1', '0', 0, '2022-04-16 09:45:46', '2022-04-16 09:45:46', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (3, '1515145333120315392', 'banner_type', '轮播类型', '0', '默认', '轮播类型', 1, '1', '0', 0, '2022-04-16 09:49:32', '2022-04-16 09:49:32', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (4, '1515145333128704000', 'banner_type', '轮播类型', '1', '指定内容', '轮播类型', 2, '1', '0', 0, '2022-04-16 09:49:32', '2022-04-16 09:49:32', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (5, '1515145333132898304', 'banner_type', '轮播类型', '2', '指定链接', '轮播类型', 3, '1', '0', 0, '2022-04-16 09:49:32', '2022-04-16 09:49:32', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (6, '1515146812689408000', 'notice_type', '公告类型', '0', '默认', '公告类型', 1, '1', '0', 0, '2022-04-16 09:55:24', '2022-04-16 09:55:24', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (7, '1515146812697796608', 'notice_type', '公告类型', '1', '指定内容', '公告类型', 2, '1', '0', 0, '2022-04-16 09:55:24', '2022-04-16 09:55:24', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (8, '1515146812701990912', 'notice_type', '公告类型', '2', '指定链接', '公告类型', 3, '1', '0', 0, '2022-04-16 09:55:25', '2022-04-16 09:55:25', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (9, '1548655333098307584', 'sys_rule_type', '业务规则类型', '1', '是', '是否系统内置（1是 0否），系统内置的禁止删除', 1, '1', '0', 0, '2022-07-17 21:06:18', '2023-03-03 10:01:27', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (10, '1548655333115084800', 'sys_rule_type', '业务规则类型', '0', '否', '是否系统内置（1是 0否），系统内置的禁止删除', 2, '1', '0', 0, '2022-07-17 21:06:18', '2023-03-03 10:01:32', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (11, '1548657501037576192', 'sys_rule_status', '业务规则状态', '0', '正常', '状态（0--正常 1--停用）', 1, '1', '0', 0, '2022-07-17 21:14:55', '2023-03-03 10:01:36', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (12, '1548657501050159104', 'sys_rule_status', '业务规则状态', '1', '停用', '状态（0--正常 1--停用）', 2, '1', '0', 0, '2022-07-17 21:14:55', '2023-03-03 10:01:41', '1', NULL);
INSERT INTO `t_ac_dict` VALUES (13, '32132132131', 'user_sex', '性别', '0', '未知', '用户性别', 1, '1', '0', 0, '2022-04-15 15:41:52', '2022-04-15 15:42:17', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (14, '32132132132', 'user_sex', '性别', '1', '男', '用户性别', 2, '1', '0', 0, '2022-04-15 15:30:25', '2022-04-15 15:42:19', '0', NULL);
INSERT INTO `t_ac_dict` VALUES (15, '32132132133', 'user_sex', '性别', '2', '女', '用户性别', 3, '1', '0', 0, '2022-04-15 15:30:25', '2022-04-15 15:42:20', '0', NULL);
INSERT INTO `t_ac_dict` VALUES (16, '43123131212', 'itfc_key_status', '服务密钥状态', '0', '正常', '服务密钥的状态', 0, '1', '0', 0, '2022-04-15 22:55:56', '2022-04-15 22:56:38', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (17, '43123131299', 'itfc_key_status', '服务密钥状态', '1', '停用', '服务密钥的状态', 0, '1', '0', 0, '2022-04-15 22:55:56', '2022-04-15 22:56:38', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (18, '76763456334', 'menu_type', '菜单权限类型', '0', '模块', '菜单权限类型', 1, '1', '0', 0, '2022-04-15 15:31:05', '2022-04-15 15:34:16', '0', NULL);
INSERT INTO `t_ac_dict` VALUES (19, '76763456335', 'menu_type', '菜单权限类型', '1', '目录', '菜单权限类型', 2, '1', '0', 0, '2022-04-15 15:31:05', '2022-04-15 15:37:02', '0', NULL);
INSERT INTO `t_ac_dict` VALUES (20, '76763456336', 'menu_type', '菜单权限类型', '2', '菜单', '菜单权限类型', 3, '1', '0', 0, '2022-04-15 15:31:05', '2022-04-15 15:37:03', '0', NULL);
INSERT INTO `t_ac_dict` VALUES (21, '76763456337', 'menu_type', '菜单权限类型', '3', '按钮', '菜单权限类型', 4, '1', '0', 0, '2022-04-15 15:31:05', '2022-04-15 15:37:05', '0', NULL);
INSERT INTO `t_ac_dict` VALUES (22, '76763456339', 'login_status', '登录状态', '1', '失败', '登录日志的记录状态', 1, '1', '0', 0, '2022-05-04 18:11:59', '2022-05-04 18:12:04', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (23, '76763456340', 'login_status', '登录状态', '0', '成功', '登录日志的记录状态', 2, '1', '0', 0, '2022-04-15 15:36:12', '2022-04-15 15:38:26', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (24, '76763456341', 'permission_target', '权限菜单打开方式', '_blank', '外链', '菜单是内链/外链', 2, '1', '0', 0, '2022-04-15 15:39:24', '2022-04-15 15:40:11', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (25, '76763456342', 'permission_target', '权限菜单打开方式', '_self', '内链', '菜单是内链/外链', 1, '1', '0', 0, '2022-04-15 15:38:59', '2022-04-15 15:43:16', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (26, '76763456343', 'user_status', '用户状态', '0', '正常', '用户的禁/启用状态', 1, '1', '0', 0, '2022-04-15 15:40:20', '2022-04-15 15:41:26', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (27, '76763456344', 'user_status', '用户状态', '1', '锁定', '用户的禁/启用状态', 2, '1', '0', 0, '2022-04-15 15:40:41', '2022-04-15 15:41:29', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (28, '76763456345', 'quartz_data_status', '定时任务状态', '0', '正常', '定时任务的禁/启用状态', 1, '1', '0', 0, '2022-04-15 15:43:55', '2022-04-15 15:45:55', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (29, '76763456346', 'quartz_data_status', '定时任务状态', '1', '禁用', '定时任务的禁/启用状态', 2, '1', '0', 0, '2022-04-15 15:44:09', '2022-04-15 15:44:50', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (30, '76763456347', 'quartz_status', '定时任务启动状态', '0', '启动', '定时任务启动状态', 1, '1', '0', 0, '2022-04-15 15:46:19', '2022-04-15 15:47:02', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (31, '76763456348', 'quartz_status', '定时任务启动状态', '1', '停止', '定时任务启动状态', 2, '1', '0', 0, '2022-04-15 15:46:22', '2022-04-15 15:47:34', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (32, '76763456349', 'role_status', '角色状态', '0', '正常', '角色的禁/启用状态', 1, '1', '0', 0, '2022-04-15 15:49:32', '2022-04-15 15:50:21', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (33, '76763456350', 'role_status', '角色状态', '1', '停用', '角色的禁/启用状态', 1, '1', '0', 0, '2022-04-15 15:49:59', '2022-04-15 15:50:29', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (34, '76763456352', 'dict_status', '数据字典状态', '1', '停用', '数据字典的禁/启用状态', 2, '1', '0', 0, '2022-04-15 15:51:45', '2022-04-15 15:54:46', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (35, '76763456353', 'dict_status', '数据字典状态', '0', '正常', '数据字典的禁/启用状态', 1, '1', '0', 0, '2022-04-15 15:51:20', '2022-04-15 15:56:23', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (36, '76763456354', 'syspostinfo_status', '岗位状态', '0', '正常', NULL, 1, '1', '0', 0, '2022-04-15 15:55:13', '2022-04-15 15:57:15', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (37, '76763456355', 'syspostinfo_status', '岗位状态', '1', '停用', NULL, 2, '1', '0', 0, '2022-04-15 15:55:26', '2022-04-15 15:56:43', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (38, '76763456357', 'sys_job_group', '定时任务分组', 'SYSTEM', '系统', '定时任务分组', 2, '1', '0', 0, '2022-04-15 22:50:46', '2022-04-15 22:51:09', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (39, '76763456358', 'sys_job_group', '定时任务分组', 'DEFAULT', '默认', '定时任务分组', 1, '1', '0', 0, '2022-04-15 22:49:58', '2022-04-15 22:52:55', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (40, '76763456360', 'sys_job_status', '定时任务状态', '0', '正常', '定时任务状态', 0, '1', '0', 0, '2022-04-15 22:53:11', '2022-04-15 22:55:21', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (41, '76763456361', 'sys_job_status', '定时任务状态', '1', '暂停', '定时任务状态', 0, '1', '0', 0, '2022-04-15 22:54:46', '2022-04-15 22:55:20', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (42, '76763456390', 'sysdeptinfo_status', '部门状态', '0', '正常', NULL, 1, '1', '0', 0, '2022-04-15 15:55:13', '2022-04-15 15:57:15', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (43, '76763456391', 'sysdeptinfo_status', '部门状态', '1', '停用', NULL, 2, '1', '0', 0, '2022-04-15 15:55:13', '2022-04-15 15:57:15', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (44, '79554426699', 'car_type', '车辆类型', '01', '汽油车', NULL, 1, '1', '0', 0, '2023-03-03 10:13:01', '2023-03-03 10:14:01', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (45, '79554426600', 'car_type', '车辆类型', '02', '柴油车', NULL, 2, '1', '0', 0, '2023-03-03 10:13:33', '2023-03-03 10:14:03', NULL, NULL);
INSERT INTO `t_ac_dict` VALUES (46, '79554426696', 'car_type', '车辆类型', '03', '新能源车', NULL, 3, '1', '0', 0, '2023-03-03 10:14:00', '2023-03-03 10:14:06', NULL, NULL);

-- ----------------------------
-- Table structure for t_ac_idtable
-- ----------------------------
DROP TABLE IF EXISTS `t_ac_idtable`;
CREATE TABLE `t_ac_idtable`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键，内码',
  `id_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务流水号id',
  `id_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务流水号标识编码',
  `id_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务流水号名称',
  `id_value` int(11) NOT NULL COMMENT '业务流水号当前值',
  `id_length` int(11) NOT NULL COMMENT '业务流水号长度',
  `has_prefix` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否有前缀 0有，1没有',
  `id_prefix` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务流水号前缀',
  `has_suffix` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否有后缀 0有，1没有',
  `id_suffix` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务流水号后缀',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细描述',
  `del_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标志（0--未删除1--已删除）',
  `created_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `t_ac_idtable_idcode`(`id_code`) USING BTREE COMMENT '唯一索引',
  UNIQUE INDEX `t_ac_idtable_idid`(`id_id`) USING BTREE COMMENT '唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统流水号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_ac_upload_file
-- ----------------------------
DROP TABLE IF EXISTS `t_ac_upload_file`;
CREATE TABLE `t_ac_upload_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `file_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件id',
  `file_name_old` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件原名称',
  `file_name_new` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件新名称',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件路径',
  `file_size` decimal(10, 0) NULL DEFAULT NULL COMMENT '文件大小（单位KB）',
  `file_size_str` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件大小（带单位）',
  `file_suffix` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `file_md5` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件MD5',
  `created_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传人-对应sys_user.user_id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_biz_car
-- ----------------------------
DROP TABLE IF EXISTS `t_biz_car`;
CREATE TABLE `t_biz_car`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键，内码',
  `car_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车辆id',
  `car_license` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车牌号，最多八位',
  `owners` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车主名称',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车主电话',
  `insured_expiry_date` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '保险到期日，格式yyyy-MM-dd',
  `year_expiry_date` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年检到期日，格式yyyy-MM-dd',
  `car_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车型',
  `car_color` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '车身颜色',
  `car_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '01' COMMENT '类型（01：汽油车，02：柴油车，03：新能源车）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志（0--未删除1--已删除）',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注描述信息',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人-对应sys_user.user_id',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人-对应sys_user.user_id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '车辆资料表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_uc_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_uc_login_log`;
CREATE TABLE `t_uc_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键，内码',
  `log_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志编码',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号-对应sys_user.username',
  `session_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本次登录的session_id或者token，登陆失败的话则不插入',
  `login_ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录ip',
  `user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'user_agent',
  `login_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0--登陆成功 1--登陆失败）',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注描述信息',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_uc_login_log
-- ----------------------------
INSERT INTO `t_uc_login_log` VALUES (2, '1630217447112671232', 'admin', '99983186310e49e582bc3cba077f57b5', '192.168.0.103', NULL, '上海市杨浦区 电信', '0', '登录成功', '2023-02-27 22:45:02', '2023-02-27 22:45:02');
INSERT INTO `t_uc_login_log` VALUES (3, '1630218349449465856', 'admin', 'f0880c5b0cd84974a692c659fca047f1', '192.168.0.103', NULL, '本地局域网', '0', '登录成功', '2023-02-27 22:48:37', '2023-02-27 22:48:37');
INSERT INTO `t_uc_login_log` VALUES (4, '1630380949829521408', 'admin', '060ff078c2084e59bc6a90d5e3fbd4af', '192.168.5.220', NULL, '本地局域网', '0', '登录成功', '2023-02-28 09:34:44', '2023-02-28 09:34:44');
INSERT INTO `t_uc_login_log` VALUES (6, '1630386617309810688', 'admin', '', '192.168.5.220', NULL, '本地局域网', '1', '用户名或密码错误', '2023-02-28 09:57:15', '2023-02-28 09:57:15');
INSERT INTO `t_uc_login_log` VALUES (7, '1630386689380536320', 'admin', '', '192.168.5.220', NULL, '本地局域网', '1', '用户名或密码错误', '2023-02-28 09:57:32', '2023-02-28 09:57:32');
INSERT INTO `t_uc_login_log` VALUES (8, '1630386759089868800', 'admin', '', '192.168.5.220', NULL, '本地局域网', '1', '用户名或密码错误', '2023-02-28 09:57:49', '2023-02-28 09:57:49');
INSERT INTO `t_uc_login_log` VALUES (9, '1630386791713165312', 'admin', '', '192.168.5.220', NULL, '本地局域网', '1', '用户名或密码错误', '2023-02-28 09:57:57', '2023-02-28 09:57:57');
INSERT INTO `t_uc_login_log` VALUES (10, '1630386831584219136', 'admin', '', '192.168.5.220', NULL, '本地局域网', '1', '用户名或密码错误', '2023-02-28 09:58:06', '2023-02-28 09:58:06');
INSERT INTO `t_uc_login_log` VALUES (11, '1630806966247813120', 'admin', '9a98018c2dde4e588a49933b8c865382', '192.168.5.220', NULL, '本地局域网', '0', '登录成功', '2023-03-01 13:47:34', '2023-03-01 13:47:34');
INSERT INTO `t_uc_login_log` VALUES (12, '1630808959304347648', 'admin', '72318d9b52b646a1a731892d88e884c4', '192.168.5.220', NULL, '本地局域网', '0', '登录成功', '2023-03-01 13:55:30', '2023-03-01 13:55:30');
INSERT INTO `t_uc_login_log` VALUES (13, '1630851469145653248', 'admin', 'b2e7feda69b348fcb024f887fc824a67', '100.100.100.65', NULL, '保留地址', '0', '登录成功', '2023-03-01 16:44:25', '2023-03-01 16:44:25');
INSERT INTO `t_uc_login_log` VALUES (14, '1630861959989891072', 'admin', '', '100.100.100.65', NULL, '保留地址', '1', '用户名或密码错误！', '2023-03-01 17:26:06', '2023-03-01 17:26:06');
INSERT INTO `t_uc_login_log` VALUES (15, '1630864068467122176', 'admin', '28a439da7ae2406c80ea57414a64c7e1', '100.100.100.65', NULL, '保留地址', '0', '登录成功', '2023-03-01 17:34:28', '2023-03-01 17:34:28');
INSERT INTO `t_uc_login_log` VALUES (16, '1630865592731721728', 'admin', '940fde82c8884aaaaad0336d918663f3', '100.100.100.65', NULL, '保留地址', '0', '登录成功', '2023-03-01 17:40:32', '2023-03-01 17:40:32');
INSERT INTO `t_uc_login_log` VALUES (17, '1630908726903595008', 'admin', 'd7c33b8e5f704bd589a04a7eb357e679', '192.168.0.100', NULL, '本地局域网', '0', '登录成功', '2023-03-01 20:31:56', '2023-03-01 20:31:56');
INSERT INTO `t_uc_login_log` VALUES (18, '1630909117066141696', 'admin', '6034437fcc5f4f9fa9e1077012847bee', '192.168.0.100', NULL, '本地局域网', '0', '登录成功', '2023-03-01 20:33:29', '2023-03-01 20:33:29');
INSERT INTO `t_uc_login_log` VALUES (19, '1630909736236851200', 'admin', 'd97bd2b6f93e4b5b8ad042b5169f6cdf', '192.168.0.100', NULL, '本地局域网', '0', '登录成功', '2023-03-01 20:35:57', '2023-03-01 20:35:57');
INSERT INTO `t_uc_login_log` VALUES (20, '1630910137933733888', 'admin', 'ffc314e4a5dd499c877b96947e5dc97e', '192.168.0.100', NULL, '本地局域网', '0', '登录成功', '2023-03-01 20:37:32', '2023-03-01 20:37:32');
INSERT INTO `t_uc_login_log` VALUES (21, '1630932131156484096', 'admin', 'ad9955277ae843939430f86964603678', '192.168.0.100', NULL, '本地局域网', '0', '登录成功', '2023-03-01 22:04:56', '2023-03-01 22:04:56');
INSERT INTO `t_uc_login_log` VALUES (22, '1630936828009406464', 'admin', 'bb736136802b41d59de9bfdc1600c27e', '192.168.0.100', NULL, '本地局域网', '0', '登录成功', '2023-03-01 22:23:35', '2023-03-01 22:23:35');
INSERT INTO `t_uc_login_log` VALUES (23, '1630937665838407680', 'admin', '81b93d0f6bd146a58c2a347bef29bad5', '192.168.0.100', NULL, '本地局域网', '0', '登录成功', '2023-03-01 22:26:55', '2023-03-01 22:26:55');
INSERT INTO `t_uc_login_log` VALUES (24, '1631103622174494720', 'admin', 'f51d9eabf1ae4c43b58d88336036542a', '100.100.100.65', NULL, '保留地址', '0', '登录成功', '2023-03-02 09:26:23', '2023-03-02 09:26:23');
INSERT INTO `t_uc_login_log` VALUES (25, '1631111874178207744', 'admin', 'b588e44a865e429193b83110696426e6', '100.100.100.65', NULL, '保留地址', '0', '登录成功', '2023-03-02 09:59:10', '2023-03-02 09:59:10');
INSERT INTO `t_uc_login_log` VALUES (26, '1631561475939803136', 'admin', 'ade3d9d96a3f4e62b94233a9daef7769', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 15:45:44', '2023-03-03 15:45:44');
INSERT INTO `t_uc_login_log` VALUES (27, '1631562078246051840', 'admin', '1236f404cffc40bf8497f79df59fdab3', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 15:48:07', '2023-03-03 15:48:07');
INSERT INTO `t_uc_login_log` VALUES (28, '1631564114031190016', 'admin', 'd66d1fc1584a4dc79f275275e78f014c', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 15:56:12', '2023-03-03 15:56:12');
INSERT INTO `t_uc_login_log` VALUES (29, '1631564537634922496', 'admin', 'adf885eaf87b4839846e1be7547b1ce4', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 15:57:53', '2023-03-03 15:57:53');
INSERT INTO `t_uc_login_log` VALUES (30, '1631565511745249280', 'admin', '5df6fde640e94e3caed50c7b38465933', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 16:01:45', '2023-03-03 16:01:45');
INSERT INTO `t_uc_login_log` VALUES (31, '1631588069085454336', 'admin', 'f0cca672ef094f239d88c5e3eedda3c6', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 17:31:23', '2023-03-03 17:31:23');
INSERT INTO `t_uc_login_log` VALUES (32, '1631588471021412352', 'admin', 'ca2fd1e707604277adc8a46b2ad9c458', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 17:32:59', '2023-03-03 17:32:59');
INSERT INTO `t_uc_login_log` VALUES (33, '1631588585744015360', 'admin', '86e40bdf614b4e9896f0428337c3ee33', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 17:33:27', '2023-03-03 17:33:27');
INSERT INTO `t_uc_login_log` VALUES (34, '1631590118095233024', 'admin', '306950fd64de4148a5bb2caee8365c29', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 17:39:32', '2023-03-03 17:39:32');
INSERT INTO `t_uc_login_log` VALUES (35, '1631592139326136320', 'admin', '2bb7f856d1f840c381893cfb6c2e5000', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 17:47:34', '2023-03-03 17:47:34');
INSERT INTO `t_uc_login_log` VALUES (36, '1631593531814092800', 'admin', '2a3a19ce9c9c437d896f8cc05b858b26', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 17:53:06', '2023-03-03 17:53:06');
INSERT INTO `t_uc_login_log` VALUES (37, '1631594105376776192', 'admin', '0709fb4bef6c42e492fc4a0dce52e420', '192.168.5.254', NULL, '本地局域网', '0', '登录成功', '2023-03-03 17:55:23', '2023-03-03 17:55:23');
INSERT INTO `t_uc_login_log` VALUES (38, '1631616524855123968', 'admin', '415ad504b53f4839b650e6493934264a', '192.168.0.104', NULL, '本地局域网', '0', '登录成功', '2023-03-03 19:24:28', '2023-03-03 19:24:28');
INSERT INTO `t_uc_login_log` VALUES (39, '1631616631860207616', 'admin', 'b21c9612d87a4912816edcb026bab990', '192.168.0.104', NULL, '本地局域网', '0', '登录成功', '2023-03-03 19:24:53', '2023-03-03 19:24:53');
INSERT INTO `t_uc_login_log` VALUES (40, '1631617608180932608', 'admin', '3fb9802b4fdf40f4b1ee44ca30da70a7', '192.168.0.104', NULL, '本地局域网', '0', '登录成功', '2023-03-03 19:28:46', '2023-03-03 19:28:46');
INSERT INTO `t_uc_login_log` VALUES (41, '1631620259866058752', 'admin', '240875a1687d48aab4be746964d2bed8', '192.168.0.104', NULL, '本地局域网', '0', '登录成功', '2023-03-03 19:39:18', '2023-03-03 19:39:18');
INSERT INTO `t_uc_login_log` VALUES (42, '1631623078031499264', 'admin', '25e25b8e4ffc429bb473b4bc43190994', '192.168.0.104', NULL, '本地局域网', '0', '登录成功', '2023-03-03 19:50:30', '2023-03-03 19:50:30');
INSERT INTO `t_uc_login_log` VALUES (43, '1631623158570524672', 'admin', '3a03d995170f453cb05899b22bdfeebc', '192.168.0.104', NULL, '本地局域网', '0', '登录成功', '2023-03-03 19:50:49', '2023-03-03 19:50:49');
INSERT INTO `t_uc_login_log` VALUES (44, '1631623496308465664', 'admin', '625feaacc62e4e1392bfe010bf62034a', '192.168.0.104', NULL, '本地局域网', '0', '登录成功', '2023-03-03 19:52:10', '2023-03-03 19:52:10');
INSERT INTO `t_uc_login_log` VALUES (45, '1631624104281219072', 'admin', '0c0d816bb41047a4bb405b1bef46b859', '192.168.0.104', NULL, '本地局域网', '0', '登录成功', '2023-03-03 19:54:35', '2023-03-03 19:54:35');
INSERT INTO `t_uc_login_log` VALUES (46, '1631637386589216768', 'admin', '656609c6ecd94424b8ef216a235da1a3', '192.168.0.104', NULL, '本地局域网', '0', '登录成功', '2023-03-03 20:47:22', '2023-03-03 20:47:22');
INSERT INTO `t_uc_login_log` VALUES (47, '1633801523711528960', 'admin', '', '192.168.1.106', NULL, '本地局域网', '1', '用户名或密码错误！', '2023-03-09 20:06:53', '2023-03-09 20:06:53');
INSERT INTO `t_uc_login_log` VALUES (48, '1633801563075072000', 'admin', 'de3057265fb242df84d068344b248706', '192.168.1.106', NULL, '本地局域网', '0', '登录成功', '2023-03-09 20:07:02', '2023-03-09 20:07:02');
INSERT INTO `t_uc_login_log` VALUES (49, '423770118836891648', 'admin', '1f402f14e1af4fe498dbdba126a951ed', '192.168.3.189', NULL, '本地局域网', '0', '登录成功', '2023-03-15 10:12:12', '2023-03-15 10:12:12');

-- ----------------------------
-- Table structure for t_uc_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_uc_permission`;
CREATE TABLE `t_uc_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务主键',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父级ID',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '类型（0是目录，1是菜单，2是按钮）',
  `sign` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源值',
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径\r\n1、菜单：和component_name相同就行\r\n2、目录：/ + component_name',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `iframe` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否外链（0不是，1是）',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外链地址（iframe为1的时候再填）',
  `component_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件名称\r\n1、菜单：和组件的name要对的上\r\n2、目录：默认和前端父目录名相同，如’monitor‘，注意不要重复',
  `component_path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径\r\n1、菜单：view目录下的路径，第一级不带/，如\'monitor/loginlog/index\'\r\n2、目录：不用配置，空着就行',
  `hide_in_menu` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '在左侧菜单中是否隐藏（0不隐藏，1隐藏）',
  `hide_in_bread` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '在面包屑中是否隐藏（0不隐藏，1隐藏）',
  `cache` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否缓存（0不缓存，1缓存）',
  `descript` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0正常，1停用）',
  `del_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除状态（0未删除，1已删除）',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `created_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `updated_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单权限信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_uc_permission
-- ----------------------------
INSERT INTO `t_uc_permission` VALUES (1, '1256049937693029110', '1256049937693029999', '三级-1', '1', NULL, '/multilevel/level-2-2/level-2-2-1', 0, 'md-funnel', '0', NULL, 'level_2_2_1', '/multilevel/level-2-2/level-2-2-1', '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 13:50:03', NULL, '2022-07-22 15:25:49');
INSERT INTO `t_uc_permission` VALUES (2, '1256049937693029111', '1256049937693029999', '三级-2', '1', NULL, '/multilevel/level-2-2/level-2-2-2', 0, 'md-funnel', '0', NULL, 'level_2_2_2', '/multilevel/level-2-2/level-2-2-2', '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 13:50:27', NULL, '2022-07-22 15:25:55');
INSERT INTO `t_uc_permission` VALUES (3, '1256049937693029112', '0', '系统监控', '0', NULL, '/monitor', 3, 'md-menu', '0', NULL, 'monitor', NULL, '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 22:06:16', NULL, '2022-01-01 10:35:16');
INSERT INTO `t_uc_permission` VALUES (4, '1256049937693029113', '1256049937693029112', '登陆日志', '1', NULL, '/monitor/loginlog/index2', 0, 'md-menu', '0', NULL, 'loginlog-index2', '/core/monitor/logging/loginlog/index2', '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 22:07:14', NULL, '2023-03-01 22:26:26');
INSERT INTO `t_uc_permission` VALUES (5, '1256049937693029992', '0', '文档', '1', NULL, '/doc', 0, 'ios-book', '1', 'https://lison16.github.io/iview-admin-doc/#/', 'doc', NULL, '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 13:22:13', NULL, '2022-07-22 15:22:20');
INSERT INTO `t_uc_permission` VALUES (6, '1256049937693029993', '0', 'QQ群', '1', NULL, '/join_page', 1, '_qq', '0', NULL, 'join_page', '/join-page', '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 13:33:03', NULL, '2022-07-22 15:25:02');
INSERT INTO `t_uc_permission` VALUES (7, '1256049937693029994', '0', '数据上传', '0', NULL, '/update', 2, 'md-cloud-upload', '0', NULL, 'update', '', '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 13:36:25', NULL, '2021-12-31 13:46:01');
INSERT INTO `t_uc_permission` VALUES (8, '1256049937693029995', '1256049937693029994', '上传Csv', '1', '', '/update/update-table', 0, 'ios-document', '0', NULL, 'update_table_page', '/update/update-table', '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 13:39:43', NULL, '2022-07-22 15:26:05');
INSERT INTO `t_uc_permission` VALUES (9, '1256049937693029996', '1256049937693029994', '粘贴表格数据', '1', NULL, '/update/update-paste', 1, 'md-clipboard', '0', NULL, 'update_paste_page', '/update/update-paste', '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 13:41:18', NULL, '2022-07-22 15:26:13');
INSERT INTO `t_uc_permission` VALUES (10, '1256049937693029997', '0', '多级菜单', '0', NULL, '/multilevel', 4, 'md-menu', '0', NULL, 'multilevel', NULL, '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 13:45:54', NULL, '2022-01-04 13:24:31');
INSERT INTO `t_uc_permission` VALUES (11, '1256049937693029998', '1256049937693029997', '二级-1', '1', NULL, '/multilevel/level-2-1', 1, 'md-funnel', '0', NULL, 'level_2_1', '/multilevel/level-2-1', '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 13:48:06', NULL, '2022-07-22 15:26:23');
INSERT INTO `t_uc_permission` VALUES (12, '1256049937693029999', '1256049937693029997', '二级-2', '0', NULL, '/level_2_2', 0, 'md-funnel', '0', NULL, 'level_2_2', NULL, '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 13:48:40', NULL, '2021-12-31 13:49:11');
INSERT INTO `t_uc_permission` VALUES (13, '1256049937693821821', '1256049937693029112', '登陆日志(新)', '1', 'loginlog:list', '/monitor/loginlog/index', 0, 'md-menu', '0', NULL, 'loginlog-index', '/core/monitor/logging/loginlog/index', '0', '0', '0', NULL, '0', 0, NULL, '2022-01-03 12:05:12', NULL, '2023-03-01 22:26:20');
INSERT INTO `t_uc_permission` VALUES (14, '1256049937693821878', '0', '示例页面', '0', NULL, '/example', 5, 'md-menu', '0', NULL, 'example', NULL, '0', '0', '0', NULL, '0', 0, NULL, '2022-07-16 15:56:16', NULL, '2022-07-16 20:51:17');
INSERT INTO `t_uc_permission` VALUES (15, '1256049937693821879', '1256049937693821878', '帮助框', '1', NULL, '/example/helpbox/index', 0, 'md-menu', '0', NULL, 'helpbox-demo', '/example/helpbox/index', '0', '0', '0', NULL, '0', 0, NULL, '2022-07-16 15:57:49', NULL, '2022-08-04 10:57:50');
INSERT INTO `t_uc_permission` VALUES (16, '1256049937693821880', '1256049937693821878', '可编辑表格', '1', NULL, '/example/editable/index', 0, 'md-menu', '0', NULL, 'editable-demo', '/example/edittable/index', '0', '0', '0', NULL, '0', 0, NULL, '2022-07-16 21:02:59', NULL, '2022-08-04 10:57:50');
INSERT INTO `t_uc_permission` VALUES (17, '1256049937693821881', '1256049937693821878', '表格Render', '1', NULL, '/example/tablerender/index', 0, 'md-menu', '0', NULL, 'table-render-demo', '/example/tablerender/index', '0', '0', '0', NULL, '0', 0, NULL, '2022-07-17 15:57:41', NULL, '2022-07-22 15:24:47');
INSERT INTO `t_uc_permission` VALUES (18, '1256049937693821882', '1256049937693821878', '表格Render-echarts', '1', NULL, '/example/tablerender/index2', 0, 'md-menu', '0', NULL, 'table-render-echarts', '/example/tablerender/index2', '0', '0', '0', NULL, '0', 0, NULL, '2022-07-22 15:05:18', NULL, '2022-07-22 15:24:50');
INSERT INTO `t_uc_permission` VALUES (19, '1256049937693029339', '0', '维修保养', '0', NULL, '/maintain', 4, 'md-menu', '0', NULL, 'maintain', NULL, '0', '0', '0', NULL, '0', 0, NULL, '2021-12-31 22:06:16', NULL, '2023-03-03 15:52:04');
INSERT INTO `t_uc_permission` VALUES (20, '1256049937693029340', '1256049937693029339', '车辆资料', '1', NULL, '/maintain/car', 0, 'md-menu', '0', NULL, 'car-index', '/business/maintain/car/index', '0', '0', '0', NULL, '0', 0, NULL, '2023-03-03 15:43:20', NULL, '2023-03-03 15:43:20');
INSERT INTO `t_uc_permission` VALUES (21, '1256049937693029342', '1256049937693029339', '配件管理', '1', NULL, '/maintain/parts', 0, 'md-menu', '0', NULL, 'parts-index', '/business/maintain/parts/index', '0', '0', '0', NULL, '0', 0, NULL, '2023-03-03 16:00:11', NULL, '2023-03-03 16:00:51');

-- ----------------------------
-- Table structure for t_uc_role
-- ----------------------------
DROP TABLE IF EXISTS `t_uc_role`;
CREATE TABLE `t_uc_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `sign` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标志',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0--正常1--停用）',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '删除状态（0--未删除1--已删除）',
  `note` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_uc_role
-- ----------------------------
INSERT INTO `t_uc_role` VALUES (1, '1263999628210487296', '超级管理员', 'superadmin', '0', 0, '超级管理员，拥有至高无上的权力', '150917903665', '2020-09-06 19:40:31', '150917903665', '2023-03-01 16:01:13');

-- ----------------------------
-- Table structure for t_uc_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_uc_role_permission`;
CREATE TABLE `t_uc_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '角色id',
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '权限id',
  `created_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_uc_role_permission
-- ----------------------------
INSERT INTO `t_uc_role_permission` VALUES (1, '1263999628210487296', '1256049937693029110', '2021-12-28 15:43:44', '2021-12-31 13:52:43');
INSERT INTO `t_uc_role_permission` VALUES (2, '1263999628210487296', '1256049937693029111', '2021-12-28 22:07:06', '2021-12-31 13:52:44');
INSERT INTO `t_uc_role_permission` VALUES (3, '1263999628210487296', '1256049937693029112', '2021-12-31 22:08:27', '2021-12-31 22:08:37');
INSERT INTO `t_uc_role_permission` VALUES (4, '1263999628210487296', '1256049937693029113', '2021-12-31 22:08:36', '2021-12-31 22:08:40');
INSERT INTO `t_uc_role_permission` VALUES (5, '1263999628210487296', '1256049937693029992', '2021-12-26 20:14:06', '2021-12-26 20:14:21');
INSERT INTO `t_uc_role_permission` VALUES (6, '1263999628210487296', '1256049937693029993', '2021-12-26 22:16:04', '2021-12-26 22:16:04');
INSERT INTO `t_uc_role_permission` VALUES (7, '1263999628210487296', '1256049937693029994', '2021-12-26 20:13:07', '2021-12-31 13:52:19');
INSERT INTO `t_uc_role_permission` VALUES (8, '1263999628210487296', '1256049937693029995', '2021-12-26 20:13:07', '2021-12-31 13:52:23');
INSERT INTO `t_uc_role_permission` VALUES (9, '1263999628210487296', '1256049937693029996', '2021-12-26 20:13:53', '2021-12-31 13:52:27');
INSERT INTO `t_uc_role_permission` VALUES (10, '1263999628210487296', '1256049937693029997', '2021-12-26 20:13:59', '2021-12-31 13:52:31');
INSERT INTO `t_uc_role_permission` VALUES (11, '1263999628210487296', '1256049937693029998', '2021-12-27 13:00:13', '2021-12-31 13:52:35');
INSERT INTO `t_uc_role_permission` VALUES (12, '1263999628210487296', '1256049937693029999', '2021-12-27 13:00:02', '2021-12-31 13:52:39');
INSERT INTO `t_uc_role_permission` VALUES (13, '1263999628210487296', '1256049937693821821', '2022-01-03 12:11:04', '2022-01-03 12:11:07');
INSERT INTO `t_uc_role_permission` VALUES (14, '1263999628210487296', '1256049937693821878', '2022-07-16 15:58:47', '2022-07-16 15:58:47');
INSERT INTO `t_uc_role_permission` VALUES (15, '1263999628210487296', '1256049937693821879', '2022-07-16 15:58:38', '2022-07-16 15:58:51');
INSERT INTO `t_uc_role_permission` VALUES (16, '1263999628210487296', '1256049937693821880', '2022-07-16 21:08:21', '2022-07-16 21:08:24');
INSERT INTO `t_uc_role_permission` VALUES (17, '1263999628210487296', '1256049937693821881', '2022-07-17 15:58:06', '2022-07-17 15:58:09');
INSERT INTO `t_uc_role_permission` VALUES (18, '1263999628210487296', '1256049937693821882', '2022-07-22 15:19:25', '2022-07-22 15:19:28');
INSERT INTO `t_uc_role_permission` VALUES (19, '1263999628210487296', '1256049937693029339', '2023-03-03 15:44:01', '2023-03-03 15:44:13');
INSERT INTO `t_uc_role_permission` VALUES (20, '1263999628210487296', '1256049937693029340', '2023-03-03 15:44:09', '2023-03-03 20:47:33');
INSERT INTO `t_uc_role_permission` VALUES (21, '1263999628210487296', '1256049937693029342', '2023-03-03 16:01:15', '2023-03-03 19:51:20');

-- ----------------------------
-- Table structure for t_uc_user
-- ----------------------------
DROP TABLE IF EXISTS `t_uc_user`;
CREATE TABLE `t_uc_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键，内码',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编码',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称（中文姓名）',
  `phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0--正常 1--冻结）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `del_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标志（0--未删除1--已删除）',
  `created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `created_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_uc_user
-- ----------------------------
INSERT INTO `t_uc_user` VALUES (1, '150917903665', 'admin', '1738db25ed2c4259c1242ad5f68f356596bbb75fdfdfbcc3d0e88c6cb102bf72', '系统管理员', '17862719591', '62677272@qq.com', '0', 'http://halo.lxyccc.top/f778738c-e4f8-4870-b634-56703b4acafe_1608734603765.gif', 0, '2023-02-13 15:02:05', '2023-03-16 19:43:51', NULL, NULL);
INSERT INTO `t_uc_user` VALUES (2, '5485658986', 'zhangsan', '1738db25ed2c4259c1242ad5f68f356596bbb75fdfdfbcc3d0e88c6cb102bf72', '张三', '17862719592', '17862719592@qq.com', '0', 'http://halo.lxyccc.top/f778738c-e4f8-4870-b634-56703b4acafe_1608734603765.gif', 0, '2023-02-13 15:02:05', '2023-03-16 19:43:51', NULL, NULL);

-- ----------------------------
-- Table structure for t_uc_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_uc_user_role`;
CREATE TABLE `t_uc_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '角色id',
  `created_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_uc_user_role
-- ----------------------------
INSERT INTO `t_uc_user_role` VALUES (1, '150917903665', '1263999628210487296', '2021-05-01 23:02:42', '2023-03-01 16:02:49');

SET FOREIGN_KEY_CHECKS = 1;
