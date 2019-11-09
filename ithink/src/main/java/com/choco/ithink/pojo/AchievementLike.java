package com.choco.ithink.pojo;

import java.util.Date;

public class AchievementLike extends AchievementLikeKey {
    private Boolean type;

    private Date time;

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}