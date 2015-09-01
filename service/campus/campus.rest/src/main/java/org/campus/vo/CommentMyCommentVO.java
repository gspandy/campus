package org.campus.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentMyCommentVO", description = "评论我的评论信息")
public class CommentMyCommentVO {

    private String commentId;

    private String commentUserId;

    private String commentNickName;

    private String commentHeadPic;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date commentDate;

    private String commentContent;

    private String myCommentId;

    private String content;

    private String postId;

    private String brief;

    private String postContent;

    private List<String> picUrls;

    @ApiModelProperty(value = "评论Id", required = true)
    public String getCommentId() {
        return commentId;
    }

    @ApiModelProperty(value = "评论Id", required = true)
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @ApiModelProperty(value = "评论人Id", required = true)
    public String getCommentUserId() {
        return commentUserId;
    }

    @ApiModelProperty(value = "评论人Id", required = true)
    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    @ApiModelProperty(value = "评论人昵称", required = true)
    public String getCommentNickName() {
        return commentNickName;
    }

    @ApiModelProperty(value = "评论人昵称", required = true)
    public void setCommentNickName(String commentNickName) {
        this.commentNickName = commentNickName;
    }

    @ApiModelProperty(value = "评论人头像", required = true)
    public String getCommentHeadPic() {
        return commentHeadPic;
    }

    @ApiModelProperty(value = "评论人头像", required = true)
    public void setCommentHeadPic(String commentHeadPic) {
        this.commentHeadPic = commentHeadPic;
    }

    @ApiModelProperty(value = "评论时间", required = true)
    public Date getCommentDate() {
        return commentDate;
    }

    @ApiModelProperty(value = "评论时间", required = true)
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @ApiModelProperty(value = "评论内容", required = true)
    public String getCommentContent() {
        return commentContent;
    }

    @ApiModelProperty(value = "评论内容", required = true)
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    @ApiModelProperty(value = "我的评论ID", required = true)
    public String getMyCommentId() {
        return myCommentId;
    }

    @ApiModelProperty(value = "我的评论ID", required = true)
    public void setMyCommentId(String myCommentId) {
        this.myCommentId = myCommentId;
    }

    @ApiModelProperty(value = "我的评论", required = true)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "我的评论", required = true)
    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(value = "原帖子ID", required = true)
    public String getPostId() {
        return postId;
    }

    @ApiModelProperty(value = "原帖子ID", required = true)
    public void setPostId(String postId) {
        this.postId = postId;
    }

    @ApiModelProperty(value = "原帖子简介", required = true)
    public String getBrief() {
        return brief;
    }

    @ApiModelProperty(value = "原帖子简介", required = true)
    public void setBrief(String brief) {
        this.brief = brief;
    }

    @ApiModelProperty(value = "原帖子内容", required = true)
    public String getPostContent() {
        return postContent;
    }

    @ApiModelProperty(value = "原帖子内容", required = true)
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @ApiModelProperty(value = "原帖子图片列表", required = false)
    public List<String> getPicUrls() {
        return picUrls;
    }

    @ApiModelProperty(value = "原帖子图片列表", required = false)
    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

}
