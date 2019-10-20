package com.choco.ithink.pojo;

import java.util.Date;

public class BbsComment {
    private Integer commentId;

    private Integer achievementId;

    private String commentContent;

    private Date commentBuildtime;

    private Integer commentLikenum;

    private Integer commentReplynum;

    private Integer fromUid;

    private Integer toUid;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Date getCommentBuildtime() {
        return commentBuildtime;
    }

    public void setCommentBuildtime(Date commentBuildtime) {
        this.commentBuildtime = commentBuildtime;
    }

    public Integer getCommentLikenum() {
        return commentLikenum;
    }

    public void setCommentLikenum(Integer commentLikenum) {
        this.commentLikenum = commentLikenum;
    }

    public Integer getCommentReplynum() {
        return commentReplynum;
    }

    public void setCommentReplynum(Integer commentReplynum) {
        this.commentReplynum = commentReplynum;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }
}