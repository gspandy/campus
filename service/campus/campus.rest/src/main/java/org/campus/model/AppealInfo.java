package org.campus.model;

public class AppealInfo {

    private String appealuid;

    private String schooluid;

    private String collegeuid;

    private String professionuid;

    private String professionname;

    private Integer inschoolyear;

    private String sextype;

    public String getAppealuid() {
        return appealuid;
    }

    public void setAppealuid(String appealuid) {
        this.appealuid = appealuid == null ? null : appealuid.trim();
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

    public String getProfessionuid() {
        return professionuid;
    }

    public void setProfessionuid(String professionuid) {
        this.professionuid = professionuid == null ? null : professionuid.trim();
    }

    public String getProfessionname() {
        return professionname;
    }

    public void setProfessionname(String professionname) {
        this.professionname = professionname == null ? null : professionname.trim();
    }

    public Integer getInschoolyear() {
        return inschoolyear;
    }

    public void setInschoolyear(Integer inschoolyear) {
        this.inschoolyear = inschoolyear;
    }

    public String getSextype() {
        return sextype;
    }

    public void setSextype(String sextype) {
        this.sextype = sextype == null ? null : sextype.trim();
    }

}