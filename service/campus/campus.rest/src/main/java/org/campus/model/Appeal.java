package org.campus.model;

import java.util.Date;

public class Appeal {

    private String uid;

    private String appealuseruid;

    private Integer manageoperate;

    private String operateuseruid;

    private Date operatetime;

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

    public String getAppealuseruid() {
        return appealuseruid;
    }

    public void setAppealuseruid(String appealuseruid) {
        this.appealuseruid = appealuseruid == null ? null : appealuseruid.trim();
    }

    public Integer getManageoperate() {
        return manageoperate;
    }

    public void setManageoperate(Integer manageoperate) {
        this.manageoperate = manageoperate;
    }

    public String getOperateuseruid() {
        return operateuseruid;
    }

    public void setOperateuseruid(String operateuseruid) {
        this.operateuseruid = operateuseruid == null ? null : operateuseruid.trim();
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
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