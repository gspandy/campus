package org.campus.model;

import java.util.Date;

public class Transfer {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_transfer.UID
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    private String uid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_transfer.UserId
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_transfer.PostId
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    private String postid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ts_app_transfer.TransDate
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    private Date transdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_transfer.UID
     *
     * @return the value of ts_app_transfer.UID
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_transfer.UID
     *
     * @param uid the value for ts_app_transfer.UID
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_transfer.UserId
     *
     * @return the value of ts_app_transfer.UserId
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_transfer.UserId
     *
     * @param userid the value for ts_app_transfer.UserId
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_transfer.PostId
     *
     * @return the value of ts_app_transfer.PostId
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    public String getPostid() {
        return postid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_transfer.PostId
     *
     * @param postid the value for ts_app_transfer.PostId
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    public void setPostid(String postid) {
        this.postid = postid == null ? null : postid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ts_app_transfer.TransDate
     *
     * @return the value of ts_app_transfer.TransDate
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    public Date getTransdate() {
        return transdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ts_app_transfer.TransDate
     *
     * @param transdate the value for ts_app_transfer.TransDate
     *
     * @mbggenerated Wed Aug 26 00:06:18 CST 2015
     */
    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }
}