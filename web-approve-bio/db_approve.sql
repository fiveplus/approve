# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 220.249.89.138 (MySQL 5.1.73)
# Database: db_approve
# Generation Time: 2018-06-25 02:46:40 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sys_dept
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;

INSERT INTO `sys_dept` (`id`, `name`, `create_time`, `status`)
VALUES
	('ff80808155ba57ab0155ba5aa56a0001','总经办','1467710350659','Y'),
	('ff80808155ba57ab0155ba5aeea30002','综管部','1467710369442','Y'),
	('ff80808155ba57ab0155ba5b10e00003','招商部','1467710378206','Y'),
	('ff80808155ba57ab0155ba5b3b830004','市场部','1467710389122','Y'),
	('ff80808155ba57ab0155ba5b72220005','投融资部','1467710403105','Y'),
	('ff80808155ba57ab0155ba5ba6a40006','平台管理部','1467710416547','Y'),
	('ff80808155ba57ab0155ba5bd0560007','互联网事业部','1467710427221','Y'),
	('ff80808155ba57ab0155ba84c439002a','产业咨询部','1467713111096','Y'),
	('ff80808155ba57ab0155ba84e203002b','技术部','1467713118722','Y');

/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_free_approve
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_free_approve`;

CREATE TABLE `sys_free_approve` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;



# Dump of table sys_setting
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_setting`;

CREATE TABLE `sys_setting` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `title` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

LOCK TABLES `sys_setting` WRITE;
/*!40000 ALTER TABLE `sys_setting` DISABLE KEYS */;

INSERT INTO `sys_setting` (`id`, `title`)
VALUES
	('297e1d46530c293701530c2c24ee0009','武汉光谷百桥国际生物科技有限公司-流程审批系统');

/*!40000 ALTER TABLE `sys_setting` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_static_setting
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_static_setting`;

CREATE TABLE `sys_static_setting` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `column_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `column_value` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

LOCK TABLES `sys_static_setting` WRITE;
/*!40000 ALTER TABLE `sys_static_setting` DISABLE KEYS */;

INSERT INTO `sys_static_setting` (`id`, `column_name`, `column_value`, `remark`)
VALUES
	('402881f4540d1e3d01540d2833120001','MANAGER_ID','',NULL),
	('402881f4540d1e3d01540d2833120002','ARCHIVE_ID','',NULL);

