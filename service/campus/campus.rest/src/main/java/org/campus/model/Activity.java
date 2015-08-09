package org.campus.model;

import java.util.Date;

public class Activity {

    private String uid;

    private String schooluid;

    private String useruid;

    private String activitybrief;

    private String pictures;

    private String linkurl;

    private String address;

    private Date starttime;

    private Date endtime;

    private Integer orderby;

    private Integer ischeck;

    private String checkuseruid;

    private Date checktime;

    private String checkremark;

    private String selareacode;

    private String displaytabcode;

    private Integer partakenum;

    private Integer commentnum;

    private Integer supportnum;

    private Integer notsupportnum;

    private Integer complainnum;

    private Integer typecode;

    private String createby;

    private Date createdate;

    private Integer isactive;

    private String lastupdateby;

    private Date lastupdatedate;

    private String activitycontent;

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

    public String getUseruid() {
        return useruid;
    }

    public void setUseruid(String useruid) {
        this.useruid = useruid == null ? null : useruid.trim();
    }

    public String getActivitybrief() {
        return activitybrief;
    }

    public void setActivitybrief(String activitybrief) {
        this.activitybrief = activitybrief == null ? null : activitybrief.trim();
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures == null ? null : pictures.trim();
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl == null ? null : linkurl.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Integer getIscheck() {
        return ischeck;
    }

    public void setIscheck(Integer ischeck) {
        this.ischeck = ischeck;
    }

    public String getCheckuseruid() {
        return checkuseruid;
    }

    public void setCheckuseruid(String checkuseruid) {
        this.checkuseruid = checkuseruid == null ? null : checkuseruid.trim();
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }

    public String getCheckremark() {
        return checkremark;
    }

    public void setCheckremark(String checkremark) {
        this.checkremark = checkremark == null ? null : checkremark.trim();
    }

    public String getSelareacode() {
        return selareacode;
    }

    public void setSelareacode(String selareacode) {
        this.selareacode = selareacode == null ? null : selareacode.trim();
    }

    public String getDisplaytabcode() {
        return displaytabcode;
    }

    public void setDisplaytabcode(String displaytabcode) {
        this.displaytabcode = displaytabcode == null ? null : displaytabcode.trim();
    }

    public Integer getPartakenum() {
        return partakenum;
    }

    public void setPartakenum(Integer partakenum) {
        this.partakenum = partakenum;
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    public Integer getSupportnum() {
        return supportnum;
    }

    public void setSupportnum(Integer supportnum) {
        this.supportnum = supportnum;
    }

    public Integer getNotsupportnum() {
        return notsupportnum;
    }

    public void setNotsupportnum(Integer notsupportnum) {
        this.notsupportnum = notsupportnum;
    }

    public Integer getComplainnum() {
        return complainnum;
    }

    public void setComplainnum(Integer complainnum) {
        this.complainnum = complainnum;
    }

    public Integer getTypecode() {
        return typecode;
    }

    public void setTypecode(Integer typecode) {
        this.typecode = typecode;
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

    public Integer getIsactive() {
        return isactive;
    }

    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
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

    public String getActivitycontent() {
        return activitycontent;
    }

    public void setActivitycontent(String activitycontent) {
        this.activitycontent = activitycontent == null ? null : activitycontent.trim();
    }

}