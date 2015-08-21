package org.campus.api.domain;

import java.io.Serializable;

public class WeiboAccessToken implements Serializable {

    private static final long serialVersionUID = -7394158006031309443L;

    private String access_token;

    /**
     * 有效时间（秒）
     */
    private String expires_in;

    private String remind_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRemind_in() {
        return remind_in;
    }

    public void setRemind_in(String remind_in) {
        this.remind_in = remind_in;
    }

}
