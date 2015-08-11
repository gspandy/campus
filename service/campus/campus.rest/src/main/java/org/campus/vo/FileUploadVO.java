package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class FileUploadVO {

    private String picFullUrl;

    private String picName;

    @ApiModelProperty(value = "图片名称", required = true)
    public String getPicName() {
        return picName;
    }

    @ApiModelProperty(value = "图片名称", required = true)
    public void setPicName(String picName) {
        this.picName = picName;
    }

    @ApiModelProperty(value = "图片全路径", required = true)
    public String getPicFullUrl() {
        return picFullUrl;
    }

    @ApiModelProperty(value = "图片全路径", required = true)
    public void setPicFullUrl(String picFullUrl) {
        this.picFullUrl = picFullUrl;
    }

}
