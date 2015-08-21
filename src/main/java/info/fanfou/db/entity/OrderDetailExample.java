package info.fanfou.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDetailExample() {
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

        public Criteria andDetaileIdIsNull() {
            addCriterion("DETAILE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDetaileIdIsNotNull() {
            addCriterion("DETAILE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDetaileIdEqualTo(Long value) {
            addCriterion("DETAILE_ID =", value, "detaileId");
            return (Criteria) this;
        }

        public Criteria andDetaileIdNotEqualTo(Long value) {
            addCriterion("DETAILE_ID <>", value, "detaileId");
            return (Criteria) this;
        }

        public Criteria andDetaileIdGreaterThan(Long value) {
            addCriterion("DETAILE_ID >", value, "detaileId");
            return (Criteria) this;
        }

        public Criteria andDetaileIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DETAILE_ID >=", value, "detaileId");
            return (Criteria) this;
        }

        public Criteria andDetaileIdLessThan(Long value) {
            addCriterion("DETAILE_ID <", value, "detaileId");
            return (Criteria) this;
        }

        public Criteria andDetaileIdLessThanOrEqualTo(Long value) {
            addCriterion("DETAILE_ID <=", value, "detaileId");
            return (Criteria) this;
        }

        public Criteria andDetaileIdIn(List<Long> values) {
            addCriterion("DETAILE_ID in", values, "detaileId");
            return (Criteria) this;
        }

        public Criteria andDetaileIdNotIn(List<Long> values) {
            addCriterion("DETAILE_ID not in", values, "detaileId");
            return (Criteria) this;
        }

        public Criteria andDetaileIdBetween(Long value1, Long value2) {
            addCriterion("DETAILE_ID between", value1, value2, "detaileId");
            return (Criteria) this;
        }

        public Criteria andDetaileIdNotBetween(Long value1, Long value2) {
            addCriterion("DETAILE_ID not between", value1, value2, "detaileId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("GOODS_ID is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("GOODS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Long value) {
            addCriterion("GOODS_ID =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Long value) {
            addCriterion("GOODS_ID <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Long value) {
            addCriterion("GOODS_ID >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("GOODS_ID >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Long value) {
            addCriterion("GOODS_ID <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("GOODS_ID <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Long> values) {
            addCriterion("GOODS_ID in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Long> values) {
            addCriterion("GOODS_ID not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Long value1, Long value2) {
            addCriterion("GOODS_ID between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("GOODS_ID not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andPrivceIsNull() {
            addCriterion("PRIVCE is null");
            return (Criteria) this;
        }

        public Criteria andPrivceIsNotNull() {
            addCriterion("PRIVCE is not null");
            return (Criteria) this;
        }

        public Criteria andPrivceEqualTo(Float value) {
            addCriterion("PRIVCE =", value, "privce");
            return (Criteria) this;
        }

        public Criteria andPrivceNotEqualTo(Float value) {
            addCriterion("PRIVCE <>", value, "privce");
            return (Criteria) this;
        }

        public Criteria andPrivceGreaterThan(Float value) {
            addCriterion("PRIVCE >", value, "privce");
            return (Criteria) this;
        }

        public Criteria andPrivceGreaterThanOrEqualTo(Float value) {
            addCriterion("PRIVCE >=", value, "privce");
            return (Criteria) this;
        }

        public Criteria andPrivceLessThan(Float value) {
            addCriterion("PRIVCE <", value, "privce");
            return (Criteria) this;
        }

        public Criteria andPrivceLessThanOrEqualTo(Float value) {
            addCriterion("PRIVCE <=", value, "privce");
            return (Criteria) this;
        }

        public Criteria andPrivceIn(List<Float> values) {
            addCriterion("PRIVCE in", values, "privce");
            return (Criteria) this;
        }

        public Criteria andPrivceNotIn(List<Float> values) {
            addCriterion("PRIVCE not in", values, "privce");
            return (Criteria) this;
        }

        public Criteria andPrivceBetween(Float value1, Float value2) {
            addCriterion("PRIVCE between", value1, value2, "privce");
            return (Criteria) this;
        }

        public Criteria andPrivceNotBetween(Float value1, Float value2) {
            addCriterion("PRIVCE not between", value1, value2, "privce");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("COUNT is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Long value) {
            addCriterion("COUNT =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Long value) {
            addCriterion("COUNT <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Long value) {
            addCriterion("COUNT >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Long value) {
            addCriterion("COUNT >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Long value) {
            addCriterion("COUNT <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Long value) {
            addCriterion("COUNT <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Long> values) {
            addCriterion("COUNT in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Long> values) {
            addCriterion("COUNT not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Long value1, Long value2) {
            addCriterion("COUNT between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Long value1, Long value2) {
            addCriterion("COUNT not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeIsNull() {
            addCriterion("CREATED_DATETIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeIsNotNull() {
            addCriterion("CREATED_DATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeEqualTo(Date value) {
            addCriterion("CREATED_DATETIME =", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeNotEqualTo(Date value) {
            addCriterion("CREATED_DATETIME <>", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeGreaterThan(Date value) {
            addCriterion("CREATED_DATETIME >", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATED_DATETIME >=", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeLessThan(Date value) {
            addCriterion("CREATED_DATETIME <", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATED_DATETIME <=", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeIn(List<Date> values) {
            addCriterion("CREATED_DATETIME in", values, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeNotIn(List<Date> values) {
            addCriterion("CREATED_DATETIME not in", values, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeBetween(Date value1, Date value2) {
            addCriterion("CREATED_DATETIME between", value1, value2, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATED_DATETIME not between", value1, value2, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeIsNull() {
            addCriterion("UPDATED_DATETIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeIsNotNull() {
            addCriterion("UPDATED_DATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeEqualTo(Date value) {
            addCriterion("UPDATED_DATETIME =", value, "updatedDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeNotEqualTo(Date value) {
            addCriterion("UPDATED_DATETIME <>", value, "updatedDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeGreaterThan(Date value) {
            addCriterion("UPDATED_DATETIME >", value, "updatedDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATED_DATETIME >=", value, "updatedDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeLessThan(Date value) {
            addCriterion("UPDATED_DATETIME <", value, "updatedDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATED_DATETIME <=", value, "updatedDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeIn(List<Date> values) {
            addCriterion("UPDATED_DATETIME in", values, "updatedDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeNotIn(List<Date> values) {
            addCriterion("UPDATED_DATETIME not in", values, "updatedDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeBetween(Date value1, Date value2) {
            addCriterion("UPDATED_DATETIME between", value1, value2, "updatedDatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatedDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATED_DATETIME not between", value1, value2, "updatedDatetime");
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