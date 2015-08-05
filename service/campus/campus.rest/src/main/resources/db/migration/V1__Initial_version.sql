/*==============================================================*/
/* Table: BaseData                                              */
/*==============================================================*/
create table BaseData
(
   id                   int not null comment '表主键',
   Category             varchar(36) comment '门类名称',
   TwoCategory          varchar(38) comment '二级门类名称',
   SchoolCode           varchar(38) comment '学校代码',
   SchoolName           varchar(8) comment '学校名称',
   ProfessionCode       varchar(36) comment '专业代码',
   SchoolingLenth       varchar(2) comment '年限',
   ProfessionName       varchar(128) comment '专业名称',
   DegreeName           varchar(18) comment '学位名称',
   CollegeName          varchar(200) comment '院系名称',
   OpenDate             float comment '开设时间',
   ProvinceCode         varchar(36) comment '省代码',
   ProvinceName         varchar(54) comment '省名称',
   DirectorCode         varchar(36) comment '区域代码',
   DirectorName         varchar(54) comment '区域名称',
   RegionName           varchar(36) comment '主管名称',
   primary key (id)
);

/*==============================================================*/
/* Table: TL_LOG                                                */
/*==============================================================*/
create table TL_LOG
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   Thread               int comment '线程',
   Level                varchar(36) comment '错误',
   Message              varchar(400) comment '详细错误',
   CustomMessage        varchar(400) comment '自定义的错误信息',
   Exception            varchar(400) comment '异常',
   ClassName            varchar(100) comment '类名',
   ModuleName           varchar(100) comment '模块名',
   MehodName            varchar(100) comment '函数名',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   Date                 datetime comment '创建时间',
   LogID                varchar(36) comment '日志id',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_Activity                                       */
/*==============================================================*/
create table TS_APP_Activity
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SchoolUID            varchar(36) comment '院校主键',
   UserUID              varchar(36) comment '用户表主键',
   ActivityBrief        varchar(128) comment '内容简介',
   ActivityContent      longtext comment '活动内容',
   Pictures             varchar(800) comment '图片路径,多张图片用”,”进行分隔',
   LinkUrl              varchar(200) comment '链接地址',
   Address              varchar(200) comment '活动地址',
   StartTime            datetime comment '活动开始时间',
   EndTime              datetime comment '活动结束时间',
   OrderBy              int comment '显示排序,最小越靠前',
   IsCheck              int default 0 comment '是否审核,0 未审核 1 已审核',
   CheckUserUID         varchar(36) comment '审核用户UID',
   CheckTime            datetime comment '审核时间',
   CheckRemark          varchar(200) comment '审核备注',
   SelAreaCode          varchar(18) comment '查看区域代码',
   DisplayTabCode       varchar(18) comment '查看选项卡代码',
   PartakeNum           int comment '参与数',
   CommentNum           int comment '评论数',
   SupportNum           int comment '点赞数',
   NotSupportNum        int comment '吐槽数',
   ComplainNum          int comment '投诉数',
   TypeCode             int comment '类型代码,1管理员发布的活动 2社团发布的活动',
   CreateBy             varchar(36) comment '创建用户账号',
   CreateDate           datetime comment '创建时间',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_Appeal                                         */
/*==============================================================*/
create table TS_APP_Appeal
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   AppealUserUID        varchar(36) comment '申诉用户表主键',
   ManageOperate        int default 0 comment '后台处理操作代码,0 未处理 1 同意 2 拒绝  3 用户修改完成',
   OperateUserUID       varchar(36) comment '处理用户主键',
   OperateTime          datetime comment '处理时间',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建用户账号',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_AppealInfo                                     */
/*==============================================================*/
create table TS_APP_AppealInfo
(
   AppealUID            varchar(36) not null comment '申诉表主键',
   SchoolUID            varchar(36) comment '学校表主键',
   CollegeUID           varchar(36) comment '院系表主键',
   ProfessionUID        varchar(36) comment '专业表主键',
   ProfessionName       varchar(54) comment '专业名称',
   InSchoolYear         int comment '入学年份',
   SexType              varchar(2) comment '性别,0 女 1 男',
   primary key (AppealUID)
);

