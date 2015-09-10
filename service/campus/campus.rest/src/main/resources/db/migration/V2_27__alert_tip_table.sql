ALTER table ts_app_comment add(status char(1) default '0' COMMENT '是否已看,0 否 1 是');
ALTER table ts_app_support add(status char(1) default '0' COMMENT '是否已看,0 否 1 是');
update ts_app_comment set status='1';
update ts_app_support set status='1';