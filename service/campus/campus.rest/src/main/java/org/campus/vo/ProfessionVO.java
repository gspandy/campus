package org.campus.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ProfessionVO", description = "专业信息")
public class ProfessionVO {

    private String professionId;

    private String professionName;

    @ApiModelProperty(value = "专业ID", required = true)
    public String getProfessionId() {
        return professionId;
    }

    @ApiModelProperty(value = "专业ID", required = true)
    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    @ApiModelProperty(value = "专业名称", required = true)
    public String getProfessionName() {
        return professionName;
    }

    @ApiModelProperty(value = "专业名称", required = true)
    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

}