/*==============================================================*/
/* Table: TS_APP_AttentionUser                                  */
/*==============================================================*/
create table TS_APP_AttentionUser
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   MyUserUID            varchar(36) comment '我的用户ID',
   AttenionUserUID      varchar(36) comment '关注用户ID',
   AttentionTime        datetime comment '开始关注的时间',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_Comment                                        */
/*==============================================================*/
create table TS_APP_Comment
(
   UID                  varchar(36) not null,
   SourceUID            varchar(36) comment '源信息表主键',
   ComUserUID           varchar(36) comment '用户表主键',
   UserNickName         varchar(36) comment '用户呢称',
   ObjUserUID           varchar(36) comment '回复用户对象',
   ObjUserNickName      varchar(36) comment '用户呢称',
   CommentContent       varchar(400) comment '评论内容',
   TypeCode             int comment '类型代码,1活动评论 2新鲜事评论',
   isActive             int comment '是否有效,0 无效 1 有效',
   CreateBy             varchar(36) comment '创建用户账号',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_Complain                                       */
/*==============================================================*/
create table TS_APP_Complain
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SourceUID            varchar(36) comment '源信息表主键',
   UserUID              varchar(36) comment '用户表主键',
   ComplainDesc         varchar(36) comment '投诉内容',
   TypeCode             int comment '投诉类型代码,1活动 2新鲜事',
   ManageOperate        int default 0 comment '后台处理操作代码,0 未处理 1 已处理',
   OperateUserUID       varchar(36) comment '处理用户UID',
   OperateTime          datetime comment '处理时间',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建用户账号',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

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
   IsShield             int default 0 comment '是否屏蔽,0 不屏蔽 1 屏蔽',
   IsActive             int default 1 comment '是否有效,0 无效 1 有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_GroupUsers                                     */
/*==============================================================*/
create table TS_APP_GroupUsers
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   GroupUID             varchar(36) comment '群组主键',
   UserUID              varchar(36) comment '用户主键',
   IsLeader             int default 0 comment '是否群主,0 否 1 是',
   CreateBy             varchar(36) comment '创建用户',
   CreateDate           datetime comment '创建时间',
   IsActive             int default 1 comment '是否有效,0 无效 1 有效',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_MessageGroup                                   */
/*==============================================================*/
create table TS_APP_MessageGroup
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   GroupName            varchar(36) comment '群组名称',
   GourpPic             varchar(200) comment '群组头像',
   CreateUserUID        varchar(36) comment '创建用户UID',
   CreateDate           datetime comment '创建时间',
   IsActive             int default 1 comment '是否有效,0 无效 1 有效',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_NotSupport                                     */
/*==============================================================*/
create table TS_APP_NotSupport
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SourceUID            varchar(36) comment '源信息表主键',
   UserUID              varchar(36) comment '用户表主键',
   UserNickName         varchar(36) comment '用户呢称',
   TypeCode             int comment '类型代码,1 活动 2新鲜事',
   isActive             int default 1 comment '是否有效,0 无效 1 有效',
   CreateBy             varchar(36) comment '创建用户账号',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_Partake                                        */
/*==============================================================*/
create table TS_APP_Partake
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   ActivityUID          varchar(36) comment '活动表主键',
   PartakeUserUID       varchar(36) comment '用户表主键',
   isActive             int default 1 comment '是否有效,0 无效 1 有效',
   CreateBy             varchar(36) comment '创建用户账号',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_PushMessage                                    */
/*==============================================================*/
create table TS_APP_PushMessage
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SourceUID            varchar(36) comment '源信息主键',
   SendUserUID          varchar(36) comment '发送用户UID',
   ReceiveUserUID       varchar(36) comment '接收用户UID',
   GroupUID             varchar(36) comment '群组表主键',
   Alert                varchar(200) comment '通知信息',
   Completepushtime     datetime comment '完成推送时间',
   Pushcount            int comment '推送次数',
   TypeCode             int comment '类型代码,1会话推送 2系统通告推送',
   Responsecode         varchar(18) comment '推送返回代码',
   Responsecontent      varchar(400) comment '推送返回消息',
   ErrorCode            varchar(18) comment '错误代码',
   ErrorMsg             varchar(400) comment '错误消息',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_ReceiveMessage                                 */
/*==============================================================*/
create table TS_APP_ReceiveMessage
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SendMessageUID       varchar(36) comment '关联消息会话表',
   ReceiveUserUID       varchar(36) comment '接收用户账号',
   SendTime             datetime comment '发送时间',
   IsRead               int comment '是否已读,0 未读 1 已读',
   ReadTime             datetime comment '读取时间',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   primary key (UID)
);

