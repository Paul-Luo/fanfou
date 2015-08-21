package info.fanfou.db.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderDetail implements Serializable {
    private Long detaileId;

    private Long orderId;

    private Long goodsId;

    private Float privce;

    private Long count;

    private Date createdDatetime;

    private Date updatedDatetime;

    private static final long serialVersionUID = 1L;

    public OrderDetail(Long detaileId, Long orderId, Long goodsId, Float privce, Long count, Date createdDatetime, Date updatedDatetime) {
        this.detaileId = detaileId;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.privce = privce;
        this.count = count;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }

    public OrderDetail() {
        super();
    }

    public Long getDetaileId() {
        return detaileId;
    }

    public void setDetaileId(Long detaileId) {
        this.detaileId = detaileId;
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

    public Float getPrivce() {
        return privce;
    }

    public void setPrivce(Float privce) {
        this.privce = privce;
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