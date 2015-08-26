package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LoginResponseVO", description = "登录成功返回信息")
public class LoginResponseVO {

    private String signId;

    private String userId;

    private String userAccount;

    private String schoolId;

    private String schoolName;

    private String collegeId;

    private String collegeName;

    private String professionId;

    private String professionName;

    private String nickName;

    private String headPic;

    private int inSchoolYear;

    private long integral;

    private String auditFlag;
    
    private String signName;

    @ApiModelProperty(value = "调用业务接口所需唯一标识", required = true)
    public String getSignId() {
        return signId;
    }

    @ApiModelProperty(value = "调用业务接口所需唯一标识", required = true)
    public void setSignId(String signId) {
        this.signId = signId;
    }

    @ApiModelProperty(value = "用户唯一标识")
    public String getUserId() {
        return userId;
    }

    @ApiModelProperty(value = "用户唯一标识")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ApiModelProperty(value = "登录号")
    public String getUserAccount() {
        return userAccount;
    }

    @ApiModelProperty(value = "登录号")
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @ApiModelProperty(value = "学校唯一标识")
    public String getSchoolId() {
        return schoolId;
    }

    @ApiModelProperty(value = "学校唯一标识")
    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @ApiModelProperty(value = "学校名称")
    public String getSchoolName() {
        return schoolName;
    }

    @ApiModelProperty(value = "学校名称")
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @ApiModelProperty(value = "院系唯一标识")
    public String getCollegeId() {
        return collegeId;
    }

    @ApiModelProperty(value = "院系唯一标识")
    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    @ApiModelProperty(value = "院系名称")
    public String getCollegeName() {
        return collegeName;
    }

    @ApiModelProperty(value = "院系名称")
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @ApiModelProperty(value = "专业唯一标识")
    public String getProfessionId() {
        return professionId;
    }

    @ApiModelProperty(value = "专业唯一标识")
    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    @ApiModelProperty(value = "专业名称")
    public String getProfessionName() {
        return professionName;
    }

    @ApiModelProperty(value = "专业名称")
    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    @ApiModelProperty(value = "用户昵称")
    public String getNickName() {
        return nickName;
    }

    @ApiModelProperty(value = "用户昵称")
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @ApiModelProperty(value = "头像")
    public String getHeadPic() {
        return headPic;
    }

    @ApiModelProperty(value = "头像")
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    @ApiModelProperty(value = "入学年份")
    public int getInSchoolYear() {
        return inSchoolYear;
    }

    @ApiModelProperty(value = "入学年份")
    public void setInSchoolYear(int inSchoolYear) {
        this.inSchoolYear = inSchoolYear;
    }

    @ApiModelProperty(value = "积分")
    public long getIntegral() {
        return integral;
    }

    @ApiModelProperty(value = "积分")
    public void setIntegral(long integral) {
        this.integral = integral;
    }

    @ApiModelProperty(value = "是否审过帖标识,0:未审过 1:已审过")
    public String getAuditFlag() {
        return auditFlag;
    }

    @ApiModelProperty(value = "是否审过帖标识,0:未审过 1:已审过")
    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }

    @ApiModelProperty(value = "签名")
	public String getSignName() {
		return signName;
	}

    @ApiModelProperty(value = "签名")
	public void setSignName(String signName) {
		this.signName = signName;
	}

}
