package org.campus.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserPhotosVO", description = "用户相册信息")
public class UserPhotosVO {

    private String photoId;

    private String nickName;

    @JsonFormat(pattern="yyyy/MM/dd")
    private Date pubDate;

    private String url;

    private String note;

    @ApiModelProperty(value = "相册Id", required = true)
    public String getPhotoId() {
        return photoId;
    }

    @ApiModelProperty(value = "相册Id", required = true)
    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    @ApiModelProperty(value = "昵称", required = true)
    public String getNickName() {
        return nickName;
    }

    @ApiModelProperty(value = "昵称", required = true)
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @ApiModelProperty(value = "发布时间", required = true)
    public Date getPubDate() {
        return pubDate;
    }

    @ApiModelProperty(value = "发布时间", required = true)
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @ApiModelProperty(value = "相片URL", required = true)
    public String getUrl() {
        return url;
    }

    @ApiModelProperty(value = "相片URL", required = true)
    public void setUrl(String url) {
        this.url = url;
    }

    @ApiModelProperty(value = "发布信息", required = false)
    public String getNote() {
        return note;
    }

    @ApiModelProperty(value = "发布信息", required = false)
    public void setNote(String note) {
        this.note = note;
    }

}
