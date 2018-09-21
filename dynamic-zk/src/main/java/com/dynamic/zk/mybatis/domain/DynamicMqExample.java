package com.dynamic.zk.mybatis.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DynamicMqExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DynamicMqExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andPronameIsNull() {
            addCriterion("proname is null");
            return (Criteria) this;
        }

        public Criteria andPronameIsNotNull() {
            addCriterion("proname is not null");
            return (Criteria) this;
        }

        public Criteria andPronameEqualTo(String value) {
            addCriterion("proname =", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameNotEqualTo(String value) {
            addCriterion("proname <>", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameGreaterThan(String value) {
            addCriterion("proname >", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameGreaterThanOrEqualTo(String value) {
            addCriterion("proname >=", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameLessThan(String value) {
            addCriterion("proname <", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameLessThanOrEqualTo(String value) {
            addCriterion("proname <=", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameLike(String value) {
            addCriterion("proname like", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameNotLike(String value) {
            addCriterion("proname not like", value, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameIn(List<String> values) {
            addCriterion("proname in", values, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameNotIn(List<String> values) {
            addCriterion("proname not in", values, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameBetween(String value1, String value2) {
            addCriterion("proname between", value1, value2, "proname");
            return (Criteria) this;
        }

        public Criteria andPronameNotBetween(String value1, String value2) {
            addCriterion("proname not between", value1, value2, "proname");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andTopicIsNull() {
            addCriterion("topic is null");
            return (Criteria) this;
        }

        public Criteria andTopicIsNotNull() {
            addCriterion("topic is not null");
            return (Criteria) this;
        }

        public Criteria andTopicEqualTo(String value) {
            addCriterion("topic =", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotEqualTo(String value) {
            addCriterion("topic <>", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicGreaterThan(String value) {
            addCriterion("topic >", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicGreaterThanOrEqualTo(String value) {
            addCriterion("topic >=", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicLessThan(String value) {
            addCriterion("topic <", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicLessThanOrEqualTo(String value) {
            addCriterion("topic <=", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicLike(String value) {
            addCriterion("topic like", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotLike(String value) {
            addCriterion("topic not like", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicIn(List<String> values) {
            addCriterion("topic in", values, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotIn(List<String> values) {
            addCriterion("topic not in", values, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicBetween(String value1, String value2) {
            addCriterion("topic between", value1, value2, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotBetween(String value1, String value2) {
            addCriterion("topic not between", value1, value2, "topic");
            return (Criteria) this;
        }

        public Criteria andTagIsNull() {
            addCriterion("tag is null");
            return (Criteria) this;
        }

        public Criteria andTagIsNotNull() {
            addCriterion("tag is not null");
            return (Criteria) this;
        }

        public Criteria andTagEqualTo(String value) {
            addCriterion("tag =", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotEqualTo(String value) {
            addCriterion("tag <>", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThan(String value) {
            addCriterion("tag >", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagGreaterThanOrEqualTo(String value) {
            addCriterion("tag >=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThan(String value) {
            addCriterion("tag <", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLessThanOrEqualTo(String value) {
            addCriterion("tag <=", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagLike(String value) {
            addCriterion("tag like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotLike(String value) {
            addCriterion("tag not like", value, "tag");
            return (Criteria) this;
        }

        public Criteria andTagIn(List<String> values) {
            addCriterion("tag in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotIn(List<String> values) {
            addCriterion("tag not in", values, "tag");
            return (Criteria) this;
        }

        public Criteria andTagBetween(String value1, String value2) {
            addCriterion("tag between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andTagNotBetween(String value1, String value2) {
            addCriterion("tag not between", value1, value2, "tag");
            return (Criteria) this;
        }

        public Criteria andGroupnameIsNull() {
            addCriterion("groupName is null");
            return (Criteria) this;
        }

        public Criteria andGroupnameIsNotNull() {
            addCriterion("groupName is not null");
            return (Criteria) this;
        }

        public Criteria andGroupnameEqualTo(String value) {
            addCriterion("groupName =", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotEqualTo(String value) {
            addCriterion("groupName <>", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThan(String value) {
            addCriterion("groupName >", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameGreaterThanOrEqualTo(String value) {
            addCriterion("groupName >=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThan(String value) {
            addCriterion("groupName <", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLessThanOrEqualTo(String value) {
            addCriterion("groupName <=", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameLike(String value) {
            addCriterion("groupName like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotLike(String value) {
            addCriterion("groupName not like", value, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameIn(List<String> values) {
            addCriterion("groupName in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotIn(List<String> values) {
            addCriterion("groupName not in", values, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameBetween(String value1, String value2) {
            addCriterion("groupName between", value1, value2, "groupname");
            return (Criteria) this;
        }

        public Criteria andGroupnameNotBetween(String value1, String value2) {
            addCriterion("groupName not between", value1, value2, "groupname");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereIsNull() {
            addCriterion("consumeFormWhere is null");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereIsNotNull() {
            addCriterion("consumeFormWhere is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereEqualTo(String value) {
            addCriterion("consumeFormWhere =", value, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereNotEqualTo(String value) {
            addCriterion("consumeFormWhere <>", value, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereGreaterThan(String value) {
            addCriterion("consumeFormWhere >", value, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereGreaterThanOrEqualTo(String value) {
            addCriterion("consumeFormWhere >=", value, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereLessThan(String value) {
            addCriterion("consumeFormWhere <", value, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereLessThanOrEqualTo(String value) {
            addCriterion("consumeFormWhere <=", value, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereLike(String value) {
            addCriterion("consumeFormWhere like", value, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereNotLike(String value) {
            addCriterion("consumeFormWhere not like", value, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereIn(List<String> values) {
            addCriterion("consumeFormWhere in", values, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereNotIn(List<String> values) {
            addCriterion("consumeFormWhere not in", values, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereBetween(String value1, String value2) {
            addCriterion("consumeFormWhere between", value1, value2, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumeformwhereNotBetween(String value1, String value2) {
            addCriterion("consumeFormWhere not between", value1, value2, "consumeformwhere");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminIsNull() {
            addCriterion("consumeThreadMin is null");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminIsNotNull() {
            addCriterion("consumeThreadMin is not null");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminEqualTo(String value) {
            addCriterion("consumeThreadMin =", value, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminNotEqualTo(String value) {
            addCriterion("consumeThreadMin <>", value, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminGreaterThan(String value) {
            addCriterion("consumeThreadMin >", value, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminGreaterThanOrEqualTo(String value) {
            addCriterion("consumeThreadMin >=", value, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminLessThan(String value) {
            addCriterion("consumeThreadMin <", value, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminLessThanOrEqualTo(String value) {
            addCriterion("consumeThreadMin <=", value, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminLike(String value) {
            addCriterion("consumeThreadMin like", value, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminNotLike(String value) {
            addCriterion("consumeThreadMin not like", value, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminIn(List<String> values) {
            addCriterion("consumeThreadMin in", values, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminNotIn(List<String> values) {
            addCriterion("consumeThreadMin not in", values, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminBetween(String value1, String value2) {
            addCriterion("consumeThreadMin between", value1, value2, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadminNotBetween(String value1, String value2) {
            addCriterion("consumeThreadMin not between", value1, value2, "consumethreadmin");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxIsNull() {
            addCriterion("consumeThreadMax is null");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxIsNotNull() {
            addCriterion("consumeThreadMax is not null");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxEqualTo(String value) {
            addCriterion("consumeThreadMax =", value, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxNotEqualTo(String value) {
            addCriterion("consumeThreadMax <>", value, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxGreaterThan(String value) {
            addCriterion("consumeThreadMax >", value, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxGreaterThanOrEqualTo(String value) {
            addCriterion("consumeThreadMax >=", value, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxLessThan(String value) {
            addCriterion("consumeThreadMax <", value, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxLessThanOrEqualTo(String value) {
            addCriterion("consumeThreadMax <=", value, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxLike(String value) {
            addCriterion("consumeThreadMax like", value, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxNotLike(String value) {
            addCriterion("consumeThreadMax not like", value, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxIn(List<String> values) {
            addCriterion("consumeThreadMax in", values, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxNotIn(List<String> values) {
            addCriterion("consumeThreadMax not in", values, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxBetween(String value1, String value2) {
            addCriterion("consumeThreadMax between", value1, value2, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andConsumethreadmaxNotBetween(String value1, String value2) {
            addCriterion("consumeThreadMax not between", value1, value2, "consumethreadmax");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueIsNull() {
            addCriterion("pullThresholdForQueue is null");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueIsNotNull() {
            addCriterion("pullThresholdForQueue is not null");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueEqualTo(String value) {
            addCriterion("pullThresholdForQueue =", value, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueNotEqualTo(String value) {
            addCriterion("pullThresholdForQueue <>", value, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueGreaterThan(String value) {
            addCriterion("pullThresholdForQueue >", value, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueGreaterThanOrEqualTo(String value) {
            addCriterion("pullThresholdForQueue >=", value, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueLessThan(String value) {
            addCriterion("pullThresholdForQueue <", value, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueLessThanOrEqualTo(String value) {
            addCriterion("pullThresholdForQueue <=", value, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueLike(String value) {
            addCriterion("pullThresholdForQueue like", value, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueNotLike(String value) {
            addCriterion("pullThresholdForQueue not like", value, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueIn(List<String> values) {
            addCriterion("pullThresholdForQueue in", values, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueNotIn(List<String> values) {
            addCriterion("pullThresholdForQueue not in", values, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueBetween(String value1, String value2) {
            addCriterion("pullThresholdForQueue between", value1, value2, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andPullthresholdforqueueNotBetween(String value1, String value2) {
            addCriterion("pullThresholdForQueue not between", value1, value2, "pullthresholdforqueue");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeIsNull() {
            addCriterion("consumeMessageBatchMaxSize is null");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeIsNotNull() {
            addCriterion("consumeMessageBatchMaxSize is not null");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeEqualTo(String value) {
            addCriterion("consumeMessageBatchMaxSize =", value, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeNotEqualTo(String value) {
            addCriterion("consumeMessageBatchMaxSize <>", value, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeGreaterThan(String value) {
            addCriterion("consumeMessageBatchMaxSize >", value, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeGreaterThanOrEqualTo(String value) {
            addCriterion("consumeMessageBatchMaxSize >=", value, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeLessThan(String value) {
            addCriterion("consumeMessageBatchMaxSize <", value, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeLessThanOrEqualTo(String value) {
            addCriterion("consumeMessageBatchMaxSize <=", value, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeLike(String value) {
            addCriterion("consumeMessageBatchMaxSize like", value, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeNotLike(String value) {
            addCriterion("consumeMessageBatchMaxSize not like", value, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeIn(List<String> values) {
            addCriterion("consumeMessageBatchMaxSize in", values, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeNotIn(List<String> values) {
            addCriterion("consumeMessageBatchMaxSize not in", values, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeBetween(String value1, String value2) {
            addCriterion("consumeMessageBatchMaxSize between", value1, value2, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andConsumemessagebatchmaxsizeNotBetween(String value1, String value2) {
            addCriterion("consumeMessageBatchMaxSize not between", value1, value2, "consumemessagebatchmaxsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeIsNull() {
            addCriterion("pullBatchSize is null");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeIsNotNull() {
            addCriterion("pullBatchSize is not null");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeEqualTo(String value) {
            addCriterion("pullBatchSize =", value, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeNotEqualTo(String value) {
            addCriterion("pullBatchSize <>", value, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeGreaterThan(String value) {
            addCriterion("pullBatchSize >", value, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeGreaterThanOrEqualTo(String value) {
            addCriterion("pullBatchSize >=", value, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeLessThan(String value) {
            addCriterion("pullBatchSize <", value, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeLessThanOrEqualTo(String value) {
            addCriterion("pullBatchSize <=", value, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeLike(String value) {
            addCriterion("pullBatchSize like", value, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeNotLike(String value) {
            addCriterion("pullBatchSize not like", value, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeIn(List<String> values) {
            addCriterion("pullBatchSize in", values, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeNotIn(List<String> values) {
            addCriterion("pullBatchSize not in", values, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeBetween(String value1, String value2) {
            addCriterion("pullBatchSize between", value1, value2, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullbatchsizeNotBetween(String value1, String value2) {
            addCriterion("pullBatchSize not between", value1, value2, "pullbatchsize");
            return (Criteria) this;
        }

        public Criteria andPullintervalIsNull() {
            addCriterion("pullInterval is null");
            return (Criteria) this;
        }

        public Criteria andPullintervalIsNotNull() {
            addCriterion("pullInterval is not null");
            return (Criteria) this;
        }

        public Criteria andPullintervalEqualTo(String value) {
            addCriterion("pullInterval =", value, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalNotEqualTo(String value) {
            addCriterion("pullInterval <>", value, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalGreaterThan(String value) {
            addCriterion("pullInterval >", value, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalGreaterThanOrEqualTo(String value) {
            addCriterion("pullInterval >=", value, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalLessThan(String value) {
            addCriterion("pullInterval <", value, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalLessThanOrEqualTo(String value) {
            addCriterion("pullInterval <=", value, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalLike(String value) {
            addCriterion("pullInterval like", value, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalNotLike(String value) {
            addCriterion("pullInterval not like", value, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalIn(List<String> values) {
            addCriterion("pullInterval in", values, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalNotIn(List<String> values) {
            addCriterion("pullInterval not in", values, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalBetween(String value1, String value2) {
            addCriterion("pullInterval between", value1, value2, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andPullintervalNotBetween(String value1, String value2) {
            addCriterion("pullInterval not between", value1, value2, "pullinterval");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyIsNull() {
            addCriterion("businessKey is null");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyIsNotNull() {
            addCriterion("businessKey is not null");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyEqualTo(String value) {
            addCriterion("businessKey =", value, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyNotEqualTo(String value) {
            addCriterion("businessKey <>", value, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyGreaterThan(String value) {
            addCriterion("businessKey >", value, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyGreaterThanOrEqualTo(String value) {
            addCriterion("businessKey >=", value, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyLessThan(String value) {
            addCriterion("businessKey <", value, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyLessThanOrEqualTo(String value) {
            addCriterion("businessKey <=", value, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyLike(String value) {
            addCriterion("businessKey like", value, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyNotLike(String value) {
            addCriterion("businessKey not like", value, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyIn(List<String> values) {
            addCriterion("businessKey in", values, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyNotIn(List<String> values) {
            addCriterion("businessKey not in", values, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyBetween(String value1, String value2) {
            addCriterion("businessKey between", value1, value2, "businesskey");
            return (Criteria) this;
        }

        public Criteria andBusinesskeyNotBetween(String value1, String value2) {
            addCriterion("businessKey not between", value1, value2, "businesskey");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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