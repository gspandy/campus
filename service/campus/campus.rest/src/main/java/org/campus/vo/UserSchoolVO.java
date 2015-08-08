package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserSchoolVO", description = "用户学校信息")
public class UserSchoolVO {

    private String schoolId;

    private String schoolName;

    private String collegeId;

    private String collegeName;

    private String professionId;

    private String professionName;

    private int inSchoolYear;

    @ApiModelProperty(value = "学校ID", required = true)
    public String getSchoolId() {
        return schoolId;
    }

    @ApiModelProperty(value = "学校ID", required = true)
    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @ApiModelProperty(value = "学校名称", required = false)
    public String getSchoolName() {
        return schoolName;
    }

    @ApiModelProperty(value = "学校名称", required = false)
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @ApiModelProperty(value = "院系ID", required = true)
    public String getCollegeId() {
        return collegeId;
    }

    @ApiModelProperty(value = "院系ID", required = true)
    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    @ApiModelProperty(value = "院系名称", required = false)
    public String getCollegeName() {
        return collegeName;
    }

    @ApiModelProperty(value = "院系名称", required = false)
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @ApiModelProperty(value = "专业ID", required = true)
    public String getProfessionId() {
        return professionId;
    }

    @ApiModelProperty(value = "专业ID", required = true)
    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    @ApiModelProperty(value = "专业名称", required = false)
    public String getProfessionName() {
        return professionName;
    }

    @ApiModelProperty(value = "专业名称", required = false)
    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    @ApiModelProperty(value = "入学年份", required = true)
    public int getInSchoolYear() {
        return inSchoolYear;
    }

    @ApiModelProperty(value = "入学年份", required = true)
    public void setInSchoolYear(int inSchoolYear) {
        this.inSchoolYear = inSchoolYear;
    }

}
