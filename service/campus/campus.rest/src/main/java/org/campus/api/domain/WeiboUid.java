package org.campus.api.domain;

import java.io.Serializable;

public class WeiboUid implements Serializable {

    private static final long serialVersionUID = -8079794891564631221L;

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
