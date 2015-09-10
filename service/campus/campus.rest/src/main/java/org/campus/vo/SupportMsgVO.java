package org.campus.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SupportMsgVO", description = "赞我的信息提示")
public class SupportMsgVO {

    private String supportUserId;

    private String supportHeadPic;

    private String supportNickName;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date supportDate;

    private String postsId;

    private String brief;

    private String content;

    private List<String> picUrls;

    private String status;

    @ApiModelProperty(value = "赞我的UserId", required = true)
    public String getSupportUserId() {
        return supportUserId;
    }

    @ApiModelProperty(value = "赞我的UserId", required = true)
    public void setSupportUserId(String supportUserId) {
        this.supportUserId = supportUserId;
    }

    @ApiModelProperty(value = "赞我的用户头像", required = true)
    public String getSupportHeadPic() {
        return supportHeadPic;
    }

    @ApiModelProperty(value = "赞我的用户头像", required = true)
    public void setSupportHeadPic(String supportHeadPic) {
        this.supportHeadPic = supportHeadPic;
    }

    @ApiModelProperty(value = "赞我的昵称", required = true)
    public String getSupportNickName() {
        return supportNickName;
    }

    @ApiModelProperty(value = "赞我的昵称", required = true)
    public void setSupportNickName(String supportNickName) {
        this.supportNickName = supportNickName;
    }

    @ApiModelProperty(value = "赞我的时间", required = true)
    public Date getSupportDate() {
        return supportDate;
    }

    @ApiModelProperty(value = "赞我的时间", required = true)
    public void setSupportDate(Date supportDate) {
        this.supportDate = supportDate;
    }

    @ApiModelProperty(value = "被赞的帖子ID", required = true)
    public String getPostsId() {
        return postsId;
    }

    @ApiModelProperty(value = "被赞的帖子ID", required = true)
    public void setPostsId(String postsId) {
        this.postsId = postsId;
    }

    @ApiModelProperty(value = "被赞的帖子简介", required = true)
    public String getBrief() {
        return brief;
    }

    @ApiModelProperty(value = "被赞的帖子简介", required = true)
    public void setBrief(String brief) {
        this.brief = brief;
    }

    @ApiModelProperty(value = "被赞的帖子内容", required = true)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "被赞的帖子内容", required = true)
    public void setContent(String content) {
        this.content = content;
    }

    @ApiModelProperty(value = "被赞的帖子图片列表", required = false)
    public List<String> getPicUrls() {
        return picUrls;
    }

    @ApiModelProperty(value = "被赞的帖子图片列表", required = false)
    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    @ApiModelProperty(value = "阅读状态，0未读；1已读", required = true)
    public String getStatus() {
        return status;
    }

    @ApiModelProperty(value = "阅读状态，0未读；1已读", required = true)
    public void setStatus(String status) {
        this.status = status;
    }

}
