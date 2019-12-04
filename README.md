# 简要说明

**所需表结构**

```mysql
/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50553
Source Host           : 127.0.0.1:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-12-04 10:58:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tu_info
-- ----------------------------
DROP TABLE IF EXISTS `tu_info`;
CREATE TABLE `tu_info` (
  `id` bigint(20) NOT NULL,
  `detail_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50553
Source Host           : 127.0.0.1:3306
Source Database       : mysql

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-12-04 10:58:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for job_info
-- ----------------------------
DROP TABLE IF EXISTS `job_info`;
CREATE TABLE `job_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_addr` varchar(255) DEFAULT NULL,
  `company_info` text,
  `company_name` varchar(255) DEFAULT NULL,
  `job_addr` varchar(255) DEFAULT NULL,
  `job_info` text,
  `job_name` varchar(255) DEFAULT NULL,
  `salary_max` int(11) DEFAULT NULL,
  `salary_min` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

```

