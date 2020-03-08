/*
Navicat MySQL Data Transfer

Source Server         : y
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : jygl

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-12-20 11:42:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `cid` int(10) NOT NULL,
  `companyname` varchar(50) DEFAULT NULL,
  `unitproperty` varchar(50) DEFAULT NULL,
  `licensenumber` varchar(50) DEFAULT NULL,
  `industry` varchar(50) DEFAULT NULL,
  `unitscale` varchar(10) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `webaddress` varchar(50) DEFAULT NULL,
  `linkman` varchar(50) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('9', '腾讯', '私企', '1512315165154', '互联网', '10000人以上', '广东深圳', 'tencent.com', '马化腾', '110', '110@qq.com', '511431');
INSERT INTO `company` VALUES ('10', '阿里巴巴集团', '私企', '粤515as1f5', '互联网购物', '10000人以上', '浙江杭州', '1688.com', '张勇', '120', '120@qq.com', '514553');

-- ----------------------------
-- Table structure for employment
-- ----------------------------
DROP TABLE IF EXISTS `employment`;
CREATE TABLE `employment` (
  `eid` int(10) NOT NULL AUTO_INCREMENT,
  `studentname` varchar(50) DEFAULT NULL,
  `school` varchar(50) DEFAULT NULL,
  `companyname` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of employment
-- ----------------------------
INSERT INTO `employment` VALUES ('1', 'mmm', 'mmm', 'mmm', 'mmm');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `mid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `msgtime` varchar(100) DEFAULT NULL,
  `content` text,
  `reply` text,
  `replytime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('2', '2', 'stu1', '测试2', '2019-12-19 18:45:01', '测试', null, null);
INSERT INTO `message` VALUES ('3', '3', 'stu2', '测试3', '2019-12-19 19:57:43', '123456', null, null);
INSERT INTO `message` VALUES ('4', '9', 'com1', '留言测试', '2019-12-19 20:26:14', '测试', null, null);
INSERT INTO `message` VALUES ('5', '9', 'com1', '测试2', '2019-12-19 20:28:39', '123456', null, null);
INSERT INTO `message` VALUES ('6', '2', 'stu1', '123', '2019-12-19 20:30:11', '456', null, null);

-- ----------------------------
-- Table structure for recruit
-- ----------------------------
DROP TABLE IF EXISTS `recruit`;
CREATE TABLE `recruit` (
  `rid` int(10) NOT NULL AUTO_INCREMENT,
  `cid` int(10) DEFAULT NULL,
  `companyname` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `recruitment` int(10) DEFAULT NULL,
  `workingplace` varchar(50) DEFAULT NULL,
  `positiontype` varchar(10) DEFAULT NULL,
  `edurequire` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `branch` varchar(50) DEFAULT NULL,
  `linkman` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `hostpage` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of recruit
-- ----------------------------
INSERT INTO `recruit` VALUES ('1', '9', '腾讯有限公司', '广东深圳', '514223', '121023', '广东1', '互联网', '5151', '1251', '5', '5151', '521', '515', '152');

-- ----------------------------
-- Table structure for recruitresume
-- ----------------------------
DROP TABLE IF EXISTS `recruitresume`;
CREATE TABLE `recruitresume` (
  `rid` int(10) NOT NULL,
  `cid` int(10) DEFAULT NULL,
  `sid` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of recruitresume
-- ----------------------------
INSERT INTO `recruitresume` VALUES ('1', '9', '2');

-- ----------------------------
-- Table structure for resume
-- ----------------------------
DROP TABLE IF EXISTS `resume`;
CREATE TABLE `resume` (
  `sid` int(100) NOT NULL,
  `sname` varchar(50) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `birthdate` varchar(10) DEFAULT NULL,
  `nation` varchar(20) DEFAULT NULL,
  `politics` varchar(20) DEFAULT NULL,
  `graduation` varchar(10) DEFAULT NULL,
  `school` varchar(50) DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL,
  `education` varchar(10) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `foreignlanguage` varchar(50) DEFAULT NULL,
  `hobby` text,
  `practice` text,
  `position` text,
  `honor` text,
  `research` text,
  `selfevaluation` text,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of resume
-- ----------------------------
INSERT INTO `resume` VALUES ('2', '李某', '男', '19870101', '汉族', '共青团员', '2012年', '中山大学', '软件工程', '本科', '110@qq.com', '110', '英语十级', '无', '1', '11', '1', '1', '1');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sid` int(100) NOT NULL,
  `sname` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `idnumber` varchar(20) DEFAULT NULL,
  `school` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `major` varchar(50) DEFAULT NULL,
  `education` varchar(10) DEFAULT NULL,
  `entrancedate` varchar(20) DEFAULT NULL,
  `nativeplace` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2', '李某', '男', '44444444444444441', '广东1', '信息工程系', '软件工程', '本科1', '20190102', '广东河北');
INSERT INTO `student` VALUES ('3', '黄某', '男', '123456789', '中山大学', '计算机系', '软件工程', '大专', '20190201', '湖南长沙');
INSERT INTO `student` VALUES ('4', '何某', '男', '4454466666666', '北京大学', '计算机系', '计算机与科学技术', '本科', '20160101', '四川成都');
INSERT INTO `student` VALUES ('5', '何某', '女', '44444444444444444', '湖南大学', '艺术系', '舞蹈', '大专', '20190101', '河南');
INSERT INTO `student` VALUES ('7', '叶某', '女', '411445451458415814', '番禺职业技术学院', '教育系', '数学教育', '本科', '20150101', '石家庄');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `usertypes` varchar(20) DEFAULT NULL,
  `verify` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'admin', '2');
INSERT INTO `user` VALUES ('2', 'stu1', '123456', 'student', '2');
INSERT INTO `user` VALUES ('3', 'stu2', '123456', 'student', '2');
INSERT INTO `user` VALUES ('4', 'stu3', '123456', 'student', '2');
INSERT INTO `user` VALUES ('5', 'stu4', '123456', 'student', '2');
INSERT INTO `user` VALUES ('6', 'stu5', '123456', 'student', '2');
INSERT INTO `user` VALUES ('7', 'stu6', '123456', 'student', '2');
INSERT INTO `user` VALUES ('8', 'stu7', '123456', 'student', '2');
INSERT INTO `user` VALUES ('9', 'com1', '12345678', 'company', '2');
INSERT INTO `user` VALUES ('10', 'com2', '123456', 'company', '2');
INSERT INTO `user` VALUES ('11', 'com3', '123456', 'company', '2');
