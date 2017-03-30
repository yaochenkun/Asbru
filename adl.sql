/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : adl

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-03-30 23:18:46
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
) ENGINE=MyISAM AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

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
INSERT INTO `action` VALUES ('44', 'sing', '2017-03-26 07:41:14');
INSERT INTO `action` VALUES ('45', 'play game', '2017-03-26 07:41:27');
INSERT INTO `action` VALUES ('46', 'watch TV', '2017-03-26 07:43:01');
INSERT INTO `action` VALUES ('47', 'read book', '2017-03-26 08:11:06');
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
INSERT INTO `action` VALUES ('65', 'enter bathroom', '2017-03-26 21:33:33');

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
  `taskId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=865 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of result
-- ----------------------------
INSERT INTO `result` VALUES ('541', 'enter bathroom', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:14:34', '1');
INSERT INTO `result` VALUES ('542', 'wash hands', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:31:44', '1');
INSERT INTO `result` VALUES ('543', 'brush teeth', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:31:53', '1');
INSERT INTO `result` VALUES ('544', 'wash face', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:32:06', '1');
INSERT INTO `result` VALUES ('545', 'leave bathroom', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:32:18', '1');
INSERT INTO `result` VALUES ('546', 'geting up', 'plan', '17.86', '17.86', 'completed', '2017-03-26 07:32:18', '1');
INSERT INTO `result` VALUES ('547', 'enter kitchen', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:44:55', '1');
INSERT INTO `result` VALUES ('548', 'open fridge', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:45:05', '1');
INSERT INTO `result` VALUES ('549', 'find food', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:45:16', '1');
INSERT INTO `result` VALUES ('550', 'close fridge', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:45:37', '1');
INSERT INTO `result` VALUES ('551', 'cook food', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:45:48', '1');
INSERT INTO `result` VALUES ('552', 'prepare breakfast', 'plan', '14.29', '14.29', 'completed', '2017-03-26 07:45:48', '1');
INSERT INTO `result` VALUES ('553', 'eat', 'action', '2.86', '3.57', 'completed', '2017-03-26 07:40:43', '1');
INSERT INTO `result` VALUES ('554', 'leave kitchen', 'action', '2.86', '3.57', 'completed', '2017-03-26 07:40:54', '1');
INSERT INTO `result` VALUES ('555', 'breakfast', 'plan', '23.57', '25.00', 'activated', '2017-03-26 07:45:48', '1');
INSERT INTO `result` VALUES ('556', 'run', 'action', '2.86', '3.57', 'completed', '2017-03-26 07:41:03', '1');
INSERT INTO `result` VALUES ('557', 'sing', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:41:14', '1');
INSERT INTO `result` VALUES ('558', 'play game', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:41:27', '1');
INSERT INTO `result` VALUES ('559', 'watch TV', 'action', '3.57', '3.57', 'completed', '2017-03-26 07:43:01', '1');
INSERT INTO `result` VALUES ('560', 'entertainment', 'plan', '10.00', '10.71', 'completed', '2017-03-26 07:43:01', '1');
INSERT INTO `result` VALUES ('561', 'read book', 'action', '3.57', '3.57', 'completed', '2017-03-26 08:11:06', '1');
INSERT INTO `result` VALUES ('562', 'open window', 'action', '3.57', '3.57', 'completed', '2017-03-26 08:11:18', '1');
INSERT INTO `result` VALUES ('563', 'sweep floor', 'action', '3.57', '3.57', 'completed', '2017-03-26 08:11:31', '1');
INSERT INTO `result` VALUES ('564', 'mod floor', 'action', '3.57', '3.57', 'completed', '2017-03-26 08:11:44', '1');
INSERT INTO `result` VALUES ('565', 'cleaning floor', 'plan', '7.14', '7.14', 'completed', '2017-03-26 08:11:44', '1');
INSERT INTO `result` VALUES ('566', 'wipe furniture', 'action', '3.57', '3.57', 'completed', '2017-03-26 08:23:26', '1');
INSERT INTO `result` VALUES ('567', 'empty trash', 'action', '3.57', '3.57', 'completed', '2017-03-26 09:23:38', '1');
INSERT INTO `result` VALUES ('568', 'enter washroom', 'action', '3.57', '3.57', 'completed', '2017-03-26 09:23:40', '1');
INSERT INTO `result` VALUES ('569', 'take dirty clothes', 'action', '3.57', '3.57', 'completed', '2017-03-26 09:23:43', '1');
INSERT INTO `result` VALUES ('570', 'wash clothes', 'action', '3.57', '3.57', 'completed', '2017-03-26 09:27:26', '1');
INSERT INTO `result` VALUES ('571', 'wring clothes', 'action', '3.57', '3.57', 'completed', '2017-03-26 09:28:27', '1');
INSERT INTO `result` VALUES ('572', 'leave washroom', 'action', '3.57', '3.57', 'completed', '2017-03-26 10:28:28', '1');
INSERT INTO `result` VALUES ('573', 'sun clothes', 'action', '3.57', '3.57', 'completed', '2017-03-26 10:59:45', '1');
INSERT INTO `result` VALUES ('574', 'washing clothes', 'plan', '21.43', '21.43', 'completed', '2017-03-26 10:59:45', '1');
INSERT INTO `result` VALUES ('575', 'cleaning', 'plan', '39.29', '39.29', 'completed', '2017-03-26 10:59:45', '1');
INSERT INTO `result` VALUES ('576', 'morning', 'plan', '97.14', '100.00', 'activated', '2017-03-26 10:59:45', '1');
INSERT INTO `result` VALUES ('577', 'enter bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('578', 'wash hands', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('579', 'brush teeth', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('580', 'wash face', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('581', 'leave bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('582', 'geting up', 'plan', '0.00', '17.86', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('583', 'enter kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('584', 'open fridge', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('585', 'find food', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('586', 'close fridge', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('587', 'cook food', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('588', 'prepare breakfast', 'plan', '0.00', '14.29', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('589', 'eat', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('590', 'leave kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('591', 'breakfast', 'plan', '0.00', '25.00', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('592', 'run', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('593', 'sing', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('594', 'play game', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('595', 'watch TV', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('596', 'entertainment', 'plan', '0.00', '10.71', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('597', 'read book', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('598', 'open window', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('599', 'sweep floor', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('600', 'mod floor', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('601', 'cleaning floor', 'plan', '0.00', '7.14', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('602', 'wipe furniture', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('603', 'empty trash', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('604', 'enter washroom', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('605', 'take dirty clothes', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('606', 'wash clothes', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('607', 'wring clothes', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('608', 'leave washroom', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('609', 'sun clothes', 'action', '0.00', '3.57', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('610', 'washing clothes', 'plan', '0.00', '21.43', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('611', 'cleaning', 'plan', '0.00', '39.29', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('612', 'morning', 'plan', '0.00', '100.00', 'considered', '2017-03-26 11:00:00', '2');
INSERT INTO `result` VALUES ('613', 'enter bathroom', 'action', '3.57', '3.57', 'completed', '2017-03-26 21:33:33', '3');
INSERT INTO `result` VALUES ('614', 'wash hands', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('615', 'brush teeth', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('616', 'wash face', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('617', 'leave bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('618', 'geting up', 'plan', '3.57', '17.86', 'activated', '2017-03-26 21:33:33', '3');
INSERT INTO `result` VALUES ('619', 'enter kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('620', 'open fridge', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('621', 'find food', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('622', 'close fridge', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('623', 'cook food', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('624', 'prepare breakfast', 'plan', '0.00', '14.29', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('625', 'eat', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('626', 'leave kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('627', 'breakfast', 'plan', '0.00', '25.00', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('628', 'run', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('629', 'sing', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('630', 'play game', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('631', 'watch TV', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('632', 'entertainment', 'plan', '0.00', '10.71', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('633', 'read book', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('634', 'open window', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('635', 'sweep floor', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('636', 'mod floor', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('637', 'cleaning floor', 'plan', '0.00', '7.14', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('638', 'wipe furniture', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('639', 'empty trash', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('640', 'enter washroom', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('641', 'take dirty clothes', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('642', 'wash clothes', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('643', 'wring clothes', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('644', 'leave washroom', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('645', 'sun clothes', 'action', '0.00', '3.57', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('646', 'washing clothes', 'plan', '0.00', '21.43', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('647', 'cleaning', 'plan', '0.00', '39.29', 'considered', '2017-03-26 18:00:00', '3');
INSERT INTO `result` VALUES ('648', 'morning', 'plan', '3.57', '100.00', 'activated', '2017-03-26 21:33:33', '3');
INSERT INTO `result` VALUES ('649', 'enter bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('650', 'wash hands', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('651', 'brush teeth', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('652', 'wash face', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('653', 'leave bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('654', 'geting up', 'plan', '0.00', '17.86', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('655', 'enter kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('656', 'open fridge', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('657', 'find food', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('658', 'close fridge', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('659', 'cook food', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('660', 'prepare breakfast', 'plan', '0.00', '14.29', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('661', 'eat', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('662', 'leave kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('663', 'breakfast', 'plan', '0.00', '25.00', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('664', 'run', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('665', 'sing', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('666', 'play game', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('667', 'watch TV', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('668', 'entertainment', 'plan', '0.00', '10.71', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('669', 'read book', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('670', 'open window', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('671', 'sweep floor', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('672', 'mod floor', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('673', 'cleaning floor', 'plan', '0.00', '7.14', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('674', 'wipe furniture', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('675', 'empty trash', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('676', 'enter washroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('677', 'take dirty clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('678', 'wash clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('679', 'wring clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('680', 'leave washroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('681', 'sun clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('682', 'washing clothes', 'plan', '0.00', '21.43', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('683', 'cleaning', 'plan', '0.00', '39.29', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('684', 'morning', 'plan', '0.00', '100.00', 'considered', '2017-03-19 07:00:00', '1');
INSERT INTO `result` VALUES ('685', 'enter bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('686', 'wash hands', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('687', 'brush teeth', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('688', 'wash face', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('689', 'leave bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('690', 'geting up', 'plan', '0.00', '17.86', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('691', 'enter kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('692', 'open fridge', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('693', 'find food', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('694', 'close fridge', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('695', 'cook food', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('696', 'prepare breakfast', 'plan', '0.00', '14.29', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('697', 'eat', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('698', 'leave kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('699', 'breakfast', 'plan', '0.00', '25.00', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('700', 'run', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('701', 'sing', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('702', 'play game', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('703', 'watch TV', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('704', 'entertainment', 'plan', '0.00', '10.71', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('705', 'read book', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('706', 'open window', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('707', 'sweep floor', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('708', 'mod floor', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('709', 'cleaning floor', 'plan', '0.00', '7.14', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('710', 'wipe furniture', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('711', 'empty trash', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('712', 'enter washroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('713', 'take dirty clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('714', 'wash clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('715', 'wring clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('716', 'leave washroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('717', 'sun clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('718', 'washing clothes', 'plan', '0.00', '21.43', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('719', 'cleaning', 'plan', '0.00', '39.29', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('720', 'morning', 'plan', '0.00', '100.00', 'considered', '2017-03-19 11:00:00', '2');
INSERT INTO `result` VALUES ('721', 'enter bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('722', 'wash hands', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('723', 'brush teeth', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('724', 'wash face', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('725', 'leave bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('726', 'geting up', 'plan', '0.00', '17.86', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('727', 'enter kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('728', 'open fridge', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('729', 'find food', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('730', 'close fridge', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('731', 'cook food', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('732', 'prepare breakfast', 'plan', '0.00', '14.29', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('733', 'eat', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('734', 'leave kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('735', 'breakfast', 'plan', '0.00', '25.00', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('736', 'run', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('737', 'sing', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('738', 'play game', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('739', 'watch TV', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('740', 'entertainment', 'plan', '0.00', '10.71', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('741', 'read book', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('742', 'open window', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('743', 'sweep floor', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('744', 'mod floor', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('745', 'cleaning floor', 'plan', '0.00', '7.14', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('746', 'wipe furniture', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('747', 'empty trash', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('748', 'enter washroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('749', 'take dirty clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('750', 'wash clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('751', 'wring clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('752', 'leave washroom', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('753', 'sun clothes', 'action', '0.00', '3.57', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('754', 'washing clothes', 'plan', '0.00', '21.43', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('755', 'cleaning', 'plan', '0.00', '39.29', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('756', 'morning', 'plan', '0.00', '100.00', 'considered', '2017-03-19 18:00:00', '3');
INSERT INTO `result` VALUES ('757', 'enter bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('758', 'wash hands', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('759', 'brush teeth', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('760', 'wash face', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('761', 'leave bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('762', 'geting up', 'plan', '0.00', '17.86', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('763', 'enter kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('764', 'open fridge', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('765', 'find food', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('766', 'close fridge', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('767', 'cook food', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('768', 'prepare breakfast', 'plan', '0.00', '14.29', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('769', 'eat', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('770', 'leave kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('771', 'breakfast', 'plan', '0.00', '25.00', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('772', 'run', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('773', 'sing', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('774', 'play game', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('775', 'watch TV', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('776', 'entertainment', 'plan', '0.00', '10.71', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('777', 'read book', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('778', 'open window', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('779', 'sweep floor', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('780', 'mod floor', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('781', 'cleaning floor', 'plan', '0.00', '7.14', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('782', 'wipe furniture', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('783', 'empty trash', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('784', 'enter washroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('785', 'take dirty clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('786', 'wash clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('787', 'wring clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('788', 'leave washroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('789', 'sun clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('790', 'washing clothes', 'plan', '0.00', '21.43', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('791', 'cleaning', 'plan', '0.00', '39.29', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('792', 'morning', 'plan', '0.00', '100.00', 'considered', '2017-03-12 07:00:00', '1');
INSERT INTO `result` VALUES ('793', 'enter bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('794', 'wash hands', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('795', 'brush teeth', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('796', 'wash face', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('797', 'leave bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('798', 'geting up', 'plan', '0.00', '17.86', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('799', 'enter kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('800', 'open fridge', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('801', 'find food', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('802', 'close fridge', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('803', 'cook food', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('804', 'prepare breakfast', 'plan', '0.00', '14.29', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('805', 'eat', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('806', 'leave kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('807', 'breakfast', 'plan', '0.00', '25.00', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('808', 'run', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('809', 'sing', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('810', 'play game', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('811', 'watch TV', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('812', 'entertainment', 'plan', '0.00', '10.71', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('813', 'read book', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('814', 'open window', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('815', 'sweep floor', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('816', 'mod floor', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('817', 'cleaning floor', 'plan', '0.00', '7.14', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('818', 'wipe furniture', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('819', 'empty trash', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('820', 'enter washroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('821', 'take dirty clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('822', 'wash clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('823', 'wring clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('824', 'leave washroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('825', 'sun clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('826', 'washing clothes', 'plan', '0.00', '21.43', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('827', 'cleaning', 'plan', '0.00', '39.29', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('828', 'morning', 'plan', '0.00', '100.00', 'considered', '2017-03-12 11:00:00', '2');
INSERT INTO `result` VALUES ('829', 'enter bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('830', 'wash hands', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('831', 'brush teeth', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('832', 'wash face', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('833', 'leave bathroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('834', 'geting up', 'plan', '0.00', '17.86', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('835', 'enter kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('836', 'open fridge', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('837', 'find food', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('838', 'close fridge', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('839', 'cook food', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('840', 'prepare breakfast', 'plan', '0.00', '14.29', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('841', 'eat', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('842', 'leave kitchen', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('843', 'breakfast', 'plan', '0.00', '25.00', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('844', 'run', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('845', 'sing', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('846', 'play game', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('847', 'watch TV', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('848', 'entertainment', 'plan', '0.00', '10.71', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('849', 'read book', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('850', 'open window', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('851', 'sweep floor', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('852', 'mod floor', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('853', 'cleaning floor', 'plan', '0.00', '7.14', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('854', 'wipe furniture', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('855', 'empty trash', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('856', 'enter washroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('857', 'take dirty clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('858', 'wash clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('859', 'wring clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('860', 'leave washroom', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('861', 'sun clothes', 'action', '0.00', '3.57', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('862', 'washing clothes', 'plan', '0.00', '21.43', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('863', 'cleaning', 'plan', '0.00', '39.29', 'considered', '2017-03-12 18:00:00', '3');
INSERT INTO `result` VALUES ('864', 'morning', 'plan', '0.00', '100.00', 'considered', '2017-03-12 18:00:00', '3');

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
INSERT INTO `task` VALUES ('3', 'evening', '18', '22');
