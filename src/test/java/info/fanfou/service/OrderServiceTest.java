package info.fanfou.service;

import info.fanfou.db.entity.Order;
import info.fanfou.db.entity.OrderDetail;
import info.fanfou.dto.OrderDto;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * author : chaoluo
 * date : 2015/8/20
 * depiction :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = {
        "classpath:spring-servlet.xml"
        , "classpath:config/spring/spring-content.xml"})
@WebAppConfiguration
public class OrderServiceTest {

    @Resource
    private OrderService orderService;



    @Test
    public void testSaveOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setPrice(new Float(9.00));
        orderDetail.setGoodsId(new Long(1));
        orderDetail.setCount(new Long(2));
        orderDetailList.add(orderDetail);
        orderDto.setOrderDetailList(orderDetailList);
        orderService.saveOrder(orderDto);
    }

    @Test
    public void testSaveOrderInfo() throws Exception {
        Order order = new Order();
        order.setUserId(new Long(1));
        order.setOrderState("init");
        orderService.saveOrder(order);
    }


    @Test
    public void testQueryDuringThe7DaysBeforeEffectOrder() throws Exception {
        List<OrderDto> orderDtos = orderService.queryDuringThe7DaysBeforeEffectOrder();
        System.out.println(StringUtils.join(orderDtos, ":"));
    }

}