package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserVO", description = "用户信息")
public class UserVO {

    private String userId;

    private String nickName;

    private String signature;

    private String qrcode;

    private int postCount;

    private int fansCount;

    private int attentionCount;

    private boolean isAttention = false;

    @ApiModelProperty(value = "用户Id", required = true)
    public String getUserId() {
        return userId;
    }

    @ApiModelProperty(value = "用户Id", required = true)
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ApiModelProperty(value = "昵称", required = true)
    public String getNickName() {
        return nickName;
    }

    @ApiModelProperty(value = "昵称", required = true)
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @ApiModelProperty(value = "个人介绍", required = false)
    public String getSignature() {
        return signature;
    }

    @ApiModelProperty(value = "个人介绍", required = false)
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @ApiModelProperty(value = "二维码地址", required = false)
    public String getQrcode() {
        return qrcode;
    }

    @ApiModelProperty(value = "二维码地址", required = false)
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    @ApiModelProperty(value = "发帖数", required = true)
    public int getPostCount() {
        return postCount;
    }

    @ApiModelProperty(value = "发帖数", required = true)
    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    @ApiModelProperty(value = "粉丝数", required = true)
    public int getFansCount() {
        return fansCount;
    }

    @ApiModelProperty(value = "粉丝数", required = true)
    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    @ApiModelProperty(value = "关注数", required = true)
    public int getAttentionCount() {
        return attentionCount;
    }

    @ApiModelProperty(value = "关注数", required = true)
    public void setAttentionCount(int attentionCount) {
        this.attentionCount = attentionCount;
    }

    @ApiModelProperty(value = "是否关注", required = true)
    public boolean isAttention() {
        return isAttention;
    }

    @ApiModelProperty(value = "是否关注", required = true)
    public void setAttention(boolean isAttention) {
        this.isAttention = isAttention;
    }

}
