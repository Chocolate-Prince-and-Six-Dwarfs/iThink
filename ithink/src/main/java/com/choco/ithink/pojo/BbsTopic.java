package com.choco.ithink.pojo;

import org.springframework.data.annotation.Id;
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

    public static final String ID = "bbs_topic-id";

    @Id
    private Integer topicId;

    private Integer topicPublisher;

    //@Field(type = FieldType.Text, searchAnalyzer = "ik", analyzer = "ik")
    private String topicTitle;

    private Integer topicCreativecapsule;

    private String topicContent;

    private Date topicReleasetime;

    private Integer topicAchievementnum;

    private Integer topicCollectionnum;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getTopicPublisher() {
        return topicPublisher;
    }

    public void setTopicPublisher(Integer topicPublisher) {
        this.topicPublisher = topicPublisher;
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

    public Date getTopicReleasetime() {
        return topicReleasetime;
    }

    public void setTopicReleasetime(Date topicReleasetime) {
        this.topicReleasetime = topicReleasetime;
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