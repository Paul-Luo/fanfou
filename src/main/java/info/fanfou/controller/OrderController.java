package info.fanfou.controller;

import info.fanfou.db.entity.Order;
import info.fanfou.db.entity.OrderDetail;
import info.fanfou.dto.OrderDto;
import info.fanfou.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
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

    @RequestMapping("/mine")
    @ResponseBody
    public List<OrderDto> queryLoginUserOrder() throws InvocationTargetException, IllegalAccessException {
        return orderService.queryLoginUserOrder();
    }


    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseBody
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

}
