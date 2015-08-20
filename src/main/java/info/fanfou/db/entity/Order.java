package info.fanfou.db.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER_ORDER.ORDER_ID
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    private Long orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER_ORDER.USER_ID
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    private Long userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER_ORDER.ORDER_STATE
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    private String orderState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER_ORDER.CREATED_DATETIME
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    private Date createdDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUSTOMER_ORDER.UPDATED_DATETIME
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    private Date updatedDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CUSTOMER_ORDER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTOMER_ORDER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public Order(Long orderId, Long userId, String orderState, Date createdDatetime, Date updatedDatetime) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderState = orderState;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUSTOMER_ORDER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public Order() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER_ORDER.ORDER_ID
     *
     * @return the value of CUSTOMER_ORDER.ORDER_ID
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER_ORDER.ORDER_ID
     *
     * @param orderId the value for CUSTOMER_ORDER.ORDER_ID
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER_ORDER.USER_ID
     *
     * @return the value of CUSTOMER_ORDER.USER_ID
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER_ORDER.USER_ID
     *
     * @param userId the value for CUSTOMER_ORDER.USER_ID
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER_ORDER.ORDER_STATE
     *
     * @return the value of CUSTOMER_ORDER.ORDER_STATE
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public String getOrderState() {
        return orderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER_ORDER.ORDER_STATE
     *
     * @param orderState the value for CUSTOMER_ORDER.ORDER_STATE
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public void setOrderState(String orderState) {
        this.orderState = orderState == null ? null : orderState.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER_ORDER.CREATED_DATETIME
     *
     * @return the value of CUSTOMER_ORDER.CREATED_DATETIME
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER_ORDER.CREATED_DATETIME
     *
     * @param createdDatetime the value for CUSTOMER_ORDER.CREATED_DATETIME
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUSTOMER_ORDER.UPDATED_DATETIME
     *
     * @return the value of CUSTOMER_ORDER.UPDATED_DATETIME
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUSTOMER_ORDER.UPDATED_DATETIME
     *
     * @param updatedDatetime the value for CUSTOMER_ORDER.UPDATED_DATETIME
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
}