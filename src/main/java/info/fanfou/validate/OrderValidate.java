package info.fanfou.validate;

import info.fanfou.constants.OrderStateDef;
import info.fanfou.db.dao.mapper.OrderMapper;
import info.fanfou.db.entity.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * author : chaoluo
 * date : 2015/8/25
 * depiction :
 */
@Component
public class OrderValidate {

    @Autowired
    private OrderMapper orderMapper;

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
}
