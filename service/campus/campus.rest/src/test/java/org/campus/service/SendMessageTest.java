package org.campus.service;

import java.util.ArrayList;
import java.util.List;

import org.campus.BaseTest;
import org.campus.model.enums.SMSType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

public class SendMessageTest extends BaseTest {

    @Autowired
    private SendMessage sendMessage;

    @Test
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "/dataset/sms/data.xml")
    public void testSendMessage() {
        List<String> phoneNumbers = new ArrayList<String>();
        phoneNumbers.add("18651874535");
        sendMessage.sendMessage(phoneNumbers, "尊敬的用户，您的验证码为：123571，请及时输入。【聚合】", SMSType.SMS_REGISTER);
    }

}
