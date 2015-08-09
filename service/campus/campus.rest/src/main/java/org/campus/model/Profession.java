package org.campus.model;

import java.util.Date;

public class Profession {
    private String uid;

    private String schooluid;

    private String collegeuid;

    private String twocategoryuid;

    private String professioncode;

    private String professionname;

    private String namepinyin;

    private String namefirstletter;

    private Integer opendate;

    private Integer schoolinglenth;

    private String degreename;

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

    public String getCollegeuid() {
        return collegeuid;
    }

    public void setCollegeuid(String collegeuid) {
        this.collegeuid = collegeuid == null ? null : collegeuid.trim();
    }

    public String getTwocategoryuid() {
        return twocategoryuid;
    }

    public void setTwocategoryuid(String twocategoryuid) {
        this.twocategoryuid = twocategoryuid == null ? null : twocategoryuid.trim();
    }

    public String getProfessioncode() {
        return professioncode;
    }

    public void setProfessioncode(String professioncode) {
        this.professioncode = professioncode == null ? null : professioncode.trim();
    }

    public String getProfessionname() {
        return professionname;
    }

    public void setProfessionname(String professionname) {
        this.professionname = professionname == null ? null : professionname.trim();
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

    public Integer getOpendate() {
        return opendate;
    }

    public void setOpendate(Integer opendate) {
        this.opendate = opendate;
    }

    public Integer getSchoolinglenth() {
        return schoolinglenth;
    }

    public void setSchoolinglenth(Integer schoolinglenth) {
        this.schoolinglenth = schoolinglenth;
    }

    public String getDegreename() {
        return degreename;
    }

    public void setDegreename(String degreename) {
        this.degreename = degreename == null ? null : degreename.trim();
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