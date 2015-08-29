package org.campus.vo;

import java.util.List;

import org.campus.model.enums.TypeCode;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentAddVO", description = "评论添加信息")
public class CommentAddVO {

    private String content;

    private boolean isTrans = false;

    private List<String> picUrls;

    private List<String> faceKeys;

    private TypeCode commentType;

    private String objUserId;

    private String objNickName;

    private String objComment;

    @ApiModelProperty(value = "评论内容", required = true)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "评论内容", required = true)
    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(value = "是否转发", required = true)
    public boolean isTrans() {
        return isTrans;
    }

    @ApiModelProperty(value = "是否转发", required = true)
    public void setTrans(boolean isTrans) {
        this.isTrans = isTrans;
    }

    @ApiModelProperty(value = "评论图片URL列表", required = false)
    public List<String> getPicUrls() {
        return picUrls;
    }

    @ApiModelProperty(value = "评论图片URL列表", required = false)
    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    @ApiModelProperty(value = "评论表情列表", required = true)
    public List<String> getFaceKeys() {
        return faceKeys;
    }

    @ApiModelProperty(value = "评论表情列表", required = true)
    public void setFaceKeys(List<String> faceKeys) {
        this.faceKeys = faceKeys;
    }

    @ApiModelProperty(value = "评论类型", required = true)
    public TypeCode getCommentType() {
        return commentType;
    }

    @ApiModelProperty(value = "评论类型", required = true)
    public void setCommentType(TypeCode commentType) {
        this.commentType = commentType;
    }

    @ApiModelProperty(value = "被评论人Id", required = false)
    public String getObjUserId() {
        return objUserId;
    }

    @ApiModelProperty(value = "被评论人Id", required = false)
    public void setObjUserId(String objUserId) {
        this.objUserId = objUserId;
    }

    @ApiModelProperty(value = "被评论人昵称", required = false)
    public String getObjNickName() {
        return objNickName;
    }

    @ApiModelProperty(value = "被评论人昵称", required = false)
    public void setObjNickName(String objNickName) {
        this.objNickName = objNickName;
    }

    @ApiModelProperty(value = "被评论人评论", required = false)
    public String getObjComment() {
        return objComment;
    }

    @ApiModelProperty(value = "被评论人评论", required = false)
    public void setObjComment(String objComment) {
        this.objComment = objComment;
    }

}