/*!40000 ALTER TABLE `sys_static_setting` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `login_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` text COLLATE utf8_unicode_ci,
  `is_admin` varchar(4) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dept_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `position` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;

INSERT INTO `sys_user` (`id`, `login_name`, `user_name`, `password`, `remark`, `is_admin`, `dept_id`, `position`, `create_time`, `status`)
VALUES
	('ff80808150802d55015084b521f700ac','admin','系统管理员','c26be8aaf53b15054896983b43eb6a65','','Y',NULL,NULL,'0','Y'),
	('ff80808155ba57ab0155ba5ca1390008','junjie.yang','杨俊杰','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5aa56a0001','0','1467710480695','Y'),
	('ff80808155ba57ab0155ba5d15f50009','george.li','李喆','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5aa56a0001','0','1467710510579','Y'),
	('ff80808155ba57ab0155ba5d6bdd000a','xli','李雪飞','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5aa56a0001','0','1467710532572','Y'),
	('ff80808155ba57ab0155ba5dcfc0000b','lisa.li','李莎','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5aeea30002','1','1467710558142','Y'),
	('ff80808155ba57ab0155ba5e5f16000c','ping.ke','柯萍','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5aeea30002','2','1467710594836','Y'),
	('ff80808155ba57ab0155ba5ef51a000d','jingyuan.chen','陈敬源','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5aeea30002','2','1467710633241','Y'),
	('ff80808155ba57ab0155ba5f7e6a000e','huihui.pan','潘慧慧','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5aeea30002','2','1467710668393','Y'),
	('ff80808155ba57ab0155ba5fd5fd000f','runqing.li','李润清','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b10e00003','1','1467710690812','Y'),
	('ff80808155ba57ab0155ba60296a0010','john.grolman','John','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b10e00003','2','1467710712168','Y'),
	('ff80808155ba57ab0155ba60e2900011','lanli','李兰','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b10e00003','2','1467710759566','Y'),
	('ff80808155ba57ab0155ba613cf70012','shuaishuai.bao','鲍帅帅','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b10e00003','2','1467710782710','Y'),
	('ff80808155ba57ab0155ba61ae200013','yao.gan','甘瑶','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b10e00003','2','1467710811678','Y'),
	('ff80808155ba57ab0155ba62209b0014','xiao.zhu','祝晓','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b10e00003','2','1467710840986','Y'),
	('ff80808155ba57ab0155ba627af40015','lihua.wang','王丽华','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b10e00003','2','1467710864115','Y'),
	('ff80808155ba57ab0155ba62d5bc0016','diana.young','曹语阳','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b10e00003','2','1467710887354','Y'),
	('ff80808155ba57ab0155ba6323320017','grace.cheung','张玉贵','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b10e00003','2','1467710907184','Y'),
	('ff80808155ba57ab0155ba638a930018','wendy.wu','吴诗瑶','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b3b830004','1','1467710933650','Y'),
	('ff80808155ba57ab0155ba63ede10019','amalia.ghorafi','Amalia','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b3b830004','2','1467710959072','Y'),
	('ff80808155ba57ab0155ba644139001a','siyu.cao','曹巳彧','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b3b830004','2','1467710980408','Y'),
	('ff80808155ba57ab0155ba64c7fb001b','wei.li','李威','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b72220005','1','1467711014905','Y'),
	('ff80808155ba57ab0155ba65448e001c','rebecca.cao','曹闻','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b72220005','2','1467711046797','Y'),
	('ff80808155ba57ab0155ba65a1dd001d','xin.yan','闫鑫','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b72220005','2','1467711070683','Y'),
	('ff80808155ba57ab0155ba66056f001e','liang.liu','刘亮','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b72220005','2','1467711096174','Y'),
	('ff80808155ba57ab0155ba80d260001f','kaijun.zhou','周开骏','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b72220005','2','1467712852575','Y'),
	('ff80808155ba57ab0155ba8121650020','ting.cai','蔡婷','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b72220005','2','1467712872804','Y'),
	('ff80808155ba57ab0155ba817fd80021','ting.zhang','张婷','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b72220005','2','1467712896982','Y'),
	('ff80808155ba57ab0155ba81ec1c0022','bo.xiong','熊博','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5b72220005','2','1467712924698','Y'),
	('ff80808155ba57ab0155ba825a450023','weihua.zhai','翟伟华','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5ba6a40006','1','1467712952900','Y'),
	('ff80808155ba57ab0155ba82b48b0024','has.hu','胡浩','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5ba6a40006','2','1467712976009','Y'),
	('ff80808155ba57ab0155ba830e540025','tangxiu.li','李堂秀','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5ba6a40006','2','1467712998994','Y'),
	('ff80808155ba57ab0155ba836ee60026','yongfang.jin','金永芳','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5ba6a40006','2','1467713023717','Y'),
	('ff80808155ba57ab0155ba83c2660027','yuan.zhong','钟媛','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5ba6a40006','2','1467713045093','Y'),
	('ff80808155ba57ab0155ba842c4f0028','sheng.ma','马昇','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5bd0560007','1','1467713072206','Y'),
	('ff80808155ba57ab0155ba8481f60029','shenwu.zhang','张珅旿','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba5bd0560007','2','1467713094132','Y'),
	('ff80808155ba57ab0155ba8538fc002c','xiaolong.li','李小龙','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba84c439002a','1','1467713140986','Y'),
	('ff80808155ba57ab0155ba8581ab002d','jingfang.dong','董井芳','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba84c439002a','2','1467713159594','Y'),
	('ff80808155ba57ab0155ba85d979002e','xia.li','李霞','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba84c439002a','2','1467713182072','Y'),
	('ff80808155ba57ab0155ba8613d9002f','ting.ji','计  婷','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba84c439002a','2','1467713197015','Y'),
	('ff80808155ba57ab0155ba86cc180030','beili.zhu','朱蓓莉','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba84e203002b','2','1467713244182','Y'),
	('ff80808155ba57ab0155ba872d300031','ying.zhang','张莹','c26be8aaf53b15054896983b43eb6a65','','N','ff80808155ba57ab0155ba84e203002b','2','1467713269038','Y');

/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_approve
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_approve`;

CREATE TABLE `tbl_approve` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` text COLLATE utf8_unicode_ci,
  `approve_type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `approve_people` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `layer_people` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `archive_people` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `control_people` text COLLATE utf8_unicode_ci,
  `manager` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `chairman` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_layer` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `layera_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `layerb_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `office_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `approve_type_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

LOCK TABLES `tbl_approve` WRITE;
/*!40000 ALTER TABLE `tbl_approve` DISABLE KEYS */;

