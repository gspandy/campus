package org.campus.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ConversationDetailVO", description = "聊天详细信息")
public class ConversationDetailVO {

    private String sendMessage;

    private String receiveMessage;

    private String sendPicUrl;

    private String receivePicUrl;

    private List<String> sendFaceKeys;

    private List<String> receiveFaceKeys;

    @JsonFormat(pattern = "yyyy/MM/dd HH/mm/ss")
    private Date sendDate;

    private String isRead;

    @JsonFormat(pattern = "yyyy/MM/dd HH/mm/ss")
    private Date readDate;

    @ApiModelProperty(value = "发送信息", required = false)
    public String getSendMessage() {
        return sendMessage;
    }

    @ApiModelProperty(value = "发送信息", required = false)
    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    @ApiModelProperty(value = "接收信息", required = false)
    public String getReceiveMessage() {
        return receiveMessage;
    }

    @ApiModelProperty(value = "接收信息", required = false)
    public void setReceiveMessage(String receiveMessage) {
        this.receiveMessage = receiveMessage;
    }

    @ApiModelProperty(value = "发送图片", required = false)
    public String getSendPicUrl() {
        return sendPicUrl;
    }

    @ApiModelProperty(value = "发送图片", required = false)
    public void setSendPicUrl(String sendPicUrl) {
        this.sendPicUrl = sendPicUrl;
    }

    @ApiModelProperty(value = "接收图片", required = false)
    public String getReceivePicUrl() {
        return receivePicUrl;
    }

    @ApiModelProperty(value = "接收图片", required = false)
    public void setReceivePicUrl(String receivePicUrl) {
        this.receivePicUrl = receivePicUrl;
    }

    @ApiModelProperty(value = "发送表情", required = false)
    public List<String> getSendFaceKeys() {
        return sendFaceKeys;
    }

    @ApiModelProperty(value = "发送表情", required = false)
    public void setSendFaceKeys(List<String> sendFaceKeys) {
        this.sendFaceKeys = sendFaceKeys;
    }

    @ApiModelProperty(value = "接收表情", required = false)
    public List<String> getReceiveFaceKeys() {
        return receiveFaceKeys;
    }

    @ApiModelProperty(value = "接收表情", required = false)
    public void setReceiveFaceKeys(List<String> receiveFaceKeys) {
        this.receiveFaceKeys = receiveFaceKeys;
    }

    @ApiModelProperty(value = "发送时间", required = false)
    public Date getSendDate() {
        return sendDate;
    }

    @ApiModelProperty(value = "发送时间", required = false)
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @ApiModelProperty(value = "是否已读(0:未读;1:已读)", required = true)
    public String getIsRead() {
        return isRead;
    }

    @ApiModelProperty(value = "是否已读(0:未读;1:已读)", required = true)
    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    @ApiModelProperty(value = "读取时间", required = false)
    public Date getReadDate() {
        return readDate;
    }

    @ApiModelProperty(value = "读取时间", required = false)
    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

}
