package org.campus.constant;

public interface ErrorCode {

    int NOT_LOGIN = 1000001; // 未登录

    int LOGIN_FAILED = 1000002;// 登录失败

    int VERFICATION_CODE_ERROR = 1100001;// 验证码生成失败

    int SMS_SEND_FAILED = 1200001;// 短信发送失败

}
