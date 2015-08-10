package org.campus.service;

import java.util.List;

import org.campus.model.enums.SMSType;

public interface SendMessage {

    /**
     * 
     * 功能描述: <br>
     * 短信发送接口
     *
     * @param phoneNumbers 号码list
     * @param message 短信内容
     * @param type 1 注册短信 2 找回密码短信
     *
     */
    void sendMessage(List<String> phoneNumbers, String message, SMSType type);

}
