package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "VersionVO", description = "版本信息")
public class VersionVO {

    private Integer typecode;

    private String versionnum;

    private String url;

    @ApiModelProperty(value = "客户端类型(1:IOS;2:Andriod)", required = true)
    public Integer getTypecode() {
        return typecode;
    }

    @ApiModelProperty(value = "客户端类型(1:IOS;2:Andriod)", required = true)
    public void setTypecode(Integer typecode) {
        this.typecode = typecode;
    }

    @ApiModelProperty(value = "版本号", required = true)
    public String getVersionnum() {
        return versionnum;
    }

    @ApiModelProperty(value = "版本号", required = true)
    public void setVersionnum(String versionnum) {
        this.versionnum = versionnum;
    }

    @ApiModelProperty(value = "下载地址", required = true)
    public String getUrl() {
        return url;
    }

    @ApiModelProperty(value = "下载地址", required = true)
    public void setUrl(String url) {
        this.url = url;
    }

}
