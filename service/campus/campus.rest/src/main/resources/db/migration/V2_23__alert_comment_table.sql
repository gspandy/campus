ALTER table ts_app_comment add(srcPostId varchar(36) COMMENT '原帖ID');
ALTER table ts_app_support add(srcPostId varchar(36) COMMENT '原帖ID');
ALTER table ts_app_notsupport add(srcPostId varchar(36) COMMENT '原帖ID');