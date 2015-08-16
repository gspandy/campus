package org.campus.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.campus.core.exception.CampusException;
import org.campus.model.ReceiveMessage;
import org.campus.model.SendMessage;
import org.campus.model.Session;
import org.campus.model.enums.IsReadType;
import org.campus.model.enums.MessageType;
import org.campus.model.enums.SessionType;
import org.campus.repository.ReceiveMessageMapper;
import org.campus.repository.SendMessageMapper;
import org.campus.repository.SessionMapper;
import org.campus.service.MessageService;
import org.campus.util.ToolUtil;
import org.campus.vo.MessageAddVO;
import org.campus.vo.MessageRequestVo;
import org.campus.vo.MessageVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MessageService {

    private SendMessageMapper sendMessageMapper;

    private ReceiveMessageMapper receiveMessageMapper;

    private SessionMapper sessionMapper;

    public List<MessageVO> getMessagePromptList(MessageRequestVo params) {
        // 校验参数
        checkParams(params);
        List<MessageVO> resultList = new LinkedList<MessageVO>();
        List<ReceiveMessage> recieveMessageList = receiveMessageMapper.selectByreceiveUserId(params.getUserId(),
                IsReadType.getIsReadTypeByCode(params.getIsRead()));

        for (ReceiveMessage receiveMessage : recieveMessageList) {
            // TODO:
            MessageVO messageVO = new MessageVO();
            messageVO.setIsRead(String.valueOf(receiveMessage.getIsread()));
            messageVO.setReadDate(receiveMessage.getReadtime());
            resultList.add(messageVO);
        }
        return null;
    }
    
    /**
     * 
     * 创建新会话
     *
     * @param sendUserId 创建的用户
     * @param recieveUserId 会话对象用户id
     * @param typeCode 会话类型，单聊或群聊
     * @param msg 会话消息
     * @return
     *
     */
    public String createSession(String sendUserId,String recieveUserId,String typeCode,String msg){
        Session record = new Session();
        record.setUid(ToolUtil.getUUid());
        record.setUseruid(sendUserId);
        record.setObjuseruid(recieveUserId);
        record.setTypecode(Integer.parseInt(typeCode));
        // fixme,消息存储
        record.setLastmsgcontent(msg);
        record.setLastmessagetime(new Date());
        sessionMapper.insertSelective(record);
        return record.getUid();
    }

    public void sendSessionMsg(String conversationId,String recieveUserId, String sendUserId, MessageAddVO messageAddVO,String sessionType) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setUid(ToolUtil.getUUid());
        sendMessage.setMsgcontent(messageAddVO.getMessage());
        sendMessage.setSendtime(new Date());
        sendMessage.setSenduseruid(sendUserId);
        sendMessage.setSoundpath(messageAddVO.getSoundUrl());
        sendMessage.setPicturepath(messageAddVO.getPicUrl());
        
        // TODO :未完成
        if(sessionType.equals(SessionType.GROUP_CHANNEL)){
            sendMessage.setGroupuid(recieveUserId);
        }
        
        ReceiveMessage receiveMessage = new ReceiveMessage();
        
        receiveMessageMapper.insertSelective(receiveMessage);
        sendMessageMapper.insert(sendMessage);
    }

    private void checkParams(MessageRequestVo params) {
        if (null == MessageType.getMessageTypeByCode(params.getType())) {
            throw new CampusException(190002, "类型错误，消息类型(0:系统公告;1:普通用户信息)");
        }

        if (null == IsReadType.getIsReadTypeByCode(params.getIsRead())) {
            throw new CampusException(190003, "类型错误，是否已读(0:未读;1:已读)");
        }
    }
}
