/*
Navicat MySQL Data Transfer

Source Server         : newtech
Source Server Version : 50722
Source Host           : 47.254.73.8:3306
Source Database       : newtech

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-06-25 09:49:58
*/

SET FOREIGN_KEY_CHECKS=0;

CREATE DATABASE IF NOT EXISTS newtech DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE newtech;

-- ----------------------------
-- Table structure for ntk_news
-- ----------------------------
DROP TABLE IF EXISTS `ntk_news`;
CREATE TABLE `ntk_news` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) NOT NULL COMMENT '标题',
  `detail` longtext COMMENT '详情',
  `logo` varchar(1024) DEFAULT NULL COMMENT '图片',
  `type` enum('COMPANY','INDUSTRY','TECH') DEFAULT 'COMPANY' COMMENT '公司新闻，行业动态，技术百科',
  `gmt_create` datetime DEFAULT NULL,
  `show_index` tinyint(2) DEFAULT NULL COMMENT '显示在首页',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ntk_product
-- ----------------------------
DROP TABLE IF EXISTS `ntk_product`;
CREATE TABLE `ntk_product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(1024) NOT NULL COMMENT '标题',
  `intro` text COMMENT '简单介绍',
  `detail` longtext COMMENT '详情',
  `logo` varchar(1024) DEFAULT NULL COMMENT '图片',
  `show_index` tinyint(2) DEFAULT NULL COMMENT '显示在首页',
  `gmt_create` datetime DEFAULT NULL,
  `seo_title` varchar(1024) DEFAULT NULL,
  `seo_description` varchar(1024) DEFAULT NULL,
  `seo_keyword` varchar(1024) DEFAULT NULL,
  `product_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(1024) NOT NULL,
  `parent` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_type_name_uindex` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sec_role
-- ----------------------------
DROP TABLE IF EXISTS `sec_role`;
CREATE TABLE `sec_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `parent` int(10) unsigned DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sec_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_role_user`;
CREATE TABLE `sec_role_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sec_user
-- ----------------------------
DROP TABLE IF EXISTS `sec_user`;
CREATE TABLE `sec_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  `keyt` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `locked` tinyint(2) DEFAULT NULL,
  `token` varchar(1024) DEFAULT NULL,
  `avatar` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;