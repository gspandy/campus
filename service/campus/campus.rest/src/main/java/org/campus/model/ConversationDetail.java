package org.campus.model;

import java.util.Date;

public class ConversationDetail {

    private String uid;

    private String senduseruid;

    private String msgcontent;

    private String picturepath;

    private String soundpath;

    private Date sendtime;

    private boolean holdFlag;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSenduseruid() {
        return senduseruid;
    }

    public void setSenduseruid(String senduseruid) {
        this.senduseruid = senduseruid;
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public String getPicturepath() {
        return picturepath;
    }

    public void setPicturepath(String picturepath) {
        this.picturepath = picturepath;
    }

    public String getSoundpath() {
        return soundpath;
    }

    public void setSoundpath(String soundpath) {
        this.soundpath = soundpath;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public boolean isHoldFlag() {
        return holdFlag;
    }

    public void setHoldFlag(boolean holdFlag) {
        this.holdFlag = holdFlag;
    }
    
}
