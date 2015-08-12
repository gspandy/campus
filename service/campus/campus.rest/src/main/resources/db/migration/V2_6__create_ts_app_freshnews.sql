drop table if exists TS_APP_FreshNews;

/*==============================================================*/
/* Table: TS_APP_FreshNews                                      */
/*==============================================================*/
create table TS_APP_FreshNews
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   NewsBrief            varchar(128) comment '内容简介',
   NewsContent          varchar(800) comment '新鲜事内容',
   Pictures             varchar(800) comment '图片路径，多张图片用”,”进行分隔',
   AddUserUID           varchar(36) comment '用户表主键',
   IsAnonymous          int default 0 comment '是否匿名,0 否 1 是',
   CommentNum           int comment '评论数',
   SupportNum           int comment '点赞数',
   ComplainNum          int comment '投诉数',
   NotSupportNum        int comment '踩数',
   TransNum             int comment '转发数',
   IsShield             int default 0 comment '是否屏蔽,0 不屏蔽 1 屏蔽',
   IsActive             int default 1 comment '是否有效,0 无效 1 有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

ALTER TABLE ts_app_smsrecord MODIFY COLUMN ResultMsg varchar(250) COMMENT '短信平台返回信息';