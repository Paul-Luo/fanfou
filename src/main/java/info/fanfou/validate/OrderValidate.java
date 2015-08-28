package info.fanfou.validate;

import info.fanfou.util.BookStateHelper;
import info.fanfou.constants.OrderStateDef;
import info.fanfou.db.dao.mapper.OrderMapper;
import info.fanfou.db.entity.OrderExample;
import info.fanfou.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * author : chaoluo
 * date : 2015/8/25
 * depiction :
 */
@Service
public class OrderValidate {

    @Autowired
    private OrderMapper orderMapper;

    @Resource
    private BookStateHelper bookStateHelper;

    public Boolean stateChangeValidate(List<Long> orderIds) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStateNotEqualTo(OrderStateDef.UNCONFIRMED.getCodeState());
        criteria.andOrderIdIn(orderIds);
        return orderMapper.selectByExample(example).size() == 0 ;
    }

    public Boolean stateChangeValidate(Long orderId) {
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(orderId);
        return stateChangeValidate(orderIds);
    }

    public Boolean createOrderPreValidate(OrderDto orderDto) throws Exception {
        if (orderDto.getCreatedDatetime() == null) {
            return bookStateHelper.todayIsAvailable();
        }
        return Boolean.TRUE;
    }
}
