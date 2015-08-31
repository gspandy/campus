package org.campus.model;

import java.util.Date;

import org.campus.model.enums.ActiveType;
import org.campus.model.enums.TypeCode;

public class Support {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column ts_app_support.UID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String uid;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_support.SourceUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String sourceuid;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_support.SupportUserUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String supportuseruid;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_support.UserNickName
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String usernickname;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_support.TypeCode
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private TypeCode typecode;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_support.isActive
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private ActiveType isactive;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_support.CreateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String createby;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_support.CreateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private Date createdate;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_support.LastUpdateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String lastupdateby;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_support.LastUpdateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private Date lastupdatedate;

    private String srcPostId;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_support.UID
     *
     * @return the value of ts_app_support.UID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_support.UID
     *
     * @param uid the value for ts_app_support.UID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_support.SourceUID
     *
     * @return the value of ts_app_support.SourceUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getSourceuid() {
        return sourceuid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_support.SourceUID
     *
     * @param sourceuid the value for ts_app_support.SourceUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setSourceuid(String sourceuid) {
        this.sourceuid = sourceuid == null ? null : sourceuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_support.SupportUserUID
     *
     * @return the value of ts_app_support.SupportUserUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getSupportuseruid() {
        return supportuseruid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_support.SupportUserUID
     *
     * @param supportuseruid the value for ts_app_support.SupportUserUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setSupportuseruid(String supportuseruid) {
        this.supportuseruid = supportuseruid == null ? null : supportuseruid.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_support.UserNickName
     *
     * @return the value of ts_app_support.UserNickName
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getUsernickname() {
        return usernickname;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_support.UserNickName
     *
     * @param usernickname the value for ts_app_support.UserNickName
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname == null ? null : usernickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_support.TypeCode
     *
     * @return the value of ts_app_support.TypeCode
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public TypeCode getTypecode() {
        return typecode;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_support.TypeCode
     *
     * @param typecode the value for ts_app_support.TypeCode
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setTypecode(TypeCode typecode) {
        this.typecode = typecode;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_support.isActive
     *
     * @return the value of ts_app_support.isActive
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public ActiveType getIsactive() {
        return isactive;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_support.isActive
     *
     * @param isactive the value for ts_app_support.isActive
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setIsactive(ActiveType isactive) {
        this.isactive = isactive;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_support.CreateBy
     *
     * @return the value of ts_app_support.CreateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getCreateby() {
        return createby;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_support.CreateBy
     *
     * @param createby the value for ts_app_support.CreateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_support.CreateDate
     *
     * @return the value of ts_app_support.CreateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_support.CreateDate
     *
     * @param createdate the value for ts_app_support.CreateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_support.LastUpdateBy
     *
     * @return the value of ts_app_support.LastUpdateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getLastupdateby() {
        return lastupdateby;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_support.LastUpdateBy
     *
     * @param lastupdateby the value for ts_app_support.LastUpdateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setLastupdateby(String lastupdateby) {
        this.lastupdateby = lastupdateby == null ? null : lastupdateby.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_support.LastUpdateDate
     *
     * @return the value of ts_app_support.LastUpdateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_support.LastUpdateDate
     *
     * @param lastupdatedate the value for ts_app_support.LastUpdateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public String getSrcPostId() {
        return srcPostId;
    }

    public void setSrcPostId(String srcPostId) {
        this.srcPostId = srcPostId;
    }

}