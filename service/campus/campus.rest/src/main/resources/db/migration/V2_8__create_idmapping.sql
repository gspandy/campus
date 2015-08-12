-- ----------------------------
-- Table structure for `idmapping`
-- ----------------------------
DROP TABLE IF EXISTS `idmapping`;
CREATE TABLE `idmapping` (
  `id` bigint(20) NOT NULL,
  `fileId` varchar(120) DEFAULT NULL,
  `fdfsId` varchar(120) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



