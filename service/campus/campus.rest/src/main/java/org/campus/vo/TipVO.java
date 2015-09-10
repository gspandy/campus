package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class TipVO {

    private String type;

    private int num;

    @ApiModelProperty(value = "提示分类", required = true)
    public String getType() {
        return type;
    }

    @ApiModelProperty(value = "提示分类", required = true)
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
