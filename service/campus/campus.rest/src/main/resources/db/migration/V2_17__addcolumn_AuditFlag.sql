ALTER table ts_app_user add(AuditFlag varchar(8) DEFAULT '0' COMMENT '是否审过帖标识');
update ts_app_user set AuditFlag='0';

create table ts_app_freshnews_audit
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   UserId               varchar(36) comment '用户Id',
   PostId               varchar(36) comment '帖子Id',
   AuditReust           char(1) comment '审核结果',
   AuditTime            datetime comment '审核时间',
   primary key (UID)
);