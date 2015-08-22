package info.fanfou.service;

import info.fanfou.constants.OrderStateDef;
import info.fanfou.db.dao.mapper.GoodsMapper;
import info.fanfou.db.dao.mapper.OrderDetailMapper;
import info.fanfou.db.dao.mapper.OrderMapper;
import info.fanfou.db.entity.*;
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

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     *
     * @param orderDto
     * @return
     */
    public OrderDto saveOrder(OrderDto orderDto) {
        orderDto.setOrderState(OrderStateDef.UNCONFIRMED.getCodeState());
        Order order = parseOrder(orderDto);
        saveOrder(order);
        Long orderId = order.getOrderId();
        List<OrderDetail> orderDetailList = orderDto.getOrderDetailLIst();
        if (CollectionUtils.isNotEmpty(orderDetailList)) {
            for (OrderDetail orderDetail : orderDetailList) {
                orderDetail.setOrderId(orderId);
                paddingGoodsDetailInfo(orderDetail.getGoodsId(), orderDetail);
                OrderDetail newOrderDetail = saveOrderDetail(orderDetail);
                Long detailId = newOrderDetail.getDetaileId();
                orderDetail.setDetaileId(detailId);
            }
        }
        return orderDto;
    }

    /**
     *
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
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

    /**
     *
     * @param orderId
     * @return
     */
    public List<OrderDetail> queryOrderDetailByOrderId(Long orderId) {
        OrderDetailExample example = new OrderDetailExample();
        OrderDetailExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        return orderDetailMapper.selectByExample(example);
    }

    /**
     *
     * @param userId
     * @return
     */
    public List<Order> queryOrderByUserId(Long userId) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return orderMapper.selectByExample(example);
    }

    /**
     *
     * @param orderDetail
     * @return
     */
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        orderDetailMapper.insertSelective(orderDetail);
        return orderDetail;
    }

    /**
     *
     * @param order
     * @return
     */
    public Order saveOrder(Order order) {
        orderMapper.insertSelective(order);
        return order;
    }

    /**
     *
     * @param orderDto
     * @return
     */
    public Order parseOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderState(orderDto.getOrderState());
        order.setUserId(SessionUtil.getLoginUser().getUserId());
        return order;
    }

    /**
     *
     * @param goodsId
     * @return
     */
    public void paddingGoodsDetailInfo(Long goodsId, OrderDetail orderDetail) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if (goods == null) {
            orderDetail.setPrice(new Float(0));
        } else {
            orderDetail.setPrice(goods.getPrice());
            orderDetail.setGoodsName(goods.getGoodsName());
        }
    }

}
