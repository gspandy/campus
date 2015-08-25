package org.campus.vo;

import java.io.Serializable;
import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MessageVO", description = "消息信息")
public class MessageVO implements Serializable{

    private static final long serialVersionUID = -3950182310343668548L;
    private String conversationId;
    
    private List<MessageListVO> messageList;
    
    @ApiModelProperty(value = "用户聊天的会话ID，每对用户之前有一个唯一的会话ID", required = true)
    public String getConversationId() {
        return conversationId;
    }

    @ApiModelProperty(value = "用户聊天的会话ID，每对用户之前有一个唯一的会话ID", required = true)
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    @ApiModelProperty(value = "消息提示列表", required = true)
    public List<MessageListVO> getMessageList() {
        return messageList;
    }

    @ApiModelProperty(value = "消息提示列表", required = true)
    public void setMessageList(List<MessageListVO> messageList) {
        this.messageList = messageList;
    }
    
}
