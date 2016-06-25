/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50615
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50615
File Encoding         : 65001

Date: 2016-06-25 16:47:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `itemcategories`
-- ----------------------------
DROP TABLE IF EXISTS `itemcategories`;
CREATE TABLE `itemcategories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `categoryName` varchar(255) DEFAULT NULL,
  `orderBy` int(11) DEFAULT NULL,
  `categoryMark` varchar(255) DEFAULT NULL,
  `deleteFlag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itemcategories
-- ----------------------------
INSERT INTO `itemcategories` VALUES ('1', '0', '类别1', '2', null, '0');
INSERT INTO `itemcategories` VALUES ('2', '1', '类别1-1', '3', null, '0');
INSERT INTO `itemcategories` VALUES ('3', '0', '类别2', '4', null, '0');
INSERT INTO `itemcategories` VALUES ('4', '1', '类别1-2', '1', null, '0');
