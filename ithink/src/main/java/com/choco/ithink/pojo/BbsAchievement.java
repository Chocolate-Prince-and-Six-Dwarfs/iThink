package com.choco.ithink.pojo;

import java.util.Date;

public class BbsAchievement {
    private Integer achievementId;

    private Integer topicId;

    private String achievementContent;

    private Integer userId;

    private Integer achievementLikenum;

    private Integer achievementUnlikenum;

    private Date achievementBulidtime;

    private Integer achievementCommentnum;

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getAchievementContent() {
        return achievementContent;
    }

    public void setAchievementContent(String achievementContent) {
        this.achievementContent = achievementContent == null ? null : achievementContent.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAchievementLikenum() {
        return achievementLikenum;
    }

    public void setAchievementLikenum(Integer achievementLikenum) {
        this.achievementLikenum = achievementLikenum;
    }

    public Integer getAchievementUnlikenum() {
        return achievementUnlikenum;
    }

    public void setAchievementUnlikenum(Integer achievementUnlikenum) {
        this.achievementUnlikenum = achievementUnlikenum;
    }

    public Date getAchievementBulidtime() {
        return achievementBulidtime;
    }

    public void setAchievementBulidtime(Date achievementBulidtime) {
        this.achievementBulidtime = achievementBulidtime;
    }

    public Integer getAchievementCommentnum() {
        return achievementCommentnum;
    }

    public void setAchievementCommentnum(Integer achievementCommentnum) {
        this.achievementCommentnum = achievementCommentnum;
    }
}