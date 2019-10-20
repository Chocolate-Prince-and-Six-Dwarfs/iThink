package com.choco.ithink.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserCreativeinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserCreativeinfoExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andUserCapsulenumIsNull() {
            addCriterion("user_capsuleNum is null");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumIsNotNull() {
            addCriterion("user_capsuleNum is not null");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumEqualTo(Integer value) {
            addCriterion("user_capsuleNum =", value, "userCapsulenum");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumNotEqualTo(Integer value) {
            addCriterion("user_capsuleNum <>", value, "userCapsulenum");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumGreaterThan(Integer value) {
            addCriterion("user_capsuleNum >", value, "userCapsulenum");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_capsuleNum >=", value, "userCapsulenum");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumLessThan(Integer value) {
            addCriterion("user_capsuleNum <", value, "userCapsulenum");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumLessThanOrEqualTo(Integer value) {
            addCriterion("user_capsuleNum <=", value, "userCapsulenum");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumIn(List<Integer> values) {
            addCriterion("user_capsuleNum in", values, "userCapsulenum");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumNotIn(List<Integer> values) {
            addCriterion("user_capsuleNum not in", values, "userCapsulenum");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumBetween(Integer value1, Integer value2) {
            addCriterion("user_capsuleNum between", value1, value2, "userCapsulenum");
            return (Criteria) this;
        }

        public Criteria andUserCapsulenumNotBetween(Integer value1, Integer value2) {
            addCriterion("user_capsuleNum not between", value1, value2, "userCapsulenum");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumIsNull() {
            addCriterion("user_creativeTopicNum is null");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumIsNotNull() {
            addCriterion("user_creativeTopicNum is not null");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumEqualTo(Integer value) {
            addCriterion("user_creativeTopicNum =", value, "userCreativetopicnum");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumNotEqualTo(Integer value) {
            addCriterion("user_creativeTopicNum <>", value, "userCreativetopicnum");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumGreaterThan(Integer value) {
            addCriterion("user_creativeTopicNum >", value, "userCreativetopicnum");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_creativeTopicNum >=", value, "userCreativetopicnum");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumLessThan(Integer value) {
            addCriterion("user_creativeTopicNum <", value, "userCreativetopicnum");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumLessThanOrEqualTo(Integer value) {
            addCriterion("user_creativeTopicNum <=", value, "userCreativetopicnum");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumIn(List<Integer> values) {
            addCriterion("user_creativeTopicNum in", values, "userCreativetopicnum");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumNotIn(List<Integer> values) {
            addCriterion("user_creativeTopicNum not in", values, "userCreativetopicnum");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumBetween(Integer value1, Integer value2) {
            addCriterion("user_creativeTopicNum between", value1, value2, "userCreativetopicnum");
            return (Criteria) this;
        }

        public Criteria andUserCreativetopicnumNotBetween(Integer value1, Integer value2) {
            addCriterion("user_creativeTopicNum not between", value1, value2, "userCreativetopicnum");
            return (Criteria) this;
        }

        public Criteria andUserFollownumIsNull() {
            addCriterion("user_followNum is null");
            return (Criteria) this;
        }

        public Criteria andUserFollownumIsNotNull() {
            addCriterion("user_followNum is not null");
            return (Criteria) this;
        }

        public Criteria andUserFollownumEqualTo(Integer value) {
            addCriterion("user_followNum =", value, "userFollownum");
            return (Criteria) this;
        }

        public Criteria andUserFollownumNotEqualTo(Integer value) {
            addCriterion("user_followNum <>", value, "userFollownum");
            return (Criteria) this;
        }

        public Criteria andUserFollownumGreaterThan(Integer value) {
            addCriterion("user_followNum >", value, "userFollownum");
            return (Criteria) this;
        }

        public Criteria andUserFollownumGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_followNum >=", value, "userFollownum");
            return (Criteria) this;
        }

        public Criteria andUserFollownumLessThan(Integer value) {
            addCriterion("user_followNum <", value, "userFollownum");
            return (Criteria) this;
        }

        public Criteria andUserFollownumLessThanOrEqualTo(Integer value) {
            addCriterion("user_followNum <=", value, "userFollownum");
            return (Criteria) this;
        }

        public Criteria andUserFollownumIn(List<Integer> values) {
            addCriterion("user_followNum in", values, "userFollownum");
            return (Criteria) this;
        }

        public Criteria andUserFollownumNotIn(List<Integer> values) {
            addCriterion("user_followNum not in", values, "userFollownum");
            return (Criteria) this;
        }

        public Criteria andUserFollownumBetween(Integer value1, Integer value2) {
            addCriterion("user_followNum between", value1, value2, "userFollownum");
            return (Criteria) this;
        }

        public Criteria andUserFollownumNotBetween(Integer value1, Integer value2) {
            addCriterion("user_followNum not between", value1, value2, "userFollownum");
            return (Criteria) this;
        }

        public Criteria andUserFansnumIsNull() {
            addCriterion("user_fansNum is null");
            return (Criteria) this;
        }

        public Criteria andUserFansnumIsNotNull() {
            addCriterion("user_fansNum is not null");
            return (Criteria) this;
        }

        public Criteria andUserFansnumEqualTo(Integer value) {
            addCriterion("user_fansNum =", value, "userFansnum");
            return (Criteria) this;
        }

        public Criteria andUserFansnumNotEqualTo(Integer value) {
            addCriterion("user_fansNum <>", value, "userFansnum");
            return (Criteria) this;
        }

        public Criteria andUserFansnumGreaterThan(Integer value) {
            addCriterion("user_fansNum >", value, "userFansnum");
            return (Criteria) this;
        }

        public Criteria andUserFansnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_fansNum >=", value, "userFansnum");
            return (Criteria) this;
        }

        public Criteria andUserFansnumLessThan(Integer value) {
            addCriterion("user_fansNum <", value, "userFansnum");
            return (Criteria) this;
        }

        public Criteria andUserFansnumLessThanOrEqualTo(Integer value) {
            addCriterion("user_fansNum <=", value, "userFansnum");
            return (Criteria) this;
        }

        public Criteria andUserFansnumIn(List<Integer> values) {
            addCriterion("user_fansNum in", values, "userFansnum");
            return (Criteria) this;
        }

        public Criteria andUserFansnumNotIn(List<Integer> values) {
            addCriterion("user_fansNum not in", values, "userFansnum");
            return (Criteria) this;
        }

        public Criteria andUserFansnumBetween(Integer value1, Integer value2) {
            addCriterion("user_fansNum between", value1, value2, "userFansnum");
            return (Criteria) this;
        }

        public Criteria andUserFansnumNotBetween(Integer value1, Integer value2) {
            addCriterion("user_fansNum not between", value1, value2, "userFansnum");
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