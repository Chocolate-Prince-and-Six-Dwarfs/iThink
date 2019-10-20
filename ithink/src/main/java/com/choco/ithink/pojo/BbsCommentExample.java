package com.choco.ithink.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BbsCommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BbsCommentExample() {
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

        public Criteria andCommentIdIsNull() {
            addCriterion("comment_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNotNull() {
            addCriterion("comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentIdEqualTo(Integer value) {
            addCriterion("comment_id =", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotEqualTo(Integer value) {
            addCriterion("comment_id <>", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThan(Integer value) {
            addCriterion("comment_id >", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_id >=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThan(Integer value) {
            addCriterion("comment_id <", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThanOrEqualTo(Integer value) {
            addCriterion("comment_id <=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdIn(List<Integer> values) {
            addCriterion("comment_id in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotIn(List<Integer> values) {
            addCriterion("comment_id not in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdBetween(Integer value1, Integer value2) {
            addCriterion("comment_id between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_id not between", value1, value2, "commentId");
            return (Criteria) this;
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

        public Criteria andCommentContentIsNull() {
            addCriterion("comment_content is null");
            return (Criteria) this;
        }

        public Criteria andCommentContentIsNotNull() {
            addCriterion("comment_content is not null");
            return (Criteria) this;
        }

        public Criteria andCommentContentEqualTo(String value) {
            addCriterion("comment_content =", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotEqualTo(String value) {
            addCriterion("comment_content <>", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentGreaterThan(String value) {
            addCriterion("comment_content >", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentGreaterThanOrEqualTo(String value) {
            addCriterion("comment_content >=", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentLessThan(String value) {
            addCriterion("comment_content <", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentLessThanOrEqualTo(String value) {
            addCriterion("comment_content <=", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentLike(String value) {
            addCriterion("comment_content like", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotLike(String value) {
            addCriterion("comment_content not like", value, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentIn(List<String> values) {
            addCriterion("comment_content in", values, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotIn(List<String> values) {
            addCriterion("comment_content not in", values, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentBetween(String value1, String value2) {
            addCriterion("comment_content between", value1, value2, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentContentNotBetween(String value1, String value2) {
            addCriterion("comment_content not between", value1, value2, "commentContent");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeIsNull() {
            addCriterion("comment_buildTime is null");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeIsNotNull() {
            addCriterion("comment_buildTime is not null");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeEqualTo(Date value) {
            addCriterion("comment_buildTime =", value, "commentBuildtime");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeNotEqualTo(Date value) {
            addCriterion("comment_buildTime <>", value, "commentBuildtime");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeGreaterThan(Date value) {
            addCriterion("comment_buildTime >", value, "commentBuildtime");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("comment_buildTime >=", value, "commentBuildtime");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeLessThan(Date value) {
            addCriterion("comment_buildTime <", value, "commentBuildtime");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeLessThanOrEqualTo(Date value) {
            addCriterion("comment_buildTime <=", value, "commentBuildtime");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeIn(List<Date> values) {
            addCriterion("comment_buildTime in", values, "commentBuildtime");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeNotIn(List<Date> values) {
            addCriterion("comment_buildTime not in", values, "commentBuildtime");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeBetween(Date value1, Date value2) {
            addCriterion("comment_buildTime between", value1, value2, "commentBuildtime");
            return (Criteria) this;
        }

        public Criteria andCommentBuildtimeNotBetween(Date value1, Date value2) {
            addCriterion("comment_buildTime not between", value1, value2, "commentBuildtime");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumIsNull() {
            addCriterion("comment_likeNum is null");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumIsNotNull() {
            addCriterion("comment_likeNum is not null");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumEqualTo(Integer value) {
            addCriterion("comment_likeNum =", value, "commentLikenum");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumNotEqualTo(Integer value) {
            addCriterion("comment_likeNum <>", value, "commentLikenum");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumGreaterThan(Integer value) {
            addCriterion("comment_likeNum >", value, "commentLikenum");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_likeNum >=", value, "commentLikenum");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumLessThan(Integer value) {
            addCriterion("comment_likeNum <", value, "commentLikenum");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumLessThanOrEqualTo(Integer value) {
            addCriterion("comment_likeNum <=", value, "commentLikenum");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumIn(List<Integer> values) {
            addCriterion("comment_likeNum in", values, "commentLikenum");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumNotIn(List<Integer> values) {
            addCriterion("comment_likeNum not in", values, "commentLikenum");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumBetween(Integer value1, Integer value2) {
            addCriterion("comment_likeNum between", value1, value2, "commentLikenum");
            return (Criteria) this;
        }

        public Criteria andCommentLikenumNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_likeNum not between", value1, value2, "commentLikenum");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumIsNull() {
            addCriterion("comment_replyNum is null");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumIsNotNull() {
            addCriterion("comment_replyNum is not null");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumEqualTo(Integer value) {
            addCriterion("comment_replyNum =", value, "commentReplynum");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumNotEqualTo(Integer value) {
            addCriterion("comment_replyNum <>", value, "commentReplynum");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumGreaterThan(Integer value) {
            addCriterion("comment_replyNum >", value, "commentReplynum");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_replyNum >=", value, "commentReplynum");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumLessThan(Integer value) {
            addCriterion("comment_replyNum <", value, "commentReplynum");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumLessThanOrEqualTo(Integer value) {
            addCriterion("comment_replyNum <=", value, "commentReplynum");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumIn(List<Integer> values) {
            addCriterion("comment_replyNum in", values, "commentReplynum");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumNotIn(List<Integer> values) {
            addCriterion("comment_replyNum not in", values, "commentReplynum");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumBetween(Integer value1, Integer value2) {
            addCriterion("comment_replyNum between", value1, value2, "commentReplynum");
            return (Criteria) this;
        }

        public Criteria andCommentReplynumNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_replyNum not between", value1, value2, "commentReplynum");
            return (Criteria) this;
        }

        public Criteria andFromUidIsNull() {
            addCriterion("from_uid is null");
            return (Criteria) this;
        }

        public Criteria andFromUidIsNotNull() {
            addCriterion("from_uid is not null");
            return (Criteria) this;
        }

        public Criteria andFromUidEqualTo(Integer value) {
            addCriterion("from_uid =", value, "fromUid");
            return (Criteria) this;
        }

        public Criteria andFromUidNotEqualTo(Integer value) {
            addCriterion("from_uid <>", value, "fromUid");
            return (Criteria) this;
        }

        public Criteria andFromUidGreaterThan(Integer value) {
            addCriterion("from_uid >", value, "fromUid");
            return (Criteria) this;
        }

        public Criteria andFromUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("from_uid >=", value, "fromUid");
            return (Criteria) this;
        }

        public Criteria andFromUidLessThan(Integer value) {
            addCriterion("from_uid <", value, "fromUid");
            return (Criteria) this;
        }

        public Criteria andFromUidLessThanOrEqualTo(Integer value) {
            addCriterion("from_uid <=", value, "fromUid");
            return (Criteria) this;
        }

        public Criteria andFromUidIn(List<Integer> values) {
            addCriterion("from_uid in", values, "fromUid");
            return (Criteria) this;
        }

        public Criteria andFromUidNotIn(List<Integer> values) {
            addCriterion("from_uid not in", values, "fromUid");
            return (Criteria) this;
        }

        public Criteria andFromUidBetween(Integer value1, Integer value2) {
            addCriterion("from_uid between", value1, value2, "fromUid");
            return (Criteria) this;
        }

        public Criteria andFromUidNotBetween(Integer value1, Integer value2) {
            addCriterion("from_uid not between", value1, value2, "fromUid");
            return (Criteria) this;
        }

        public Criteria andToUidIsNull() {
            addCriterion("to_uid is null");
            return (Criteria) this;
        }

        public Criteria andToUidIsNotNull() {
            addCriterion("to_uid is not null");
            return (Criteria) this;
        }

        public Criteria andToUidEqualTo(Integer value) {
            addCriterion("to_uid =", value, "toUid");
            return (Criteria) this;
        }

        public Criteria andToUidNotEqualTo(Integer value) {
            addCriterion("to_uid <>", value, "toUid");
            return (Criteria) this;
        }

        public Criteria andToUidGreaterThan(Integer value) {
            addCriterion("to_uid >", value, "toUid");
            return (Criteria) this;
        }

        public Criteria andToUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_uid >=", value, "toUid");
            return (Criteria) this;
        }

        public Criteria andToUidLessThan(Integer value) {
            addCriterion("to_uid <", value, "toUid");
            return (Criteria) this;
        }

        public Criteria andToUidLessThanOrEqualTo(Integer value) {
            addCriterion("to_uid <=", value, "toUid");
            return (Criteria) this;
        }

        public Criteria andToUidIn(List<Integer> values) {
            addCriterion("to_uid in", values, "toUid");
            return (Criteria) this;
        }

        public Criteria andToUidNotIn(List<Integer> values) {
            addCriterion("to_uid not in", values, "toUid");
            return (Criteria) this;
        }

        public Criteria andToUidBetween(Integer value1, Integer value2) {
            addCriterion("to_uid between", value1, value2, "toUid");
            return (Criteria) this;
        }

        public Criteria andToUidNotBetween(Integer value1, Integer value2) {
            addCriterion("to_uid not between", value1, value2, "toUid");
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