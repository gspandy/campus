package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SchoolVO", description = "学校信息")
public class SchoolVO {

    private String userId;

    private String nickName;

    private String introduction;

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
    public String getIntroduction() {
        return introduction;
    }

    @ApiModelProperty(value = "个人介绍", required = false)
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
