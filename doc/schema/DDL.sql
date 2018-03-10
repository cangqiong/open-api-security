/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.50 : Database - test
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `api_blank_list` */

CREATE TABLE `api_blank_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `API` varchar(128) NOT NULL COMMENT 'API路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `API` (`API`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `user_api` */

CREATE TABLE `user_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uiid` varchar(32) NOT NULL COMMENT 'uiid 用户唯一标识',
  `api_path` varchar(128) NOT NULL COMMENT 'API路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uiid` (`uiid`,`api_path`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
