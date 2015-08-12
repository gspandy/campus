ALTER table ts_app_freshnews add(newstype varchar(1) COMMENT '发帖类型:1:休闲;2:新鲜;3:秘密;4:言论;');
ALTER table ts_app_freshnews add(ishot varchar(1) COMMENT '热门贴标识:0:否;1:是;');