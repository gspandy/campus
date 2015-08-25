ALTER table ts_app_user add(AuditFlag varchar(8) DEFAULT '0' COMMENT '是否审过帖标识');
update ts_app_user set AuditFlag='0';