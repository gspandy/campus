package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LoginResponseVO", description = "登录成功返回信息")
public class LoginResponseVO {
    
    private String signId;

    @ApiModelProperty(value = "调用业务接口所需唯一标识", required = true)
    public String getSignId() {
        return signId;
    }

    @ApiModelProperty(value = "调用业务接口所需唯一标识", required = true)
    public void setSignId(String signId) {
        this.signId = signId;
    }

}
