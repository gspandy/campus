package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CountVO", description = "帖子、粉丝、关注数信息")
public class CountVO {

    private int postCount;

    private int fansCount;

    private int attentionCount;

    @ApiModelProperty(value = "发帖数", required = true)
    public int getPostCount() {
        return postCount;
    }

    @ApiModelProperty(value = "发帖数", required = true)
    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    @ApiModelProperty(value = "粉丝数", required = true)
    public int getFansCount() {
        return fansCount;
    }

    @ApiModelProperty(value = "粉丝数", required = true)
    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    @ApiModelProperty(value = "关注数", required = true)
    public int getAttentionCount() {
        return attentionCount;
    }

    @ApiModelProperty(value = "关注数", required = true)
    public void setAttentionCount(int attentionCount) {
        this.attentionCount = attentionCount;
    }

}
