package com.choco.ithink.pojo;

import io.searchbox.annotations.JestId;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = BbsTopic.INDEX, type = BbsTopic.TYPE)
public class BbsTopic implements Serializable {
    //建立索引 必须小写
    public static final String INDEX = "bbs_topic-index";

    //类型
    public static final String TYPE = "bbs_topic-type";

    @JestId
    private Integer topicId;

    private Integer userId;

    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String topicTitle;

    private Integer topicCreativecapsule;

    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String topicContent;

    private Date topicBuildtime;

    private Integer topicAchievementnum;

    private Integer topicCollectionnum;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle == null ? null : topicTitle.trim();
    }

    public Integer getTopicCreativecapsule() {
        return topicCreativecapsule;
    }

    public void setTopicCreativecapsule(Integer topicCreativecapsule) {
        this.topicCreativecapsule = topicCreativecapsule;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent == null ? null : topicContent.trim();
    }

    public Date getTopicBuildtime() {
        return topicBuildtime;
    }

    public void setTopicBuildtime(Date topicBuildtime) {
        this.topicBuildtime = topicBuildtime;
    }

    public Integer getTopicAchievementnum() {
        return topicAchievementnum;
    }

    public void setTopicAchievementnum(Integer topicAchievementnum) {
        this.topicAchievementnum = topicAchievementnum;
    }

    public Integer getTopicCollectionnum() {
        return topicCollectionnum;
    }

    public void setTopicCollectionnum(Integer topicCollectionnum) {
        this.topicCollectionnum = topicCollectionnum;
    }
}