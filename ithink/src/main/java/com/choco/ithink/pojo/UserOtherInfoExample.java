package com.choco.ithink.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserOtherInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserOtherInfoExample() {
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

        public Criteria andUserAddressIsNull() {
            addCriterion("user_address is null");
            return (Criteria) this;
        }

        public Criteria andUserAddressIsNotNull() {
            addCriterion("user_address is not null");
            return (Criteria) this;
        }

        public Criteria andUserAddressEqualTo(String value) {
            addCriterion("user_address =", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotEqualTo(String value) {
            addCriterion("user_address <>", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressGreaterThan(String value) {
            addCriterion("user_address >", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressGreaterThanOrEqualTo(String value) {
            addCriterion("user_address >=", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLessThan(String value) {
            addCriterion("user_address <", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLessThanOrEqualTo(String value) {
            addCriterion("user_address <=", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLike(String value) {
            addCriterion("user_address like", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotLike(String value) {
            addCriterion("user_address not like", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressIn(List<String> values) {
            addCriterion("user_address in", values, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotIn(List<String> values) {
            addCriterion("user_address not in", values, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressBetween(String value1, String value2) {
            addCriterion("user_address between", value1, value2, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotBetween(String value1, String value2) {
            addCriterion("user_address not between", value1, value2, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserIndustryIsNull() {
            addCriterion("user_industry is null");
            return (Criteria) this;
        }

        public Criteria andUserIndustryIsNotNull() {
            addCriterion("user_industry is not null");
            return (Criteria) this;
        }

        public Criteria andUserIndustryEqualTo(String value) {
            addCriterion("user_industry =", value, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryNotEqualTo(String value) {
            addCriterion("user_industry <>", value, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryGreaterThan(String value) {
            addCriterion("user_industry >", value, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("user_industry >=", value, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryLessThan(String value) {
            addCriterion("user_industry <", value, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryLessThanOrEqualTo(String value) {
            addCriterion("user_industry <=", value, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryLike(String value) {
            addCriterion("user_industry like", value, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryNotLike(String value) {
            addCriterion("user_industry not like", value, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryIn(List<String> values) {
            addCriterion("user_industry in", values, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryNotIn(List<String> values) {
            addCriterion("user_industry not in", values, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryBetween(String value1, String value2) {
            addCriterion("user_industry between", value1, value2, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserIndustryNotBetween(String value1, String value2) {
            addCriterion("user_industry not between", value1, value2, "userIndustry");
            return (Criteria) this;
        }

        public Criteria andUserSchoolIsNull() {
            addCriterion("user_school is null");
            return (Criteria) this;
        }

        public Criteria andUserSchoolIsNotNull() {
            addCriterion("user_school is not null");
            return (Criteria) this;
        }

        public Criteria andUserSchoolEqualTo(String value) {
            addCriterion("user_school =", value, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolNotEqualTo(String value) {
            addCriterion("user_school <>", value, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolGreaterThan(String value) {
            addCriterion("user_school >", value, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("user_school >=", value, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolLessThan(String value) {
            addCriterion("user_school <", value, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolLessThanOrEqualTo(String value) {
            addCriterion("user_school <=", value, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolLike(String value) {
            addCriterion("user_school like", value, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolNotLike(String value) {
            addCriterion("user_school not like", value, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolIn(List<String> values) {
            addCriterion("user_school in", values, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolNotIn(List<String> values) {
            addCriterion("user_school not in", values, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolBetween(String value1, String value2) {
            addCriterion("user_school between", value1, value2, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSchoolNotBetween(String value1, String value2) {
            addCriterion("user_school not between", value1, value2, "userSchool");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionIsNull() {
            addCriterion("user_selfIntroduction is null");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionIsNotNull() {
            addCriterion("user_selfIntroduction is not null");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionEqualTo(String value) {
            addCriterion("user_selfIntroduction =", value, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionNotEqualTo(String value) {
            addCriterion("user_selfIntroduction <>", value, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionGreaterThan(String value) {
            addCriterion("user_selfIntroduction >", value, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionGreaterThanOrEqualTo(String value) {
            addCriterion("user_selfIntroduction >=", value, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionLessThan(String value) {
            addCriterion("user_selfIntroduction <", value, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionLessThanOrEqualTo(String value) {
            addCriterion("user_selfIntroduction <=", value, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionLike(String value) {
            addCriterion("user_selfIntroduction like", value, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionNotLike(String value) {
            addCriterion("user_selfIntroduction not like", value, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionIn(List<String> values) {
            addCriterion("user_selfIntroduction in", values, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionNotIn(List<String> values) {
            addCriterion("user_selfIntroduction not in", values, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionBetween(String value1, String value2) {
            addCriterion("user_selfIntroduction between", value1, value2, "userSelfintroduction");
            return (Criteria) this;
        }

        public Criteria andUserSelfintroductionNotBetween(String value1, String value2) {
            addCriterion("user_selfIntroduction not between", value1, value2, "userSelfintroduction");
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