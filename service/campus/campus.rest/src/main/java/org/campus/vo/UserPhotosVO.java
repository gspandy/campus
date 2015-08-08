package org.campus.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserPhotosVO", description = "用户相册信息")
public class UserPhotosVO {

    private String photoId;

    private String nickName;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date pubDate;

    private String url;

    private String content;

    private int transNum;

    private int commentNum;

    private int supportNum;

    @ApiModelProperty(value = "相册Id", required = true)
    public String getPhotoId() {
        return photoId;
    }

    @ApiModelProperty(value = "相册Id", required = true)
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    @ApiModelProperty(value = "昵称", required = true)
    public String getNickName() {
        return nickName;
    }

    @ApiModelProperty(value = "昵称", required = true)
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @ApiModelProperty(value = "发布时间", required = true)
    public Date getPubDate() {
        return pubDate;
    }

    @ApiModelProperty(value = "发布时间", required = true)
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @ApiModelProperty(value = "相片URL", required = true)
    public String getUrl() {
        return url;
    }

    @ApiModelProperty(value = "相片URL", required = true)
    public void setUrl(String url) {
        this.url = url;
    }

    @ApiModelProperty(value = "发布内容", required = false)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "发布内容", required = false)
    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(value = "转发数", required = true)
    public int getTransNum() {
        return transNum;
    }

    @ApiModelProperty(value = "转发数", required = true)
    public void setTransNum(int transNum) {
        this.transNum = transNum;
    }

    @ApiModelProperty(value = "评论数", required = true)
    public int getCommentNum() {
        return commentNum;
    }

    @ApiModelProperty(value = "评论数", required = true)
    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    @ApiModelProperty(value = "点赞数", required = true)
    public int getSupportNum() {
        return supportNum;
    }

    @ApiModelProperty(value = "点赞数", required = true)
    public void setSupportNum(int supportNum) {
        this.supportNum = supportNum;
    }

}
