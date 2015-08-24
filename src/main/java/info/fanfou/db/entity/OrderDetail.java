package info.fanfou.db.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderDetail implements Serializable {
    private Long detailId;

    private Long orderId;

    private Long goodsId;

    private String goodsName;

    private Float price;

    private Long count;

    private Date createdDatetime;

    private Date updatedDatetime;

    private static final long serialVersionUID = 1L;

    public OrderDetail(Long detailId, Long orderId, Long goodsId, String goodsName, Float price, Long count, Date createdDatetime, Date updatedDatetime) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.price = price;
        this.count = count;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }

    public OrderDetail() {
        super();
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
}