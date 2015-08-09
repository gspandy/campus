package org.campus.model;

import java.math.BigDecimal;

public class BaseDataVersionNum {

    private String uid;

    private BigDecimal schoolversionnum;

    private BigDecimal collegeversionnum;

    private BigDecimal professionversionnum;

    private BigDecimal categoryversionnum;

    private BigDecimal twocategoryversionnum;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getSchoolversionnum() {
        return schoolversionnum;
    }

    public void setSchoolversionnum(BigDecimal schoolversionnum) {
        this.schoolversionnum = schoolversionnum;
    }

    public BigDecimal getCollegeversionnum() {
        return collegeversionnum;
    }

    public void setCollegeversionnum(BigDecimal collegeversionnum) {
        this.collegeversionnum = collegeversionnum;
    }

    public BigDecimal getProfessionversionnum() {
        return professionversionnum;
    }

    public void setProfessionversionnum(BigDecimal professionversionnum) {
        this.professionversionnum = professionversionnum;
    }

    public BigDecimal getCategoryversionnum() {
        return categoryversionnum;
    }

    public void setCategoryversionnum(BigDecimal categoryversionnum) {
        this.categoryversionnum = categoryversionnum;
    }

    public BigDecimal getTwocategoryversionnum() {
        return twocategoryversionnum;
    }

    public void setTwocategoryversionnum(BigDecimal twocategoryversionnum) {
        this.twocategoryversionnum = twocategoryversionnum;
    }

}