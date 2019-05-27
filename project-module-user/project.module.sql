CREATE DATABASE  IF NOT EXISTS `project_module` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `project_module`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 192.168.111.135    Database: iissy
-- ------------------------------------------------------
-- Server version	8.0.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `login_user`
--


DROP TABLE IF EXISTS `login_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `avatar` varchar(255)  NOT NULL COMMENT '头像',
  `account` varchar(45)  NOT NULL COMMENT '账号',
  `password` varchar(45)  NOT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT '' COMMENT 'md5密码盐',
  `user_name` varchar(45) DEFAULT '' COMMENT '用户名字',
  `nice_name` varchar(45) DEFAULT '' COMMENT '昵称名字',
  `birthday` datetime DEFAULT '1970-01-01' COMMENT '生日',
  `sex` tinyint(1) DEFAULT 1 COMMENT '性别（1：男 2：女）',
  `email` varchar(45) DEFAULT '' COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT '' COMMENT '电话',
  `user_status` int(11) DEFAULT 1 COMMENT '状态(1：启用  2：冻结  3：删除）',
  `role_id` int(11) DEFAULT 0 COMMENT '角色id',
  `dept_id` int(11) DEFAULT 0 COMMENT '部门id',
  `account_type` tinyint(10) DEFAULT 0 COMMENT '账号类型',
  `create_time` datetime DEFAULT '1970-01-01' COMMENT '创建时间',
  `update_time` datetime DEFAULT '1970-01-01' COMMENT '创建时间',
  `user_version` varchar(45) DEFAULT '' COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='用户登录表';