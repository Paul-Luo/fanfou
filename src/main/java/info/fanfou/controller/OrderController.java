package info.fanfou.controller;

import info.fanfou.db.custom.mapper.DBUtils;
import info.fanfou.db.entity.Order;
import info.fanfou.db.entity.OrderDetail;
import info.fanfou.dto.OrderDto;
import info.fanfou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private DBUtils dbUtils;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView gotoOrderView() {
        ModelAndView view = new ModelAndView("order/order");
        view.addObject("now", dbUtils.selectNow().getTime());
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
        List<OrderDto> list = orderService.queryTodayEffectOrder();
        return list;
    }


    @RequestMapping(value = "/count/{count}", method = RequestMethod.POST)
    @ResponseBody
    public OrderDto createOrderDirect(@PathVariable Long count, @RequestParam(value = "createdDatetime", required = true) @DateTimeFormat(pattern="yyyy-MM-dd")  Date createdDatetime) throws InvocationTargetException, IllegalAccessException {
        OrderDto orderDto = new OrderDto();
        orderDto.setCreatedDatetime(createdDatetime);
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCount(count);
        orderDetail.setGoodsId(new Long(1));
        List<OrderDetail> list = new ArrayList<>();
        list.add(orderDetail);
        orderDto.setOrderDetailList(list);
        return orderService.saveOrder(orderDto);
    }

    @PreAuthorize("hasRole('App_Admin')")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ResponseBody
    public Boolean cancelOrders(@RequestBody List<Long> orderIds) {
        return orderService.cancelOrders(orderIds);
    }


    @PreAuthorize("hasRole('App_Admin')")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Boolean confirmedOrders(@RequestBody List<Long> orderIds) {
        return orderService.confirmedOrders(orderIds);
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public OrderDto createOrder(@RequestBody OrderDto orderDto) throws InvocationTargetException, IllegalAccessException {
        return orderService.saveOrder(orderDto);
    }


    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
     @ResponseBody
     public Boolean cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }

    @RequestMapping(value = "/book/{state}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean changeBookStateOrder(@PathVariable Boolean state) {
        return orderService.changeBookState(state);
    }


}