INSERT INTO `tbl_approve` (`id`, `name`, `create_time`, `remark`, `approve_type`, `approve_people`, `layer_people`, `archive_people`, `control_people`, `manager`, `chairman`, `is_layer`, `status`, `layera_id`, `layerb_id`, `office_id`, `approve_type_id`)
VALUES
	('ff80808155ec22c80155ed406881000b','请假申请','1468564269183',NULL,'0','ff80808155ba57ab0155ba5dcfc0000b',NULL,'ff80808155ba57ab0155ba5e5f16000c',NULL,'ff80808155ba57ab0155ba5d15f50009',NULL,NULL,'Y',NULL,NULL,NULL,'ff80808155ec22c80155ed3fad06000a');

/*!40000 ALTER TABLE `tbl_approve` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_approve_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_approve_log`;

CREATE TABLE `tbl_approve_log` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ua_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `approve_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `to_user_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `from_user_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `create_time` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `stage` int(10) DEFAULT NULL,
  `status` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;



# Dump of table tbl_approve_type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_approve_type`;

CREATE TABLE `tbl_approve_type` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` text COLLATE utf8_unicode_ci,
  `create_time` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

LOCK TABLES `tbl_approve_type` WRITE;
/*!40000 ALTER TABLE `tbl_approve_type` DISABLE KEYS */;

INSERT INTO `tbl_approve_type` (`id`, `name`, `remark`, `create_time`, `status`)
VALUES
	('ff80808155ec22c80155ed3fad06000a','日常流程',NULL,'1468564221187','Y');

/*!40000 ALTER TABLE `tbl_approve_type` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_archive_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_archive_log`;

CREATE TABLE `tbl_archive_log` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `ua_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;



# Dump of table tbl_user_approve
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_user_approve`;

CREATE TABLE `tbl_user_approve` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `user_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `approve_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `approve_index` int(11) DEFAULT NULL,
  `approve_num` text COLLATE utf8_unicode_ci,
  `remark` text COLLATE utf8_unicode_ci,
  `status` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;



# Dump of table tbl_user_dept
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_user_dept`;

CREATE TABLE `tbl_user_dept` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dept_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

LOCK TABLES `tbl_user_dept` WRITE;
/*!40000 ALTER TABLE `tbl_user_dept` DISABLE KEYS */;

INSERT INTO `tbl_user_dept` (`id`, `user_id`, `dept_id`)
VALUES
	('ff80808155ec22c80155ed3e8d220001','ff80808155ba57ab0155ba5d15f50009','ff80808155ba57ab0155ba5aa56a0001'),
	('ff80808155ec22c80155ed3e8d360002','ff80808155ba57ab0155ba5d15f50009','ff80808155ba57ab0155ba5aeea30002'),
	('ff80808155ec22c80155ed3e8d400003','ff80808155ba57ab0155ba5d15f50009','ff80808155ba57ab0155ba5b10e00003'),
	('ff80808155ec22c80155ed3e8d520004','ff80808155ba57ab0155ba5d15f50009','ff80808155ba57ab0155ba5b3b830004'),
	('ff80808155ec22c80155ed3e8d5d0005','ff80808155ba57ab0155ba5d15f50009','ff80808155ba57ab0155ba5b72220005'),
	('ff80808155ec22c80155ed3e8d710006','ff80808155ba57ab0155ba5d15f50009','ff80808155ba57ab0155ba5bd0560007'),
	('ff80808155ec22c80155ed3e8d7a0007','ff80808155ba57ab0155ba5d15f50009','ff80808155ba57ab0155ba84e203002b'),
	('ff80808155ec22c80155ed3ecef10008','ff80808155ba57ab0155ba5d6bdd000a','ff80808155ba57ab0155ba84c439002a'),
	('ff80808155ec22c80155ed3f02840009','ff80808155ba57ab0155ba5ca1390008','ff80808155ba57ab0155ba5ba6a40006');

/*!40000 ALTER TABLE `tbl_user_dept` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
