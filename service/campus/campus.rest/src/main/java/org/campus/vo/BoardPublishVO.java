package org.campus.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardPublishVO", description = "板块内容发布信息")
public class BoardPublishVO {

    private String type;

    private List<String> picUrls;

    private List<String> faceKeys;

    private String content;

    @ApiModelProperty(value = "1:休闲;2:新鲜;3:秘密;4:言论", required = true)
    public String getType() {
        return type;
    }

    @ApiModelProperty(value = "1:休闲;2:新鲜;3:秘密;4:言论", required = true)
    public void setType(String type) {
        this.type = type;
    }

    @ApiModelProperty(value = "发帖图片URL列表", required = false)
    public List<String> getPicUrls() {
        return picUrls;
    }

    @ApiModelProperty(value = "发帖图片URL列表", required = false)
    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    @ApiModelProperty(value = "表情键值列表", required = false)
    public List<String> getFaceKeys() {
        return faceKeys;
    }

    @ApiModelProperty(value = "表情键值列表", required = false)
    public void setFaceKeys(List<String> faceKeys) {
        this.faceKeys = faceKeys;
    }

    @ApiModelProperty(value = "发帖内容", required = false)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "发帖内容", required = false)
    public void setContent(String content) {
        this.content = content;
    }

}
