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
  `user_status` tinyint(10) DEFAULT 1 COMMENT '状态(1：启用  2：冻结  3：删除）',
  `role_id` int(11) DEFAULT 0 COMMENT '角色id',
  `dept_id` int(11) DEFAULT 0 COMMENT '部门id',
  `user_city` int(11) DEFAULT 0 COMMENT '城市',
  `account_type` tinyint(10) DEFAULT 0 COMMENT '账号类型',
  `create_time` datetime DEFAULT '1970-01-01' COMMENT '创建时间',
  `update_time` datetime DEFAULT '1970-01-01' COMMENT '创建时间',
  `user_version` varchar(45) DEFAULT '' COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COMMENT='用户登录表';


DROP TABLE IF EXISTS `core_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_value` varchar(16) NOT NULL,
  `dict_name` varchar(128) NOT NULL COMMENT '名称',
  `dict_type` varchar(64) NOT NULL COMMENT '字典编码',
  `type_name` varchar(64) NOT NULL COMMENT '类型描述',
  `dict_sort` int(6) DEFAULT 0 COMMENT '排序',
  `dict_parent` int(64) DEFAULT 0 COMMENT '父id',
  `dict_status` tinyint(4) DEFAULT 1 COMMENT '删除标记',
  `dict_remark` varchar(255) DEFAULT "" COMMENT '备注',
  `create_time` datetime(6) DEFAULT '1970-01-01' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='字典表';


DROP TABLE IF EXISTS `core_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(16) DEFAULT "" COMMENT '角色编码',
  `role_name` varchar(255) DEFAULT "" COMMENT '角色名称',
  `create_time` datetime(6) DEFAULT '1970-01-01' COMMENT '创建时间',
  `role_type` varchar(6) DEFAULT 2 COMMENT '1 可以配置 2 固定权限角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `core_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `role_id` int(20) NOT NULL,
  `org_id` int(20) NOT NULL,
  `create_time` datetime(6) DEFAULT '1970-01-01',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';