alter table TS_APP_ReceiveMessage comment '消息明细-接收';

/*==============================================================*/
/* Table: TS_APP_SMSRecord                                      */
/*==============================================================*/
create table TS_APP_SMSRecord
(
   PhoneNumber          varchar(12) not null comment '手机号码',
   ValidateCode         varchar(8) comment '短信验证码',
   SMSMsg               varchar(36) comment '短信详细信息',
   SMSType              int comment '短信类型,1 注册短信 2 找回密码短信',
   Expire               int comment '过期时长,以秒为单位',
   SendTime             datetime comment '发送时间',
   CompleteTime         datetime comment '完成时间',
   IsSuccess            int default 0 comment '发送是否成功, 0 未成功 1 成功',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   primary key (PhoneNumber)
);

/*==============================================================*/
/* Table: TS_APP_SendMessage                                    */
/*==============================================================*/
create table TS_APP_SendMessage
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SendUserUID          varchar(36) comment '发送用户账号',
   GroupUID             varchar(36) comment '群组表主键',
   MsgContent           varchar(400) comment '消息内容',
   PicturePath          varchar(200) comment '图片路径',
   SoundPath            varchar(200) comment '语音路径',
   SendTime             datetime comment '发送时间',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_Session                                        */
/*==============================================================*/
create table TS_APP_Session
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   UserUID              varchar(36) comment '会话用户主键',
   ObjUserUID           varchar(36) comment '会话对象主键,若为单聊时关联用户表,若为群聊时关联群组表',
   LastMsgContent       varchar(400) comment '最新会话内容',
   TypeCode             int comment '会话类型代码,1单聊 2 群聊',
   LastMessageTime      datetime comment '最新会话时间',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_Share                                          */
/*==============================================================*/
create table TS_APP_Share
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   ActivityUID          varchar(36) comment '活动表主键',
   ShareUserUID         varchar(36) comment '用户表主键',
   ShareTime            datetime comment '分享时间',
   FreshNewsUID         varchar(36) comment '分享后，创建我的新鲜事；关联新鲜事表',
   isActive             int default 1 comment '是否有效,0 无效 1 有效',
   CreateBy             varchar(36) comment '创建用户账号',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_Support                                        */
/*==============================================================*/
create table TS_APP_Support
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SourceUID            varchar(36) comment '源信息表主键',
   SupportUserUID       varchar(36) comment '用户表主键',
   UserNickName         varchar(36) comment '用户呢称',
   TypeCode             int comment '类型代码,1 活动 2 新鲜事',
   isActive             int default 1 comment '是否有效,0 无效 1 有效',
   CreateBy             varchar(36) comment '创建用户账号',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_APP_User                                           */
/*==============================================================*/
create table TS_APP_User
(
   UserUID              varchar(36) not null comment '用户表主键',
   StudentID            varchar(36) comment '学号',
   RealName             varchar(38) comment '真实姓名',
   NamePinYin           varchar(38) comment '真实姓名全拼',
   NameFirstLetter      varchar(8) comment '真实姓名首字母',
   NickName             varchar(36) comment '用户昵称',
   SexType              varchar(2) comment '性别,0 女 1 男',
   Email                varchar(128) comment '邮箱地址',
   IDCard               varchar(18) comment '身份证号',
   HeadPic              varchar(200) comment '头像地址',
   BackGroundImg        varchar(200) comment '背景图地址',
   SchoolUID            varchar(36) comment '高校表主键',
   SchoolName           varchar(54) comment '高校名称',
   CollegeUID           varchar(36) comment '院系表主键',
   CollegeName          varchar(54) comment '院系名称',
   ProfessionUID        varchar(36) comment '专业表主键',
   ProfessionName       varchar(54) comment '专业名称',
   InSchoolYear         int comment '入学年份',
   CityCode             varchar(18) comment '所在市代码',
   CityName             varchar(36) comment '所在市名称',
   ProvinceCode         varchar(18) comment '所在省代码',
   ProvinceName         varchar(36) comment '所在省名称',
   IsValidated          int default 0 comment '身份是否验证,0 未验证 1已验证',
   IsLocked             int default 0 comment '账户是否锁住,0 未住  1锁住',
   IsOpen               int default 1 comment '是否公开,0 不公开 1公开',
   IsGraduate           int default 0 comment '是否已毕业,0 未毕业 1已毕业',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最新修改人',
   LastUpdateDate       datetime comment '最新修改时间',
   IsActive             int default 1 comment '账户是否可用,0 不可用  1  可用',
   primary key (UserUID)
);

