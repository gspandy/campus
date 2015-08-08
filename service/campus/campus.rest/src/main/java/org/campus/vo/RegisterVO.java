package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RegisterVO", description = "注册信息")
public class RegisterVO {

    private String loginName;

    private String password;

    private String secPassword;

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

    @ApiModelProperty(value = "第二次输入密码", required = true)
    public String getSecPassword() {
        return secPassword;
    }

    @ApiModelProperty(value = "第二次输入密码", required = true)
    public void setSecPassword(String secPassword) {
        this.secPassword = secPassword;
    }

}
