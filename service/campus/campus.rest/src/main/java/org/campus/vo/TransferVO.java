package org.campus.vo;

import java.util.List;

import org.campus.model.enums.TopicType;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "TransferVO", description = "转发信息")
public class TransferVO {

    private String content;

    private boolean isComment;

    private List<String> picUrls;

    private TopicType topicType;

    @ApiModelProperty(value = "评论内容", required = true)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "评论内容", required = true)
    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(value = "是否转发", required = false)
    public boolean isComment() {
        return isComment;
    }

    @ApiModelProperty(value = "是否转发", required = false)
    public void setComment(boolean isComment) {
        this.isComment = isComment;
    }

    @ApiModelProperty(value = "图片", required = false)
    public List<String> getPicUrls() {
        return picUrls;
    }

    @ApiModelProperty(value = "图片", required = false)
    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    @ApiModelProperty(value = "类型", required = true)
    public TopicType getTopicType() {
        return topicType;
    }

    @ApiModelProperty(value = "类型", required = true)
    public void setTopicType(TopicType topicType) {
        this.topicType = topicType;
    }

}
