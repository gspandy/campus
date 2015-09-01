package org.campus.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentPostsMsgVO", description = "评论我的帖子信息")
public class CommentPostsMsgVO {

    private String commentId;

    private String commentUserId;

    private String commentNickName;

    private String commentHeadPic;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date commentDate;

    private String commentContent;

    private String postId;

    private String brief;

    private String content;

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

    @ApiModelProperty(value = "帖子ID", required = true)
    public String getPostId() {
        return postId;
    }

    @ApiModelProperty(value = "帖子ID", required = true)
    public void setPostId(String postId) {
        this.postId = postId;
    }

    @ApiModelProperty(value = "帖子简介", required = true)
    public String getBrief() {
        return brief;
    }

    @ApiModelProperty(value = "帖子简介", required = true)
    public void setBrief(String brief) {
        this.brief = brief;
    }

    @ApiModelProperty(value = "帖子内容", required = true)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "帖子内容", required = true)
    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(value = "帖子图片列表", required = false)
    public List<String> getPicUrls() {
        return picUrls;
    }

    @ApiModelProperty(value = "帖子图片列表", required = false)
    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

}
