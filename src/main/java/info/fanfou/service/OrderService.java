package info.fanfou.service;

import info.fanfou.db.dao.mapper.OrderDetailMapper;
import info.fanfou.db.dao.mapper.OrderMapper;
import info.fanfou.db.entity.Order;
import info.fanfou.db.entity.OrderDetail;
import info.fanfou.db.entity.OrderDetailExample;
import info.fanfou.db.entity.OrderExample;
import info.fanfou.dto.OrderDto;
import info.fanfou.util.SessionUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
                orderDetail.setPrice(new Float(9.00));
                OrderDetail newOrderDetail = saveOrderDetail(orderDetail);
                Long detailId = newOrderDetail.getDetaileId();
                orderDetail.setDetaileId(detailId);
            }
        }
        return orderDto;
    }


    public List<OrderDto> queryLoginUserOrder() throws InvocationTargetException, IllegalAccessException {
        Long userId = SessionUtil.getLoginUser().getUserId();
        List<Order> orders = queryOrderByUserId(userId);
        List<OrderDto> orderDtos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(orders)) {
            for (Order order : orders) {
                OrderDto orderDto = new OrderDto();
                BeanUtils.copyProperties(orderDto, order);
                List<OrderDetail> orderDetails = queryOrderDetailByOrderId(order.getOrderId());
                orderDto.setOrderDetailLIst(orderDetails);
                orderDtos.add(orderDto);
            }
        }
        return orderDtos;
    }

    public List<OrderDetail> queryOrderDetailByOrderId(Long orderId) {
        OrderDetailExample example = new OrderDetailExample();
        OrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        return orderDetailMapper.selectByExample(example);
    }

    public List<Order> queryOrderByUserId(Long userId) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return orderMapper.selectByExample(example);
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
