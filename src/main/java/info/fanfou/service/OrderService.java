package info.fanfou.service;

import info.fanfou.util.BookStateHelper;
import info.fanfou.constants.OrderStatusDef;
import info.fanfou.db.custom.mapper.OrderExMapper;
import info.fanfou.db.dao.mapper.GoodsMapper;
import info.fanfou.db.dao.mapper.OrderDetailMapper;
import info.fanfou.db.dao.mapper.OrderMapper;
import info.fanfou.db.entity.*;
import info.fanfou.dto.OrderDto;
import info.fanfou.util.SessionUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private OrderExMapper orderExMapper;

    @Resource
    private SessionUtil sessionUtil;

    @Resource
    private BookStateHelper bookStateHelper;


    /**
     *
     * @param orderDto
     * @return
     */
    @PreAuthorize("@orderValidate.createOrderPreValidate(#orderDto)")
    public OrderDto saveOrder(OrderDto orderDto) throws InvocationTargetException, IllegalAccessException {
        orderDto.setOrderState(OrderStatusDef.UNCONFIRMED.getCodeState());
        Order order = parseOrder(orderDto);
        order = saveOrder(order);
        Long orderId = order.getOrderId();
        orderDto.setOrderId(orderId);
        orderDto.setCreatedDatetime(order.getCreatedDatetime());
        orderDto.setUpdatedDatetime(order.getUpdatedDatetime());
        List<OrderDetail> orderDetailList = orderDto.getOrderDetailList();
        if (CollectionUtils.isNotEmpty(orderDetailList)) {
            for (OrderDetail orderDetail : orderDetailList) {
                orderDetail.setOrderId(orderId);
                paddingGoodsDetailInfo(orderDetail.getGoodsId(), orderDetail);
                OrderDetail newOrderDetail = saveOrderDetail(orderDetail);
                Long detailId = newOrderDetail.getDetailId();
                orderDetail.setDetailId(detailId);
            }
        }
        return orderDto;
    }

    /**
     *
     * @return
     */
    public List<OrderDto> queryTodayEffectOrder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateFormat.format(new Date()).toString();
        List<String> status = new ArrayList<>();
        status.add(OrderStatusDef.CANCELED.getCodeState());
        status.add(OrderStatusDef.CONFIRMED.getCodeState());
        status.add(OrderStatusDef.UNCONFIRMED.getCodeState());
        status.add(OrderStatusDef.PAID.getCodeState());
        return orderExMapper.queryOrdersByStatuesAndTime(status, now);
    }

    /**
     *
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public List<OrderDto> queryLoginUserOrder() throws InvocationTargetException, IllegalAccessException {
        Long userId = sessionUtil.getLoginUser().getUserId();
        return orderExMapper.queryUserOrder(userId);
    }


    public Boolean cancelOrder(Long orderId) {
        List<Long> orderIds = new ArrayList<>();
        return cancelOrders(orderIds);
    }

    public Boolean cancelOrders(List<Long> orderIds) {
        return changeOrdersStatus(orderIds, OrderStatusDef.CANCELED);
    }

    public Boolean confirmOrders(List<Long> orderIds) {
        return changeOrdersStatus(orderIds, OrderStatusDef.CONFIRMED);
    }


    public Boolean payOrders(List<Long> orderIds) {
        return changeOrdersStatus(orderIds, OrderStatusDef.PAID);
    }

    @PreAuthorize("@orderValidate.stateChangeValidate(#orderIds, #nextOrderState)")
    private Boolean changeOrdersStatus(List<Long> orderIds, OrderStatusDef nextOrderState) {
        if (CollectionUtils.isEmpty(orderIds)) {
            return Boolean.TRUE;
        }
        Order order = new Order();
        order.setOrderState(nextOrderState.getCodeState());
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdIn(orderIds);
        return orderMapper.updateByExampleSelective(order, example) > 0;
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
        example.setOrderByClause("created_datetime desc");
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
        return orderMapper.selectByPrimaryKey(order.getOrderId());
    }

    /**
     *
     * @param orderDto
     * @return
     */
    public Order parseOrder(OrderDto orderDto) throws InvocationTargetException, IllegalAccessException {
        Order order = new Order();
        BeanUtils.copyProperties(order, orderDto);
        order.setOrderState(orderDto.getOrderState());
        order.setUserId(sessionUtil.getLoginUser().getUserId());
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

    public Boolean changeBookState(Boolean state) {
        if (state) {
            bookStateHelper.setTodayAvailable();
        } else {
            bookStateHelper.setTodayDisable();
        }
        return state;
    }

    public List<OrderDto> queryDuringThe7DaysBeforeEffectOrder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Date before7Days = DateUtils.addDays(today, -7);
        return orderExMapper.queryOrderByStateAndTimeRange(OrderStatusDef.CONFIRMED.getCodeState(), dateFormat.format(before7Days).toString(), dateFormat.format(today).toString());

    }

    public List<OrderDto> queryConfirmedOrders() {
        return orderExMapper.queryOrdersByState(OrderStatusDef.CONFIRMED.getCodeState());

    }

}
