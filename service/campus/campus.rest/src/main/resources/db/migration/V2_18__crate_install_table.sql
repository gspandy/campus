create table ts_app_install
(
   UID                  varchar(36) comment 'pk.固定信息',
   Source               varchar(36) comment 'app下载来源',
   CreateDate           datetime comment '创建时间'
);

alter table ts_app_install comment '安卓用户装机记录';

create table ts_app_transfer
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   UserId               varchar(36) comment '用户Id',
   PostId               varchar(36) comment '帖子Id',
   TransDate            datetime comment '转发时间',
   primary key (UID)
);

alter table ts_app_transfer comment '转发';