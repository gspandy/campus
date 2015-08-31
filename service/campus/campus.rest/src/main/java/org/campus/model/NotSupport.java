package org.campus.model;

import java.util.Date;

import org.campus.model.enums.ActiveType;
import org.campus.model.enums.TypeCode;

public class NotSupport {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_notsupport.UID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String uid;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_notsupport.SourceUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String sourceuid;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_notsupport.UserUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String useruid;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_notsupport.UserNickName
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String usernickname;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_notsupport.TypeCode
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private TypeCode typecode;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_notsupport.isActive
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private ActiveType isactive;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_notsupport.CreateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String createby;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_notsupport.CreateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private Date createdate;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_notsupport.LastUpdateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private String lastupdateby;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * ts_app_notsupport.LastUpdateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    private Date lastupdatedate;

    private String srcPostId;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_notsupport.UID
     *
     * @return the value of ts_app_notsupport.UID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_notsupport.UID
     *
     * @param uid the value for ts_app_notsupport.UID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_notsupport.SourceUID
     *
     * @return the value of ts_app_notsupport.SourceUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getSourceuid() {
        return sourceuid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_notsupport.SourceUID
     *
     * @param sourceuid the value for ts_app_notsupport.SourceUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setSourceuid(String sourceuid) {
        this.sourceuid = sourceuid == null ? null : sourceuid.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_notsupport.UserUID
     *
     * @return the value of ts_app_notsupport.UserUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getUseruid() {
        return useruid;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_notsupport.UserUID
     *
     * @param useruid the value for ts_app_notsupport.UserUID
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setUseruid(String useruid) {
        this.useruid = useruid == null ? null : useruid.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_notsupport.UserNickName
     *
     * @return the value of ts_app_notsupport.UserNickName
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getUsernickname() {
        return usernickname;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_notsupport.UserNickName
     *
     * @param usernickname the value for ts_app_notsupport.UserNickName
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname == null ? null : usernickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_notsupport.TypeCode
     *
     * @return the value of ts_app_notsupport.TypeCode
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public TypeCode getTypecode() {
        return typecode;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_notsupport.TypeCode
     *
     * @param typecode the value for ts_app_notsupport.TypeCode
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setTypecode(TypeCode typecode) {
        this.typecode = typecode;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_notsupport.isActive
     *
     * @return the value of ts_app_notsupport.isActive
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public ActiveType getIsactive() {
        return isactive;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_notsupport.isActive
     *
     * @param isactive the value for ts_app_notsupport.isActive
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setIsactive(ActiveType isactive) {
        this.isactive = isactive;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_notsupport.CreateBy
     *
     * @return the value of ts_app_notsupport.CreateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getCreateby() {
        return createby;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_notsupport.CreateBy
     *
     * @param createby the value for ts_app_notsupport.CreateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_notsupport.CreateDate
     *
     * @return the value of ts_app_notsupport.CreateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_notsupport.CreateDate
     *
     * @param createdate the value for ts_app_notsupport.CreateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_notsupport.LastUpdateBy
     *
     * @return the value of ts_app_notsupport.LastUpdateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public String getLastupdateby() {
        return lastupdateby;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_notsupport.LastUpdateBy
     *
     * @param lastupdateby the value for ts_app_notsupport.LastUpdateBy
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public void setLastupdateby(String lastupdateby) {
        this.lastupdateby = lastupdateby == null ? null : lastupdateby.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column
     * ts_app_notsupport.LastUpdateDate
     *
     * @return the value of ts_app_notsupport.LastUpdateDate
     *
     * @mbggenerated Sun Aug 09 23:25:01 CST 2015
     */
    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * ts_app_notsupport.LastUpdateDate
     *
     * @param lastupdatedate the value for ts_app_notsupport.LastUpdateDate
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