-- MySQL dump 10.11
--
-- Host: localhost    Database: xh_media_count
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt

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
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `index_xh_media_channel` (
  `ID` int(11) NOT NULL auto_increment COMMENT '主键',
  `CHANNEL_ID` varchar(50) default '0' COMMENT '频道ID',
  `CHANNEL_NAME` varchar(50) default NULL COMMENT '频道名称',
  `STATUS` tinyint(2) default '1' COMMENT '状态，0停用，1正常',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

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
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `index_xh_media_origin` (
  `ID` int(11) NOT NULL auto_increment COMMENT '主键',
  `ORIGIN_ID` varchar(50) default '0' COMMENT '渠道ID',
  `ORIGIN_NAME` varchar(50) default NULL COMMENT '渠道名称',
  `STATUS` tinyint(2) default '1' COMMENT '状态，0停用，1正常',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

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
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `index_xh_media_program` (
  `ID` int(11) NOT NULL auto_increment COMMENT '主键',
  `GLOBAL_ID` varchar(50) default '0' COMMENT '全局ID',
  `CHINESE_NAME` varchar(100) default NULL COMMENT '节目名称',
  `TIME_LENGTH` varchar(20) default '0' COMMENT '节目时长，单位秒',
  `TYPE` smallint(4) default '0' COMMENT '节目类型，0未知，1图文，2视频',
  `PUB_DATE` varchar(20) default NULL COMMENT '发布时间',
  `SOURCE` varchar(20) default NULL COMMENT '节目来源',
  `STATUS` tinyint(2) default '1' COMMENT '状态，0停用，1正常',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

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
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `index_xh_media_user` (
  `ID` int(11) NOT NULL auto_increment COMMENT '主键',
  `USER_ID` int(11) default NULL COMMENT '用户表ID',
  `USERNAME` varchar(50) default NULL COMMENT '用户名',
  `NICKNAME` varchar(50) default NULL COMMENT '昵称',
  `TRUENAME` varchar(50) default NULL COMMENT '真实姓名',
  `PHONE` varchar(20) default NULL COMMENT '手机号',
  `REG_TYPE` smallint(4) default NULL COMMENT '注册类型(主要区分手机号注册还是第三方注册)',
  `REGISTER_TIME` varchar(50) default NULL COMMENT '注册时间',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

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
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `log_xh_media_interactive` (
  `ID` int(11) NOT NULL auto_increment COMMENT '主键',
  `DATE` varchar(20) default NULL COMMENT '日期',
  `HOUR` varchar(10) default NULL COMMENT '小时',
  `ORIGIN_ID` varchar(50) default NULL COMMENT '渠道ID',
  `ORIGIN_NAME` varchar(50) default NULL COMMENT '渠道名称',
  `CHANNEL_ID` varchar(50) default NULL COMMENT '频道ID',
  `CHANNEL_NAME` varchar(50) default NULL COMMENT '频道名称',
  `GLOBAL_ID` varchar(50) default NULL COMMENT '节目全局ID',
  `CHINESE_NAME` varchar(100) default NULL COMMENT '节目中文名称',
  `SOURCE` varchar(20) default NULL COMMENT '节目来源(合并之用)',
  `TYPE` smallint(4) default NULL COMMENT '节目类型，0未知，1图文，2视频',
  `COMMENT_NUMBER` int(11) default NULL COMMENT '评论数',
  `TOPIC_NUMBER` int(11) default NULL COMMENT '话题数',
  `SHARE_NUMBER` int(11) default NULL COMMENT '分享数',
  `COLLECTION_NUMBER` int(11) default NULL COMMENT '收藏数',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `log_xh_media_interactive`
--

LOCK TABLES `log_xh_media_interactive` WRITE;
/*!40000 ALTER TABLE `log_xh_media_interactive` DISABLE KEYS */;
INSERT INTO `log_xh_media_interactive` VALUES (1,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:04'),(2,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:06'),(3,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:06'),(4,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:07'),(5,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:07'),(6,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:07'),(7,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:07'),(8,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:07'),(9,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:08'),(10,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:08'),(11,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:08'),(12,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:08'),(13,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:08'),(14,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:08'),(15,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:08'),(16,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:09'),(17,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:09'),(18,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:09'),(19,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:09'),(20,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:09'),(21,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:09'),(22,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:09'),(23,'2017/05/18','13','555','腾讯','666','新华01','1978','新版西游','首页',2,9,10,20,40,'2017-05-17 03:57:10');
/*!40000 ALTER TABLE `log_xh_media_interactive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_xh_media_program_analysis`
--

DROP TABLE IF EXISTS `log_xh_media_program_analysis`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `log_xh_media_program_analysis` (
  `ID` int(11) NOT NULL auto_increment COMMENT '主键',
  `DATE` varchar(20) default NULL COMMENT '日期',
  `HOUR` varchar(10) default NULL COMMENT '小时',
  `ORIGIN_ID` varchar(50) default NULL COMMENT '渠道ID',
  `ORIGIN_NAME` varchar(50) default NULL COMMENT '渠道名称',
  `CHANNEL_ID` varchar(50) default NULL COMMENT '频道ID',
  `CHANNEL_NAME` varchar(50) default NULL COMMENT '频道名称',
  `GLOBAL_ID` varchar(50) default NULL COMMENT '节目全局ID',
  `CHINESE_NAME` varchar(100) default NULL COMMENT '节目中文名称',
  `PUB_DATE` varchar(20) default NULL COMMENT '发布时间',
  `TYPE` smallint(4) default NULL COMMENT '节目类型，0未知，1图文，2视频',
  `PLAY_TIME` int(11) default '0' COMMENT '播放总时长',
  `TIME_LENGTH` int(11) default '0' COMMENT '节目总时长',
  `PLAY_NUMBER` int(11) default '0' COMMENT '播放次数',
  `PLAY_USER_NUMBER` int(11) default '0' COMMENT '播放人数',
  `SOURCE` varchar(20) default NULL COMMENT '节目来源(用于同一节目，放在不同位置的数据合并用)',
  `PV` int(11) default NULL COMMENT 'PV数',
  `UV` int(11) default NULL COMMENT 'UV数',
  `IP` varchar(20) default NULL COMMENT '用户客户端IP地址数',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `log_xh_media_program_analysis`
--

LOCK TABLES `log_xh_media_program_analysis` WRITE;
/*!40000 ALTER TABLE `log_xh_media_program_analysis` DISABLE KEYS */;
INSERT INTO `log_xh_media_program_analysis` VALUES (1,'2017/5/17','12','111','优酷','22','新华','18709','一带一路','2017/04/09',2,20,2,10,4,'首页',200,20,'192.168','2017-05-17 02:10:18'),(2,'2017/5/16','13','121','土豆','212','新华01','18909','西游记','2017/05/08',2,23,2,110,40,'详情页',210,22,'192.167','2017-05-17 02:43:03'),(3,'2017/5/14','15','122','腾讯','222','新华02','1879','三国','2017/04/08',1,243,25,130,410,'内容页',230,222,'172.167','2017-05-17 02:43:05'),(4,'2017/5/13','17','132','芒果','242','新华03','3879','新三国','2017/04/04',2,253,25,140,430,'内容页01',230,222,'172.167','2017-05-17 02:43:07'),(5,'2017/5/12','22','132','咪咕','242','新华04','3879','水浒传','2017/04/04',2,253,25,140,430,'内容页02',230,222,'172.167','2017-05-17 02:43:10'),(6,'2017/5/13','19','132','影音','242','新华05','3879','红楼梦','2017/04/04',1,253,25,140,430,'内容页03',230,222,'172.167','2017-05-17 02:43:13'),(7,'2017/5/11','22','132','凤凰','242','新华06','3879','人民的名义','2017/04/04',2,253,25,140,430,'内容页04',230,222,'172.167','2017-05-17 02:43:15'),(8,'2017/5/14','20','132','芒果2','242','新华07','3879','西游记续集','2017/04/04',2,253,25,140,430,'内容页05',230,222,'172.167','2017-05-17 02:43:17'),(9,'2017/5/10','22','132','芒果3','242','新华08','3879','水浒后传','2017/04/04',1,253,25,140,430,'内容页06',230,222,'172.167','2017-05-17 02:43:18'),(10,'2017/4/13','21','132','芒果4','242','新华09','3879','外科风云','2017/04/04',1,253,25,140,430,'内容页07',230,222,'172.167','2017-05-17 02:43:20'),(11,'2017/4/12','22','132','芒果5','242','新华010','3879','爸爸去哪','2017/04/04',2,253,25,140,430,'内容页08',230,222,'172.167','2017-05-17 02:43:22'),(12,'2017/4/13','8','132','芒果6','242','新华012','3879','欢乐颂','2017/04/04',1,253,25,140,430,'内容页09',230,222,'172.167','2017-05-17 02:43:24'),(13,'2017/4/12','22','132','芒果7','242','新华013','3879','欢乐颂2','2017/04/04',2,253,25,140,430,'内容页011',230,222,'172.167','2017-05-17 02:43:25'),(14,'2017/4/01','10','132','芒果8','242','新华014','3879','琅琊榜','2017/04/04',1,253,25,140,430,'内容页012',230,222,'172.167','2017-05-17 02:43:27'),(15,'2017/5/01','12','132','芒果9','242','新华015','3879','继承者','2017/04/04',2,253,25,140,430,'内容页013',230,222,'172.167','2017-05-17 02:43:35'),(16,'2017/5/03','14','132','芒果0','242','新华016','3879','亮剑','2017/04/04',1,253,25,140,430,'内容页014',230,222,'172.167','2017-05-17 02:43:36');
/*!40000 ALTER TABLE `log_xh_media_program_analysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_xh_media_user`
--

DROP TABLE IF EXISTS `log_xh_media_user`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `log_xh_media_user` (
  `ID` int(11) NOT NULL auto_increment COMMENT '主键',
  `DATE` varchar(20) default NULL COMMENT '日期',
  `HOUR` varchar(10) default NULL COMMENT '小时',
  `ORIGIN_ID` varchar(50) default NULL COMMENT '渠道ID',
  `ORIGIN_NAME` varchar(50) default NULL COMMENT '渠道名称',
  `CHANNEL_ID` varchar(50) default NULL COMMENT '频道ID',
  `CHANNEL_NAME` varchar(50) default NULL COMMENT '频道名称',
  `NEW_USER` int(11) default NULL COMMENT '新增用户数()',
  `ACTIVE_USER` int(11) default NULL COMMENT '活跃用户数',
  `USE_LENGTH` int(11) default NULL COMMENT '使用时长，单位秒',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `log_xh_media_user`
--

LOCK TABLES `log_xh_media_user` WRITE;
/*!40000 ALTER TABLE `log_xh_media_user` DISABLE KEYS */;
INSERT INTO `log_xh_media_user` VALUES (1,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:21'),(2,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:29'),(3,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:31'),(4,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:31'),(5,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:32'),(6,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:32'),(7,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:32'),(8,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:32'),(9,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:32'),(10,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:32'),(11,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:32'),(12,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:33'),(13,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:33'),(14,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:33'),(15,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:33'),(16,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:33'),(17,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:33'),(18,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:34'),(19,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:34'),(20,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:34'),(21,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:34'),(22,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:34'),(23,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:34'),(24,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:35'),(25,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:35'),(26,'2017/05/13','12','333','新华01','44','优酷',100,89,30,'2017-05-17 03:50:35');
/*!40000 ALTER TABLE `log_xh_media_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_xh_media_visit`
--

DROP TABLE IF EXISTS `log_xh_media_visit`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `log_xh_media_visit` (
  `ID` int(11) NOT NULL auto_increment COMMENT '主键',
  `DATE` varchar(20) default NULL COMMENT '日期',
  `HOUR` varchar(10) default NULL COMMENT '小时',
  `ORIGIN_ID` varchar(50) default NULL COMMENT '渠道ID',
  `ORIGIN_NAME` varchar(50) default NULL COMMENT '渠道名称',
  `CHANNEL_ID` varchar(50) default NULL COMMENT '频道ID',
  `CHANNEL_NAME` varchar(50) default NULL COMMENT '频道名称',
  `PV` int(11) default NULL COMMENT 'PV数',
  `UV` int(11) default NULL COMMENT 'UV数',
  `IP` int(11) default NULL COMMENT 'IP数',
  `PLAY_TIME` int(11) default NULL COMMENT '播放时长',
  `LOCAL` int(11) default NULL COMMENT '站内访问',
  `NON_LOCAL` int(11) default NULL COMMENT '站外访问',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `log_xh_media_visit`
--

LOCK TABLES `log_xh_media_visit` WRITE;
/*!40000 ALTER TABLE `log_xh_media_visit` DISABLE KEYS */;
INSERT INTO `log_xh_media_visit` VALUES (2,'2017/05/15','10','123','咪咕','234','北京Tv',100,12,30,13,14,15,'2017-05-15 14:50:54'),(3,'2017/05/16','11','1233','咪咕1','2334','北京Tv1',103,112,330,113,114,135,'2017-05-15 14:52:22'),(4,'2017/05/17','12','1433','咪咕2','2434','北京Tv2',113,142,340,121,125,175,'2017-05-17 03:58:38'),(5,'2017/05/27','14','1533','咪咕3','2534','北京Tv3',143,152,344,151,135,185,'2017-05-17 03:58:40'),(6,'2017/05/17','15','1543','咪咕4','2554','北京Tv4',145,155,354,155,155,195,'2017-05-17 03:58:43'),(7,'2017/05/15','10','123','咪咕0','234','北京Tv',100,12,30,13,14,15,'2017-05-16 03:56:06'),(8,'2017/05/16','10','123','咪咕8','234','北京Tv8',100,12,30,13,14,15,'2017-05-16 05:28:34'),(9,'2017/05/18','16','1233','咪咕9','234','北京Tv9',100,12,30,13,14,15,'2017-05-17 03:58:45'),(10,'2017/05/17','18','143','咪咕9','254','北京Tv9',108,129,305,163,164,175,'2017-05-17 03:58:51'),(11,'2017/05/10','21','1333','咪咕10','254','北京Tv10',140,142,330,133,144,155,'2017-05-17 03:58:55');
/*!40000 ALTER TABLE `log_xh_media_visit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `source_xh_media_user_visit_log`
--

DROP TABLE IF EXISTS `source_xh_media_user_visit_log`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `source_xh_media_user_visit_log` (
  `ID` int(11) NOT NULL auto_increment COMMENT '主键,自增',
  `CHANNLE_ID` varchar(50) default NULL COMMENT '频道ID',
  `ORIGIN_ID` varchar(50) default NULL COMMENT '渠道ID',
  `HOST` varchar(50) default NULL COMMENT '站点域名',
  `USER_ID` varchar(50) default '0' COMMENT '用户ID',
  `USER_REF` varchar(10) default '0' COMMENT '用户来源，1为C类客户端，2为B类客户端,0为H5用户',
  `GLOBAL_ID` varchar(50) default '0' COMMENT '节目全局ID',
  `SITE_CODE` varchar(14) default NULL COMMENT '站点标识',
  `REF` varchar(200) default NULL COMMENT '来源URL',
  `REF_ID` varchar(10) default '0' COMMENT '来源ID,0站内 1QQ 2QQ空间 3微信 4微信朋友圈 5新浪微博',
  `LAST_VISIT_TIME` varchar(50) default NULL COMMENT '上次访问时间',
  `RESOLUTION` varchar(50) default NULL COMMENT '屏幕宽高,宽:高',
  `PLAY_TIME` varchar(20) default NULL COMMENT '节目播放时长,单位:秒',
  `TIME_LENGTH` varchar(20) default NULL COMMENT '节目时长,单位:秒',
  `IP` varchar(20) default NULL COMMENT '用户客户端IP地址',
  `CREATE_TIME` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

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
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `sys_menu_list` (
  `MENU_ID` smallint(6) NOT NULL auto_increment,
  `MENU_NAME` varchar(50) NOT NULL,
  `MENU_TAG` varchar(50) NOT NULL,
  `MENU_LEVEL` smallint(6) NOT NULL,
  `PARENT_ID` smallint(6) NOT NULL,
  `URL` varchar(100) NOT NULL,
  `ICON` varchar(50) NOT NULL,
  `MODEL_ID` smallint(3) NOT NULL default '0',
  PRIMARY KEY  (`MENU_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `sys_menu_list`
--

LOCK TABLES `sys_menu_list` WRITE;
/*!40000 ALTER TABLE `sys_menu_list` DISABLE KEYS */;
INSERT INTO `sys_menu_list` VALUES (1,'统计内容','',1,0,'0','icon-application-form-edit',0),(2,'系统管理','',2,0,'0','icon-wrench',0),(4,'用户管理','',2,2,'#','icon-users',1),(6,'访问统计','',1,1,'#','icon-book',1),(20,'视频分析明细','',2,1,'#','icon-book',2),(21,'节目分析明细','',3,1,'#','icon-book',3),(22,'新老访客','',4,1,'#','icon-book',4),(23,'效互分析','',5,1,'#','icon-book',5);
/*!40000 ALTER TABLE `sys_menu_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_users`
--

DROP TABLE IF EXISTS `sys_users`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `sys_users` (
  `ID` int(11) NOT NULL auto_increment,
  `USER_ACCOUNT` varchar(50) NOT NULL,
  `USER_NAME` varchar(120) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `TRUE_PASSWORD` varchar(50) NOT NULL,
  `DESC` varchar(300) NOT NULL,
  `STATUS` smallint(1) NOT NULL,
  `DEPT` varchar(60) NOT NULL,
  `DUTY` varchar(30) NOT NULL,
  `SUB_SYSTEM` varchar(60) NOT NULL,
  `ISADMIN` tinyint(1) NOT NULL default '0' COMMENT '是否是管理员帐号,0不是,1是',
  `CREATE_TIME` timestamp NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

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

-- Dump completed on 2017-05-17 10:10:50
