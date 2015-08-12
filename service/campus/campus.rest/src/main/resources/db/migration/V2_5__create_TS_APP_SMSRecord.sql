drop table if exists TS_APP_SMSRecord;

/*==============================================================*/
/* Table: TS_APP_SMSRecord                                      */
/*==============================================================*/
create table TS_APP_SMSRecord
(
   UID                  varchar(36) not null,
   PhoneNumber          varchar(12) comment '手机号码',
   ValidateCode         varchar(8) comment '短信验证码',
   SMSMsg               varchar(36) comment '短信详细信息',
   SMSType              int comment '短信类型,1 注册短信 2 找回密码短信',
   Expire               int comment '过期时长,以秒为单位',
   SendTime             datetime comment '发送时间',
   CompleteTime         datetime comment '完成时间',
   IsSuccess            int default 0 comment '发送是否成功, 0 未成功 1 成功',
   ResultMsg            varchar(250) comment '发送错误信息',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   primary key (UID)
);