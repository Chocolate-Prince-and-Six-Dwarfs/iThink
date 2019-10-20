package com.choco.ithink.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreativeCapsuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CreativeCapsuleExample() {
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

        public Criteria andCreativeIdIsNull() {
            addCriterion("creative_id is null");
            return (Criteria) this;
        }

        public Criteria andCreativeIdIsNotNull() {
            addCriterion("creative_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreativeIdEqualTo(Integer value) {
            addCriterion("creative_id =", value, "creativeId");
            return (Criteria) this;
        }

        public Criteria andCreativeIdNotEqualTo(Integer value) {
            addCriterion("creative_id <>", value, "creativeId");
            return (Criteria) this;
        }

        public Criteria andCreativeIdGreaterThan(Integer value) {
            addCriterion("creative_id >", value, "creativeId");
            return (Criteria) this;
        }

        public Criteria andCreativeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("creative_id >=", value, "creativeId");
            return (Criteria) this;
        }

        public Criteria andCreativeIdLessThan(Integer value) {
            addCriterion("creative_id <", value, "creativeId");
            return (Criteria) this;
        }

        public Criteria andCreativeIdLessThanOrEqualTo(Integer value) {
            addCriterion("creative_id <=", value, "creativeId");
            return (Criteria) this;
        }

        public Criteria andCreativeIdIn(List<Integer> values) {
            addCriterion("creative_id in", values, "creativeId");
            return (Criteria) this;
        }

        public Criteria andCreativeIdNotIn(List<Integer> values) {
            addCriterion("creative_id not in", values, "creativeId");
            return (Criteria) this;
        }

        public Criteria andCreativeIdBetween(Integer value1, Integer value2) {
            addCriterion("creative_id between", value1, value2, "creativeId");
            return (Criteria) this;
        }

        public Criteria andCreativeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("creative_id not between", value1, value2, "creativeId");
            return (Criteria) this;
        }

        public Criteria andCreativeNameIsNull() {
            addCriterion("creative_name is null");
            return (Criteria) this;
        }

        public Criteria andCreativeNameIsNotNull() {
            addCriterion("creative_name is not null");
            return (Criteria) this;
        }

        public Criteria andCreativeNameEqualTo(String value) {
            addCriterion("creative_name =", value, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameNotEqualTo(String value) {
            addCriterion("creative_name <>", value, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameGreaterThan(String value) {
            addCriterion("creative_name >", value, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameGreaterThanOrEqualTo(String value) {
            addCriterion("creative_name >=", value, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameLessThan(String value) {
            addCriterion("creative_name <", value, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameLessThanOrEqualTo(String value) {
            addCriterion("creative_name <=", value, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameLike(String value) {
            addCriterion("creative_name like", value, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameNotLike(String value) {
            addCriterion("creative_name not like", value, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameIn(List<String> values) {
            addCriterion("creative_name in", values, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameNotIn(List<String> values) {
            addCriterion("creative_name not in", values, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameBetween(String value1, String value2) {
            addCriterion("creative_name between", value1, value2, "creativeName");
            return (Criteria) this;
        }

        public Criteria andCreativeNameNotBetween(String value1, String value2) {
            addCriterion("creative_name not between", value1, value2, "creativeName");
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

        public Criteria andCreativeContentIsNull() {
            addCriterion("creative_content is null");
            return (Criteria) this;
        }

        public Criteria andCreativeContentIsNotNull() {
            addCriterion("creative_content is not null");
            return (Criteria) this;
        }

        public Criteria andCreativeContentEqualTo(String value) {
            addCriterion("creative_content =", value, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentNotEqualTo(String value) {
            addCriterion("creative_content <>", value, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentGreaterThan(String value) {
            addCriterion("creative_content >", value, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentGreaterThanOrEqualTo(String value) {
            addCriterion("creative_content >=", value, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentLessThan(String value) {
            addCriterion("creative_content <", value, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentLessThanOrEqualTo(String value) {
            addCriterion("creative_content <=", value, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentLike(String value) {
            addCriterion("creative_content like", value, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentNotLike(String value) {
            addCriterion("creative_content not like", value, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentIn(List<String> values) {
            addCriterion("creative_content in", values, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentNotIn(List<String> values) {
            addCriterion("creative_content not in", values, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentBetween(String value1, String value2) {
            addCriterion("creative_content between", value1, value2, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeContentNotBetween(String value1, String value2) {
            addCriterion("creative_content not between", value1, value2, "creativeContent");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeIsNull() {
            addCriterion("creative_buildTime is null");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeIsNotNull() {
            addCriterion("creative_buildTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeEqualTo(Date value) {
            addCriterion("creative_buildTime =", value, "creativeBuildtime");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeNotEqualTo(Date value) {
            addCriterion("creative_buildTime <>", value, "creativeBuildtime");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeGreaterThan(Date value) {
            addCriterion("creative_buildTime >", value, "creativeBuildtime");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creative_buildTime >=", value, "creativeBuildtime");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeLessThan(Date value) {
            addCriterion("creative_buildTime <", value, "creativeBuildtime");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeLessThanOrEqualTo(Date value) {
            addCriterion("creative_buildTime <=", value, "creativeBuildtime");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeIn(List<Date> values) {
            addCriterion("creative_buildTime in", values, "creativeBuildtime");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeNotIn(List<Date> values) {
            addCriterion("creative_buildTime not in", values, "creativeBuildtime");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeBetween(Date value1, Date value2) {
            addCriterion("creative_buildTime between", value1, value2, "creativeBuildtime");
            return (Criteria) this;
        }

        public Criteria andCreativeBuildtimeNotBetween(Date value1, Date value2) {
            addCriterion("creative_buildTime not between", value1, value2, "creativeBuildtime");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeIsNull() {
            addCriterion("creative_uploadTime is null");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeIsNotNull() {
            addCriterion("creative_uploadTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeEqualTo(Date value) {
            addCriterion("creative_uploadTime =", value, "creativeUploadtime");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeNotEqualTo(Date value) {
            addCriterion("creative_uploadTime <>", value, "creativeUploadtime");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeGreaterThan(Date value) {
            addCriterion("creative_uploadTime >", value, "creativeUploadtime");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creative_uploadTime >=", value, "creativeUploadtime");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeLessThan(Date value) {
            addCriterion("creative_uploadTime <", value, "creativeUploadtime");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeLessThanOrEqualTo(Date value) {
            addCriterion("creative_uploadTime <=", value, "creativeUploadtime");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeIn(List<Date> values) {
            addCriterion("creative_uploadTime in", values, "creativeUploadtime");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeNotIn(List<Date> values) {
            addCriterion("creative_uploadTime not in", values, "creativeUploadtime");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeBetween(Date value1, Date value2) {
            addCriterion("creative_uploadTime between", value1, value2, "creativeUploadtime");
            return (Criteria) this;
        }

        public Criteria andCreativeUploadtimeNotBetween(Date value1, Date value2) {
            addCriterion("creative_uploadTime not between", value1, value2, "creativeUploadtime");
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