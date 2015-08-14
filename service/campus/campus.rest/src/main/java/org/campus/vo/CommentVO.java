package org.campus.vo;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentVO", description = "评论信息")
public class CommentVO {

    private String commentId;

    private String userId;

    private String nickName;

    private String objUserId;

    private String objNickName;

    private Date commentDate;

    private String commentContent;

    private int supportNum;

    @ApiModelProperty(value = "评论ID", required = true)
    public String getCommentId() {
        return commentId;
    }

    @ApiModelProperty(value = "评论ID", required = true)
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @ApiModelProperty(value = "评论人ID", required = true)
    public String getUserId() {
        return userId;
    }

    @ApiModelProperty(value = "评论人ID", required = true)
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ApiModelProperty(value = "评论人昵称", required = true)
    public String getNickName() {
        return nickName;
    }

    @ApiModelProperty(value = "评论人昵称", required = true)
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @ApiModelProperty(value = "回复人ID", required = false)
    public String getObjUserId() {
        return objUserId;
    }

    @ApiModelProperty(value = "回复人ID", required = false)
    public void setObjUserId(String objUserId) {
        this.objUserId = objUserId;
    }

    @ApiModelProperty(value = "回复人昵称", required = false)
    public String getObjNickName() {
        return objNickName;
    }

    @ApiModelProperty(value = "回复人昵称", required = false)
    public void setObjNickName(String objNickName) {
        this.objNickName = objNickName;
    }

    @ApiModelProperty(value = "评论时间", required = true)
    public Date getCommentDate() {
        return commentDate;
    }

    @ApiModelProperty(value = "评论时间", required = true)
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @ApiModelProperty(value = "评论内容", required = false)
    public String getCommentContent() {
        return commentContent;
    }

    @ApiModelProperty(value = "评论内容", required = false)
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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
