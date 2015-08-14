package org.campus.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MyCommentVO", description = "我的评论信息")
public class MyCommentVO {

    private List<CommentVO> commentVOs;

    private String postId;

    private String brief;

    private String content;

    private List<String> postPics;

    @ApiModelProperty(value = "评论内容列表", required = true)
    public List<CommentVO> getCommentVOs() {
        return commentVOs;
    }

    @ApiModelProperty(value = "评论内容列表", required = true)
    public void setCommentVOs(List<CommentVO> commentVOs) {
        this.commentVOs = commentVOs;
    }

    @ApiModelProperty(value = "被评论的帖子ID", required = true)
    public String getPostId() {
        return postId;
    }

    @ApiModelProperty(value = "被评论的帖子ID", required = true)
    public void setPostId(String postId) {
        this.postId = postId;
    }

    @ApiModelProperty(value = "被评论的帖子简介", required = false)
    public String getBrief() {
        return brief;
    }

    @ApiModelProperty(value = "被评论的帖子简介", required = false)
    public void setBrief(String brief) {
        this.brief = brief;
    }

    @ApiModelProperty(value = "被评论的帖子内容", required = false)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "被评论的帖子内容", required = false)
    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(value = "被评论的帖子图片", required = false)
    public List<String> getPostPics() {
        return postPics;
    }

    @ApiModelProperty(value = "被评论的帖子图片", required = false)
    public void setPostPics(List<String> postPics) {
        this.postPics = postPics;
    }

}
