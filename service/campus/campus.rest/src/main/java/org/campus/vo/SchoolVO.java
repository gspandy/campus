package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SchoolVO", description = "学校信息")
public class SchoolVO {

    private String schoolId;

    private String schoolName;
    
    private String cityCode;
    
    private String cityName;
    
    private String provinceCode;
    
    private String provinceName;

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
}
