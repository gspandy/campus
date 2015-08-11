package org.campus.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.campus.config.SystemConfig;
import org.campus.constant.Constant;
import org.campus.constant.ErrorCode;
import org.campus.core.exception.CampusException;
import org.campus.model.SmsRecord;
import org.campus.model.enums.SMSResult;
import org.campus.model.enums.SMSType;
import org.campus.repository.SmsRecordMapper;
import org.campus.service.SendMessage;
import org.campus.util.CollectionUtils;
import org.campus.util.HttpClientUtil;
import org.campus.util.ToolUtil;
import org.campus.util.URLBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SendMessageImpl implements SendMessage {

    private static Logger logger = LoggerFactory.getLogger(SendMessageImpl.class);

    @Autowired
    private SmsRecordMapper smsRecordMapper;

    @Override
    public void sendMessage(String phone, String message, SMSType type) {
        List<String> phones = new ArrayList<String>();
        phones.add(phone);
        sendMessage(phones, message, type);
    }

    @Override
    public void sendMessage(List<String> phones, String message, SMSType type) {
        // 一次最多只能发送50个号码
        List<List<String>> ret = CollectionUtils.split(phones, 50);
        for (List<String> phoneNumbers : ret) {
            if (CollectionUtils.isEmpty(phoneNumbers)) {
                continue;
            }
            StringBuffer buffer = new StringBuffer();
            int count = 0;
            List<SmsRecord> records = new ArrayList<SmsRecord>();
            for (String phone : phoneNumbers) {
                buffer.append(phone);
                if (count < phoneNumbers.size() - 1) {
                    buffer.append(",");
                }
                records.add(setRecord(message, type, phone));
                count++;
            }

            // 记录发送信息
            smsRecordMapper.batchInsert(records);

            // 短信发送
            String urlStr = SystemConfig.getString("SMS_URL");
            Map<String, String> map = new LinkedHashMap<String, String>();
            map.put("name", SystemConfig.getString("SMS_USER_NAME"));
            map.put("pwd", SystemConfig.getString("SMS_USER_PWD"));
            map.put("dest", buffer.toString());
            map.put("content", message);
            String url = URLBuilder.resetUrl(map, urlStr);
            String response = HttpClientUtil.get(url);
            logger.debug("短信平台返回信息：{0}", response);
            if (StringUtils.hasText(response)) {
                String[] split = response.split(":");
                if (Constant.SUCCESS.equals(split[0])) {
                    updateSms(records, SMSResult.SMS_SUCCESS, response);
                } else {
                    updateSms(records, SMSResult.SMS_FAILED, response);
                }
            } else {
                throw new CampusException(ErrorCode.SMS_SEND_FAILED, "短信发送失败");
            }
        }
    }

    private SmsRecord setRecord(String message, SMSType type, String phone) {
        SmsRecord record = new SmsRecord();
        record.setUid(ToolUtil.getUUid());
        record.setPhonenumber(phone);
        record.setSmsmsg(message);
        record.setSmstype(type);
        record.setCreateby(Constant.CREATE_BY);
        record.setCreatedate(new Date());
        return record;
    }

    private void updateSms(List<SmsRecord> records, SMSResult isSuccess, String resultMsg) {
        for (SmsRecord record : records) {
            record.setIssuccess(isSuccess);
            record.setCompletetime(new Date());
            record.setResultMsg(resultMsg);
        }
        smsRecordMapper.batchUpdate(records);
    }

}
