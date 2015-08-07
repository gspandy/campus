package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "VerifyCodeReqVO", description = "验证验证码请求信息")
public class VerifyCodeReqVO {

    private String phone;

    private String checkCode;

    private String type;

    @ApiModelProperty(value = "手机号码", required = true)
    public String getPhone() {
        return phone;
    }

    @ApiModelProperty(value = "手机号码", required = true)
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ApiModelProperty(value = "手机验证码", required = true)
    public String getCheckCode() {
        return checkCode;
    }

    @ApiModelProperty(value = "手机验证码", required = true)
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @ApiModelProperty(value = "验证码类型(1.注册短信;2.找回密码;3.修改手机号)", required = true)
    public String getType() {
        return type;
    }

    @ApiModelProperty(value = "验证码类型(1.注册短信;2.找回密码;3.修改手机号)", required = true)
    public void setType(String type) {
        this.type = type;
    }

}
