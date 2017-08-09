-- MySQL dump 10.13  Distrib 5.5.28, for Win64 (x86)
--
-- Host: localhost    Database: xh_media_count
-- ------------------------------------------------------
-- Server version	5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `index_xh_media_channel`
--

DROP TABLE IF EXISTS `index_xh_media_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `index_xh_media_channel` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CHANNEL_ID` varchar(50) DEFAULT '0' COMMENT '频道ID',
  `CHANNEL_NAME` varchar(50) DEFAULT NULL COMMENT '频道名称',
  `STATUS` tinyint(2) DEFAULT '1' COMMENT '状态，0停用，1正常',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `index_xh_media_channel`
--

LOCK TABLES `index_xh_media_channel` WRITE;
/*!40000 ALTER TABLE `index_xh_media_channel` DISABLE KEYS */;
/*!40000 ALTER TABLE `index_xh_media_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `index_xh_media_origin`
--

DROP TABLE IF EXISTS `index_xh_media_origin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `index_xh_media_origin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ORIGIN_ID` varchar(50) DEFAULT '0' COMMENT '渠道ID',
  `ORIGIN_NAME` varchar(50) DEFAULT NULL COMMENT '渠道名称',
  `STATUS` tinyint(2) DEFAULT '1' COMMENT '状态，0停用，1正常',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `index_xh_media_origin`
--

LOCK TABLES `index_xh_media_origin` WRITE;
/*!40000 ALTER TABLE `index_xh_media_origin` DISABLE KEYS */;
/*!40000 ALTER TABLE `index_xh_media_origin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `index_xh_media_program`
--

DROP TABLE IF EXISTS `index_xh_media_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `index_xh_media_program` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `GLOBAL_ID` varchar(50) DEFAULT '0' COMMENT '全局ID',
  `CHINESE_NAME` varchar(100) DEFAULT NULL COMMENT '节目名称',
  `TIME_LENGTH` varchar(20) DEFAULT '0' COMMENT '节目时长，单位秒',
  `TYPE` smallint(4) DEFAULT '0' COMMENT '节目类型，0未知，1图文，2视频',
  `PUB_DATE` varchar(20) DEFAULT NULL COMMENT '发布时间',
  `SOURCE` varchar(20) DEFAULT NULL COMMENT '节目来源',
  `STATUS` tinyint(2) DEFAULT '1' COMMENT '状态，0停用，1正常',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `index_xh_media_program`
--

LOCK TABLES `index_xh_media_program` WRITE;
/*!40000 ALTER TABLE `index_xh_media_program` DISABLE KEYS */;
/*!40000 ALTER TABLE `index_xh_media_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `index_xh_media_user`
--

DROP TABLE IF EXISTS `index_xh_media_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `index_xh_media_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `USER_ID` int(11) DEFAULT NULL COMMENT '用户表ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `NICKNAME` varchar(50) DEFAULT NULL COMMENT '昵称',
  `TRUENAME` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `PHONE` varchar(20) DEFAULT NULL COMMENT '手机号',
  `REG_TYPE` smallint(4) DEFAULT NULL COMMENT '注册类型(主要区分手机号注册还是第三方注册)',
  `REGISTER_TIME` varchar(50) DEFAULT NULL COMMENT '注册时间',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `index_xh_media_user`
--

LOCK TABLES `index_xh_media_user` WRITE;
/*!40000 ALTER TABLE `index_xh_media_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `index_xh_media_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_xh_media_interactive`
--

DROP TABLE IF EXISTS `log_xh_media_interactive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_xh_media_interactive` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DATE` varchar(20) DEFAULT NULL COMMENT '日期',
  `HOUR` varchar(10) DEFAULT NULL COMMENT '小时',
  `ORIGIN_ID` varchar(50) DEFAULT NULL COMMENT '渠道ID',
  `ORIGIN_NAME` varchar(50) DEFAULT NULL COMMENT '渠道名称',
  `CHANNEL_ID` varchar(50) DEFAULT NULL COMMENT '频道ID',
  `CHANNEL_NAME` varchar(50) DEFAULT NULL COMMENT '频道名称',
  `GLOBAL_ID` varchar(50) DEFAULT NULL COMMENT '节目全局ID',
  `CHINESE_NAME` varchar(100) DEFAULT NULL COMMENT '节目中文名称',
  `SOURCE` varchar(20) DEFAULT NULL COMMENT '节目来源(合并之用)',
  `TYPE` smallint(4) DEFAULT NULL COMMENT '节目类型，0未知，1图文，2视频',
  `COMMENT_NUMBER` int(11) DEFAULT NULL COMMENT '评论数',
  `TOPIC_NUMBER` int(11) DEFAULT NULL COMMENT '话题数',
  `SHARE_NUMBER` int(11) DEFAULT NULL COMMENT '分享数',
  `COLLECTION_NUMBER` int(11) DEFAULT NULL COMMENT '收藏数',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_xh_media_interactive`
--

LOCK TABLES `log_xh_media_interactive` WRITE;
/*!40000 ALTER TABLE `log_xh_media_interactive` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_xh_media_interactive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_xh_media_program_analysis`
--

DROP TABLE IF EXISTS `log_xh_media_program_analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_xh_media_program_analysis` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DATE` varchar(20) DEFAULT NULL COMMENT '日期',
  `HOUR` varchar(10) DEFAULT NULL COMMENT '小时',
  `ORIGIN_ID` varchar(50) DEFAULT NULL COMMENT '渠道ID',
  `ORIGIN_NAME` varchar(50) DEFAULT NULL COMMENT '渠道名称',
  `CHANNEL_ID` varchar(50) DEFAULT NULL COMMENT '频道ID',
  `CHANNEL_NAME` varchar(50) DEFAULT NULL COMMENT '频道名称',
  `GLOBAL_ID` varchar(50) DEFAULT NULL COMMENT '节目全局ID',
  `CHINESE_NAME` varchar(100) DEFAULT NULL COMMENT '节目中文名称',
  `PUB_DATE` varchar(20) DEFAULT NULL COMMENT '发布时间',
  `TYPE` smallint(4) DEFAULT NULL COMMENT '节目类型，0未知，1图文，2视频',
  `PLAY_TIME` int(11) DEFAULT '0' COMMENT '播放总时长',
  `TIME_LENGTH` int(11) DEFAULT '0' COMMENT '节目总时长',
  `PLAY_NUMBER` int(11) DEFAULT '0' COMMENT '播放次数',
  `PLAY_USER_NUMBER` int(11) DEFAULT '0' COMMENT '播放人数',
  `SOURCE` varchar(20) DEFAULT NULL COMMENT '节目来源(用于同一节目，放在不同位置的数据合并用)',
  `PV` int(11) DEFAULT NULL COMMENT 'PV数',
  `UV` int(11) DEFAULT NULL COMMENT 'UV数',
  `IP` varchar(20) DEFAULT NULL COMMENT '用户客户端IP地址数',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_xh_media_program_analysis`
--

LOCK TABLES `log_xh_media_program_analysis` WRITE;
/*!40000 ALTER TABLE `log_xh_media_program_analysis` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_xh_media_program_analysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_xh_media_user`
--

DROP TABLE IF EXISTS `log_xh_media_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_xh_media_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DATE` varchar(20) DEFAULT NULL COMMENT '日期',
  `HOUR` varchar(10) DEFAULT NULL COMMENT '小时',
  `ORIGIN_ID` varchar(50) DEFAULT NULL COMMENT '渠道ID',
  `ORIGIN_NAME` varchar(50) DEFAULT NULL COMMENT '渠道名称',
  `CHANNEL_ID` varchar(50) DEFAULT NULL COMMENT '频道ID',
  `CHANNEL_NAME` varchar(50) DEFAULT NULL COMMENT '频道名称',
  `NEW_USER` int(11) DEFAULT NULL COMMENT '新增用户数()',
  `ACTIVE_USER` int(11) DEFAULT NULL COMMENT '活跃用户数',
  `USE_LENGTH` int(11) DEFAULT NULL COMMENT '使用时长，单位秒',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_xh_media_user`
--

LOCK TABLES `log_xh_media_user` WRITE;
/*!40000 ALTER TABLE `log_xh_media_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_xh_media_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_xh_media_visit`
--

DROP TABLE IF EXISTS `log_xh_media_visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_xh_media_visit` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DATE` varchar(20) DEFAULT NULL COMMENT '日期',
  `HOUR` varchar(10) DEFAULT NULL COMMENT '小时',
  `ORIGIN_ID` varchar(50) DEFAULT NULL COMMENT '渠道ID',
  `ORIGIN_NAME` varchar(50) DEFAULT NULL COMMENT '渠道名称',
  `CHANNEL_ID` varchar(50) DEFAULT NULL COMMENT '频道ID',
  `CHANNEL_NAME` varchar(50) DEFAULT NULL COMMENT '频道名称',
  `PV` int(11) DEFAULT NULL COMMENT 'PV数',
  `UV` int(11) DEFAULT NULL COMMENT 'UV数',
  `IP` int(11) DEFAULT NULL COMMENT 'IP数',
  `PLAY_TIME` int(11) DEFAULT NULL COMMENT '播放时长',
  `LOCAL` int(11) DEFAULT NULL COMMENT '站内访问',
  `NON_LOCAL` int(11) DEFAULT NULL COMMENT '站外访问',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_xh_media_visit`
--

LOCK TABLES `log_xh_media_visit` WRITE;
/*!40000 ALTER TABLE `log_xh_media_visit` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_xh_media_visit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `source_xh_media_user_visit_log`
--

DROP TABLE IF EXISTS `source_xh_media_user_visit_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `source_xh_media_user_visit_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,自增',
  `CHANNLE_ID` varchar(50) DEFAULT NULL COMMENT '频道ID',
  `ORIGIN_ID` varchar(50) DEFAULT NULL COMMENT '渠道ID',
  `HOST` varchar(50) DEFAULT NULL COMMENT '站点域名',
  `USER_ID` varchar(50) DEFAULT '0' COMMENT '用户ID',
  `USER_REF` varchar(10) DEFAULT '0' COMMENT '用户来源，1为C类客户端，2为B类客户端,0为H5用户',
  `GLOBAL_ID` varchar(50) DEFAULT '0' COMMENT '节目全局ID',
  `SITE_CODE` varchar(14) DEFAULT NULL COMMENT '站点标识',
  `REF` varchar(200) DEFAULT NULL COMMENT '来源URL',
  `REF_ID` varchar(10) DEFAULT '0' COMMENT '来源ID,0站内 1QQ 2QQ空间 3微信 4微信朋友圈 5新浪微博',
  `LAST_VISIT_TIME` varchar(50) DEFAULT NULL COMMENT '上次访问时间',
  `RESOLUTION` varchar(50) DEFAULT NULL COMMENT '屏幕宽高,宽:高',
  `PLAY_TIME` varchar(20) DEFAULT NULL COMMENT '节目播放时长,单位:秒',
  `TIME_LENGTH` varchar(20) DEFAULT NULL COMMENT '节目时长,单位:秒',
  `IP` varchar(20) DEFAULT NULL COMMENT '用户客户端IP地址',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `source_xh_media_user_visit_log`
--

LOCK TABLES `source_xh_media_user_visit_log` WRITE;
/*!40000 ALTER TABLE `source_xh_media_user_visit_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `source_xh_media_user_visit_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_list`
--

DROP TABLE IF EXISTS `sys_menu_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu_list` (
  `MENU_ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `MENU_NAME` varchar(50) NOT NULL,
  `MENU_TAG` varchar(50) NOT NULL,
  `MENU_LEVEL` smallint(6) NOT NULL,
  `PARENT_ID` smallint(6) NOT NULL,
  `URL` varchar(100) NOT NULL,
  `ICON` varchar(50) NOT NULL,
  `MODEL_ID` smallint(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_list`
--

LOCK TABLES `sys_menu_list` WRITE;
/*!40000 ALTER TABLE `sys_menu_list` DISABLE KEYS */;
INSERT INTO `sys_menu_list` VALUES (1,'统计内容','',1,0,'0','icon-application-form-edit',0),(2,'系统管理','',2,0,'0','icon-wrench',0),(4,'用户管理','',2,2,'#','icon-users',1),(6,'访问统计','',1,1,'#','icon-book',2),(20,'视频分析明细','',2,1,'#','icon-book',2),(21,'页面分析明细','',3,1,'#','icon-book',2),(22,'新老访客','',4,1,'#','icon-book',2),(23,'效互分析','',5,1,'#','icon-book',2);
/*!40000 ALTER TABLE `sys_menu_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_users`
--

DROP TABLE IF EXISTS `sys_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ACCOUNT` varchar(50) NOT NULL,
  `USER_NAME` varchar(120) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `TRUE_PASSWORD` varchar(50) NOT NULL,
  `DESC` varchar(300) NOT NULL,
  `STATUS` smallint(1) NOT NULL,
  `DEPT` varchar(60) NOT NULL,
  `DUTY` varchar(30) NOT NULL,
  `SUB_SYSTEM` varchar(60) NOT NULL,
  `ISADMIN` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是管理员帐号,0不是,1是',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_users`
--

LOCK TABLES `sys_users` WRITE;
/*!40000 ALTER TABLE `sys_users` DISABLE KEYS */;
INSERT INTO `sys_users` VALUES (2,'sssss','普通用户','2d02e669731cbade6a64b58d602cf2a4','sssss','测试用账户',0,'1','1','1',0,'2014-08-14 05:45:13'),(1,'admin','管理员','2d02e669731cbade6a64b58d602cf2a4','sssss','测试管理员账户',0,'1','1','1',0,'2014-08-17 03:52:13'),(4,'Sunny','Sunny','4297f44b13955235245b2497399d7a93','123123','佟伟栋',0,'1','1','1',0,'2014-09-17 22:09:23');
/*!40000 ALTER TABLE `sys_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-15 15:06:52
