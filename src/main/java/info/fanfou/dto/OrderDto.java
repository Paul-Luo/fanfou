package info.fanfou.dto;

import info.fanfou.db.entity.Order;
import info.fanfou.db.entity.OrderDetail;

import java.util.List;

/**
 * author : chaoluo
 * date : 2015/8/20
 * depiction :
 */
public class OrderDto extends Order {

    private List<OrderDetail> orderDetailList;


    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
