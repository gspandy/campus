package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RegisterVO", description = "注册信息")
public class RegisterVO {

    // private String schoolId;
    //
    // private String collegeId;
    //
    // private String professionId;
    //
    // private int inSchoolYear;

    private String loginName;

//    private String checkCode;

    private String password;

    private String secPassword;

    // private String nickName;
    //
    // private String sex;

    // @ApiModelProperty(value = "学校ID", required = true)
    // public String getSchoolId() {
    // return schoolId;
    // }
    //
    // @ApiModelProperty(value = "学校ID", required = true)
    // public void setSchoolId(String schoolId) {
    // this.schoolId = schoolId;
    // }
    //
    // @ApiModelProperty(value = "院系ID", required = true)
    // public String getCollegeId() {
    // return collegeId;
    // }
    //
    // @ApiModelProperty(value = "院系ID", required = true)
    // public void setCollegeId(String collegeId) {
    // this.collegeId = collegeId;
    // }
    //
    // @ApiModelProperty(value = "专业ID", required = true)
    // public String getProfessionId() {
    // return professionId;
    // }
    //
    // @ApiModelProperty(value = "专业ID", required = true)
    // public void setProfessionId(String professionId) {
    // this.professionId = professionId;
    // }
    //
    // @ApiModelProperty(value = "入学年份", required = true)
    // public int getInSchoolYear() {
    // return inSchoolYear;
    // }
    //
    // @ApiModelProperty(value = "入学年份", required = true)
    // public void setInSchoolYear(int inSchoolYear) {
    // this.inSchoolYear = inSchoolYear;
    // }

    @ApiModelProperty(value = "登录名", required = true)
    public String getLoginName() {
        return loginName;
    }

    @ApiModelProperty(value = "登录名", required = true)
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    // @ApiModelProperty(value = "短信验证码", required = true)
    // public String getCheckCode() {
    // return checkCode;
    // }
    //
    // @ApiModelProperty(value = "短信验证码", required = true)
    // public void setCheckCode(String checkCode) {
    // this.checkCode = checkCode;
    // }

    @ApiModelProperty(value = "密码", required = true)
    public String getPassword() {
        return password;
    }

    @ApiModelProperty(value = "密码", required = true)
    public void setPassword(String password) {
        this.password = password;
    }

    @ApiModelProperty(value = "第二次输入密码", required = true)
    public String getSecPassword() {
        return secPassword;
    }

    @ApiModelProperty(value = "第二次输入密码", required = true)
    public void setSecPassword(String secPassword) {
        this.secPassword = secPassword;
    }

    // @ApiModelProperty(value = "昵称", required = true)
    // public String getNickName() {
    // return nickName;
    // }
    //
    // @ApiModelProperty(value = "昵称", required = true)
    // public void setNickName(String nickName) {
    // this.nickName = nickName;
    // }
    //
    // @ApiModelProperty(value = "性别", required = true)
    // public String getSex() {
    // return sex;
    // }
    //
    // @ApiModelProperty(value = "性别", required = true)
    // public void setSex(String sex) {
    // this.sex = sex;
    // }

}
