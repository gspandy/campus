package org.campus.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Idmapping {
    private Long id;

    private String fileId;

    private String fdfsid;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFdfsid() {
        return fdfsid;
    }

    public void setFdfsid(String fdfsid) {
        this.fdfsid = fdfsid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}