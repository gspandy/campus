drop table ts_app_version;
CREATE TABLE `ts_app_version` (
  `UID` varchar(36) NOT NULL COMMENT 'pk.固定信息',
  `TypeCode` int(11) DEFAULT '1' COMMENT '客户端类型,Android :1;ios:2',
  `VersionNum` varchar(20) DEFAULT NULL COMMENT '版本号',
  `Url` varchar(128) DEFAULT NULL COMMENT 'App下载地址',
  `IsActive` int(11) DEFAULT '1' COMMENT '是否有效',
  `CreateBy` varchar(36) DEFAULT NULL COMMENT '创建人',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
  `LastUpdateBy` varchar(36) DEFAULT NULL COMMENT '最后修改用户',
  `LastUpdateDate` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`UID`)
);