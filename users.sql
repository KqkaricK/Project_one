/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bakery

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-05 16:48:30
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE Blog;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `anons` tinytext NOT NULL,
  `ful_text` mediumtext DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

create table users
(
  user_id  int auto_increment
    primary key,
  username varchar(45)       not null,
  password varchar(45)       not null,
  email    varchar(45)       null,
  role     varchar(45)       not null,
  enabled  tinyint default 1 not null
);

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `post` VALUES ('1', 'Что-то', 'Кратко', 'чтооооооооооооооо-тоооооооооооооо длииииииииииинооооооооооооооооое');
