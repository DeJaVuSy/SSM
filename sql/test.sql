/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 127.0.0.1:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 15/02/2022 15:42:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for edu_class
-- ----------------------------
DROP TABLE IF EXISTS `edu_class`;
CREATE TABLE `edu_class`  (
  `class_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `class_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名',
  `class_Date` date NOT NULL COMMENT '开班时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `class_Major` bigint(20) NOT NULL COMMENT '班级专业id',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_class
-- ----------------------------

-- ----------------------------
-- Table structure for edu_class_course
-- ----------------------------
DROP TABLE IF EXISTS `edu_class_course`;
CREATE TABLE `edu_class_course`  (
  `class_course_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '班级课程ID',
  `class_course_class_id` bigint(20) NOT NULL COMMENT '班级ID',
  `class_course_teacherid` bigint(20) NOT NULL COMMENT '任课教师id',
  `class_course_course_id` bigint(20) NOT NULL COMMENT '课程ID',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者（只能由系统用户设置）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  PRIMARY KEY (`class_course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_class_course
-- ----------------------------

-- ----------------------------
-- Table structure for edu_school
-- ----------------------------
DROP TABLE IF EXISTS `edu_school`;
CREATE TABLE `edu_school`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `school_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `build_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学校名称' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_school
-- ----------------------------

-- ----------------------------
-- Table structure for edu_score
-- ----------------------------
DROP TABLE IF EXISTS `edu_score`;
CREATE TABLE `edu_score`  (
  `score_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '课程分数ID',
  `score_studentid` bigint(11) NOT NULL COMMENT '学生id',
  `class_course_course_id` int(11) NOT NULL COMMENT '班级课程ID',
  `score` double(30, 1) NOT NULL COMMENT '分数',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updata_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `updata_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考试描述（比如期末测评 等等）',
  PRIMARY KEY (`score_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程分数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_score
-- ----------------------------

-- ----------------------------
-- Table structure for edu_student
-- ----------------------------
DROP TABLE IF EXISTS `edu_student`;
CREATE TABLE `edu_student`  (
  `student_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '学生信息ID',
  `student_no` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号（格式：年号+开班时间+报名序号）',
  `student_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生名（学生真实姓名）',
  `sys_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `student_class` bigint(11) NOT NULL COMMENT '所属班级ID',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '状态（0.在籍1:毕业 2:休学 3:退学）',
  `student_Majorstudiedid` bigint(20) NOT NULL COMMENT '所学专业id',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_student
-- ----------------------------

-- ----------------------------
-- Table structure for edu_teacher
-- ----------------------------
DROP TABLE IF EXISTS `edu_teacher`;
CREATE TABLE `edu_teacher`  (
  `teacher_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '教师信息ID',
  `teacher_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名（老师真实姓名）',
  `sys_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `Education` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历（0.高中1.大专.2.本科3.研究生）',
  `academic_title` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称（0.一级教师，1.二级教师，2.三级教师）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0.在职 1.离职）',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu_teacher
-- ----------------------------
INSERT INTO `edu_teacher` VALUES (3, '熊苏梅', 13, '2', '2', '0');
INSERT INTO `edu_teacher` VALUES (4, '搜索', 15, '0', '0', '0');
INSERT INTO `edu_teacher` VALUES (6, '李玟', 17, '0', '0', '0');
INSERT INTO `edu_teacher` VALUES (7, '杨非', 18, '0', '0', '0');
INSERT INTO `edu_teacher` VALUES (8, '张飞', 19, '1', '0', '1');

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表描述',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `module_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `package_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `business_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `options` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_time` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `function_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '已生成的表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (53, 'student', '学生', 'Student', NULL, NULL, NULL, NULL, NULL, '2021-12-07 15:31:16', '2021-12-13 12:00:41', NULL, NULL, NULL, NULL);
INSERT INTO `gen_table` VALUES (55, 'sys_user', '用户信息表', 'SysUser', NULL, NULL, NULL, NULL, NULL, '2021-12-14 10:09:25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `gen_table` VALUES (56, 'edu_class', '班级信息表', 'EduClass', NULL, NULL, NULL, NULL, NULL, '2021-12-15 11:21:18', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `gen_table` VALUES (57, 'sys_menu', '菜单权限表', 'SysMenu', NULL, NULL, NULL, NULL, NULL, '2021-12-15 15:08:51', '2022-02-11 09:46:48', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_bin COMMENT = '学生' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '张三', 21, '男');
INSERT INTO `student` VALUES (2, '刘一', 18, '男');
INSERT INTO `student` VALUES (3, '钱二', 19, '女');
INSERT INTO `student` VALUES (4, '刘一', 18, '男');
INSERT INTO `student` VALUES (5, '李四', 18, '女');
INSERT INTO `student` VALUES (6, '王五', 17, '男');
INSERT INTO `student` VALUES (7, '赵六', 19, '女');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-red', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2020-01-06 15:56:53', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-light', 'Y', 'admin', '2018-03-16 11:33:00', 'admin', '2020-01-06 15:57:41', '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1126 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, '#', '', 'M', '0', '', 'Setting', 'root', '2022-02-11 11:33:00', '鹏哥', '2022-02-11 11:33:00', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, '#', '', 'M', '0', '', 'VideoCamera', 'root', '2022-02-11 11:33:00', '鹏哥', '2022-02-11 11:33:00', '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, '#', '', 'M', '0', '', 'Tools', 'root', '2022-02-11 11:33:00', '鹏哥', '2022-02-11 11:33:00', '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, '代码生成', 3, 2, '/genTableHomeVue3', '', 'F', '0', 'edusystem:teacher:add', '#', 'root', '2022-02-11 00:00:00', '鹏哥', '2022-02-11 00:00:00', '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-01-02 01:12:00', '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-01-02 01:11:22', '管理员');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '教学主管', 1, '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-01-01 22:44:37', '');
INSERT INTO `sys_post` VALUES (2, 'se', '辅导员主管', 2, '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-01-01 22:44:53', '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人事主管', 3, '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-01-01 22:45:19', '');
INSERT INTO `sys_post` VALUES (4, 'user', '教员', 4, '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-01-01 22:46:02', '');
INSERT INTO `sys_post` VALUES (5, 'fdy', '辅导员', 5, '0', 'admin', '2020-01-01 22:46:43', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 1, '1', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '管理员');
INSERT INTO `sys_role` VALUES (2, '教师', 'teacher', 2, '2', '0', '0', 'admin', '2018-03-16 11:33:00', 'admin', '2020-01-06 15:26:59', '教师角色');
INSERT INTO `sys_role` VALUES (3, '学生', 'student', 3, '1', '0', '0', 'admin', '2020-01-06 15:27:39', 'admin', '2020-01-06 15:28:15', '学生角色');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'root', '鹏哥', '00', 'ry@163.com', '15888888888', '0', '/profile/avatar/2020/01/07/0b1f7e70fd1f5eb1fbe29de084c41ccb.png', '9c102f25233787c60a07e5a6cba789a3', 'dbae94eba31144e191ceb911ba72b59e', '0', '0', '127.0.0.1', '2021-12-06 14:22:11', 'admin', '2018-03-16 11:33:00', 'ry', '2021-12-06 14:22:10', '管理员');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '第一个姓名',
  `lastName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '最后一个姓名',
  `age` int(11) NOT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'Timber1', 'Saw1', 21);
INSERT INTO `user` VALUES (2, 'Timber2', 'Saw2', 26);
INSERT INTO `user` VALUES (3, 'Timber3', 'Saw3', 27);
INSERT INTO `user` VALUES (4, 'Timber4', 'Saw4', 28);
INSERT INTO `user` VALUES (5, '张三1', '李四1', 27);
INSERT INTO `user` VALUES (6, '张三2', '李四2', 30);
INSERT INTO `user` VALUES (7, '张三3', '李四3', 31);
INSERT INTO `user` VALUES (10, '张三4', '法外狂徒张三', 31);
INSERT INTO `user` VALUES (11, '王五', '赵六', 23);
INSERT INTO `user` VALUES (12, '王五', '赵六', 23);
INSERT INTO `user` VALUES (13, '王五', '赵六', 23);

-- ----------------------------
-- Table structure for user_metadata
-- ----------------------------
DROP TABLE IF EXISTS `user_metadata`;
CREATE TABLE `user_metadata`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `height` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `orientation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `compressed` tinyint(4) NOT NULL,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `REL_6afb43681a21cf7815932bc38a`(`userId`) USING BTREE,
  CONSTRAINT `FK_6afb43681a21cf7815932bc38ac` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = 'user外键' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_metadata
-- ----------------------------
INSERT INTO `user_metadata` VALUES (1, 640, 480, 'portait', 1, 'cybershoot', 11);
INSERT INTO `user_metadata` VALUES (2, 640, 480, 'portait', 1, 'cybershoot', 12);
INSERT INTO `user_metadata` VALUES (3, 640, 480, 'portait', 1, 'cybershoot', 13);

SET FOREIGN_KEY_CHECKS = 1;
