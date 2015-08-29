package org.campus.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.campus.constant.Constant;
import org.campus.core.exception.CampusException;
import org.campus.model.ConversationDetail;
import org.campus.model.GroupUsers;
import org.campus.model.ReceiveMessage;
import org.campus.model.SendMessage;
import org.campus.model.Session;
import org.campus.model.enums.IsReadType;
import org.campus.model.enums.SessionType;
import org.campus.repository.GroupUsersMapper;
import org.campus.repository.MessageGroupMapper;
import org.campus.repository.ReceiveMessageMapper;
import org.campus.repository.SendMessageMapper;
import org.campus.repository.SessionMapper;
import org.campus.repository.UserMapper;
import org.campus.service.MessageService;
import org.campus.util.ToolUtil;
import org.campus.vo.ConversationDetailVO;
import org.campus.vo.MessageAddVO;
import org.campus.vo.MessageListVO;
import org.campus.vo.MessageRequestVo;
import org.campus.vo.MessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final static Logger logger = LoggerFactory.getLogger(MessageService.class);
    @Autowired
    private SendMessageMapper sendMessageMapper;

    @Autowired
    private ReceiveMessageMapper receiveMessageMapper;

    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageGroupMapper messageGroupMapper;

    @Autowired
    private GroupUsersMapper groupUsersMapper;

    public List<MessageVO> getMessagePromptList(MessageRequestVo params) {
        // 校验参数
        checkParams(params);
        List<MessageVO> resultList = new LinkedList<MessageVO>();
        List<ReceiveMessage> recieveMessageList = receiveMessageMapper.selectByreceiveUserId(params.getUserId(),
                IsReadType.getIsReadTypeByCode(params.getIsRead()));

        for (ReceiveMessage receiveMessage : recieveMessageList) {
            MessageListVO messageListVO = new MessageListVO();
            messageListVO.setIsRead(String.valueOf(receiveMessage.getIsread()));
            messageListVO.setReadDate(receiveMessage.getReadtime());
            messageListVO.setSendDate(receiveMessage.getSendtime());

            SendMessage sendMessage = sendMessageMapper.selectByPrimaryKey(receiveMessage.getSendmessageuid());
            messageListVO.setMessage(sendMessage.getMsgcontent());
            messageListVO.setMessageId(sendMessage.getUid());
            messageListVO.setPicUrl(sendMessage.getPicturepath());
            messageListVO.setSendUserId(sendMessage.getSenduseruid());
            String nickName = userMapper.selectNickNameByPrimaryKey(sendMessage.getSenduseruid());
            messageListVO.setSendNickName(nickName);
            String objUseruid = receiveMessage.getReceiveuseruid();

            // 判断是否为群组消息
            if (StringUtils.isNotEmpty(sendMessage.getGroupuid())) {
                objUseruid = sendMessage.getGroupuid();
                String groupName = messageGroupMapper.selectNameByPrimaryKey(objUseruid);
                messageListVO.setGroupName(groupName);
            }
            String conversationId = sessionMapper.selectBySessionUserId(sendMessage.getSenduseruid(), objUseruid);
            processMessage(resultList, conversationId, messageListVO);
            updateReadState(receiveMessage.getUid());
        }
        return resultList;
    }

    private void processMessage(List<MessageVO> resultList, String conversationId, MessageListVO messageListVO) {
        // boolean flag = true;
        // for (MessageVO messageVO : resultList) {
        // if(messageVO.getConversationId().equals(conversationId)){
        // flag = false;
        // resultList.remove(messageVO);
        // messageVO.getMessageList().add(messageListVO);
        // resultList.add(messageVO);
        // break;
        // }
        // }

        // if(flag){
        MessageVO messageVO = new MessageVO();
        messageVO.setConversationId(conversationId);
        List<MessageListVO> messageList = new ArrayList<MessageListVO>();
        messageList.add(messageListVO);
        messageVO.setMessageList(messageList);
        resultList.add(messageVO);
        // }
    }

    public String getConversationId(String userId, String objUserId) {
        logger.info("###getConversationId###,userId:" + userId + ",objUserId:" + objUserId);
        String conversationId = sessionMapper.selectBySessionUserId(userId, objUserId);
        if (StringUtils.isEmpty(conversationId)) {
            return "";
        }
        logger.info("conversationId:" + conversationId);
        return conversationId;
    }

    // 更新已读状态
    private void updateReadState(String uid) {
        ReceiveMessage receiveMessage = new ReceiveMessage();

        receiveMessage.setUid(uid);
        receiveMessage.setIsread(IsReadType.READ);
        receiveMessage.setReadtime(new Date());

        receiveMessageMapper.updateByPrimaryKeySelective(receiveMessage);
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
    public String createSession(String sendUserId, String recieveUserId, String typeCode, String msg) {
        String conversationId = sessionMapper.selectBySessionUserId(sendUserId, recieveUserId);
        if (StringUtils.isNotEmpty(conversationId)) {
            return conversationId;
        }
        Session record = new Session();
        record.setUid(ToolUtil.getUUid());
        record.setUseruid(sendUserId);
        record.setObjuseruid(recieveUserId);
        record.setTypecode(Integer.parseInt(typeCode));
        // fixme,消息存储
        record.setLastmsgcontent(msg);
        record.setLastmessagetime(new Date());
        record.setCreateby(Constant.CREATE_BY);
        record.setCreatedate(new Date());
        sessionMapper.insertSelective(record);
        return record.getUid();
    }

    public void sendSessionMsg(String conversationId, String recieveUserId, String sendUserId,
            MessageAddVO messageAddVO, String sessionType) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setUid(ToolUtil.getUUid());
        sendMessage.setMsgcontent(messageAddVO.getMessage());
        sendMessage.setSendtime(new Date());
        sendMessage.setSenduseruid(sendUserId);
        sendMessage.setSoundpath(messageAddVO.getSoundUrl());
        sendMessage.setPicturepath(messageAddVO.getPicUrl());
        sendMessage.setCreateby(Constant.CREATE_BY);
        sendMessage.setCreatedate(new Date());
        if (sessionType.equals(SessionType.GROUP_CHANNEL)) {
            sendMessage.setGroupuid(recieveUserId);

            List<GroupUsers> groupUserList = groupUsersMapper.selectByGroupUserId(recieveUserId);
            for (GroupUsers groupUser : groupUserList) {
                // 给群组成员发送消息
                saveRecieveMessage(groupUser.getUseruid(), sendMessage.getUid(), sendMessage.getSendtime());
            }
        } else {
            // 给会话对象发送消息
            saveRecieveMessage(recieveUserId, sendMessage.getUid(), sendMessage.getSendtime());
        }

        sendMessageMapper.insert(sendMessage);
    }

    public List<ConversationDetailVO> queryConversationList(String holdUserId, String conversationId) {

        Session session = sessionMapper.selectByPrimaryKey(conversationId);
        if (null == session) {
            throw new CampusException(1900005, "没有该会话");
        }

        List<ConversationDetailVO> resultList = new LinkedList<>();
        if (SessionType.SINGLE_CHANNEL.getCode().equals(String.valueOf(session.getTypecode()))) {
            // 单聊
            String objUserId = session.getObjuseruid();
            if (objUserId.equals(holdUserId)) {
                objUserId = session.getUseruid();
            }
            setSingleMessages(resultList, objUserId, holdUserId);
        } else {
            // 群聊
            setGroupMessages(resultList, session.getObjuseruid(), holdUserId);
        }
        return resultList;
    }

    private void setSingleMessages(List<ConversationDetailVO> resultList, String objuseruid, String holdUserId) {
        // 单聊
        List<ConversationDetail> list = receiveMessageMapper.selectMessageDetailSingle(holdUserId, objuseruid);
        for (ConversationDetail conversationDetail : list) {
            ConversationDetailVO detail = new ConversationDetailVO();
            detail.setHoldFlag(conversationDetail.isHoldFlag());
            detail.setMessage(conversationDetail.getMsgcontent());
            detail.setPicUrl(conversationDetail.getPicturepath());
            detail.setSendDate(conversationDetail.getSendtime());
            detail.setSoundUrl(conversationDetail.getSoundpath());
            String nickName = userMapper.selectNickNameByPrimaryKey(conversationDetail.getSenduseruid());
            nickName = StringUtils.isEmpty(nickName) ? "未知用户" : nickName;
            detail.setNickName(nickName);
            resultList.add(detail);
        }
    }

    private void setGroupMessages(List<ConversationDetailVO> resultList, String objuseruid, String holdUserId) {
        // 群聊
        List<SendMessage> list = sendMessageMapper.selectByGroupUID(objuseruid);

        for (SendMessage sendMessage : list) {
            ConversationDetailVO detail = new ConversationDetailVO();
            detail.setMessage(sendMessage.getMsgcontent());
            detail.setPicUrl(sendMessage.getPicturepath());
            detail.setSendDate(sendMessage.getSendtime());
            detail.setSoundUrl(sendMessage.getSoundpath());

            // 设置消息持有者标识
            if (holdUserId.equals(sendMessage.getSenduseruid())) {
                detail.setHoldFlag(true);
            } else {
                detail.setHoldFlag(false);
            }

            String nickName = userMapper.selectNickNameByPrimaryKey(sendMessage.getSenduseruid());
            nickName = StringUtils.isEmpty(nickName) ? "未知用户" : nickName;
            detail.setNickName(nickName);
            resultList.add(detail);
        }
    }

    private void saveRecieveMessage(String recieveUserId, String sendMsgUid, Date sendtime) {
        ReceiveMessage receiveMessage = new ReceiveMessage();
        receiveMessage.setCreateby(Constant.CREATE_BY);
        receiveMessage.setCreatedate(new Date());
        receiveMessage.setIsread(IsReadType.UNREAD);
        receiveMessage.setReceiveuseruid(recieveUserId);
        receiveMessage.setSendmessageuid(sendMsgUid);
        receiveMessage.setSendtime(sendtime);
        receiveMessage.setUid(ToolUtil.getUUid());
        receiveMessageMapper.insertSelective(receiveMessage);
    }

    private void checkParams(MessageRequestVo params) {
        if (null == IsReadType.getIsReadTypeByCode(params.getIsRead())) {
            throw new CampusException(190003, "类型错误，是否已读(0:未读;1:已读)");
        }
    }
}
