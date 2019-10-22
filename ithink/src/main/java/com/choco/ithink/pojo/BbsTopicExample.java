package com.choco.ithink.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BbsTopicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BbsTopicExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTopicIdIsNull() {
            addCriterion("topic_id is null");
            return (Criteria) this;
        }

        public Criteria andTopicIdIsNotNull() {
            addCriterion("topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andTopicIdEqualTo(Integer value) {
            addCriterion("topic_id =", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotEqualTo(Integer value) {
            addCriterion("topic_id <>", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThan(Integer value) {
            addCriterion("topic_id >", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_id >=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThan(Integer value) {
            addCriterion("topic_id <", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThanOrEqualTo(Integer value) {
            addCriterion("topic_id <=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdIn(List<Integer> values) {
            addCriterion("topic_id in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotIn(List<Integer> values) {
            addCriterion("topic_id not in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdBetween(Integer value1, Integer value2) {
            addCriterion("topic_id between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_id not between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andTopicTitleIsNull() {
            addCriterion("topic_title is null");
            return (Criteria) this;
        }

        public Criteria andTopicTitleIsNotNull() {
            addCriterion("topic_title is not null");
            return (Criteria) this;
        }

        public Criteria andTopicTitleEqualTo(String value) {
            addCriterion("topic_title =", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotEqualTo(String value) {
            addCriterion("topic_title <>", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleGreaterThan(String value) {
            addCriterion("topic_title >", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleGreaterThanOrEqualTo(String value) {
            addCriterion("topic_title >=", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleLessThan(String value) {
            addCriterion("topic_title <", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleLessThanOrEqualTo(String value) {
            addCriterion("topic_title <=", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleLike(String value) {
            addCriterion("topic_title like", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotLike(String value) {
            addCriterion("topic_title not like", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleIn(List<String> values) {
            addCriterion("topic_title in", values, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotIn(List<String> values) {
            addCriterion("topic_title not in", values, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleBetween(String value1, String value2) {
            addCriterion("topic_title between", value1, value2, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotBetween(String value1, String value2) {
            addCriterion("topic_title not between", value1, value2, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleIsNull() {
            addCriterion("topic_creativeCapsule is null");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleIsNotNull() {
            addCriterion("topic_creativeCapsule is not null");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleEqualTo(Integer value) {
            addCriterion("topic_creativeCapsule =", value, "topicCreativecapsule");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleNotEqualTo(Integer value) {
            addCriterion("topic_creativeCapsule <>", value, "topicCreativecapsule");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleGreaterThan(Integer value) {
            addCriterion("topic_creativeCapsule >", value, "topicCreativecapsule");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_creativeCapsule >=", value, "topicCreativecapsule");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleLessThan(Integer value) {
            addCriterion("topic_creativeCapsule <", value, "topicCreativecapsule");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleLessThanOrEqualTo(Integer value) {
            addCriterion("topic_creativeCapsule <=", value, "topicCreativecapsule");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleIn(List<Integer> values) {
            addCriterion("topic_creativeCapsule in", values, "topicCreativecapsule");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleNotIn(List<Integer> values) {
            addCriterion("topic_creativeCapsule not in", values, "topicCreativecapsule");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleBetween(Integer value1, Integer value2) {
            addCriterion("topic_creativeCapsule between", value1, value2, "topicCreativecapsule");
            return (Criteria) this;
        }

        public Criteria andTopicCreativecapsuleNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_creativeCapsule not between", value1, value2, "topicCreativecapsule");
            return (Criteria) this;
        }

        public Criteria andTopicContentIsNull() {
            addCriterion("topic_content is null");
            return (Criteria) this;
        }

        public Criteria andTopicContentIsNotNull() {
            addCriterion("topic_content is not null");
            return (Criteria) this;
        }

        public Criteria andTopicContentEqualTo(String value) {
            addCriterion("topic_content =", value, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentNotEqualTo(String value) {
            addCriterion("topic_content <>", value, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentGreaterThan(String value) {
            addCriterion("topic_content >", value, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentGreaterThanOrEqualTo(String value) {
            addCriterion("topic_content >=", value, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentLessThan(String value) {
            addCriterion("topic_content <", value, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentLessThanOrEqualTo(String value) {
            addCriterion("topic_content <=", value, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentLike(String value) {
            addCriterion("topic_content like", value, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentNotLike(String value) {
            addCriterion("topic_content not like", value, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentIn(List<String> values) {
            addCriterion("topic_content in", values, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentNotIn(List<String> values) {
            addCriterion("topic_content not in", values, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentBetween(String value1, String value2) {
            addCriterion("topic_content between", value1, value2, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicContentNotBetween(String value1, String value2) {
            addCriterion("topic_content not between", value1, value2, "topicContent");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeIsNull() {
            addCriterion("topic_buildTime is null");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeIsNotNull() {
            addCriterion("topic_buildTime is not null");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeEqualTo(Date value) {
            addCriterion("topic_buildTime =", value, "topicBuildtime");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeNotEqualTo(Date value) {
            addCriterion("topic_buildTime <>", value, "topicBuildtime");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeGreaterThan(Date value) {
            addCriterion("topic_buildTime >", value, "topicBuildtime");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("topic_buildTime >=", value, "topicBuildtime");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeLessThan(Date value) {
            addCriterion("topic_buildTime <", value, "topicBuildtime");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeLessThanOrEqualTo(Date value) {
            addCriterion("topic_buildTime <=", value, "topicBuildtime");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeIn(List<Date> values) {
            addCriterion("topic_buildTime in", values, "topicBuildtime");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeNotIn(List<Date> values) {
            addCriterion("topic_buildTime not in", values, "topicBuildtime");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeBetween(Date value1, Date value2) {
            addCriterion("topic_buildTime between", value1, value2, "topicBuildtime");
            return (Criteria) this;
        }

        public Criteria andTopicBuildtimeNotBetween(Date value1, Date value2) {
            addCriterion("topic_buildTime not between", value1, value2, "topicBuildtime");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumIsNull() {
            addCriterion("topic_achievementNum is null");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumIsNotNull() {
            addCriterion("topic_achievementNum is not null");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumEqualTo(Integer value) {
            addCriterion("topic_achievementNum =", value, "topicAchievementnum");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumNotEqualTo(Integer value) {
            addCriterion("topic_achievementNum <>", value, "topicAchievementnum");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumGreaterThan(Integer value) {
            addCriterion("topic_achievementNum >", value, "topicAchievementnum");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_achievementNum >=", value, "topicAchievementnum");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumLessThan(Integer value) {
            addCriterion("topic_achievementNum <", value, "topicAchievementnum");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumLessThanOrEqualTo(Integer value) {
            addCriterion("topic_achievementNum <=", value, "topicAchievementnum");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumIn(List<Integer> values) {
            addCriterion("topic_achievementNum in", values, "topicAchievementnum");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumNotIn(List<Integer> values) {
            addCriterion("topic_achievementNum not in", values, "topicAchievementnum");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumBetween(Integer value1, Integer value2) {
            addCriterion("topic_achievementNum between", value1, value2, "topicAchievementnum");
            return (Criteria) this;
        }

        public Criteria andTopicAchievementnumNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_achievementNum not between", value1, value2, "topicAchievementnum");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumIsNull() {
            addCriterion("topic_collectionNum is null");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumIsNotNull() {
            addCriterion("topic_collectionNum is not null");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumEqualTo(Integer value) {
            addCriterion("topic_collectionNum =", value, "topicCollectionnum");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumNotEqualTo(Integer value) {
            addCriterion("topic_collectionNum <>", value, "topicCollectionnum");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumGreaterThan(Integer value) {
            addCriterion("topic_collectionNum >", value, "topicCollectionnum");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_collectionNum >=", value, "topicCollectionnum");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumLessThan(Integer value) {
            addCriterion("topic_collectionNum <", value, "topicCollectionnum");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumLessThanOrEqualTo(Integer value) {
            addCriterion("topic_collectionNum <=", value, "topicCollectionnum");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumIn(List<Integer> values) {
            addCriterion("topic_collectionNum in", values, "topicCollectionnum");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumNotIn(List<Integer> values) {
            addCriterion("topic_collectionNum not in", values, "topicCollectionnum");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumBetween(Integer value1, Integer value2) {
            addCriterion("topic_collectionNum between", value1, value2, "topicCollectionnum");
            return (Criteria) this;
        }

        public Criteria andTopicCollectionnumNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_collectionNum not between", value1, value2, "topicCollectionnum");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}