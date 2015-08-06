package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LoginRequestVO", description = "登录信息")
public class LoginRequestVO {

    private String loginName;

    private String password;

    private String verificationCode;

    @ApiModelProperty(value = "登录名", required = true)
    public String getLoginName() {
        return loginName;
    }

    @ApiModelProperty(value = "登录名", required = true)
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @ApiModelProperty(value = "密码", required = true)
    public String getPassword() {
        return password;
    }

    @ApiModelProperty(value = "密码", required = true)
    public void setPassword(String password) {
        this.password = password;
    }

    @ApiModelProperty(value = "验证码", required = false)
    public String getVerificationCode() {
        return verificationCode;
    }

    @ApiModelProperty(value = "验证码", required = false)
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

}
