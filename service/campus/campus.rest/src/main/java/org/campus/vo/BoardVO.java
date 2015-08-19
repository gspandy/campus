package org.campus.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardVO", description = "板块内容信息")
public class BoardVO {

    private String postsId;

    private String userId;

    private String nickName;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date publishDate;

    private List<String> picUrls;

    private String brief;

    private String content;

    private boolean isSupported;

    @ApiModelProperty(value = "发帖ID", required = true)
    public String getPostsId() {
        return postsId;
    }

    @ApiModelProperty(value = "发帖ID", required = true)
    public void setPostsId(String postsId) {
        this.postsId = postsId;
    }

    @ApiModelProperty(value = "发帖用户ID", required = true)
    public String getUserId() {
        return userId;
    }

    @ApiModelProperty(value = "发帖用户ID", required = true)
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ApiModelProperty(value = "发帖用户昵称", required = true)
    public String getNickName() {
        return nickName;
    }

    @ApiModelProperty(value = "发帖用户昵称", required = true)
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @ApiModelProperty(value = "发帖时间", required = true)
    public Date getPublishDate() {
        return publishDate;
    }

    @ApiModelProperty(value = "发帖时间", required = true)
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @ApiModelProperty(value = "发帖图片URL", required = false)
    public List<String> getPicUrls() {
        return picUrls;
    }

    @ApiModelProperty(value = "发帖图片URL", required = false)
    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    @ApiModelProperty(value = "发帖简介", required = false)
    public String getBrief() {
        return brief;
    }

    @ApiModelProperty(value = "发帖简介", required = false)
    public void setBrief(String brief) {
        this.brief = brief;
    }

    @ApiModelProperty(value = "发帖内容", required = false)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "发帖内容", required = false)
    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(value = "是否被赞过", required = false)
    public boolean isSupported() {
        return isSupported;
    }

    @ApiModelProperty(value = "是否被赞过", required = false)
    public void setSupported(boolean isSupported) {
        this.isSupported = isSupported;
    }

}
