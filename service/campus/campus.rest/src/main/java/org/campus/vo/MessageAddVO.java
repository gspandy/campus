package org.campus.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MessageAddVO", description = "消息发送信息")
public class MessageAddVO {

    private String message;

    private List<String> faceKey;

    private String picUrl;
    
    // 语言文件路径
    private String soundUrl;

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

    @ApiModelProperty(value = "发送图片地址", required = false)
    public String getPicUrl() {
        return picUrl;
    }

    @ApiModelProperty(value = "发送图片地址", required = false)
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @ApiModelProperty(value = "语言文件路径", required = false)
    public String getSoundUrl() {
        return soundUrl;
    }
    
    @ApiModelProperty(value = "语言文件路径", required = false)
    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

}
