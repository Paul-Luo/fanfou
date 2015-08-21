package info.fanfou.service;

import info.fanfou.constants.OrderStateDef;
import info.fanfou.db.dao.mapper.OrderDetailMapper;
import info.fanfou.db.dao.mapper.OrderMapper;
import info.fanfou.db.entity.Order;
import info.fanfou.db.entity.OrderDetail;
import info.fanfou.dto.OrderDto;
import info.fanfou.util.SessionUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author : chaoluo
 * date : 2015/8/20
 * depiction :
 */
@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public OrderDto saveOrder(OrderDto orderDto) {
        Order order = parseOrder(orderDto);
        saveOrder(order);
        Long orderId = order.getOrderId();
        List<OrderDetail> orderDetailList = orderDto.getOrderDetailLIst();
        if (CollectionUtils.isNotEmpty(orderDetailList)) {
            for (OrderDetail orderDetail : orderDetailList) {
                orderDetail.setOrderId(orderId);
                OrderDetail newOrderDetail = saveOrderDetail(orderDetail);
                Long detailId = newOrderDetail.getDetaileId();
                orderDetail.setDetaileId(detailId);
            }
        }
        return orderDto;
    }

    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        orderDetailMapper.insertSelective(orderDetail);
        return orderDetail;
    }

    public Order saveOrder(Order order) {
        orderMapper.insertSelective(order);
        return order;
    }

    public Order parseOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderState(orderDto.getOrderState());
        order.setUserId(SessionUtil.getLoginUser().getUserId());
        return order;
    }

}
