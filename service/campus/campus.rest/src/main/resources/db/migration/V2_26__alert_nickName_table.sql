ALTER table ts_app_comment add(IsAnonymous varchar(36) COMMENT '是否匿名,0 否 1 是');
ALTER table ts_app_freshnews add(CheckDate datetime COMMENT '审核通过时间');