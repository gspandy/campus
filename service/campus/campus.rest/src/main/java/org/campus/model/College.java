package org.campus.model;

import java.util.Date;

public class College {
    private String uid;

    private String schooluid;

    private String collegename;

    private String namepinyin;

    private String namefirstletter;

    private Integer orderby;

    private String versionnumber;

    private Integer isactive;

    private String createby;

    private Date createdate;

    private String lastupdateby;

    private Date lastupdatedate;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getSchooluid() {
        return schooluid;
    }

    public void setSchooluid(String schooluid) {
        this.schooluid = schooluid == null ? null : schooluid.trim();
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename == null ? null : collegename.trim();
    }

    public String getNamepinyin() {
        return namepinyin;
    }

    public void setNamepinyin(String namepinyin) {
        this.namepinyin = namepinyin == null ? null : namepinyin.trim();
    }

    public String getNamefirstletter() {
        return namefirstletter;
    }

    public void setNamefirstletter(String namefirstletter) {
        this.namefirstletter = namefirstletter == null ? null : namefirstletter.trim();
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public String getVersionnumber() {
        return versionnumber;
    }

    public void setVersionnumber(String versionnumber) {
        this.versionnumber = versionnumber == null ? null : versionnumber.trim();
    }

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getLastupdateby() {
        return lastupdateby;
    }

    public void setLastupdateby(String lastupdateby) {
        this.lastupdateby = lastupdateby == null ? null : lastupdateby.trim();
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }
}