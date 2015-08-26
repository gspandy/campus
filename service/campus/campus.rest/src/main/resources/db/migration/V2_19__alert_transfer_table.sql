drop table ts_app_transfer;
create table ts_app_transfer
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   UserId               varchar(36) comment '用户Id',
   PostId               varchar(36) comment '帖子Id',
   ObjPostId            varchar(36) comment '新生成帖子ID',
   TransDate            datetime comment '转发时间',
   deleted              char(1) default '0' comment '原帖删除标识 0 未删除, 1 已删除',
   primary key (UID)
);

alter table ts_app_transfer comment '转发';

ALTER table ts_app_freshnews add(deleted char(1) default '0' COMMENT '删除标识 0 未删除, 1 已删除');