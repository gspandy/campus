package org.campus.vo;

import org.springframework.data.domain.Page;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ConversationVO", description = "聊天信息")
public class ConversationVO {

    private String conversationId;

    private String sendNickName;

    private String receiveNickName;

    Page<ConversationDetailVO> page;

    @ApiModelProperty(value = "会话ID", required = true)
    public String getConversationId() {
        return conversationId;
    }

    @ApiModelProperty(value = "会话ID", required = false)
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    @ApiModelProperty(value = "发送者昵称", required = false)
    public String getSendNickName() {
        return sendNickName;
    }

    @ApiModelProperty(value = "发送者昵称", required = true)
    public void setSendNickName(String sendNickName) {
        this.sendNickName = sendNickName;
    }

    @ApiModelProperty(value = "接收者昵称", required = false)
    public String getReceiveNickName() {
        return receiveNickName;
    }

    @ApiModelProperty(value = "接收者昵称", required = false)
    public void setReceiveNickName(String receiveNickName) {
        this.receiveNickName = receiveNickName;
    }

    @ApiModelProperty(value = "聊天分页信息", required = false)
    public Page<ConversationDetailVO> getPage() {
        return page;
    }

    @ApiModelProperty(value = "聊天分页信息", required = false)
    public void setPage(Page<ConversationDetailVO> page) {
        this.page = page;
    }

}
