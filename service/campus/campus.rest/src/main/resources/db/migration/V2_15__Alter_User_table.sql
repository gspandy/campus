ALTER table ts_app_user add(ApiType char(2) COMMENT '第三方类型 1 qq;2 微博 3 微信');
ALTER table ts_app_user add(ApiId varchar(100) COMMENT '第三方唯一Id');