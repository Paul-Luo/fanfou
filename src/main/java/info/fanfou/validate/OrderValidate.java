package info.fanfou.validate;

import info.fanfou.constants.BookState;
import info.fanfou.constants.OrderStateDef;
import info.fanfou.db.dao.mapper.OrderMapper;
import info.fanfou.db.entity.Order;
import info.fanfou.db.entity.OrderExample;
import info.fanfou.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
            return BookState.TODAY_BOOK_STATE_AVAILABLE;
        }
        return Boolean.TRUE;
    }
}
