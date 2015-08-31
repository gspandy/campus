package org.campus.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class MessageListVO implements Serializable {

    private static final long serialVersionUID = -8534128791786774565L;

    private String messageId;

    private String message;

    private List<String> faceKey;

    private String picUrl;

    private String sendUserId;

    private String sendNickName;

    private String headPic;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date sendDate;

    private String isRead;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date readDate;

    private String groupName;

    @ApiModelProperty(value = "消息ID", required = true)
    public String getMessageId() {
        return messageId;
    }

    @ApiModelProperty(value = "消息ID", required = true)
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @ApiModelProperty(value = "消息文字内容", required = false)
    public String getMessage() {
        return message;
    }

    @ApiModelProperty(value = "消息文字内容", required = false)
    public void setMessage(String message) {
        this.message = message;
    }

    @ApiModelProperty(value = "表情键值列表", required = false)
    public List<String> getFaceKey() {
        return faceKey;
    }

    @ApiModelProperty(value = "表情键值列表", required = false)
    public void setFaceKey(List<String> faceKey) {
        this.faceKey = faceKey;
    }

    @ApiModelProperty(value = "发送者ID", required = true)
    public String getSendUserId() {
        return sendUserId;
    }

    @ApiModelProperty(value = "发送者ID", required = true)
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    @ApiModelProperty(value = "发送者昵称", required = true)
    public String getSendNickName() {
        return sendNickName;
    }

    @ApiModelProperty(value = "发送者昵称", required = true)
    public void setSendNickName(String sendNickName) {
        this.sendNickName = sendNickName;
    }

    @ApiModelProperty(value = "头像", required = false)
    public String getHeadPic() {
        return headPic;
    }

    @ApiModelProperty(value = "头像", required = false)
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    @ApiModelProperty(value = "发送者时间", required = true)
    public Date getSendDate() {
        return sendDate;
    }

    @ApiModelProperty(value = "发送者时间", required = true)
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @ApiModelProperty(value = "发送图片地址", required = false)
    public String getPicUrl() {
        return picUrl;
    }

    @ApiModelProperty(value = "发送图片地址", required = false)
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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

    @ApiModelProperty(value = "如果是群消息，显示群名称", required = false)
    public String getGroupName() {
        return groupName;
    }

    @ApiModelProperty(value = "如果是群消息，显示群名称", required = false)
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
