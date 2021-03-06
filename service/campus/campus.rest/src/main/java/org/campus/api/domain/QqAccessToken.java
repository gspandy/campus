package org.campus.api.domain;

import java.io.Serializable;

public class QqAccessToken implements Serializable {

    private static final long serialVersionUID = 7933972480952186708L;

    private String access_token;

    /**
     * 有效时间（秒）
     */
    private String expires_in;

    private String refresh_token;

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

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

}
