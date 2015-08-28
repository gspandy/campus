package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentMyCommentVO", description = "评论我的评论信息")
public class CommentMyCommentVO {

    private String commentId;

    private String commentUserId;

    private String commentNickName;

    private String commentContent;

    private String myCommentId;

    private String content;

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

}
