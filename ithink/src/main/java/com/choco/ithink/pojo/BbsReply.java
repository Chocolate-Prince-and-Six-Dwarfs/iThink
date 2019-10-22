package com.choco.ithink.pojo;

import java.util.Date;

public class BbsReply {
    private Integer replyId;

    private Integer commentId;

    private String replyContent;

    private Date replyBuildtime;

    private Integer replyLikenum;

    private Integer fromUid;

    private Integer toUid;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Date getReplyBuildtime() {
        return replyBuildtime;
    }

    public void setReplyBuildtime(Date replyBuildtime) {
        this.replyBuildtime = replyBuildtime;
    }

    public Integer getReplyLikenum() {
        return replyLikenum;
    }

    public void setReplyLikenum(Integer replyLikenum) {
        this.replyLikenum = replyLikenum;
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