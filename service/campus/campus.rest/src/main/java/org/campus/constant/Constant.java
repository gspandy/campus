package org.campus.constant;

public interface Constant {

    String CAMPUS_SECURITY_SESSION = "CAMPUS_SECURITY_SESSION"; //用户信息变量名

    String VERFICATION_CODE = "VERIFICATION_CODE"; //图形验证码
    
    String SMS_CHECKCODE = "SMS_CHECK_CODE"; //手机验证码

    int CACHE_TIME = 1800;// 默认缓存时间,单位秒

    String SUCCESS = "success";

    String ERROR = "error";

    String CREATE_BY = "CAMP";
    
    String CAMPUS_DISPLAYMODEL = "CAMPUS_DISPLAY_MODEL"; //显示模式;白天/夜晚

    String CAMPUS_TEMP_NICKNAME = "CAMPUS_TEMP_NICKNAME"; //临时昵称;
}
