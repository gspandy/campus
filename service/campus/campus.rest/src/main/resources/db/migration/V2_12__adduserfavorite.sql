/*==============================================================*/
/* Table: ts_app_user_favorite                                  */
/*==============================================================*/
create table ts_app_user_favorite
(
   uid                  varchar(36) not null,
   userid               varchar(36) not null comment '用户编号',
   postsid              varchar(36) not null comment '收藏帖子编号',
   createtime           datetime not null comment '收藏时间',
   primary key (uid)
);

alter table ts_app_user_favorite comment '帖子收藏';

/*==============================================================*/
/* Index: idx_userfavorite_userid                               */
/*==============================================================*/
create index idx_userfavorite_userid on ts_app_user_favorite
(
   userid
);
