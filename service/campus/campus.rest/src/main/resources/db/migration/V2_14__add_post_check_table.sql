create table TS_app_posts_check
(
   UID                  varchar(36) comment 'pk.固定信息',
   PostId               varchar(36) comment '帖子Id',
   UserId               varchar(36) comment '审核人Id',
   CheckTime            datetime comment '审核时间',
   CheckType            char(2) comment '审核结果类型(1 投诉;2 赞; 3 踩)'
);