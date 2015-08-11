package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RegisterVO", description = "注册信息")
public class RegisterVO {

    private String loginName;

    private String password;

    private String secPassword;
    
    private String schoolId;
    
    private String schoolName;

    private String collegeId;
    
    private String collegeName;
    
    private String professionId;
    
    private String professionName;
    
    private int inSchoolYear;

    private String cityCode;
    
    private String cityName;
    
    private String provinceCode;
    
    private String provinceName;
    
    private String sex;
    
    @ApiModelProperty(value = "登录名", required = true)
    public String getLoginName() {
        return loginName;
    }

    @ApiModelProperty(value = "登录名", required = true)
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

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

    @ApiModelProperty(value = "入学年份")
	public int getInSchoolYear() {
		return inSchoolYear;
	}

    @ApiModelProperty(value = "入学年份")
	public void setInSchoolYear(int inSchoolYear) {
		this.inSchoolYear = inSchoolYear;
	}
    
    @ApiModelProperty(value = "城市编码")
	public String getCityCode() {
		return cityCode;
	}

    @ApiModelProperty(value = "城市编码")
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

    @ApiModelProperty(value = "城市名称")
	public String getCityName() {
		return cityName;
	}

    @ApiModelProperty(value = "城市名称")
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

    @ApiModelProperty(value = "省份编码")
	public String getProvinceCode() {
		return provinceCode;
	}

    @ApiModelProperty(value = "省份编码")
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

    @ApiModelProperty(value = "省份名称")
	public String getProvinceName() {
		return provinceName;
	}

    @ApiModelProperty(value = "省份名称")
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

    @ApiModelProperty(value = "性别")
	public String getSex() {
		return sex;
	}

    @ApiModelProperty(value = "性别")
	public void setSex(String sex) {
		this.sex = sex;
	}

    
}
