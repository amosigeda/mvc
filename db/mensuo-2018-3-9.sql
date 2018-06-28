/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.5.53-0ubuntu0.14.04.1 : Database - suo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`suo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `suo`;

/*Table structure for table `apilog` */

DROP TABLE IF EXISTS `apilog`;

CREATE TABLE `apilog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `req` text,
  `resp` text,
  `imei` varchar(128) DEFAULT NULL,
  `rstatus` int(4) NOT NULL DEFAULT '0',
  `rmsg` text,
  `time` int(11) NOT NULL DEFAULT '0',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21141 DEFAULT CHARSET=utf8;

/*Table structure for table `authcode` */

DROP TABLE IF EXISTS `authcode`;

CREATE TABLE `authcode` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tel` varchar(13) NOT NULL,
  `code` varchar(10) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Table structure for table `bind_device` */

DROP TABLE IF EXISTS `bind_device`;

CREATE TABLE `bind_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0不是管理员1是管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8;

/*Table structure for table `blood_oxygen_info` */

DROP TABLE IF EXISTS `blood_oxygen_info`;

CREATE TABLE `blood_oxygen_info` (
  `bo_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `pulse_rate` int(10) NOT NULL COMMENT '脉率',
  `blood_oxygen` int(10) NOT NULL COMMENT '血氧',
  `user_id` int(11) unsigned NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `bloodfat_info` */

DROP TABLE IF EXISTS `bloodfat_info`;

CREATE TABLE `bloodfat_info` (
  `bi_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `blood_fat` int(10) NOT NULL COMMENT '血脂',
  `user_id` int(11) unsigned NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bloodsugar_info` */

DROP TABLE IF EXISTS `bloodsugar_info`;

CREATE TABLE `bloodsugar_info` (
  `bs_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `blood_sugar` int(10) NOT NULL COMMENT '电压',
  `user_id` int(11) unsigned NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Table structure for table `call_info` */

DROP TABLE IF EXISTS `call_info`;

CREATE TABLE `call_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `phone` varchar(128) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `msg` text,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Table structure for table `conf` */

DROP TABLE IF EXISTS `conf`;

CREATE TABLE `conf` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(32) NOT NULL,
  `value` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `device_info` */

DROP TABLE IF EXISTS `device_info`;

CREATE TABLE `device_info` (
  `dev_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dv` varchar(256) NOT NULL COMMENT '设备固件版本号',
  `sd` varchar(256) NOT NULL COMMENT '软件版本号',
  `user_id` int(11) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dev_id`),
  UNIQUE KEY `unique_idx_dv` (`dv`(191)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `contact` varchar(20) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `fence` */

DROP TABLE IF EXISTS `fence`;

CREATE TABLE `fence` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `lat` varchar(64) NOT NULL,
  `lng` varchar(64) NOT NULL,
  `radius` int(10) NOT NULL DEFAULT '0',
  `status` int(2) NOT NULL DEFAULT '0',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `fencelog` */

DROP TABLE IF EXISTS `fencelog`;

CREATE TABLE `fencelog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `lat` varchar(64) NOT NULL,
  `lng` varchar(64) NOT NULL,
  `radius` int(10) NOT NULL DEFAULT '0',
  `lat1` varchar(64) NOT NULL,
  `lng1` varchar(64) NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `content` varchar(128) DEFAULT NULL,
  `upload_time` timestamp NULL DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `finger_info` */

DROP TABLE IF EXISTS `finger_info`;

CREATE TABLE `finger_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `finger_id` int(11) NOT NULL,
  `imei` varchar(128) NOT NULL,
  `type` int(1) DEFAULT '0' COMMENT '0普通1报警',
  `createtime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  `updatetime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  `name` varchar(128) DEFAULT 'name',
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8;

/*Table structure for table `heart_pressure` */

DROP TABLE IF EXISTS `heart_pressure`;

CREATE TABLE `heart_pressure` (
  `hp_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `max_heart_pressure` int(10) NOT NULL COMMENT '高压',
  `min_heart_pressure` int(10) NOT NULL COMMENT '低压',
  `user_id` int(11) unsigned NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `imei` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`hp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=282 DEFAULT CHARSET=utf8;

/*Table structure for table `heart_rate` */

DROP TABLE IF EXISTS `heart_rate`;

CREATE TABLE `heart_rate` (
  `hr_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `heart_rate` int(10) NOT NULL,
  `user_id` int(11) NOT NULL,
  `upload_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `imei` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`hr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8;

/*Table structure for table `location` */

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `l_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `location_type` varchar(4) NOT NULL COMMENT '类型，0：gps， 1：基站',
  `lat` varchar(64) NOT NULL,
  `lng` varchar(64) NOT NULL,
  `accuracy` int(10) NOT NULL DEFAULT '0',
  `status` int(4) NOT NULL DEFAULT '0',
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `imei` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=440 DEFAULT CHARSET=utf8;

/*Table structure for table `member_info` */

DROP TABLE IF EXISTS `member_info`;

CREATE TABLE `member_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `phone` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `imei` varchar(128) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  `updatetime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

/*Table structure for table `not_register_info` */

DROP TABLE IF EXISTS `not_register_info`;

CREATE TABLE `not_register_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `phone` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `imei` varchar(128) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

/*Table structure for table `open_door_info` */

DROP TABLE IF EXISTS `open_door_info`;

CREATE TABLE `open_door_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(1) NOT NULL DEFAULT '1' COMMENT 'F1开锁记录F2指纹F3撬锁',
  `imei` varchar(22) NOT NULL DEFAULT '0',
  `user_id` int(10) NOT NULL DEFAULT '0' COMMENT '0为门里开锁，用户不明',
  `way` int(1) NOT NULL DEFAULT '0' COMMENT ' 0:门把开锁 1:APP开锁,2:指纹开锁,3:密码开锁4撬开',
  `side` int(1) NOT NULL DEFAULT '1' COMMENT '1门里2外面',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(128) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13543 DEFAULT CHARSET=utf8;

/*Table structure for table `pushlog` */

DROP TABLE IF EXISTS `pushlog`;

CREATE TABLE `pushlog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `target` varchar(64) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `content` text,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` tinyint(2) DEFAULT '0' COMMENT '类型，0：sos',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=618 DEFAULT CHARSET=utf8;

/*Table structure for table `pwd_info` */

DROP TABLE IF EXISTS `pwd_info`;

CREATE TABLE `pwd_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `pwd` varchar(128) NOT NULL,
  `imei` varchar(128) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  `updatetime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  `pwd_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

/*Table structure for table `pwd_moment_info` */

DROP TABLE IF EXISTS `pwd_moment_info`;

CREATE TABLE `pwd_moment_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `pwd` varchar(6) NOT NULL,
  `imei` varchar(128) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  `updatetime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '0未使用1使用',
  `status` int(2) DEFAULT '0' COMMENT '0未分享1分享',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1713 DEFAULT CHARSET=utf8;

/*Table structure for table `sensitive_point` */

DROP TABLE IF EXISTS `sensitive_point`;

CREATE TABLE `sensitive_point` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `lat` varchar(64) NOT NULL,
  `lng` varchar(64) NOT NULL,
  `radius` int(10) NOT NULL DEFAULT '0',
  `status` int(2) NOT NULL DEFAULT '0',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Table structure for table `sensitive_point_log` */

DROP TABLE IF EXISTS `sensitive_point_log`;

CREATE TABLE `sensitive_point_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `sp_id` int(11) NOT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `lat` varchar(64) NOT NULL,
  `lng` varchar(64) NOT NULL,
  `radius` int(10) NOT NULL DEFAULT '0',
  `lat1` varchar(64) NOT NULL,
  `lng1` varchar(64) NOT NULL,
  `status` int(2) NOT NULL DEFAULT '0',
  `content` varchar(128) DEFAULT NULL,
  `upload_time` timestamp NULL DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `smslog` */

DROP TABLE IF EXISTS `smslog`;

CREATE TABLE `smslog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `mobile` varchar(13) NOT NULL,
  `tpl_code` varchar(20) NOT NULL,
  `tpl_param` text NOT NULL,
  `rstatus` int(4) NOT NULL DEFAULT '0',
  `rmsg` text,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;

/*Table structure for table `sos_white_list` */

DROP TABLE IF EXISTS `sos_white_list`;

CREATE TABLE `sos_white_list` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `phone` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  `updatetime` timestamp NOT NULL DEFAULT '2017-01-01 01:01:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Table structure for table `soslog` */

DROP TABLE IF EXISTS `soslog`;

CREATE TABLE `soslog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `lat` varchar(64) DEFAULT NULL,
  `lng` varchar(64) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createtimeStr` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Table structure for table `step` */

DROP TABLE IF EXISTS `step`;

CREATE TABLE `step` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `imei` varchar(128) DEFAULT NULL,
  `step_number` int(11) NOT NULL DEFAULT '0',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys` */

DROP TABLE IF EXISTS `sys`;

CREATE TABLE `sys` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `service_content` text NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `PERM_ID` int(4) NOT NULL AUTO_INCREMENT,
  `PERM_NAME` varchar(32) DEFAULT NULL,
  `PERMISSION` varchar(128) DEFAULT NULL,
  `PERM_DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PERM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `RESC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `RESC_NAME` varchar(50) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `IDENTITY` varchar(64) DEFAULT NULL,
  `URL` varchar(200) DEFAULT NULL,
  `TYPE` int(2) DEFAULT NULL,
  `ICON` varchar(64) DEFAULT NULL,
  `SORT` int(2) DEFAULT NULL,
  `RESC_DESC` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`RESC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `ROLE_ID` int(4) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(64) DEFAULT NULL,
  `ROLE_KEY` varchar(64) DEFAULT NULL,
  `DESC` varchar(200) DEFAULT NULL,
  `STATUS` int(2) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role_resource` */

DROP TABLE IF EXISTS `sys_role_resource`;

CREATE TABLE `sys_role_resource` (
  `ROLE_ID` int(8) NOT NULL,
  `RESC_ID` int(8) NOT NULL,
  `PERMISSION_IDS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`,`RESC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `USER_ID` int(9) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(32) DEFAULT NULL,
  `PASSWORD` varchar(32) DEFAULT NULL,
  `REAL_NAME` varchar(64) DEFAULT NULL,
  `SALT` varchar(32) DEFAULT NULL,
  `AGE` int(4) DEFAULT NULL,
  `SEX` int(2) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  `STATUS` int(2) DEFAULT NULL,
  `DEDUCT` double(8,2) DEFAULT '0.00' COMMENT '扣量%',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `USER_ID` int(11) NOT NULL DEFAULT '0',
  `ROLE_ID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `token_info` */

DROP TABLE IF EXISTS `token_info`;

CREATE TABLE `token_info` (
  `t_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `token` varchar(128) NOT NULL,
  `user_id` int(11) NOT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`t_id`),
  UNIQUE KEY `unique_idx_token` (`token`),
  UNIQUE KEY `unique_idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1295 DEFAULT CHARSET=utf8;

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) DEFAULT NULL,
  `dv` varchar(128) DEFAULT NULL COMMENT '设备固件版本号',
  `sd` varchar(128) DEFAULT NULL COMMENT '软件版本号',
  `imei` varchar(128) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bindingtime` timestamp NULL DEFAULT NULL,
  `avatar` text,
  `nickname` varchar(128) DEFAULT NULL,
  `sex` tinyint(2) DEFAULT NULL COMMENT '性别，0：男性，1: 女性',
  `weight` varchar(8) DEFAULT NULL,
  `height` varchar(8) DEFAULT NULL,
  `address` text,
  `head` text COMMENT '头像',
  `type` int(2) DEFAULT '1' COMMENT '1正常注册用户  0添加成员后期要修改用户',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

/*Table structure for table `voltage_info` */

DROP TABLE IF EXISTS `voltage_info`;

CREATE TABLE `voltage_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `voltage` int(10) NOT NULL COMMENT '电压',
  `imei` varchar(128) NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=412 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
