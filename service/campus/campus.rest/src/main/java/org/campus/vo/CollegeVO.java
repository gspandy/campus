package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CollegeVO", description = "院系信息")
public class CollegeVO {

    private String collegeId;

    private String collegeName;

    @ApiModelProperty(value = "院系ID", required = true)
    public String getCollegeId() {
        return collegeId;
    }

    @ApiModelProperty(value = "院系ID", required = true)
    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    @ApiModelProperty(value = "院系名称", required = true)
    public String getCollegeName() {
        return collegeName;
    }

    @ApiModelProperty(value = "院系名称", required = true)
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

}
