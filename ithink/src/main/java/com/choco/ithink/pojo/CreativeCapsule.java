package com.choco.ithink.pojo;

import java.util.Date;

public class CreativeCapsule {
    private Integer creativeId;

    private String creativeName;

    private Integer userId;

    private String creativeContent;

    private Date creativeBuildtime;

    private Date creativeUploadtime;

    public Integer getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(Integer creativeId) {
        this.creativeId = creativeId;
    }

    public String getCreativeName() {
        return creativeName;
    }

    public void setCreativeName(String creativeName) {
        this.creativeName = creativeName == null ? null : creativeName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreativeContent() {
        return creativeContent;
    }

    public void setCreativeContent(String creativeContent) {
        this.creativeContent = creativeContent == null ? null : creativeContent.trim();
    }

    public Date getCreativeBuildtime() {
        return creativeBuildtime;
    }

    public void setCreativeBuildtime(Date creativeBuildtime) {
        this.creativeBuildtime = creativeBuildtime;
    }

    public Date getCreativeUploadtime() {
        return creativeUploadtime;
    }

    public void setCreativeUploadtime(Date creativeUploadtime) {
        this.creativeUploadtime = creativeUploadtime;
    }
}