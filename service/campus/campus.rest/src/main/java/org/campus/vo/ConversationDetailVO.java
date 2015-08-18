package org.campus.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ConversationDetailVO", description = "聊天详细信息")
public class ConversationDetailVO {

    private String nickName;
    
    private String message;

    private String picUrl;

    private List<String> faceKeys;

    @JsonFormat(pattern = "yyyy/MM/dd HH/mm/ss")
    private Date sendDate;
    
    private String soundUrl;

    private boolean holdFlag = false;
    
    @ApiModelProperty(value = "昵称", required = false)
    public String getNickName() {
        return nickName;
    }

    @ApiModelProperty(value = "昵称", required = false)
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @ApiModelProperty(value = "消息", required = false)
    public String getMessage() {
        return message;
    }

    @ApiModelProperty(value = "消息", required = false)
    public void setMessage(String message) {
        this.message = message;
    }

    @ApiModelProperty(value = "图片地址", required = false)
    public String getPicUrl() {
        return picUrl;
    }

    @ApiModelProperty(value = "图片地址", required = false)
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @ApiModelProperty(value = "表情键值", required = false)
    public List<String> getFaceKeys() {
        return faceKeys;
    }

    @ApiModelProperty(value = "表情键值", required = false)
    public void setFaceKeys(List<String> faceKeys) {
        this.faceKeys = faceKeys;
    }

    @ApiModelProperty(value = "会话持有者标识", required = false)
    public boolean isHoldFlag() {
        return holdFlag;
    }

    @ApiModelProperty(value = "会话持有者标识", required = false)
    public void setHoldFlag(boolean holdFlag) {
        this.holdFlag = holdFlag;
    }

    @ApiModelProperty(value = "发送时间", required = false)
    public Date getSendDate() {
        return sendDate;
    }

    @ApiModelProperty(value = "发送时间", required = false)
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    
    @ApiModelProperty(value = "语言路径", required = false)
    public String getSoundUrl() {
        return soundUrl;
    }
    
    @ApiModelProperty(value = "语言路径", required = false)
    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

}
