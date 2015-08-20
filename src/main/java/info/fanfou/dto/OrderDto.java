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

    private List<OrderDetail> orderDetailLIst;


    public List<OrderDetail> getOrderDetailLIst() {
        return orderDetailLIst;
    }

    public void setOrderDetailLIst(List<OrderDetail> orderDetailLIst) {
        this.orderDetailLIst = orderDetailLIst;
    }
}
