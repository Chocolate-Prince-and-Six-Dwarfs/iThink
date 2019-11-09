package com.choco.ithink.pojo;

import java.util.Date;

public class UpdateTime {
    private Integer userId;

    private Date time;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}