package com.choco.ithink.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BbsAchievementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BbsAchievementExample() {
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

        public Criteria andAchievementIdIsNull() {
            addCriterion("achievement_id is null");
            return (Criteria) this;
        }

        public Criteria andAchievementIdIsNotNull() {
            addCriterion("achievement_id is not null");
            return (Criteria) this;
        }

        public Criteria andAchievementIdEqualTo(Integer value) {
            addCriterion("achievement_id =", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdNotEqualTo(Integer value) {
            addCriterion("achievement_id <>", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdGreaterThan(Integer value) {
            addCriterion("achievement_id >", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("achievement_id >=", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdLessThan(Integer value) {
            addCriterion("achievement_id <", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdLessThanOrEqualTo(Integer value) {
            addCriterion("achievement_id <=", value, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdIn(List<Integer> values) {
            addCriterion("achievement_id in", values, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdNotIn(List<Integer> values) {
            addCriterion("achievement_id not in", values, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdBetween(Integer value1, Integer value2) {
            addCriterion("achievement_id between", value1, value2, "achievementId");
            return (Criteria) this;
        }

        public Criteria andAchievementIdNotBetween(Integer value1, Integer value2) {
            addCriterion("achievement_id not between", value1, value2, "achievementId");
            return (Criteria) this;
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

        public Criteria andAchievementContentIsNull() {
            addCriterion("achievement_content is null");
            return (Criteria) this;
        }

        public Criteria andAchievementContentIsNotNull() {
            addCriterion("achievement_content is not null");
            return (Criteria) this;
        }

        public Criteria andAchievementContentEqualTo(String value) {
            addCriterion("achievement_content =", value, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentNotEqualTo(String value) {
            addCriterion("achievement_content <>", value, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentGreaterThan(String value) {
            addCriterion("achievement_content >", value, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentGreaterThanOrEqualTo(String value) {
            addCriterion("achievement_content >=", value, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentLessThan(String value) {
            addCriterion("achievement_content <", value, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentLessThanOrEqualTo(String value) {
            addCriterion("achievement_content <=", value, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentLike(String value) {
            addCriterion("achievement_content like", value, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentNotLike(String value) {
            addCriterion("achievement_content not like", value, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentIn(List<String> values) {
            addCriterion("achievement_content in", values, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentNotIn(List<String> values) {
            addCriterion("achievement_content not in", values, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentBetween(String value1, String value2) {
            addCriterion("achievement_content between", value1, value2, "achievementContent");
            return (Criteria) this;
        }

        public Criteria andAchievementContentNotBetween(String value1, String value2) {
            addCriterion("achievement_content not between", value1, value2, "achievementContent");
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

        public Criteria andAchievementLikenumIsNull() {
            addCriterion("achievement_likeNum is null");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumIsNotNull() {
            addCriterion("achievement_likeNum is not null");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumEqualTo(Integer value) {
            addCriterion("achievement_likeNum =", value, "achievementLikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumNotEqualTo(Integer value) {
            addCriterion("achievement_likeNum <>", value, "achievementLikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumGreaterThan(Integer value) {
            addCriterion("achievement_likeNum >", value, "achievementLikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("achievement_likeNum >=", value, "achievementLikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumLessThan(Integer value) {
            addCriterion("achievement_likeNum <", value, "achievementLikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumLessThanOrEqualTo(Integer value) {
            addCriterion("achievement_likeNum <=", value, "achievementLikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumIn(List<Integer> values) {
            addCriterion("achievement_likeNum in", values, "achievementLikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumNotIn(List<Integer> values) {
            addCriterion("achievement_likeNum not in", values, "achievementLikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumBetween(Integer value1, Integer value2) {
            addCriterion("achievement_likeNum between", value1, value2, "achievementLikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementLikenumNotBetween(Integer value1, Integer value2) {
            addCriterion("achievement_likeNum not between", value1, value2, "achievementLikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumIsNull() {
            addCriterion("achievement_unlikeNum is null");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumIsNotNull() {
            addCriterion("achievement_unlikeNum is not null");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumEqualTo(Integer value) {
            addCriterion("achievement_unlikeNum =", value, "achievementUnlikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumNotEqualTo(Integer value) {
            addCriterion("achievement_unlikeNum <>", value, "achievementUnlikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumGreaterThan(Integer value) {
            addCriterion("achievement_unlikeNum >", value, "achievementUnlikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("achievement_unlikeNum >=", value, "achievementUnlikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumLessThan(Integer value) {
            addCriterion("achievement_unlikeNum <", value, "achievementUnlikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumLessThanOrEqualTo(Integer value) {
            addCriterion("achievement_unlikeNum <=", value, "achievementUnlikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumIn(List<Integer> values) {
            addCriterion("achievement_unlikeNum in", values, "achievementUnlikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumNotIn(List<Integer> values) {
            addCriterion("achievement_unlikeNum not in", values, "achievementUnlikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumBetween(Integer value1, Integer value2) {
            addCriterion("achievement_unlikeNum between", value1, value2, "achievementUnlikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementUnlikenumNotBetween(Integer value1, Integer value2) {
            addCriterion("achievement_unlikeNum not between", value1, value2, "achievementUnlikenum");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeIsNull() {
            addCriterion("achievement_bulidTime is null");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeIsNotNull() {
            addCriterion("achievement_bulidTime is not null");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeEqualTo(Date value) {
            addCriterion("achievement_bulidTime =", value, "achievementBulidtime");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeNotEqualTo(Date value) {
            addCriterion("achievement_bulidTime <>", value, "achievementBulidtime");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeGreaterThan(Date value) {
            addCriterion("achievement_bulidTime >", value, "achievementBulidtime");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("achievement_bulidTime >=", value, "achievementBulidtime");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeLessThan(Date value) {
            addCriterion("achievement_bulidTime <", value, "achievementBulidtime");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeLessThanOrEqualTo(Date value) {
            addCriterion("achievement_bulidTime <=", value, "achievementBulidtime");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeIn(List<Date> values) {
            addCriterion("achievement_bulidTime in", values, "achievementBulidtime");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeNotIn(List<Date> values) {
            addCriterion("achievement_bulidTime not in", values, "achievementBulidtime");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeBetween(Date value1, Date value2) {
            addCriterion("achievement_bulidTime between", value1, value2, "achievementBulidtime");
            return (Criteria) this;
        }

        public Criteria andAchievementBulidtimeNotBetween(Date value1, Date value2) {
            addCriterion("achievement_bulidTime not between", value1, value2, "achievementBulidtime");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumIsNull() {
            addCriterion("achievement_commentNum is null");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumIsNotNull() {
            addCriterion("achievement_commentNum is not null");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumEqualTo(Integer value) {
            addCriterion("achievement_commentNum =", value, "achievementCommentnum");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumNotEqualTo(Integer value) {
            addCriterion("achievement_commentNum <>", value, "achievementCommentnum");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumGreaterThan(Integer value) {
            addCriterion("achievement_commentNum >", value, "achievementCommentnum");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("achievement_commentNum >=", value, "achievementCommentnum");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumLessThan(Integer value) {
            addCriterion("achievement_commentNum <", value, "achievementCommentnum");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumLessThanOrEqualTo(Integer value) {
            addCriterion("achievement_commentNum <=", value, "achievementCommentnum");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumIn(List<Integer> values) {
            addCriterion("achievement_commentNum in", values, "achievementCommentnum");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumNotIn(List<Integer> values) {
            addCriterion("achievement_commentNum not in", values, "achievementCommentnum");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumBetween(Integer value1, Integer value2) {
            addCriterion("achievement_commentNum between", value1, value2, "achievementCommentnum");
            return (Criteria) this;
        }

        public Criteria andAchievementCommentnumNotBetween(Integer value1, Integer value2) {
            addCriterion("achievement_commentNum not between", value1, value2, "achievementCommentnum");
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