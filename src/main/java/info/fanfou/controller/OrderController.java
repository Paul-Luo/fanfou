package info.fanfou.controller;

import info.fanfou.db.entity.Order;
import info.fanfou.db.entity.OrderDetail;
import info.fanfou.dto.OrderDto;
import info.fanfou.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : chaoluo
 * date : 2015/8/21
 * depiction :
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView gotoOrderView() {
        ModelAndView view = new ModelAndView("order/order");
        return view;
    }

    @RequestMapping("/mine")
    @ResponseBody
    public List<OrderDto> queryLoginUserOrder() throws InvocationTargetException, IllegalAccessException {
        return orderService.queryLoginUserOrder();
    }

    @RequestMapping(value = "/today", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderDto> queryTodayEffectOrder() throws InvocationTargetException, IllegalAccessException {
        return orderService.queryTodayEffectOrder();
    }


    @RequestMapping(value = "/count/{count}", method = RequestMethod.POST)
    @ResponseBody
    public OrderDto createOrderDirect(@PathVariable Long count) {
        OrderDto orderDto = new OrderDto();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCount(count);
        orderDetail.setGoodsId(new Long(1));
        List<OrderDetail> list = new ArrayList<>();
        list.add(orderDetail);
        orderDto.setOrderDetailList(list);
        return orderService.saveOrder(orderDto);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean cancelOrders(@RequestBody List<Long> orderIds) {
        return orderService.cancelOrders(orderIds);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Boolean confirmedOrders(@RequestBody List<Long> orderIds) {
        return orderService.confirmedOrders(orderIds);
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }


    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }


}
