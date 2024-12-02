/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : basics-authorize

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 26/04/2024 15:22:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info`  (
  `id` bigint(20) NOT NULL,
  `business_id` bigint(20) NULL DEFAULT NULL COMMENT '业务Id',
  `created` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` bigint(20) NOT NULL,
  `created` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ip_address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `login_location` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态',
  `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `id` bigint(20) NOT NULL,
  `created` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '业务类型(新增 修改 删除)',
  `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `params` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '返回参数',
  `status` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作状态(成功 失败)',
  `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
INSERT INTO `sys_operation_log` VALUES (1781227612621987841, NULL, 'admin', '新增用户', 'INSERT', 'POST', '{\"header\":[{\"authorization\":\"Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxNzc4MzA5Nzg2OTU5ODEwNTYxIiwidXNlcm5hbWUiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzEzNDk3NTU1LCJleHAiOjE3MTM1MTc3MTV9.MQzbiDseRG7VFuylq4LeqwKXGaFNuWmonqdDnxEQIjIEst86_FrnQlh3Zv5pyM_zF2RamV44gVKxHb5Eb-sSdg\",\"content-length\":\"272\",\"host\":\"localhost:9000\",\"content-type\":\"application/json\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Apifox/1.0.0 (https://apifox.com)\",\"accept\":\"*/*\"}],\"body0\":[{\"phoneNumber\":\"13000000003\",\"realName\":\"张三4\",\"roleIdList\":[1746740664973447169],\"sex\":\"男\",\"username\":\"zhangsan12200\"}]}', '用户名已存在', 'FAIL', 'com.project.template.common.exception.BaseException: 用户名已存在');
INSERT INTO `sys_operation_log` VALUES (1781229432891158529, NULL, 'admin', '新增用户', 'INSERT', 'POST', '{\"header\":[{\"authorization\":\"Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxNzc4MzA5Nzg2OTU5ODEwNTYxIiwidXNlcm5hbWUiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzEzNDk3NTU1LCJleHAiOjE3MTM1MTc3MTV9.MQzbiDseRG7VFuylq4LeqwKXGaFNuWmonqdDnxEQIjIEst86_FrnQlh3Zv5pyM_zF2RamV44gVKxHb5Eb-sSdg\",\"content-length\":\"272\",\"host\":\"localhost:9000\",\"content-type\":\"application/json\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Apifox/1.0.0 (https://apifox.com)\",\"accept\":\"*/*\"}],\"body\":[{\"phoneNumber\":\"13000000003\",\"realName\":\"张三4\",\"roleIdList\":[1746740664973447169],\"sex\":\"男\",\"username\":\"zhangsan12200\"}]}', '用户名已存在', 'FAIL', 'com.project.template.common.exception.BaseException: 用户名已存在');
INSERT INTO `sys_operation_log` VALUES (1781229631910883330, NULL, 'admin', '删除用户', 'DELETE', 'DELETE', '{\"header\":[{\"authorization\":\"Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxNzc4MzA5Nzg2OTU5ODEwNTYxIiwidXNlcm5hbWUiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzEzNDk3NTU1LCJleHAiOjE3MTM1MTc3MTV9.MQzbiDseRG7VFuylq4LeqwKXGaFNuWmonqdDnxEQIjIEst86_FrnQlh3Zv5pyM_zF2RamV44gVKxHb5Eb-sSdg\",\"host\":\"localhost:9000\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Apifox/1.0.0 (https://apifox.com)\",\"accept\":\"*/*\"}],\"body\":[{\"ids\":[1781174780585766913]}]}', 'ResultBean(code=20000, message=操作成功, timestamp=1713513212537, data=null)', 'SUCCESS', '操作成功');
INSERT INTO `sys_operation_log` VALUES (1781234528664358914, NULL, 'admin', '删除用户', 'DELETE', 'DELETE', '{\"header\":[{\"authorization\":\"Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxNzc4MzA5Nzg2OTU5ODEwNTYxIiwidXNlcm5hbWUiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzEzNDk3NTU1LCJleHAiOjE3MTM1MTc3MTV9.MQzbiDseRG7VFuylq4LeqwKXGaFNuWmonqdDnxEQIjIEst86_FrnQlh3Zv5pyM_zF2RamV44gVKxHb5Eb-sSdg\",\"host\":\"localhost:9000\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Apifox/1.0.0 (https://apifox.com)\",\"accept\":\"*/*\"}],\"body\":[{\"ids\":[1781177105052270593,1781177742183788546,1781207302078119937]}]}', 'ResultBean(code=20000, message=操作成功, timestamp=1713514379724, data=null)', 'SUCCESS', '操作成功');
INSERT INTO `sys_operation_log` VALUES (1781234929425887234, '2024-04-19 16:14:35', 'admin', '删除用户', 'DELETE', 'DELETE', '{\"header\":[{\"authorization\":\"Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxNzc4MzA5Nzg2OTU5ODEwNTYxIiwidXNlcm5hbWUiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzEzNDk3NTU1LCJleHAiOjE3MTM1MTc3MTV9.MQzbiDseRG7VFuylq4LeqwKXGaFNuWmonqdDnxEQIjIEst86_FrnQlh3Zv5pyM_zF2RamV44gVKxHb5Eb-sSdg\",\"host\":\"localhost:9000\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Apifox/1.0.0 (https://apifox.com)\",\"accept\":\"*/*\"}],\"body\":[{\"ids\":[1781176515068878850,1781207798708903938,1781208118184869889]}]}', 'ResultBean(code=20000, message=操作成功, timestamp=1713514475128, data=null)', 'SUCCESS', '操作成功');
INSERT INTO `sys_operation_log` VALUES (1781234977035431938, '2024-04-19 16:14:47', 'admin', '新增用户', 'INSERT', 'POST', '{\"header\":[{\"authorization\":\"Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxNzc4MzA5Nzg2OTU5ODEwNTYxIiwidXNlcm5hbWUiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNzEzNDk3NTU1LCJleHAiOjE3MTM1MTc3MTV9.MQzbiDseRG7VFuylq4LeqwKXGaFNuWmonqdDnxEQIjIEst86_FrnQlh3Zv5pyM_zF2RamV44gVKxHb5Eb-sSdg\",\"content-length\":\"272\",\"host\":\"localhost:9000\",\"content-type\":\"application/json\",\"connection\":\"keep-alive\",\"accept-encoding\":\"gzip, deflate, br\",\"user-agent\":\"Apifox/1.0.0 (https://apifox.com)\",\"accept\":\"*/*\"}],\"body\":[{\"phoneNumber\":\"13000000003\",\"realName\":\"张三4\",\"roleIdList\":[1746740664973447169],\"sex\":\"男\",\"username\":\"zhangsan12200\"}]}', 'com.project.template.common.exception.BaseException: 用户名已存在', 'FAIL', '用户名已存在');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1761659758827319297, '删除用户', 'system:user:delete');
INSERT INTO `sys_permission` VALUES (1761659842398826497, '修改用户', 'system:user:update');
INSERT INTO `sys_permission` VALUES (1761660176621940737, '创建用户', 'system:user:create');
INSERT INTO `sys_permission` VALUES (1761660299645071361, '查询用户列表', 'system:user:list');
INSERT INTO `sys_permission` VALUES (1761660463290036226, '创建角色', 'system:role:create');
INSERT INTO `sys_permission` VALUES (1761660590125789185, '删除角色', 'system:role:delete');
INSERT INTO `sys_permission` VALUES (1761660724469346306, '修改权限', 'system:role:update');
INSERT INTO `sys_permission` VALUES (1761661048785514498, '查询角色列表', 'system:role:list');
INSERT INTO `sys_permission` VALUES (1761661509206847489, '创建权限', 'system:permission:create');
INSERT INTO `sys_permission` VALUES (1761661657332887554, '删除权限', 'system:permission:delete');
INSERT INTO `sys_permission` VALUES (1761661747241988097, '更新权限', 'system:permission:update');
INSERT INTO `sys_permission` VALUES (1761661877802283009, '查询权限列表', 'system:permission:list');
INSERT INTO `sys_permission` VALUES (1761663074131030017, '更新用户状态', 'system:user:updatestatus');
INSERT INTO `sys_permission` VALUES (1761663322836480002, '重置用户密码', 'system:user:resetpassword');
INSERT INTO `sys_permission` VALUES (1762836138415931394, '仪表盘', 'system:dashboard');
INSERT INTO `sys_permission` VALUES (1762864916999483394, '系统管理', 'system');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1747646258756993026, '管理员');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色Id',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1778332881355476994, 1747646258756993026, 1761659758827319297);
INSERT INTO `sys_role_permission` VALUES (1778332881414197249, 1747646258756993026, 1761659842398826497);
INSERT INTO `sys_role_permission` VALUES (1778332881414197250, 1747646258756993026, 1761660176621940737);
INSERT INTO `sys_role_permission` VALUES (1778332881414197251, 1747646258756993026, 1761660299645071361);
INSERT INTO `sys_role_permission` VALUES (1778332881414197252, 1747646258756993026, 1761660463290036226);
INSERT INTO `sys_role_permission` VALUES (1778332881502277634, 1747646258756993026, 1761660590125789185);
INSERT INTO `sys_role_permission` VALUES (1778332881502277635, 1747646258756993026, 1761660724469346306);
INSERT INTO `sys_role_permission` VALUES (1778332881502277636, 1747646258756993026, 1761661048785514498);
INSERT INTO `sys_role_permission` VALUES (1778332881502277637, 1747646258756993026, 1761661509206847489);
INSERT INTO `sys_role_permission` VALUES (1778332881502277638, 1747646258756993026, 1761661657332887554);
INSERT INTO `sys_role_permission` VALUES (1778332881502277639, 1747646258756993026, 1761661747241988097);
INSERT INTO `sys_role_permission` VALUES (1778332881502277640, 1747646258756993026, 1761661877802283009);
INSERT INTO `sys_role_permission` VALUES (1778332881502277641, 1747646258756993026, 1761663074131030017);
INSERT INTO `sys_role_permission` VALUES (1778332881581969409, 1747646258756993026, 1761663322836480002);
INSERT INTO `sys_role_permission` VALUES (1778332881581969410, 1747646258756993026, 1762836138415931394);
INSERT INTO `sys_role_permission` VALUES (1778332881581969411, 1747646258756993026, 1762864916999483394);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL,
  `created` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1778309786959810561, '2024-04-11 14:31:07', '2024-04-11 14:46:32', 'admin', '{bcrypt}$2a$10$AmeSp61KjQWH9PaEa1h.Ku/qH4CxIAdZBmIoatjCEt.N4C0rBut/.', 'admin', '男', '13000000003', '启用');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户Id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1762862941239033857, 1778309786959810561, 1747646258756993026);

SET FOREIGN_KEY_CHECKS = 1;
