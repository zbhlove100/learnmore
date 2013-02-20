-- MySQL dump 10.13  Distrib 5.1.63, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: learnmore
-- ------------------------------------------------------
-- Server version	5.1.63-0ubuntu0.11.10.1

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT '0',
  `oldprice` int(11) DEFAULT '0',
  `image` varchar(45) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `lesson_system_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_lesson_system1` (`lesson_system_id`),
  CONSTRAINT `fk_book_lesson_system1` FOREIGN KEY (`lesson_system_id`) REFERENCES `lesson_system` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'快乐思维初级A教程+盘','ENG',38,38,'/public/images/book/chua.jpg','杰睿学校丛书编委会 著 / 杰睿学校中小学内部教程 / 2009年1月','Active',5),(2,'快乐思维初级B教程+盘',NULL,38,38,'/public/images/book/chub.jpg','杰睿学校丛书编委会 著 / 杰睿学校中小学内部教程 / 2009年1月','Active',5),(3,'快乐思维初级C教程+盘',NULL,38,38,'/public/images/book/zhonga.jpg','杰睿学校丛书编委会 著 / 杰睿学校中小学内部教程 / 2009年1月','Active',5),(4,'快乐思维中级A教程+盘',NULL,38,38,'/public/images/book/zhongb.jpg','杰睿学校丛书编委会 著 / 杰睿学校中小学内部教程 / 2009年1月','Active',5),(5,'剑桥国际儿童英语','ENG',50,30,'/public/images/index/jianqiao.jpg','《剑桥国际儿童英语》（Playway to English）是针对母语非英语国家的初学英语的儿童出版的一套综合教材。以3 ～ 7岁儿童英语启蒙学习为主，分为四个级别。其最基本的特点在于寓教于乐，让孩子在愉快的游戏和优美的歌谣中掌握英语。\n					    《剑桥国际儿童英语》独创的SMILE教学法让孩子在轻松的学习环境中掌握基本的听、说、读、写能力。内容采用孩子乐于接受的短剧、动画片、歌曲、歌谣、韵律诗和行动故事来呈现。有趣的画面、活泼的节奏以及手脑并用的动作调动了孩子的多个感官，让孩子以母语的方式习得英语！','Active',7),(6,'新概念英语1','ENG',38,38,'/public/images/index/xingainian1.jpg','作为一套世界闻名的英语教程，《新概念英语》以其全新的教学理念，有趣的课文内容和全面的技能训练，深受广大英语学习者的欢迎和喜爱。进入中国以后，《新概念英语》历经了数次重印，而为了最大限度地满足不同层次、不同类型英语学习者的需求，与本教程相配套的系列辅导用书和音像产品也是林林总总，不一而足。','Active',6),(7,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(8,NULL,NULL,0,0,NULL,NULL,NULL,NULL),(9,NULL,NULL,0,0,NULL,NULL,NULL,NULL),(10,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(11,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(12,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(13,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(14,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(15,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(16,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(17,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(18,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(19,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(20,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(21,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(22,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(23,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(24,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(25,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(26,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(27,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(28,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(29,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(30,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(31,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(32,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(33,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(34,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(35,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(36,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(37,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(38,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(39,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(40,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(41,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(42,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(43,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(44,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(45,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(46,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(47,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(48,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(49,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(50,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(51,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(52,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(53,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(54,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(55,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(56,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(57,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(58,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(59,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(60,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(61,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(62,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(63,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(64,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(65,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(66,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(67,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(68,'{TXT}\' or ',NULL,0,0,NULL,NULL,NULL,NULL),(69,'{TXT}',NULL,0,0,NULL,NULL,NULL,NULL),(70,'{TXT} AND 1=1',NULL,0,0,NULL,NULL,NULL,NULL),(71,'{TXT} AND 1=2',NULL,0,0,NULL,NULL,NULL,NULL),(72,'{TXT} AND 1=1--',NULL,0,0,NULL,NULL,NULL,NULL),(73,'{TXT} AND 1=2--',NULL,0,0,NULL,NULL,NULL,NULL),(74,'{TXT}\' AND \'1\'=\'1',NULL,0,0,NULL,NULL,NULL,NULL),(75,'{TXT}\' AND \'1\'=\'2',NULL,0,0,NULL,NULL,NULL,NULL),(76,'{TXT}\' AND \'1\'=\'1\'--',NULL,0,0,NULL,NULL,NULL,NULL),(77,'{TXT}\' AND \'1\'=\'2\'--',NULL,0,0,NULL,NULL,NULL,NULL),(78,'{TXT}\" AND \"1\"=\"1\"--',NULL,0,0,NULL,NULL,NULL,NULL),(79,'{TXT}\" AND \"1\"=\"2\"--',NULL,0,0,NULL,NULL,NULL,NULL),(80,'/etc/passwd',NULL,0,0,NULL,NULL,NULL,NULL),(81,'/etc/passwd\0',NULL,0,0,NULL,NULL,NULL,NULL),(82,'/../../../../../../../../etc/passwd',NULL,0,0,NULL,NULL,NULL,NULL),(83,'..././..././..././..././..././..././..././..././..././..././..././etc/passwd',NULL,0,0,NULL,NULL,NULL,NULL),(84,'/../../../../../../../../etc/passwd\0',NULL,0,0,NULL,NULL,NULL,NULL),(85,'/../../../../../../../../etc/passwd\0.html',NULL,0,0,NULL,NULL,NULL,NULL),(86,'/../../../../../../../../etc/passwd\0index.html',NULL,0,0,NULL,NULL,NULL,NULL),(87,'../../../../../../../etc/passwd',NULL,0,0,NULL,NULL,NULL,NULL),(88,'Li4vLi4vLi4vLi4vLi4vLi4vZXRjL3Bhc3N3ZAo=',NULL,0,0,NULL,NULL,NULL,NULL),(89,'`/etc/passwd`',NULL,0,0,NULL,NULL,NULL,NULL),(90,'..\\..\\..\\..\\..\\..\\..\\..\\..\\boot.ini',NULL,0,0,NULL,NULL,NULL,NULL),(91,'..\\..\\..\\..\\..\\..\\..\\..\\..\\boot.ini\0',NULL,0,0,NULL,NULL,NULL,NULL),(92,'/../../../../../../../../../../boot.ini',NULL,0,0,NULL,NULL,NULL,NULL),(93,'/../../../../../../../../../../boot.ini\0',NULL,0,0,NULL,NULL,NULL,NULL),(94,'../../../../../../../boot.ini',NULL,0,0,NULL,NULL,NULL,NULL),(95,'..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\etc\\passwd\0',NULL,0,0,NULL,NULL,NULL,NULL),(96,'..\\..\\..\\..\\..\\..\\..\\..\\..\\..\\etc\\passwd',NULL,0,0,NULL,NULL,NULL,NULL),(97,'.\\\\./.\\\\./.\\\\./.\\\\./.\\\\./.\\\\./etc/passwd',NULL,0,0,NULL,NULL,NULL,NULL),(98,'.\\\\./.\\\\./.\\\\./.\\\\./.\\\\./.\\\\./boot.ini',NULL,0,0,NULL,NULL,NULL,NULL),(99,'c:\\boot.ini',NULL,0,0,NULL,NULL,NULL,NULL),(100,'/����/����/����/����/����/����/����/����/����/����/����/����/etc/passwd',NULL,0,0,NULL,NULL,NULL,NULL),(101,'/����/����/����/����/����/����/����/����/����/����/����/����/boot.ini',NULL,0,0,NULL,NULL,NULL,NULL),(102,'/etc',NULL,0,0,NULL,NULL,NULL,NULL),(103,'../../../../../../../../etc',NULL,0,0,NULL,NULL,NULL,NULL),(104,'../../../../../../../../etc',NULL,0,0,NULL,NULL,NULL,NULL),(105,'../../../../../../../etc',NULL,0,0,NULL,NULL,NULL,NULL),(106,'../../../../../../../winnt',NULL,0,0,NULL,NULL,NULL,NULL),(107,'../../../../../../../windows',NULL,0,0,NULL,NULL,NULL,NULL),(108,'..\\..\\..\\..\\..\\..\\..\\windows',NULL,0,0,NULL,NULL,NULL,NULL),(109,'..\\..\\..\\..\\..\\..\\..\\winnt',NULL,0,0,NULL,NULL,NULL,NULL),(110,'|cat /etc/passwd|',NULL,0,0,NULL,NULL,NULL,NULL),(111,'{TXT}\ncat /etc/passwd',NULL,0,0,NULL,NULL,NULL,NULL),(112,';id',NULL,0,0,NULL,NULL,NULL,NULL),(113,'|id',NULL,0,0,NULL,NULL,NULL,NULL),(114,'id\0',NULL,0,0,NULL,NULL,NULL,NULL),(115,'id\0|',NULL,0,0,NULL,NULL,NULL,NULL),(116,'`id`',NULL,0,0,NULL,NULL,NULL,NULL),(117,'{TXT};/bin/id',NULL,0,0,NULL,NULL,NULL,NULL),(118,'{TXT} | dir',NULL,0,0,NULL,NULL,NULL,NULL),(119,'\";system(id);',NULL,0,0,NULL,NULL,NULL,NULL),(120,'\'.system(\'id\').exit().\'',NULL,0,0,NULL,NULL,NULL,NULL),(121,'<script>alert(\'foo bar\');</script>',NULL,0,0,NULL,NULL,NULL,NULL),(122,'<script>SECUISECUISECUISECUISECUISECUI</script>',NULL,0,0,NULL,NULL,NULL,NULL),(123,'<!--#exec cmd=\'cat /etc/passwd\'-->',NULL,0,0,NULL,NULL,NULL,NULL),(124,'<!--#exec cmd=\'dir\'-->',NULL,0,0,NULL,NULL,NULL,NULL),(125,'\r\nInjectedMyHeader: MyValue',NULL,0,0,NULL,NULL,NULL,NULL),(126,NULL,NULL,0,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_student`
--

DROP TABLE IF EXISTS `book_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_student` (
  `book_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  PRIMARY KEY (`book_id`,`student_id`),
  KEY `fk_book_has_user_user1` (`student_id`),
  KEY `fk_book_has_user_book1` (`book_id`),
  CONSTRAINT `fk_book_has_user_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_book_has_user_user1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_student`
--

LOCK TABLES `book_student` WRITE;
/*!40000 ALTER TABLE `book_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_level`
--

DROP TABLE IF EXISTS `class_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `discription` varchar(45) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_level`
--

LOCK TABLES `class_level` WRITE;
/*!40000 ALTER TABLE `class_level` DISABLE KEYS */;
INSERT INTO `class_level` VALUES (1,'Please note that the whole 2.7.x series of ve','1',0),(2,'One of the most common questions we keep hear','2',0),(3,'After a year of work new versions of GEGL, ne','3',0),(4,'北京教育考试院 | 北京市民讲外语办公室 | 中国青少年宫协会 | 全国青少年全能王系列展','4',0),(5,'海淀区联 想 桥82121556/82121559都市网景82121556/8212155','5',0),(6,'3T二三综合班16次杨旭广渠门1680元查看详细\n中考冲刺提高班15次王巍','6',0),(7,'杰睿黄寺教学区开课有礼喽 杰睿白云桥教学点重磅优惠 杰睿两校区春季 开班优惠啦公主坟勇闯冒','7',0);
/*!40000 ALTER TABLE `class_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_group`
--

DROP TABLE IF EXISTS `code_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_group_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_group`
--

LOCK TABLES `code_group` WRITE;
/*!40000 ALTER TABLE `code_group` DISABLE KEYS */;
INSERT INTO `code_group` VALUES (1,'search_condition'),(2,'children'),(3,'information_type');
/*!40000 ALTER TABLE `code_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code_table`
--

DROP TABLE IF EXISTS `code_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_name` varchar(255) DEFAULT NULL,
  `code_value` varchar(5000) DEFAULT NULL,
  `discription` varchar(5000) DEFAULT NULL,
  `parent_code` bigint(20) DEFAULT '0',
  `state` varchar(45) DEFAULT NULL,
  `code_group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_code_table_code_group1` (`code_group_id`),
  CONSTRAINT `fk_code_table_code_group1` FOREIGN KEY (`code_group_id`) REFERENCES `code_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code_table`
--

LOCK TABLES `code_table` WRITE;
/*!40000 ALTER TABLE `code_table` DISABLE KEYS */;
INSERT INTO `code_table` VALUES (10,'lesson_time_type','寒假班',NULL,0,'Active',1),(11,'lesson_time_type','春季班',NULL,0,'Active',1),(12,'lesson_time_type','暑期班',NULL,0,'Active',1),(13,'lesson_time_type','秋季班',NULL,0,'Active',1),(14,'lesson_type','英语',NULL,0,'Active',1),(15,'lesson_type','数学',NULL,0,'Active',1),(16,'collection','少儿系列',NULL,0,'Active',1),(17,'collection','小升初系列',NULL,0,'Active',1),(23,'sub_collection','自然拼音',NULL,16,'Active',2),(24,'sub_collection','快乐思维',NULL,16,'Active',2),(25,'sub_collection','小升初基础',NULL,17,'Active',2),(26,'sub_collection','小升初提高',NULL,17,'Active',2),(27,'sub_collection','小升初强化',NULL,17,'Active',2),(28,'sub_collection','小升初冲刺',NULL,17,'Active',2),(36,'information_type','sale','优惠信息',0,'Active',NULL),(37,'information_type','main','新闻',0,'Active',NULL),(38,'information_type','notice','通知',0,'Active',NULL);
/*!40000 ALTER TABLE `code_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parent_code` bigint(20) DEFAULT NULL,
  `leader_id` bigint(20) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `removed_at` timestamp NULL DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_department_teacher1` (`leader_id`),
  CONSTRAINT `fk_department_teacher1` FOREIGN KEY (`leader_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (4,'总部',0,3,NULL,'2013-02-17 08:44:06',NULL,'Active',NULL),(5,'新思维',4,3,NULL,'2013-02-17 08:49:52',NULL,'Active','TEACHER'),(6,'进阶',4,11,NULL,'2013-02-17 08:49:52',NULL,'Active','TEACHER');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,'1年级',1),(2,'2年级',2),(3,'3年级',3),(4,'4年级',4),(5,'5年级',5),(6,'6年级',6),(7,'初1',7),(8,'初2',8),(9,'初3',9);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `img_detail`
--

DROP TABLE IF EXISTS `img_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `img_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `basic_img` varchar(5000) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_img_detail_teacher1` (`teacher_id`),
  KEY `fk_img_detail_student1` (`student_id`),
  CONSTRAINT `fk_img_detail_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_img_detail_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `img_detail`
--

LOCK TABLES `img_detail` WRITE;
/*!40000 ALTER TABLE `img_detail` DISABLE KEYS */;
INSERT INTO `img_detail` VALUES (1,'/teacher/hong_miaomiao.jpg',3,NULL,'Active'),(5,'article-2137915-12D540C0000005DC-161_306x397-231x300.jpg',NULL,12,'Active'),(6,'/teacher/400_F_24118840_qLIbz64G6fcBd74iiDd59VOe1aW9ZfuG.jpg',11,NULL,'Active'),(8,'b0029488_892359.jpg',NULL,3,'Active');
/*!40000 ALTER TABLE `img_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `information` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(10000) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `removed_at` timestamp NULL DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `main_tag` varchar(45) DEFAULT NULL,
  `view_time` int(11) DEFAULT '0',
  `resource` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_information_user1` (`created_by`),
  CONSTRAINT `fk_information_user1` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `information`
--

LOCK TABLES `information` WRITE;
/*!40000 ALTER TABLE `information` DISABLE KEYS */;
INSERT INTO `information` VALUES (1,'博苗教育三八节致信','<p>  你们的辛苦和努力的工作我看在眼里、感在心上。\n \n    在这个属于你们的日子里，我代表所有的杰睿人把最美丽的祝福送给你们，祝你们和你们的家人们快乐、健康、幸福每一天！\n \n    为了让所有的杰睿人在杰睿这个大家庭里感到温 馨、温暖、幸福，为了我们的家，我，还有杰睿 总监团校长团的成员们一直在努力！</p><input type=\"image\" height=\"360\" width=\"480\" src=\"http://imgcache.3tedu.com.cn/image/2012/03/201203081258003.jpg\">','main','2012-03-29 18:38:33',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1),(2,'博苗教育','  你们的辛苦和努力的工作我看在眼里、感在心上。\n \n    在这个属于你们的日子里，我代表所有的杰睿人把最美丽的祝福送给你们，祝你们和你们的家人们快乐、健康、幸福每一天！\n \n    为了让所有的杰睿人在杰睿这个大家庭里感到温 馨、温暖、幸福，为了我们的家，我，还有杰睿 总监团校长团的成员们一直在努力！<br/>致杰睿最可爱的人：\n    忙碌的春季开班后，迎来了属于我们的节日---妇女节!\n    在这样的日子里，我们中间的很多人仍然投入在繁忙的工作中,没时间为自己庆祝节日。\n \n其实你们一直都是这样：\n    为了工作，年轻的你们牺牲了浪漫的约会时光；\n    为了工作，新婚的你们舍弃了美丽的蜜月旅行；\n    为了工作，准妈妈的你们在宝贝出生的前一天还奋战在自己的工作岗位上；\n    为了工作，作为母亲的你们心痛却还是坚定地把未满半岁的小宝宝交给了上一辈照看。\n \n    无论是老师，前台，助教，市场，亦或是其他行政部门人员，你们为杰睿这个大家都付出了很多，你们把最美好的青春时光都倾注在了教书育人的杰睿事业当中。无数个寒假暑假，你们割舍了难能可贵的休息时间；无数个节庆假日，你们割舍了和亲人朋友团聚的机会。其实在孩子心里，你们既是老师，更像妈妈，因为你们永远都在做的一件事，那就是：用心地呵护着我们的每一个宝贝。\n \n \n    那些纷涌而来的感谢信啊，只是你们全情投入的见证！\n    杰睿一天天地发展壮大，就是你们和其他所有杰睿人心血浇灌的结晶。\n    杰睿最可爱的女人们：节日快乐！\n    杰睿同样可爱的男人们，请把我们最美好的祝福带给你们身边最可爱的女人！致杰睿最可爱的人：\n    忙碌的春季开班后，迎来了属于我们的节日---妇女节!<br/>   在这样的日子里，我们中间的很多人仍然投入在繁忙的工作中,没时间为自己庆祝节日。\\n其实你们一直都是这样：\n    为了工作，年轻的你们牺牲了浪漫的约会时光；\n    为了工作，新婚的你们舍弃了美丽的蜜月旅行；\n    为了工作，准妈妈的你们在宝贝出生的前一天还奋战在自己的工作岗位上；\n    为了工作，作为母亲的你们心痛却还是坚定地把未满半岁的小宝宝交给了上一辈照看。  你们的辛苦和努力的工作我看在眼里、感在心上。\n \n    在这个属于你们的日子里，我代表所有的杰睿人把最美丽的祝福送给你们，祝你们和你们的家人们快乐、健康、幸福每一天！\n \n    为了让所有的杰睿人在杰睿这个大家庭里感到温 馨、温暖、幸福，为了我们的家，我，还有杰睿 总监团校长团的成员们一直在努力！<br/>致杰睿最可爱的人：\n    忙碌的春季开班后，迎来了属于我们的节日---妇女节!\n    在这样的日子里，我们中间的很多人仍然投入在繁忙的工作中,没时间为自己庆祝节日。\n \n其实你们一直都是这样：\n    为了工作，年轻的你们牺牲了浪漫的约会时光；\n    为了工作，新婚的你们舍弃了美丽的蜜月旅行；\n    为了工作，准妈妈的你们在宝贝出生的前一天还奋战在自己的工作岗位上；\n    为了工作，作为母亲的你们心痛却还是坚定地把未满半岁的小宝宝交给了上一辈照看。\n \n    无论是老师，前台，助教，市场，亦或是其他行政部门人员，你们为杰睿这个大家都付出了很多，你们把最美好的青春时光都倾注在了教书育人的杰睿事业当中。无数个寒假暑假，你们割舍了难能可贵的休息时间；无数个节庆假日，你们割舍了和亲人朋友团聚的机会。其实在孩子心里，你们既是老师，更像妈妈，因为你们永远都在做的一件事，那就是：用心地呵护着我们的每一个宝贝。\n \n \n    那些纷涌而来的感谢信啊，只是你们全情投入的见证！\n    杰睿一天天地发展壮大，就是你们和其他所有杰睿人心血浇灌的结晶。\n    杰睿最可爱的女人们：节日快乐！\n    杰睿同样可爱的男人们，请把我们最美好的祝福带给你们身边最可爱的女人！致杰睿最可爱的人：\n    忙碌的春季开班后，迎来了属于我们的节日---妇女节!<br/>   在这样的日子里，我们中间的很多人仍然投入在繁忙的工作中,没时间为自己庆祝节日。\\n其实你们一直都是这样：\n    为了工作，年轻的你们牺牲了浪漫的约会时光；\n    为了工作，新婚的你们舍弃了美丽的蜜月旅行；\n    为了工作，准妈妈的你们在宝贝出生的前一天还奋战在自己的工作岗位上；\n    为了工作，作为母亲的你们心痛却还是坚定地把未满半岁的小宝宝交给了上一辈照看。  你们的辛苦和努力的工作我看在眼里、感在心上。\n \n    在这个属于你们的日子里，我代表所有的杰睿人把最美丽的祝福送给你们，祝你们和你们的家人们快乐、健康、幸福每一天！\n \n    为了让所有的杰睿人在杰睿这个大家庭里感到温 馨、温暖、幸福，为了我们的家，我，还有杰睿 总监团校长团的成员们一直在努力！<br/>致杰睿最可爱的人：\n    忙碌的春季开班后，迎来了属于我们的节日---妇女节!\n    在这样的日子里，我们中间的很多人仍然投入在繁忙的工作中,没时间为自己庆祝节日。\n \n其实你们一直都是这样：\n    为了工作，年轻的你们牺牲了浪漫的约会时光；\n    为了工作，新婚的你们舍弃了美丽的蜜月旅行；\n    为了工作，准妈妈的你们在宝贝出生的前一天还奋战在自己的工作岗位上；\n    为了工作，作为母亲的你们心痛却还是坚定地把未满半岁的小宝宝交给了上一辈照看。\n \n    无论是老师，前台，助教，市场，亦或是其他行政部门人员，你们为杰睿这个大家都付出了很多，你们把最美好的青春时光都倾注在了教书育人的杰睿事业当中。无数个寒假暑假，你们割舍了难能可贵的休息时间；无数个节庆假日，你们割舍了和亲人朋友团聚的机会。其实在孩子心里，你们既是老师，更像妈妈，因为你们永远都在做的一件事，那就是：用心地呵护着我们的每一个宝贝。\n \n \n    那些纷涌而来的感谢信啊，只是你们全情投入的见证！\n    杰睿一天天地发展壮大，就是你们和其他所有杰睿人心血浇灌的结晶。\n    杰睿最可爱的女人们：节日快乐！\n    杰睿同样可爱的男人们，请把我们最美好的祝福带给你们身边最可爱的女人！致杰睿最可爱的人：\n    忙碌的春季开班后，迎来了属于我们的节日---妇女节!<br/>   在这样的日子里，我们中间的很多人仍然投入在繁忙的工作中,没时间为自己庆祝节日。\\n其实你们一直都是这样：\n    为了工作，年轻的你们牺牲了浪漫的约会时光；\n    为了工作，新婚的你们舍弃了美丽的蜜月旅行；\n    为了工作，准妈妈的你们在宝贝出生的前一天还奋战在自己的工作岗位上；\n    为了工作，作为母亲的你们心痛却还是坚定地把未满半岁的小宝宝交给了上一辈照看。','main','2012-03-29 18:38:33',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1),(3,'博苗教育优惠汇总','即将到来','main','2012-03-29 18:38:33',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1),(4,'博苗教育官方微薄','博苗教育微波即将到来。','main','2012-03-29 18:38:33',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1),(5,'博苗教育校区介绍','校区建设中，稍后跟新相关信息。','main','2012-03-29 18:38:33',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1),(6,'博苗教育网站使用帮助','专门的帮助单元正在建设中，工程师会尽快为大家带来帮助中心。','main','2012-03-29 18:38:33',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1),(7,'sale7','sale1ccccccc','sale','2012-03-26 18:59:57',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1),(8,'sale8','sale1ccccccc','sale','2012-03-26 18:59:57',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1),(9,'博苗教育网站初步上线','博苗教育，由北京名师领衔，专注于沈阳青少年英语教育，博苗教育为您提供专业的沈阳英语教育服务。','first','2012-03-29 18:36:42',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1),(10,'博苗教育优惠汇总(沈阳)',NULL,'sale','2012-03-29 18:38:33',NULL,'Active','资讯',0,'http://www.miaoedu.net',NULL,1),(11,'博苗教育优惠汇总(沈阳)',NULL,'sale','2012-03-29 18:38:33',NULL,'Active','资讯',0,'http://www.miaoedu.net',NULL,1),(12,'博苗教育优惠汇总(沈阳)',NULL,'sale','2012-03-29 18:38:33',NULL,'Active','资讯',0,'http://www.miaoedu.net',NULL,1),(13,'博苗教育优惠汇总(沈阳)',NULL,'sale','2012-03-29 18:38:33',NULL,'Active','myschool',0,'http://www.miaoedu.net',NULL,1),(14,'博苗教育优惠汇总(沈阳)',NULL,'sale','2012-03-29 18:38:33',NULL,'Active','myschool',0,'http://www.miaoedu.net',NULL,1),(15,'博苗教育优惠汇总(沈阳)',NULL,'sale','2012-03-29 18:38:33',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1),(16,'博苗教育优惠汇总(沈阳)',NULL,'sale','2012-03-29 18:38:33',NULL,'Active','myschool',0,'http://www.miaoedu.net',NULL,1),(17,'博苗教育优惠汇总(沈阳)',NULL,'sale','2012-03-29 18:38:33',NULL,'Active',NULL,0,'http://www.miaoedu.net',NULL,1);
/*!40000 ALTER TABLE `information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `information_log`
--

DROP TABLE IF EXISTS `information_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `information_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` varchar(45) DEFAULT NULL,
  `count` int(11) NOT NULL DEFAULT '0',
  `information_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_information_log_information1` (`information_id`),
  CONSTRAINT `fk_information_log_information1` FOREIGN KEY (`information_id`) REFERENCES `information` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `information_log`
--

LOCK TABLES `information_log` WRITE;
/*!40000 ALTER TABLE `information_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `information_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `lesson_time_type` varchar(45) DEFAULT NULL,
  `lesson_type` varchar(45) DEFAULT NULL,
  `collection` varchar(255) DEFAULT NULL,
  `sub_collection` varchar(255) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `times` int(11) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `student_num` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `school_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) NOT NULL,
  `lesson_system_id` bigint(20) DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `duration` varchar(45) DEFAULT NULL,
  `grade_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lesson_school1` (`school_id`),
  KEY `fk_lesson_teacher1` (`teacher_id`),
  KEY `fk_lesson_lesson_system1` (`lesson_system_id`),
  KEY `fk_lesson_book1` (`book_id`),
  KEY `fk_lesson_grade1` (`grade_id`),
  CONSTRAINT `fk_lesson_school1` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lesson_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lesson_book1` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lesson_grade1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lesson_lesson_system1` FOREIGN KEY (`lesson_system_id`) REFERENCES `lesson_system` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,'新思维课程1','寒假班','英语','少儿系列','快乐思维','初A','Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00','阿斯搜索搜索搜索搜索搜索搜索搜索ssss',0,NULL,1,4,NULL,1,1569,'3小时',NULL),(2,'新思维课程2','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-03-04 16:00:00','2012-04-04 16:00:00',NULL,0,NULL,1,3,NULL,1,1569,NULL,NULL),(3,'新思维课程3','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-03-31 16:00:00','2012-04-30 16:00:00',NULL,0,NULL,1,3,NULL,1,1569,NULL,NULL),(4,'新思维课程4','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-04-04 16:00:00','2012-05-04 16:00:00',NULL,0,NULL,1,3,NULL,2,1569,NULL,NULL),(5,'新思维课程5','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00',NULL,0,NULL,1,3,NULL,2,1569,NULL,NULL),(6,'我打卡洛斯讲道理看我','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00',NULL,0,NULL,1,3,NULL,2,1569,NULL,NULL),(7,'我打卡洛斯讲道理看我','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00',NULL,0,NULL,1,3,NULL,2,1569,NULL,NULL),(8,'我打卡洛斯讲道理看我','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00',NULL,0,NULL,1,3,NULL,2,1569,NULL,NULL),(9,'我打卡洛斯讲道理看我','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00',NULL,0,NULL,1,3,NULL,2,1569,NULL,NULL),(10,'我打卡洛斯讲道理看我','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00',NULL,0,NULL,1,3,NULL,3,1569,NULL,NULL),(11,'我打卡洛斯讲道理看我','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00',NULL,0,NULL,1,3,NULL,3,1569,NULL,NULL),(12,'我打卡洛斯讲道理看我','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00',NULL,0,NULL,1,3,NULL,1,1569,NULL,NULL),(13,'我打卡洛斯讲道理看我','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00',NULL,0,NULL,1,3,NULL,2,1569,NULL,NULL),(14,'我打卡洛斯讲道理看我','寒假班','英语','少儿系列','快乐思维',NULL,'Active',15,'2012-02-29 16:00:00','2012-02-29 16:00:00',NULL,0,NULL,1,3,NULL,NULL,1569,NULL,NULL),(15,'les1','寒假班','英语','少儿系列','自然拼音','3j','Active',10,'2013-02-09 16:00:00','2013-02-28 16:00:00',NULL,0,NULL,1,3,NULL,NULL,1500,NULL,NULL);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_student`
--

DROP TABLE IF EXISTS `lesson_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_student` (
  `lesson_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  PRIMARY KEY (`lesson_id`,`student_id`),
  KEY `fk_lesson_has_user_user1` (`student_id`),
  KEY `fk_lesson_has_user_lesson1` (`lesson_id`),
  CONSTRAINT `fk_lesson_has_user_lesson1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lesson_has_user_user1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_student`
--

LOCK TABLES `lesson_student` WRITE;
/*!40000 ALTER TABLE `lesson_student` DISABLE KEYS */;
INSERT INTO `lesson_student` VALUES (1,2),(2,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(1,3),(2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(12,3),(13,3),(14,3),(1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(8,4),(9,4),(10,4),(11,4),(12,4),(13,4),(14,4),(1,5),(2,5),(3,5),(4,5),(5,5),(6,5),(7,5),(8,5),(9,5),(10,5),(11,5),(12,5),(13,5),(14,5),(1,6),(2,6),(3,6),(4,6),(5,6),(6,6),(7,6),(8,6),(9,6),(10,6),(11,6),(12,6),(13,6),(14,6);
/*!40000 ALTER TABLE `lesson_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_system`
--

DROP TABLE IF EXISTS `lesson_system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_system` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_system`
--

LOCK TABLES `lesson_system` WRITE;
/*!40000 ALTER TABLE `lesson_system` DISABLE KEYS */;
INSERT INTO `lesson_system` VALUES (5,'少儿系列','eng','Active','《剑桥国际儿童英语》（Playway to English）是针对母语非英语国家的初学英语的儿童出版的一套综合教材。以3 ～ 7岁儿童英语启蒙学习为主，分为四个级别。其最基本的特点在于寓教于乐，让孩子在愉快的游戏和优美的歌谣中掌握英语。《剑桥国际儿童英语》独创的SMILE教学法让孩子在轻松的学习环境中掌握基本的听、说、读、写能力。内容采用孩子乐于接受的短剧、动画片、歌曲、歌谣、韵律诗和行动故事来呈现。有趣的画面、活泼的节奏以及手脑并用的动作调动了孩子的多个感官，让孩子以母语的方式习得英语！'),(6,'新概念系列','eng','Active','作为一套世界闻名的英语教程，《新概念英语》以其全新的教学理念，有趣的课文内容和全面的技能训练，深受广大英语学习者的欢迎和喜爱。进入中国以后，《新概念英语》历经了数次重印，而为了最大限度地满足不同层次、不同类型英语学习者的需求，与本教程相配套的系列辅导用书和音像产品也是林林总总，不一而足。'),(7,'剑桥系列','eng','Active','《剑桥国际儿童英语》（Playway to English）是针对母语非英语国家的初学英语的儿童出版的一套综合教材。以3 ～ 7岁儿童英语启蒙学习为主，分为四个级别。其最基本的特点在于寓教于乐，让孩子在愉快的游戏和优美的歌谣中掌握英语。《剑桥国际儿童英语》独创的SMILE教学法让孩子在轻松的学习环境中掌握基本的听、说、读、写能力。内容采用孩子乐于接受的短剧、动画片、歌曲、歌谣、韵律诗和行动故事来呈现。有趣的画面、活泼的节奏以及手脑并用的动作调动了孩子的多个感官，让孩子以母语的方式习得英语！'),(8,'口语系列','eng','Active','《剑桥国际儿童英语》（Playway to English）是针对母语非英语国家的初学英语的儿童出版的一套综合教材。以3 ～ 7岁儿童英语启蒙学习为主，分为四个级别。其最基本的特点在于寓教于乐，让孩子在愉快的游戏和优美的歌谣中掌握英语。《剑桥国际儿童英语》独创的SMILE教学法让孩子在轻松的学习环境中掌握基本的听、说、读、写能力。内容采用孩子乐于接受的短剧、动画片、歌曲、歌谣、韵律诗和行动故事来呈现。有趣的画面、活泼的节奏以及手脑并用的动作调动了孩子的多个感官，让孩子以母语的方式习得英语！');
/*!40000 ALTER TABLE `lesson_system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_table`
--

DROP TABLE IF EXISTS `lesson_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `lesson_date` timestamp NULL DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `lesson_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lesson_table_lesson1` (`lesson_id`),
  CONSTRAINT `fk_lesson_table_lesson1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2231 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_table`
--

LOCK TABLES `lesson_table` WRITE;
/*!40000 ALTER TABLE `lesson_table` DISABLE KEYS */;
INSERT INTO `lesson_table` VALUES (1,'第1课','2012-03-04 16:00:00','Finish',1),(2,'第2课','2012-03-04 16:00:00','Finish',1),(3,'第3课','2012-03-04 16:00:00','Finish',1),(4,'第3课','2012-03-04 16:00:00','Active',1),(5,'第3课','2012-03-04 16:00:00','Active',1),(6,'第3课','2012-03-04 16:00:00','Active',1),(7,'第3课','2012-03-04 16:00:00','Finish',1),(8,'第3课','2012-03-04 16:00:00','Active',1),(9,'第3课','2012-03-04 16:00:00','Active',1),(10,'第3课','2012-03-04 16:00:00','Active',1),(11,'第3课','2012-03-04 16:00:00','Active',1),(12,'第3课','2012-03-04 16:00:00','Active',1),(13,'第3课','2012-03-04 16:00:00','Active',1),(14,'第3课','2012-03-04 16:00:00','Active',1),(15,'第3课','2012-03-04 16:00:00','Active',1),(16,'第3课','2012-03-04 16:00:00','Active',1),(17,'第3课','2012-03-04 16:00:00','Active',1),(2016,'第1课','2012-10-09 16:00:00','Finish',2),(2017,'第2课','2012-10-09 16:00:00','Finish',2),(2018,'第3课','2012-10-09 16:00:00','Finish',2),(2019,'第4课','2012-10-09 16:00:00','Finish',2),(2020,'第5课','2012-10-09 16:00:00','Finish',2),(2021,'第6课','2012-10-09 16:00:00','Finish',2),(2022,'第7课','2012-10-09 16:00:00','Active',2),(2023,'第8课','2012-10-09 16:00:00','Active',2),(2024,'第9课','2012-10-09 16:00:00','Active',2),(2025,'第10课','2012-10-09 16:00:00','Active',2),(2026,'第11课','2012-10-09 16:00:00','Active',2),(2027,'第12课','2012-10-09 16:00:00','Active',2),(2028,'第13课','2012-10-09 16:00:00','Active',2),(2029,'第14课','2012-10-09 16:00:00','Active',2),(2030,'第15课','2012-10-09 16:00:00','Active',2),(2031,'第1课','2012-10-09 16:00:00','Finish',3),(2032,'第2课','2012-10-09 16:00:00','Finish',3),(2033,'第3课','2012-10-09 16:00:00','Finish',3),(2034,'第4课','2012-10-09 16:00:00','Finish',3),(2035,'第5课','2012-10-09 16:00:00','Finish',3),(2036,'第6课','2012-10-09 16:00:00','Finish',3),(2037,'第7课','2012-10-09 16:00:00','Active',3),(2038,'第8课','2012-10-09 16:00:00','Active',3),(2039,'第9课','2012-10-09 16:00:00','Active',3),(2040,'第10课','2012-10-09 16:00:00','Active',3),(2041,'第11课','2012-10-09 16:00:00','Active',3),(2042,'第12课','2012-10-09 16:00:00','Active',3),(2043,'第13课','2012-10-09 16:00:00','Active',3),(2044,'第14课','2012-10-09 16:00:00','Active',3),(2045,'第15课','2012-10-09 16:00:00','Active',3),(2046,'第1课','2012-10-09 16:00:00','Finish',4),(2047,'第2课','2012-10-09 16:00:00','Finish',4),(2048,'第3课','2012-10-09 16:00:00','Finish',4),(2049,'第4课','2012-10-09 16:00:00','Finish',4),(2050,'第5课','2012-10-09 16:00:00','Finish',4),(2051,'第6课','2012-10-09 16:00:00','Finish',4),(2052,'第7课','2012-10-09 16:00:00','Active',4),(2053,'第8课','2012-10-09 16:00:00','Active',4),(2054,'第9课','2012-10-09 16:00:00','Active',4),(2055,'第10课','2012-10-09 16:00:00','Active',4),(2056,'第11课','2012-10-09 16:00:00','Active',4),(2057,'第12课','2012-10-09 16:00:00','Active',4),(2058,'第13课','2012-10-09 16:00:00','Active',4),(2059,'第14课','2012-10-09 16:00:00','Active',4),(2060,'第15课','2012-10-09 16:00:00','Active',4),(2061,'第1课','2012-10-09 16:00:00','Finish',5),(2062,'第2课','2012-10-09 16:00:00','Finish',5),(2063,'第3课','2012-10-09 16:00:00','Finish',5),(2064,'第4课','2012-10-09 16:00:00','Finish',5),(2065,'第5课','2012-10-09 16:00:00','Finish',5),(2066,'第6课','2012-10-09 16:00:00','Finish',5),(2067,'第7课','2012-10-09 16:00:00','Active',5),(2068,'第8课','2012-10-09 16:00:00','Active',5),(2069,'第9课','2012-10-09 16:00:00','Active',5),(2070,'第10课','2012-10-09 16:00:00','Active',5),(2071,'第11课','2012-10-09 16:00:00','Active',5),(2072,'第12课','2012-10-09 16:00:00','Active',5),(2073,'第13课','2012-10-09 16:00:00','Active',5),(2074,'第14课','2012-10-09 16:00:00','Active',5),(2075,'第15课','2012-10-09 16:00:00','Active',5),(2076,'第1课','2012-10-09 16:00:00','Finish',6),(2077,'第2课','2012-10-09 16:00:00','Finish',6),(2078,'第3课','2012-10-09 16:00:00','Finish',6),(2079,'第4课','2012-10-09 16:00:00','Finish',6),(2080,'第5课','2012-10-09 16:00:00','Finish',6),(2081,'第6课','2012-10-09 16:00:00','Finish',6),(2082,'第7课','2012-10-09 16:00:00','Active',6),(2083,'第8课','2012-10-09 16:00:00','Active',6),(2084,'第9课','2012-10-09 16:00:00','Active',6),(2085,'第10课','2012-10-09 16:00:00','Active',6),(2086,'第11课','2012-10-09 16:00:00','Active',6),(2087,'第12课','2012-10-09 16:00:00','Active',6),(2088,'第13课','2012-10-09 16:00:00','Active',6),(2089,'第14课','2012-10-09 16:00:00','Active',6),(2090,'第15课','2012-10-09 16:00:00','Active',6),(2091,'第1课','2012-10-09 16:00:00','Finish',7),(2092,'第2课','2012-10-09 16:00:00','Finish',7),(2093,'第3课','2012-10-09 16:00:00','Finish',7),(2094,'第4课','2012-10-09 16:00:00','Finish',7),(2095,'第5课','2012-10-09 16:00:00','Finish',7),(2096,'第6课','2012-10-09 16:00:00','Finish',7),(2097,'第7课','2012-10-09 16:00:00','Active',7),(2098,'第8课','2012-10-09 16:00:00','Active',7),(2099,'第9课','2012-10-09 16:00:00','Active',7),(2100,'第10课','2012-10-09 16:00:00','Active',7),(2101,'第11课','2012-10-09 16:00:00','Active',7),(2102,'第12课','2012-10-09 16:00:00','Active',7),(2103,'第13课','2012-10-09 16:00:00','Active',7),(2104,'第14课','2012-10-09 16:00:00','Active',7),(2105,'第15课','2012-10-09 16:00:00','Active',7),(2106,'第1课','2012-10-09 16:00:00','Finish',8),(2107,'第2课','2012-10-09 16:00:00','Finish',8),(2108,'第3课','2012-10-09 16:00:00','Finish',8),(2109,'第4课','2012-10-09 16:00:00','Finish',8),(2110,'第5课','2012-10-09 16:00:00','Finish',8),(2111,'第6课','2012-10-09 16:00:00','Finish',8),(2112,'第7课','2012-10-09 16:00:00','Active',8),(2113,'第8课','2012-10-09 16:00:00','Active',8),(2114,'第9课','2012-10-09 16:00:00','Active',8),(2115,'第10课','2012-10-09 16:00:00','Active',8),(2116,'第11课','2012-10-09 16:00:00','Active',8),(2117,'第12课','2012-10-09 16:00:00','Active',8),(2118,'第13课','2012-10-09 16:00:00','Active',8),(2119,'第14课','2012-10-09 16:00:00','Active',8),(2120,'第15课','2012-10-09 16:00:00','Active',8),(2121,'第1课','2012-10-09 16:00:00','Finish',9),(2122,'第2课','2012-10-09 16:00:00','Finish',9),(2123,'第3课','2012-10-09 16:00:00','Finish',9),(2124,'第4课','2012-10-09 16:00:00','Finish',9),(2125,'第5课','2012-10-09 16:00:00','Finish',9),(2126,'第6课','2012-10-09 16:00:00','Finish',9),(2127,'第7课','2012-10-09 16:00:00','Active',9),(2128,'第8课','2012-10-09 16:00:00','Active',9),(2129,'第9课','2012-10-09 16:00:00','Active',9),(2130,'第10课','2012-10-09 16:00:00','Active',9),(2131,'第11课','2012-10-09 16:00:00','Active',9),(2132,'第12课','2012-10-09 16:00:00','Active',9),(2133,'第13课','2012-10-09 16:00:00','Active',9),(2134,'第14课','2012-10-09 16:00:00','Active',9),(2135,'第15课','2012-10-09 16:00:00','Active',9),(2136,'第1课','2012-10-09 16:00:00','Finish',10),(2137,'第2课','2012-10-09 16:00:00','Finish',10),(2138,'第3课','2012-10-09 16:00:00','Finish',10),(2139,'第4课','2012-10-09 16:00:00','Finish',10),(2140,'第5课','2012-10-09 16:00:00','Finish',10),(2141,'第6课','2012-10-09 16:00:00','Finish',10),(2142,'第7课','2012-10-09 16:00:00','Active',10),(2143,'第8课','2012-10-09 16:00:00','Active',10),(2144,'第9课','2012-10-09 16:00:00','Active',10),(2145,'第10课','2012-10-09 16:00:00','Active',10),(2146,'第11课','2012-10-09 16:00:00','Active',10),(2147,'第12课','2012-10-09 16:00:00','Active',10),(2148,'第13课','2012-10-09 16:00:00','Active',10),(2149,'第14课','2012-10-09 16:00:00','Active',10),(2150,'第15课','2012-10-09 16:00:00','Active',10),(2151,'第1课','2012-10-09 16:00:00','Finish',11),(2152,'第2课','2012-10-09 16:00:00','Finish',11),(2153,'第3课','2012-10-09 16:00:00','Finish',11),(2154,'第4课','2012-10-09 16:00:00','Finish',11),(2155,'第5课','2012-10-09 16:00:00','Finish',11),(2156,'第6课','2012-10-09 16:00:00','Finish',11),(2157,'第7课','2012-10-09 16:00:00','Active',11),(2158,'第8课','2012-10-09 16:00:00','Active',11),(2159,'第9课','2012-10-09 16:00:00','Active',11),(2160,'第10课','2012-10-09 16:00:00','Active',11),(2161,'第11课','2012-10-09 16:00:00','Active',11),(2162,'第12课','2012-10-09 16:00:00','Active',11),(2163,'第13课','2012-10-09 16:00:00','Active',11),(2164,'第14课','2012-10-09 16:00:00','Active',11),(2165,'第15课','2012-10-09 16:00:00','Active',11),(2166,'第1课','2012-10-09 16:00:00','Finish',12),(2167,'第2课','2012-10-09 16:00:00','Finish',12),(2168,'第3课','2012-10-09 16:00:00','Finish',12),(2169,'第4课','2012-10-09 16:00:00','Finish',12),(2170,'第5课','2012-10-09 16:00:00','Finish',12),(2171,'第6课','2012-10-09 16:00:00','Finish',12),(2172,'第7课','2012-10-09 16:00:00','Active',12),(2173,'第8课','2012-10-09 16:00:00','Active',12),(2174,'第9课','2012-10-09 16:00:00','Active',12),(2175,'第10课','2012-10-09 16:00:00','Active',12),(2176,'第11课','2012-10-09 16:00:00','Active',12),(2177,'第12课','2012-10-09 16:00:00','Active',12),(2178,'第13课','2012-10-09 16:00:00','Active',12),(2179,'第14课','2012-10-09 16:00:00','Active',12),(2180,'第15课','2012-10-09 16:00:00','Active',12),(2181,'第1课','2012-10-09 16:00:00','Finish',13),(2182,'第2课','2012-10-09 16:00:00','Finish',13),(2183,'第3课','2012-10-09 16:00:00','Finish',13),(2184,'第4课','2012-10-09 16:00:00','Finish',13),(2185,'第5课','2012-10-09 16:00:00','Finish',13),(2186,'第6课','2012-10-09 16:00:00','Finish',13),(2187,'第7课','2012-10-09 16:00:00','Active',13),(2188,'第8课','2012-10-09 16:00:00','Active',13),(2189,'第9课','2012-10-09 16:00:00','Active',13),(2190,'第10课','2012-10-09 16:00:00','Active',13),(2191,'第11课','2012-10-09 16:00:00','Active',13),(2192,'第12课','2012-10-09 16:00:00','Active',13),(2193,'第13课','2012-10-09 16:00:00','Active',13),(2194,'第14课','2012-10-09 16:00:00','Active',13),(2195,'第15课','2012-10-09 16:00:00','Active',13),(2196,'第1课','2012-10-09 16:00:00','Finish',14),(2197,'第2课','2012-10-09 16:00:00','Finish',14),(2198,'第3课','2012-10-09 16:00:00','Finish',14),(2199,'第4课','2012-10-09 16:00:00','Finish',14),(2200,'第5课','2012-10-09 16:00:00','Finish',14),(2201,'第6课','2012-10-09 16:00:00','Finish',14),(2202,'第7课','2012-10-09 16:00:00','Active',14),(2203,'第8课','2012-10-09 16:00:00','Active',14),(2204,'第9课','2012-10-09 16:00:00','Active',14),(2205,'第10课','2012-10-09 16:00:00','Active',14),(2206,'第11课','2012-10-09 16:00:00','Active',14),(2207,'第12课','2012-10-09 16:00:00','Active',14),(2208,'第13课','2012-10-09 16:00:00','Active',14),(2209,'第14课','2012-10-09 16:00:00','Active',14),(2210,'第15课','2012-10-09 16:00:00','Active',14),(2221,'第1课','2013-02-16 04:00:58','Active',15),(2222,'第2课','2013-02-19 16:00:00','Active',15),(2223,'第3课','2013-02-16 04:00:58','Active',15),(2224,'第4课','2013-02-16 04:00:58','Active',15),(2225,'第5课','2013-02-16 04:00:58','Active',15),(2226,'第6课','2013-02-16 04:00:58','Active',15),(2227,'第7课','2013-02-16 04:00:58','Active',15),(2228,'第8课','2013-02-16 04:00:58','Active',15),(2229,'第9课','2013-02-16 04:00:58','Active',15),(2230,'第10课','2013-02-16 04:00:58','Active',15);
/*!40000 ALTER TABLE `lesson_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_message`
--

DROP TABLE IF EXISTS `order_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `lesson_id` bigint(20) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `removed_at` varchar(45) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `modify_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `identify_no` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_student1` (`student_id`),
  KEY `fk_order_teacher1` (`teacher_id`),
  KEY `fk_order_lesson1` (`lesson_id`),
  KEY `fk_order_message_user1` (`user_id`),
  CONSTRAINT `fk_order_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_lesson1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_message_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_message`
--

LOCK TABLES `order_message` WRITE;
/*!40000 ALTER TABLE `order_message` DISABLE KEYS */;
INSERT INTO `order_message` VALUES (1,3,3,1,NULL,'2013-01-17 03:37:38','test',500,'Delete',NULL,NULL,'2013-02-17 07:04:13','8ed9e8f1-c3c5-48d8-a107-a9f267580bbb'),(2,4,3,1,NULL,'2013-02-17 03:37:38','test',500,'Delete',NULL,NULL,'2013-02-17 03:39:41','1938a129-3eb4-492c-b4af-e1947c67c8c8'),(3,6,3,1,NULL,'2012-02-17 03:37:38','我就多收他的钱，怎么滴',700,'Delete',NULL,NULL,'2013-02-17 07:04:13','77e17963-d7a1-4a2c-8225-c2ba3f750d29'),(4,3,3,2,NULL,'2012-06-17 03:37:38','',34,'Active',NULL,NULL,'2013-02-17 07:04:13','3d870c1b-6b04-40f5-9028-ad9f4b75c691'),(5,6,3,2,NULL,'2012-07-17 03:37:38','',1111,'Active',NULL,NULL,'2013-02-17 07:04:13','3da27437-77b4-485b-9a38-62d380a1163d'),(6,4,3,2,NULL,'2013-02-17 03:37:38','',1111,'Active',NULL,NULL,'2013-02-17 03:39:41','37b53aa3-fb8d-4bf1-b151-83257b426db3'),(7,2,3,2,NULL,'2013-02-17 03:37:38','',22,'Active',NULL,NULL,'2013-02-17 03:39:41','6258f62c-3fa8-4c23-93b4-3ef820ad5e0a'),(8,4,3,3,NULL,'2013-02-17 03:37:38','',21,'Active',NULL,NULL,'2013-02-17 03:39:41','2bf6c66f-097f-43f1-8a44-94363b7a96df'),(9,6,3,3,NULL,'2013-02-17 03:37:38','',324,'Active',NULL,NULL,'2013-02-17 03:39:41','38e91d44-3221-4e0e-b89f-53616304c9eb'),(10,2,3,3,NULL,'2013-02-17 03:37:38','',200,'Active',NULL,NULL,'2013-02-17 03:39:41','ac993a73-38fa-45b1-9a7f-0c7d13cde920'),(11,4,3,1,NULL,'2013-02-17 03:37:38','',250,'Active',NULL,NULL,'2013-02-17 03:39:41','475f3c10-9ef0-48b5-945c-819e1a704223'),(12,8,3,1,NULL,'2013-02-17 03:37:38','',2134,'Active',NULL,NULL,'2013-02-17 03:39:41','f31d4747-da3c-461f-8be4-c4f55b58c10b'),(13,12,3,15,NULL,'2013-02-17 03:37:38','',1300,'Active',NULL,NULL,'2013-02-17 03:39:41','bd896a4a-8834-4169-a650-d689800e3093'),(14,6,3,4,NULL,'2013-02-17 03:37:38','',1244,'Active',NULL,NULL,NULL,'de527754-f32a-485b-ba5e-09077c559616');
/*!40000 ALTER TABLE `order_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'root');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `school` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `discription` varchar(5000) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (1,'东高地','donggaodi','123456789',NULL,'一个店铺233333','Active');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL,
  `extvalue` varchar(500) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (2,'EMPLOYE_TYPE','teacher','',''),(3,'EMPLOYE_TYPE','assistant','','');
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `removed_at` timestamp NULL DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `birthday` varchar(45) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `grade_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_student_grade1` (`grade_id`),
  CONSTRAINT `fk_student_grade1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (2,'张r','22@126.com',13,'TYUIOPKJJ','Active','2013-02-20 09:43:38',NULL,'13610101010','1999-11-11','','女',3),(3,'张2','333@126.com',14,'xxxxxxxxxxxxxxx','Active','2013-02-20 09:43:38',NULL,'13610101010','1998-06-06','','女',NULL),(4,'张3','3@126.com',14,'TYUIOPKJJ','Active','2013-02-20 09:43:38',NULL,'13610101010','1992-09-16',NULL,'女',NULL),(5,'张4','4@126.com',15,'TYUIOPKJJ','Active','2013-02-20 09:43:38',NULL,'13610101010','1988-05-05',NULL,'女',NULL),(6,'张5','5@126.com',16,'TYUIOPKJJ','Active','2013-02-20 09:43:38',NULL,'13610101010','1983-01-18',NULL,'女',NULL),(7,'王1',NULL,15,NULL,'Delete','2013-02-20 09:43:38','2013-02-01 01:53:31','18615555555','1998-01-07',NULL,'女',NULL),(8,'李里',NULL,18,NULL,'Delete','2013-02-20 09:43:38',NULL,'15815444444','1995-01-18',NULL,'女',NULL),(12,'赵一一','',15,'阿斯顿呜呜呜','Active','2013-02-20 09:43:38',NULL,'15122222222','1997-02-11','撒啊啊啊啊啊啊啊啊啊啊啊啊啊','女',NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_detail`
--

DROP TABLE IF EXISTS `student_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_detailcol` varchar(45) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_student_detail_student1` (`student_id`),
  CONSTRAINT `fk_student_detail_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_detail`
--

LOCK TABLES `student_detail` WRITE;
/*!40000 ALTER TABLE `student_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `lesson_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `information_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tags_lesson1` (`lesson_id`),
  KEY `fk_tags_teacher1` (`teacher_id`),
  KEY `fk_tags_information1` (`information_id`),
  CONSTRAINT `fk_tags_information1` FOREIGN KEY (`information_id`) REFERENCES `information` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tags_lesson1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tags_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `removed_at` timestamp NULL DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `employe_type` varchar(45) DEFAULT NULL,
  `email` varchar(245) DEFAULT NULL,
  `school_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_teacher_school1` (`school_id`),
  KEY `fk_teacher_department1` (`department_id`),
  CONSTRAINT `fk_teacher_school1` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_teacher_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='base teacher table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (2,'222222',37,'2013-02-17 08:46:08',NULL,'Active','ASSISTANT',NULL,1,5),(3,'苗苗',27,'2013-02-17 08:46:08','2012-02-29 23:52:43','Active','TEACHER','miao5616@163.com',1,5),(4,'b',12,'2013-02-17 08:46:08',NULL,'Active','TEACHER','main',1,6),(5,'c',34,'2013-02-17 08:46:08',NULL,'Active','TEACHER','main',1,6),(6,'不叫就',22,'2013-02-17 08:46:08',NULL,'Active','ASSISTANT','main',1,5),(7,'你妹妹啊',44,'2013-02-17 08:46:08',NULL,'Active','ASSISTANT','main',1,5),(8,'woqugehsd',33,'2013-02-17 08:46:08',NULL,'Active','MARKETER','main',1,6),(9,'nb的大头想',11,'2013-02-17 08:46:08',NULL,'Active','MARKETER','big',1,6),(11,'大棚',19,'2013-02-17 08:46:08',NULL,'Active',NULL,'rrrrr',1,6);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_detail`
--

DROP TABLE IF EXISTS `teacher_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_type` varchar(255) DEFAULT NULL,
  `en_name` varchar(255) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `education` varchar(45) DEFAULT NULL,
  `bloodtype` varchar(2) DEFAULT NULL,
  `birthday` varchar(45) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `interest` varchar(255) DEFAULT NULL,
  `favorite_color` varchar(45) DEFAULT NULL,
  `favorite_sport` varchar(45) DEFAULT NULL,
  `favorite_animal` varchar(45) DEFAULT NULL,
  `favorite_place` varchar(45) DEFAULT NULL,
  `teacher_word` varchar(45) DEFAULT NULL,
  `adore_man` varchar(45) DEFAULT NULL,
  `summary` varchar(5000) DEFAULT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `weibo` varchar(45) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `household` varchar(45) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `graduate_school` varchar(45) DEFAULT NULL,
  `hire_date` timestamp NULL DEFAULT NULL,
  `graduate_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_teacher_detail_teacher1` (`teacher_id`),
  CONSTRAINT `fk_teacher_detail_teacher1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_detail`
--

LOCK TABLES `teacher_detail` WRITE;
/*!40000 ALTER TABLE `teacher_detail` DISABLE KEYS */;
INSERT INTO `teacher_detail` VALUES (4,'3T,快乐思维','anna','女','本科','o','1985-05-05',160,'吃饭','红色','睡觉','笨笨','荷兰',NULL,'张博瀚',' 杰睿英语教师，曾教授过《新概念英语》等课程。熟悉历年中考题型，擅于把握考点，授课讲究循序渐进，对重要知识点进行有层次有条理的着重讲解，同时也不忽略对基础知识的铺垫，从而使学生能牢固掌握重点难点。对学生要求严格，但也不失风趣幽默，时常在课堂上引用一些西方文化的片段来调节课堂气氛，同时也让学生了解更多与英语有关的知识。','18688888888',NULL,NULL,NULL,'朝阳区','辽宁',3,'清华','2012-02-29 16:00:00','2009-02-28 16:00:00'),(5,'','dwasd','男','','','1994-01-31',0,'','','','','','','','稳定宋丹丹顶顶顶顶顶顶顶顶短短的d','12455556666','rrrrr','','','','',11,'清华','2011-02-22 16:00:00','1998-02-16 16:00:00');
/*!40000 ALTER TABLE `teacher_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `is_super` varchar(20) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','root','emJoeHp6','true','Active',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_log`
--

DROP TABLE IF EXISTS `user_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_times` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_log_user1` (`user_id`),
  CONSTRAINT `fk_user_log_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_log`
--

LOCK TABLES `user_log` WRITE;
/*!40000 ALTER TABLE `user_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_log` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-02-20 18:22:40
