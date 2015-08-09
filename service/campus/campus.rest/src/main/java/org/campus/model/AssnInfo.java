package org.campus.model;

import java.util.Date;

public class AssnInfo {

    private String useruid;

    private String assnname;

    private String assnlogo;

    private String brief;

    private String schooluid;

    private String schoolname;

    private Integer peoplenum;

    private String contactperson;

    private String contactnumber;

    private String email;

    private Integer isactive;

    private String createby;

    private Date createdate;

    private String lastupdateby;

    private Date lastupdatedate;

    public String getUseruid() {
        return useruid;
    }

    public void setUseruid(String useruid) {
        this.useruid = useruid == null ? null : useruid.trim();
    }

    public String getAssnname() {
        return assnname;
    }

    public void setAssnname(String assnname) {
        this.assnname = assnname == null ? null : assnname.trim();
    }

    public String getAssnlogo() {
        return assnlogo;
    }

    public void setAssnlogo(String assnlogo) {
        this.assnlogo = assnlogo == null ? null : assnlogo.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getSchooluid() {
        return schooluid;
    }

    public void setSchooluid(String schooluid) {
        this.schooluid = schooluid == null ? null : schooluid.trim();
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname == null ? null : schoolname.trim();
    }

    public Integer getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(Integer peoplenum) {
        this.peoplenum = peoplenum;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson == null ? null : contactperson.trim();
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber == null ? null : contactnumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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