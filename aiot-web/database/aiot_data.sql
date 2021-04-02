/*
 Navicat Premium Data Transfer

 Source Server         : aiot-remote
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 50.17.255.13:3306
 Source Schema         : aiot

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 28/02/2021 20:11:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` tinyint(4) NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES (1, '1号棚', 0, '[{x:\"116.39564504\",y:\"39.92998578\"}]');
INSERT INTO `area` VALUES (2, '2号棚', 0, '[{x:\"116.39564504\",y:\"39.92998578\"}]');
INSERT INTO `area` VALUES (3, '3号棚', 0, '[{x:\"116.39564504\",y:\"39.92998578\"}]');
INSERT INTO `area` VALUES (4, '4号棚', 0, '[{x:\"116.39564504\",y:\"39.92998578\"}]');
INSERT INTO `area` VALUES (5, '5号棚', 0, '[{x:\"116.39564504\",y:\"39.92998578\"}]');
INSERT INTO `area` VALUES (6, '6号棚', 0, '[{x:\"116.39564504\",y:\"39.92998578\"}]');
INSERT INTO `area` VALUES (7, '7号棚', 0, '[{x:\"116.39564504\",y:\"39.92998578\"}]');
INSERT INTO `area` VALUES (8, '8号棚', 0, '[{x:\"116.39564504\",y:\"39.92998578\"}]');
INSERT INTO `area` VALUES (9, '森林区', 1, '[{x:\"116.39564504\",y:\"39.92998578\"}]');

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device` bigint(20) NOT NULL,
  `information` bigint(20) NOT NULL,
  `timestamp` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `value` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `data_ibfk_1`(`device`) USING BTREE,
  INDEX `data_ibfk_3`(`information`) USING BTREE,
  CONSTRAINT `data_ibfk_1` FOREIGN KEY (`device`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `data_ibfk_3` FOREIGN KEY (`information`) REFERENCES `data_information` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of data
-- ----------------------------
INSERT INTO `data` VALUES (1, 2, 1, '2021-02-17 06:44:01.396', 27);
INSERT INTO `data` VALUES (2, 2, 2, '2021-02-17 06:44:01.396', 65);
INSERT INTO `data` VALUES (3, 2, 3, '2021-02-17 06:44:01.396', 350);
INSERT INTO `data` VALUES (4, 2, 3, '2021-02-22 12:15:11.000', 320);
INSERT INTO `data` VALUES (5, 2, 1, '2021-02-22 12:15:11.478', 25);
INSERT INTO `data` VALUES (6, 2, 2, '2021-02-22 12:15:11.000', 29);
INSERT INTO `data` VALUES (7, 2, 3, '2021-02-22 12:11:44.000', 320);
INSERT INTO `data` VALUES (8, 2, 1, '2021-02-22 12:11:44.000', 32);
INSERT INTO `data` VALUES (9, 2, 2, '2021-02-22 12:11:44.000', 35);

-- ----------------------------
-- Table structure for data_information
-- ----------------------------
DROP TABLE IF EXISTS `data_information`;
CREATE TABLE `data_information`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of data_information
-- ----------------------------
INSERT INTO `data_information` VALUES (1, '空气温度', '℃', '/temperature.png');
INSERT INTO `data_information` VALUES (2, '空气湿度', '%', '/water.png');
INSERT INTO `data_information` VALUES (3, '光照强度', 'lx', '/light.png');

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `area` bigint(20) NOT NULL,
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(4) NOT NULL,
  `parent` bigint(20) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `firmware_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `hardware_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `software_version` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mac` varchar(17) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `device_ibfk_1`(`area`) USING BTREE,
  CONSTRAINT `device_ibfk_1` FOREIGN KEY (`area`) REFERENCES `area` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (1, 1, '680563411', 0, -1, 0, '3.0.0', '1.0.0', '1.0.0', '123.123.123.3');
INSERT INTO `device` VALUES (2, 1, '1', 1, 1, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device` bigint(20) NOT NULL,
  `information` bigint(20) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `setting` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `module_ibfk_1`(`device`) USING BTREE,
  INDEX `module_ibfk_2`(`information`) USING BTREE,
  CONSTRAINT `module_ibfk_1` FOREIGN KEY (`device`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `module_ibfk_2` FOREIGN KEY (`information`) REFERENCES `module_information` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES (1, 2, 3, 1, NULL);
INSERT INTO `module` VALUES (2, 2, 2, 1, NULL);
INSERT INTO `module` VALUES (3, 2, 1, 1, NULL);

-- ----------------------------
-- Table structure for module_information
-- ----------------------------
DROP TABLE IF EXISTS `module_information`;
CREATE TABLE `module_information`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `chinese` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `module_information_ibfk_1`(`data`) USING BTREE,
  CONSTRAINT `module_information_ibfk_1` FOREIGN KEY (`data`) REFERENCES `data_information` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of module_information
-- ----------------------------
INSERT INTO `module_information` VALUES (1, 1, 'temperature', '/temperature.png', '空气温度');
INSERT INTO `module_information` VALUES (2, 2, 'humidity', '/humidify-active.png', '空气湿度');
INSERT INTO `module_information` VALUES (3, 3, 'light', '/outer-sunshade-active.png', '光照强度');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` tinyint(4) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'aiot', '$2a$10$rvycaDY6XDtj3VgVqhiI6OtfMUF.Lw2FBDxqzGQ.yqcsDqimmh.HK', 'ROLE_SU', 1, '/default.jpg', 'aiot@aiot.com', '13190909090');
INSERT INTO `user` VALUES (6, 'public', '$2a$10$4SuGGkHgE8Bh37w7R/2ixuS58xDQM.dO8gzwm/QrIKkLG4gFVvhJi', 'ROLE_MONITOR', 2, '/default.jpg', 'public@aiot.com', '13467676761');
INSERT INTO `user` VALUES (7, 'admin', '$2a$10$6SQMP.0f5SjQGjWRAOmiQuJ0Z9rKRiiTDek9ai3JObwFE4/K5cGY.', 'ROLE_ADMIN', 0, '/default.jpg', 'admin@aiot.com', '13512121211');
INSERT INTO `user` VALUES (8, 'mike', '$2a$10$BCvrwTFJ7br8sopXGsE.1eR3KLRHHWXlybhLho1yFClgCmjml/xKy', 'ROLE_ADMIN', 1, '/default.jpg', 'mike@aiot.com', '13189925360');

-- ----------------------------
-- Table structure for user_area
-- ----------------------------
DROP TABLE IF EXISTS `user_area`;
CREATE TABLE `user_area`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` bigint(20) NOT NULL,
  `area` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_area_ibfk_1`(`user`) USING BTREE,
  INDEX `user_area_ibfk_2`(`area`) USING BTREE,
  CONSTRAINT `user_area_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_area_ibfk_2` FOREIGN KEY (`area`) REFERENCES `area` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_area
-- ----------------------------
INSERT INTO `user_area` VALUES (1, 1, 1);
INSERT INTO `user_area` VALUES (2, 1, 2);
INSERT INTO `user_area` VALUES (3, 1, 3);
INSERT INTO `user_area` VALUES (4, 1, 4);
INSERT INTO `user_area` VALUES (5, 1, 5);
INSERT INTO `user_area` VALUES (6, 1, 6);
INSERT INTO `user_area` VALUES (7, 1, 7);
INSERT INTO `user_area` VALUES (8, 1, 8);
INSERT INTO `user_area` VALUES (9, 1, 9);

SET FOREIGN_KEY_CHECKS = 1;
