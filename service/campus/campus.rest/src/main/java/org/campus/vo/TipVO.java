package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class TipVO {

    private String type;

    private int num;

    @ApiModelProperty(value = "提示分类,1:查询我的帖子是否有新的赞；2:查询我的评论是否有新的赞；3:查询我的帖子是否有新的评论；4:查询我的评论是否有新评论", required = true)
    public String getType() {
        return type;
    }

    @ApiModelProperty(value = "提示分类,1:查询我的帖子是否有新的赞；2:查询我的评论是否有新的赞；3:查询我的帖子是否有新的评论；4:查询我的评论是否有新评论", required = true)
    public void setType(String type) {
        this.type = type;
    }

    @ApiModelProperty(value = "新提示个数", required = true)
    public int getNum() {
        return num;
    }

    @ApiModelProperty(value = "新提示个数", required = true)
    public void setNum(int num) {
        this.num = num;
    }

}
