package com.choco.ithink.pojo;

import java.util.Date;

public class BbsGroup {
    private Integer groupId;

    private Integer topicId;

    private String groupName;

    private Date groupBuildtime;

    private Integer groupOwnerid;

    private Integer groupNumber;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Date getGroupBuildtime() {
        return groupBuildtime;
    }

    public void setGroupBuildtime(Date groupBuildtime) {
        this.groupBuildtime = groupBuildtime;
    }

    public Integer getGroupOwnerid() {
        return groupOwnerid;
    }

    public void setGroupOwnerid(Integer groupOwnerid) {
        this.groupOwnerid = groupOwnerid;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }
}