package org.campus.model;

import java.util.Date;

public class SendMessage {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_sendmessage.UID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_sendmessage.SendUserUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String senduseruid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_sendmessage.GroupUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String groupuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_sendmessage.MsgContent
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String msgcontent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_sendmessage.PicturePath
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String picturepath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_sendmessage.SoundPath
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String soundpath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_sendmessage.SendTime
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private Date sendtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_sendmessage.CreateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String createby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_sendmessage.CreateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private Date createdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_sendmessage.UID
     *
     * @return the value of ts_app_sendmessage.UID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_sendmessage.UID
     *
     * @param uid the value for ts_app_sendmessage.UID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_sendmessage.SendUserUID
     *
     * @return the value of ts_app_sendmessage.SendUserUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getSenduseruid() {
        return senduseruid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_sendmessage.SendUserUID
     *
     * @param senduseruid the value for ts_app_sendmessage.SendUserUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setSenduseruid(String senduseruid) {
        this.senduseruid = senduseruid == null ? null : senduseruid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_sendmessage.GroupUID
     *
     * @return the value of ts_app_sendmessage.GroupUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getGroupuid() {
        return groupuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_sendmessage.GroupUID
     *
     * @param groupuid the value for ts_app_sendmessage.GroupUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setGroupuid(String groupuid) {
        this.groupuid = groupuid == null ? null : groupuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_sendmessage.MsgContent
     *
     * @return the value of ts_app_sendmessage.MsgContent
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getMsgcontent() {
        return msgcontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_sendmessage.MsgContent
     *
     * @param msgcontent the value for ts_app_sendmessage.MsgContent
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent == null ? null : msgcontent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_sendmessage.PicturePath
     *
     * @return the value of ts_app_sendmessage.PicturePath
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getPicturepath() {
        return picturepath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_sendmessage.PicturePath
     *
     * @param picturepath the value for ts_app_sendmessage.PicturePath
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setPicturepath(String picturepath) {
        this.picturepath = picturepath == null ? null : picturepath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_sendmessage.SoundPath
     *
     * @return the value of ts_app_sendmessage.SoundPath
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getSoundpath() {
        return soundpath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_sendmessage.SoundPath
     *
     * @param soundpath the value for ts_app_sendmessage.SoundPath
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setSoundpath(String soundpath) {
        this.soundpath = soundpath == null ? null : soundpath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_sendmessage.SendTime
     *
     * @return the value of ts_app_sendmessage.SendTime
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public Date getSendtime() {
        return sendtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_sendmessage.SendTime
     *
     * @param sendtime the value for ts_app_sendmessage.SendTime
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_sendmessage.CreateBy
     *
     * @return the value of ts_app_sendmessage.CreateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getCreateby() {
        return createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_sendmessage.CreateBy
     *
     * @param createby the value for ts_app_sendmessage.CreateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_sendmessage.CreateDate
     *
     * @return the value of ts_app_sendmessage.CreateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_sendmessage.CreateDate
     *
     * @param createdate the value for ts_app_sendmessage.CreateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}