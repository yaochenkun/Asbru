/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : adl

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-04-02 19:50:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `action`
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action
-- ----------------------------
INSERT INTO `action` VALUES ('31', 'enter bathroom', '2017-03-26 07:14:34');
INSERT INTO `action` VALUES ('32', 'wash hands', '2017-03-26 07:31:44');
INSERT INTO `action` VALUES ('33', 'brush teeth', '2017-03-26 07:31:53');
INSERT INTO `action` VALUES ('34', 'wash face', '2017-03-26 07:32:06');
INSERT INTO `action` VALUES ('35', 'leave bathroom', '2017-03-26 07:32:18');
INSERT INTO `action` VALUES ('36', 'enter kitchen', '2017-03-26 07:44:55');
INSERT INTO `action` VALUES ('37', 'open fridge', '2017-03-26 07:45:05');
INSERT INTO `action` VALUES ('38', 'find food', '2017-03-26 07:45:16');
INSERT INTO `action` VALUES ('39', 'close fridge', '2017-03-26 07:45:37');
INSERT INTO `action` VALUES ('40', 'cook food', '2017-03-26 07:45:48');
INSERT INTO `action` VALUES ('41', 'eat', '2017-03-26 07:40:43');
INSERT INTO `action` VALUES ('42', 'leave kitchen', '2017-03-26 07:40:54');
INSERT INTO `action` VALUES ('43', 'run', '2017-03-26 07:41:03');
INSERT INTO `action` VALUES ('44', 'sing1', '2017-03-26 07:41:14');
INSERT INTO `action` VALUES ('45', 'play game', '2017-03-26 07:41:27');
INSERT INTO `action` VALUES ('46', 'watch TV', '2017-03-26 07:43:01');
INSERT INTO `action` VALUES ('47', 'read books', '2017-03-26 08:11:06');
INSERT INTO `action` VALUES ('48', 'open window', '2017-03-26 08:11:18');
INSERT INTO `action` VALUES ('49', 'sweep floor', '2017-03-26 08:11:31');
INSERT INTO `action` VALUES ('50', 'mod floor', '2017-03-26 08:11:44');
INSERT INTO `action` VALUES ('51', 'wipe furniture', '2017-03-26 08:23:26');
INSERT INTO `action` VALUES ('52', 'empty trash', '2017-03-26 09:23:38');
INSERT INTO `action` VALUES ('53', 'enter washroom', '2017-03-26 09:23:40');
INSERT INTO `action` VALUES ('54', 'take dirty clothes', '2017-03-26 09:23:43');
INSERT INTO `action` VALUES ('55', 'wash clothes', '2017-03-26 09:27:26');
INSERT INTO `action` VALUES ('64', 'wring clothes', '2017-03-26 09:28:27');
INSERT INTO `action` VALUES ('61', 'leave washroom', '2017-03-26 10:28:28');
INSERT INTO `action` VALUES ('62', 'sun clothes', '2017-03-26 10:59:45');
INSERT INTO `action` VALUES ('66', 'leave home', '2017-03-26 11:10:45');
INSERT INTO `action` VALUES ('67', 'buy food', '2017-03-26 11:03:45');
INSERT INTO `action` VALUES ('68', 'cook food', '2017-03-26 12:40:12');
INSERT INTO `action` VALUES ('69', 'eat', '2017-03-26 12:03:15');
INSERT INTO `action` VALUES ('70', 'leave kitchen', '2017-03-26 12:21:41');
INSERT INTO `action` VALUES ('71', 'sleep2', '2017-03-26 13:01:12');
INSERT INTO `action` VALUES ('72', 'play games', '2017-03-26 12:40:12');
INSERT INTO `action` VALUES ('73', 'watch TV', '2017-03-26 13:40:12');
INSERT INTO `action` VALUES ('74', 'listen music', '2017-03-26 14:20:12');
INSERT INTO `action` VALUES ('75', 'greet', '2017-03-26 14:37:12');
INSERT INTO `action` VALUES ('76', 'share good news', '2017-03-26 14:53:12');
INSERT INTO `action` VALUES ('77', 'ask something funny', '2017-03-26 14:58:12');
INSERT INTO `action` VALUES ('78', 'laugh1', '2017-03-26 15:03:12');
INSERT INTO `action` VALUES ('79', 'farewell', '2017-03-26 15:20:12');
INSERT INTO `action` VALUES ('80', 'date', '2017-03-26 15:33:12');
INSERT INTO `action` VALUES ('81', 'meet', '2017-03-26 15:59:12');
INSERT INTO `action` VALUES ('82', 'go to the gym', '2017-03-26 16:37:12');
INSERT INTO `action` VALUES ('83', 'enter bathroom', '2017-03-26 17:30:12');
INSERT INTO `action` VALUES ('84', 'take a shower', '2017-03-26 17:34:12');
INSERT INTO `action` VALUES ('85', 'leave bathroom', '2017-03-26 17:33:12');
INSERT INTO `action` VALUES ('86', 'call restaurant', '2017-03-26 18:03:32');
INSERT INTO `action` VALUES ('87', 'select food', '2017-03-26 18:05:11');
INSERT INTO `action` VALUES ('88', 'enter kitchen', '2017-03-26 18:45:08');
INSERT INTO `action` VALUES ('89', 'eat2', '2017-03-26 18:48:11');
INSERT INTO `action` VALUES ('90', 'leave kitchen', '2017-03-26 19:03:03');
INSERT INTO `action` VALUES ('91', 'go for a walk', '2017-03-26 19:21:09');
INSERT INTO `action` VALUES ('92', 'watch movie', '2017-03-26 19:49:56');
INSERT INTO `action` VALUES ('93', 'surf the internet', '2017-03-26 21:00:44');
INSERT INTO `action` VALUES ('94', 'greet', '2017-03-26 21:10:08');
INSERT INTO `action` VALUES ('95', 'chat something funny', '2017-03-26 21:15:25');
INSERT INTO `action` VALUES ('96', 'farewell1', '2017-04-02 21:24:22');
INSERT INTO `action` VALUES ('97', 'record progress', '2017-03-26 21:25:17');
INSERT INTO `action` VALUES ('98', 'make goals', '2017-03-26 21:29:52');
INSERT INTO `action` VALUES ('99', 'give reasons', '2017-03-26 21:37:15');
INSERT INTO `action` VALUES ('100', 'set deadlines', '2017-03-26 21:42:11');
INSERT INTO `action` VALUES ('101', 'enter bathroom', '2017-03-26 21:45:12');
INSERT INTO `action` VALUES ('102', 'wash hands', '2017-03-26 21:47:19');
INSERT INTO `action` VALUES ('103', 'brush teeth', '2017-03-26 21:52:21');
INSERT INTO `action` VALUES ('104', 'wash face', '2017-03-26 21:55:22');
INSERT INTO `action` VALUES ('105', 'wash feet', '2017-03-26 21:59:29');
INSERT INTO `action` VALUES ('106', 'leave bathroom', '2017-03-26 22:01:31');
INSERT INTO `action` VALUES ('107', 'turn on the light', '2017-03-26 22:08:33');
INSERT INTO `action` VALUES ('108', 'read books', '2017-03-26 22:09:13');
INSERT INTO `action` VALUES ('109', 'turn off the light', '2017-03-26 22:30:18');
INSERT INTO `action` VALUES ('110', 'sleep', '2017-03-26 22:32:44');

-- ----------------------------
-- Table structure for `result`
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `score` float(11,2) DEFAULT NULL,
  `fullScore` float(11,2) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `finishTime` timestamp NULL DEFAULT NULL,
  `belief` float(11,2) DEFAULT NULL,
  `reason` varchar(500) DEFAULT NULL,
  `taskId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1587 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of result
-- ----------------------------

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `beginTime` tinyint(3) DEFAULT NULL,
  `endTime` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', 'morning', '7', '11');
INSERT INTO `task` VALUES ('2', 'afternoon', '11', '18');
INSERT INTO `task` VALUES ('3', 'evening', '18', '23');
