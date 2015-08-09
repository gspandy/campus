package org.campus.model;

import java.util.Date;

public class AttentionUser {

    private String uid;

    private String myuseruid;

    private String attenionuseruid;

    private Date attentiontime;

    private String createby;

    private Date createdate;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getMyuseruid() {
        return myuseruid;
    }

    public void setMyuseruid(String myuseruid) {
        this.myuseruid = myuseruid == null ? null : myuseruid.trim();
    }

    public String getAttenionuseruid() {
        return attenionuseruid;
    }

    public void setAttenionuseruid(String attenionuseruid) {
        this.attenionuseruid = attenionuseruid == null ? null : attenionuseruid.trim();
    }

    public Date getAttentiontime() {
        return attentiontime;
    }

    public void setAttentiontime(Date attentiontime) {
        this.attentiontime = attentiontime;
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

}