package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SchoolVO", description = "学校信息")
public class SchoolVO {

    private String schoolId;

    private String schoolName;

    @ApiModelProperty(value = "学校ID", required = true)
    public String getSchoolId() {
        return schoolId;
    }

    @ApiModelProperty(value = "学校ID", required = true)
    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @ApiModelProperty(value = "学校名称", required = true)
    public String getSchoolName() {
        return schoolName;
    }

    @ApiModelProperty(value = "学校名称", required = true)
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

}