/*==============================================================*/
/* Table: TS_APP_Version                                        */
/*==============================================================*/
create table TS_APP_Version
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   TypeCode             int default 1 comment '客户端类型,Android :1;ios:2',
   VersionNum           decimal(18,1) comment '版本号',
   Url                  varchar(128) comment 'App下载地址',
   IsActive             int default 1 comment '是否有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_AssnInfo                                       */
/*==============================================================*/
create table TS_SYS_AssnInfo
(
   UserUID              varchar(36) not null comment '用户表主键',
   AssnName             varchar(200) comment '社团名称',
   AssnLogo             varchar(200) comment 'Logo地址',
   Brief                varchar(800) comment '社团简介',
   SchoolUID            varchar(36) comment '学校主键',
   SchoolName           varchar(36) comment '学校名称',
   PeopleNum            int comment '现有人数',
   ContactPerson        varchar(18) comment '联系人',
   ContactNumber        varchar(36) comment '联系号码',
   Email                varchar(36) comment '邮箱地址',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UserUID)
);

/*==============================================================*/
/* Table: TS_SYS_BaseDataVersionNum                             */
/*==============================================================*/
create table TS_SYS_BaseDataVersionNum
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SchoolVersionNum     decimal(18,1) comment '当前学校最高版本',
   CollegeVersionNum    decimal(18,1) comment '当前院系最高版本',
   ProfessionVersionNum decimal(18,1) comment '当前专业最高版本',
   CategoryVersionNum   decimal(18,1) comment '当前门类最高版本',
   TwoCategoryVersionNum decimal(18,1) comment '当前二级门类最高版本',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_Category                                       */
/*==============================================================*/
create table TS_SYS_Category
(
   UID                  varchar(36) comment 'pk.固定信息',
   CategoryName         varchar(36) comment '门类名称',
   NamePinYin           varchar(128) comment '名称全拼',
   NameFirstLetter      varchar(18) comment '名称首字母',
   OrderBy              int comment '显示排序,越大越靠前',
   VersionNumber        varchar(8) comment '版本号',
   IsActive             int comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间'
);

/*==============================================================*/
/* Table: TS_SYS_College                                        */
/*==============================================================*/
create table TS_SYS_College
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SchoolUID            varchar(36) comment '高校表主键',
   CollegeName          varchar(36) comment '院系名称',
   NamePinYin           varchar(128) comment '院系名称全拼',
   NameFirstLetter      varchar(36) comment '院系名称首字母',
   OrderBy              int comment '显示排序,越大越靠前',
   VersionNumber        varchar(8) comment '版本号',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_LogType                                        */
/*==============================================================*/
create table TS_SYS_LogType
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   LogTypeCode          varchar(36) comment '日志类型代码',
   LogTypeName          varchar(36) comment '日志类型名称',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_Menu                                           */
/*==============================================================*/
create table TS_SYS_Menu
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   MenuCode             varchar(18) comment '菜单代码',
   MenuName             varchar(36) comment '菜单名称',
   ParentUID            varchar(36) comment '父菜单主键',
   MenuUrl              varchar(200) comment '链接地址',
   Orderby              int comment '显示排序,越大越靠前',
   IsShow               int default 1 comment '是否显示,0 隐藏  1显示',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_OperateLog                                     */
