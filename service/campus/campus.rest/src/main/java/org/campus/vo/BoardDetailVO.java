package org.campus.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardDetailVO", description = "帖子详情信息")
public class BoardDetailVO {

    private String postsId;

    private String userId;

    private String nickName;

    private String headPic;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date publishDate;

    private List<String> picUrls;

    private String content;

    private int supportNum;

    private int commentNum;

    private int transNum;

    private boolean isSupported;

    private boolean isCollected;

    private boolean isDeleted;

    private String transferComment;

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

    @ApiModelProperty(value = "发帖用户头像", required = false)
    public String getHeadPic() {
        return headPic;
    }

    @ApiModelProperty(value = "发帖用户头像", required = false)
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
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

    @ApiModelProperty(value = "发帖内容", required = false)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "发帖内容", required = false)
    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(value = "点赞数", required = true)
    public int getSupportNum() {
        return supportNum;
    }

    @ApiModelProperty(value = "点赞数", required = true)
    public void setSupportNum(int supportNum) {
        this.supportNum = supportNum;
    }

    @ApiModelProperty(value = "评论数", required = true)
    public int getCommentNum() {
        return commentNum;
    }

    @ApiModelProperty(value = "评论数", required = true)
    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    @ApiModelProperty(value = "转发数", required = true)
    public int getTransNum() {
        return transNum;
    }

    @ApiModelProperty(value = "转发数", required = true)
    public void setTransNum(int transNum) {
        this.transNum = transNum;
    }

    @ApiModelProperty(value = "是否赞过", required = true)
    public boolean isSupported() {
        return isSupported;
    }

    @ApiModelProperty(value = "是否赞过", required = true)
    public void setSupported(boolean isSupported) {
        this.isSupported = isSupported;
    }

    @ApiModelProperty(value = "是否收藏过", required = true)
    public boolean isCollected() {
        return isCollected;
    }

    @ApiModelProperty(value = "是否收藏过", required = true)
    public void setCollected(boolean isCollected) {
        this.isCollected = isCollected;
    }

    @ApiModelProperty(value = "原帖是否删除,true 已删", required = true)
    public boolean isDeleted() {
        return isDeleted;
    }

    @ApiModelProperty(value = "原帖是否删除,true 已删", required = true)
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @ApiModelProperty(value = "转发评论", required = true)
    public String getTransferComment() {
        return transferComment;
    }

    @ApiModelProperty(value = "转发评论", required = true)
    public void setTransferComment(String transferComment) {
        this.transferComment = transferComment;
    }

}
