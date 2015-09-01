package org.campus.vo;

import org.campus.model.enums.ApiType;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ApiLoginRequestVO", description = "第三方登录信息")
public class ApiLoginRequestVO {

    private String accessToken;

    private String openId;

    private ApiType apiType;

    @ApiModelProperty(value = "第三方AccessToken", required = true)
    public String getAccessToken() {
        return accessToken;
    }

    @ApiModelProperty(value = "第三方AccessToken", required = true)
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @ApiModelProperty(value = "第三方OpenId", required = true)
    public String getOpenId() {
        return openId;
    }

    @ApiModelProperty(value = "第三方OpenId", required = true)
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @ApiModelProperty(value = "第三方类型", required = true)
    public ApiType getApiType() {
        return apiType;
    }

    @ApiModelProperty(value = "第三方类型", required = true)
    public void setApiType(ApiType apiType) {
        this.apiType = apiType;
    }

}
