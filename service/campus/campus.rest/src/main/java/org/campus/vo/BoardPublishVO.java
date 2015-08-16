package org.campus.vo;

import java.util.List;

import org.campus.model.enums.TopicType;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardPublishVO", description = "板块内容发布信息")
public class BoardPublishVO {

	private String title; //标题
	
    private String content;//内容
   
    private List<String> picUrls;//图片
    
    private String type;

    @ApiModelProperty(value = "简介")
	public String getTitle() {
		return title;
	}

    @ApiModelProperty(value = "简介")
	public void setTitle(String title) {
		this.title = title;
	}

    @ApiModelProperty(value = "发帖内容", required = false)
    public String getContent() {
        return content;
    }

    @ApiModelProperty(value = "发帖内容", required = false)
    public void setContent(String content) {
        this.content = content;
    }
    
    @ApiModelProperty(value = "发帖图片URL列表", required = false)
    public List<String> getPicUrls() {
        return picUrls;
    }

    @ApiModelProperty(value = "发帖图片URL列表", required = false)
    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    @ApiModelProperty(value = "1:休闲;2:新鲜;3:秘密;4:言论", required = true)
    public String getType() {
        return type;
    }

    @ApiModelProperty(value = "1:休闲;2:新鲜;3:秘密;4:言论", required = true)
    public void setType(String type) {
        this.type = type;
    }

}
