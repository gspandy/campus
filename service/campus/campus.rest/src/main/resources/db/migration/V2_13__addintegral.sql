ALTER table ts_app_user add(Integral bigint(20) DEFAULT 0 COMMENT '当前积分');
ALTER table ts_app_user add(LoginCount bigint(20) DEFAULT 0 COMMENT '登陆次数');

create table TS_app_integral
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   IntegralType         char(2) comment '积分产生类型：0 每日登陆 1 发帖 2 审帖 3 评论 4 分享 5 转发',
   IntegralTime         datetime comment '积分产生时间',
   UserId               varchar(36) comment '用户Id',
   IntegralIncome       bigint comment '积分收入',
   IntegralExpend       bigint comment '积分支出',
   IntegralBalance      bigint comment '积分结余',
   Remark               varchar(100) comment '备注',
   primary key (UID)
);

create table TS_app_signin
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   UserId               varchar(36),
   LoginDate            date,
   primary key (UID)
);