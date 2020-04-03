/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : cdlg

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 03/04/2020 17:41:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chatuser
-- ----------------------------
DROP TABLE IF EXISTS `chatuser`;
CREATE TABLE `chatuser`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profilehead` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `checktype` int(255) NULL DEFAULT NULL,
  `firsttime` datetime NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `lasttime` datetime NULL DEFAULT NULL,
  `messageCount` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of chatuser
-- ----------------------------
INSERT INTO `chatuser` VALUES (1, 'root', 'root', 'root', '男', NULL, '', 1, NULL, NULL, 22, NULL, NULL);
INSERT INTO `chatuser` VALUES (2, '张飞', 'root', 'zhangfei', '男', NULL, '', 1, NULL, NULL, 17, NULL, NULL);
INSERT INTO `chatuser` VALUES (3, 'a', 'root', 'a', '男', NULL, NULL, 1, NULL, NULL, 4, NULL, NULL);
INSERT INTO `chatuser` VALUES (4, 'b', 'root', 'b', '男', NULL, NULL, 1, NULL, NULL, 32, NULL, NULL);
INSERT INTO `chatuser` VALUES (5, 'c', 'root', 'c', '男', NULL, NULL, 1, NULL, NULL, 32, NULL, NULL);
INSERT INTO `chatuser` VALUES (6, 'd', 'root', 'd', '男', NULL, NULL, 1, NULL, NULL, 23, NULL, NULL);
INSERT INTO `chatuser` VALUES (7, 'e', 'root', 'e', '女', NULL, NULL, 1, NULL, NULL, 23, NULL, NULL);
INSERT INTO `chatuser` VALUES (8, 'f', 'root', 'f', '女', NULL, NULL, 1, NULL, NULL, 23, NULL, NULL);

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `friendid` int(255) NULL DEFAULT NULL,
  `mineid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (1, 1, 2);
INSERT INTO `friend` VALUES (2, 2, 1);
INSERT INTO `friend` VALUES (3, 3, 2);
INSERT INTO `friend` VALUES (4, 2, 3);
INSERT INTO `friend` VALUES (5, 5, 2);
INSERT INTO `friend` VALUES (6, 2, 5);
INSERT INTO `friend` VALUES (7, 4, 2);
INSERT INTO `friend` VALUES (8, 2, 4);
INSERT INTO `friend` VALUES (9, 7, 2);
INSERT INTO `friend` VALUES (10, 2, 7);
INSERT INTO `friend` VALUES (11, 6, 2);
INSERT INTO `friend` VALUES (12, 2, 6);
INSERT INTO `friend` VALUES (13, 8, 2);
INSERT INTO `friend` VALUES (14, 2, 8);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromid` int(11) NULL DEFAULT NULL,
  `toid` int(11) NULL DEFAULT NULL,
  `type` int(255) NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 2, 1, 0, NULL, 'vdfb\n', 1);

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sid` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createdate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `replynum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES (1, '2', NULL, 1, 'hao ', '2020-04-03 17:24:22', NULL);

-- ----------------------------
-- Table structure for space
-- ----------------------------
DROP TABLE IF EXISTS `space`;
CREATE TABLE `space`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pics` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createdate` datetime NULL DEFAULT NULL,
  `replynum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of space
-- ----------------------------
INSERT INTO `space` VALUES (1, 1, NULL, '生命不息，奋斗不止', NULL, '2020-04-03 17:14:13', 0);
INSERT INTO `space` VALUES (2, 1, 'root', '生命不息，奋斗不止', NULL, '2020-04-03 17:23:51', 0);

SET FOREIGN_KEY_CHECKS = 1;
