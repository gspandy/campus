package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FriendVO", description = "好友信息")
public class FriendVO {

    private String userId;

    private String nickName;

    private String headUrl;

    private String signature;

    private String initial;

    @ApiModelProperty(value = "用户ID", required = true)
    public String getUserId() {
        return userId;
    }

    @ApiModelProperty(value = "用户ID", required = true)
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

    @ApiModelProperty(value = "头像url", required = true)
    public String getHeadUrl() {
        return headUrl;
    }

    @ApiModelProperty(value = "头像url", required = true)
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    @ApiModelProperty(value = "个性签名", required = false)
    public String getSignature() {
        return signature;
    }

    @ApiModelProperty(value = "个性签名", required = false)
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @ApiModelProperty(value = "名称首字母", required = true)
    public String getInitial() {
        return initial;
    }

    @ApiModelProperty(value = "名称首字母", required = true)
    public void setInitial(String initial) {
        this.initial = initial;
    }

}
