create table ts_app_install
(
   UID                  varchar(36) comment 'pk.固定信息',
   Source               varchar(36) comment 'app下载来源',
   CreateDate           datetime comment '创建时间'
);

alter table ts_app_install comment '安卓用户装机记录';