/*==============================================================*/
create table TS_SYS_OperateLog
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   OperateUserUID       varchar(36) comment '用户表主键',
   LogTypeUID           varchar(36) comment '日志类型表主键',
   OperateTime          datetime comment '操作时间',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_Profession                                     */
/*==============================================================*/
create table TS_SYS_Profession
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SchoolUID            varchar(36) comment '高校表主键',
   CollegeUID           varchar(36) comment '院系数据表主键',
   TwoCategoryUID       varchar(36) comment '二级门类表主键',
   ProfessionCode       varchar(18) comment '专业代码',
   ProfessionName       varchar(36) comment '专业名称',
   NamePinYin           varchar(128) comment '名称全拼',
   NameFirstLetter      varchar(18) comment '名称首字母',
   OpenDate             int comment '开设时间',
   SchoolingLenth       int comment '学制',
   DegreeName           varchar(36) comment '学位名称',
   VersionNumber        varchar(8) comment '版本号',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_RetrieveMMRecord                               */
/*==============================================================*/
create table TS_SYS_RetrieveMMRecord
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   Account              varchar(50) comment '账号',
   Email                varchar(50) comment '发送邮箱地址',
   Updatekey            varchar(250) comment '验证秘钥',
   MD5Updatekey         varchar(250) comment '加密key',
   SendTime             datetime comment '发送时间,以秒为单位',
   CompleteTime         datetime comment '完成时间',
   IsUpdate             int comment '是否已更新',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_Role                                           */
/*==============================================================*/
create table TS_SYS_Role
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   RoleCode             varchar(18) comment '角色代码',
   RoleName             varchar(36) comment '角色名称',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_RoleMenu                                       */
/*==============================================================*/
create table TS_SYS_RoleMenu
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   RoleUID              varchar(18) comment '角色表主键',
   MenuUID              varchar(36) comment '菜单表主键',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_School                                         */
/*==============================================================*/
create table TS_SYS_School
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   SchoolCode           varchar(18) comment '高校代码',
   SchoolName           varchar(54) comment '高校名称',
   NamePinYin           varchar(128) comment '高校名称全拼',
   NameFirstLetter      varchar(38) comment '高校名称首字母',
   CityCode             varchar(18) comment '所在市代码',
   CityName             varchar(36) comment '所在市名称',
   ProvinceCode         varchar(18) comment '所在省代码',
   ProvinceName         varchar(36) comment '所在省名称',
   DirectorCode         varchar(18) comment '主管代码',
   DirectorName         varchar(36) comment '主管名称',
   RegionName           varchar(36) comment '地区名称',
   OrderBy              int comment '显示排序,越大越靠前',
   VersionNumber        varchar(8) comment '版本号',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_TwoCategory                                    */
/*==============================================================*/
create table TS_SYS_TwoCategory
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   CategoryUID          varchar(36) comment '门类表主键',
   CategoryName         varchar(36) comment '门类名称',
   NamePinYin           varchar(128) comment '名称全拼',
   NameFirstLetter      varchar(18) comment '名称首字母',
   OrderBy              int comment '显示排序,越大越靠前',
   VersionNumber        varchar(8) comment '版本号',
   IsActive             int comment '是否有效,0 无效  1有效',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);

/*==============================================================*/
/* Table: TS_SYS_User                                           */
/*==============================================================*/
create table TS_SYS_User
(
   UID                  varchar(36) not null comment 'pk.固定信息',
   UserAccount          varchar(36) comment '用户账号',
   UserPwd              varchar(36) comment '密码',
   RoleUID              varchar(36) comment '角色表主键',
   IsCheck              int default 0 comment '是否审核,0 未审核 1 已审核',
   CheckUserUID         varchar(36) comment '审核用户UID',
   CheckTime            datetime comment '审核时间',
   CheckRemark          varchar(200) comment '审核备注',
   LastLoginTime        datetime comment '最新登录时间',
   SignId               varchar(36) comment '状态码',
   CreateBy             varchar(36) comment '创建人',
   CreateDate           datetime comment '创建时间',
   IsActive             int default 1 comment '是否有效,0 无效  1有效',
   LastUpdateBy         varchar(36) comment '最后修改用户',
   LastUpdateDate       datetime comment '最后修改时间',
   primary key (UID)
);