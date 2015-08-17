package org.campus.model;

import java.util.Date;

public class UserFavorite {
    private String uid;

    private String userid;

    private String postsid;

    private Date createtime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPostsid() {
        return postsid;
    }

    public void setPostsid(String postsid) {
        this.postsid = postsid == null ? null : postsid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}