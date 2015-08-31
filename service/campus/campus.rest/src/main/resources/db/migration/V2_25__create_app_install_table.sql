drop table ts_app_install;
CREATE TABLE `ts_app_install` (
  `UID` varchar(36) DEFAULT NULL COMMENT 'pk.固定信息',
  `Source` varchar(36) DEFAULT NULL COMMENT 'app下载来源',
  `SourceName` varchar(36) DEFAULT NULL COMMENT 'app下载来源名称',
  `Count` int(11) DEFAULT NULL COMMENT '统计',
  `CreateDate` datetime DEFAULT NULL COMMENT '创建时间',
  primary key (UID)
);