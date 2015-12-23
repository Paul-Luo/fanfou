package info.fanfou.validate;

import info.fanfou.constants.OrderStatusDef;
import info.fanfou.db.custom.mapper.DBUtils;
import info.fanfou.db.dao.mapper.OrderMapper;
import info.fanfou.db.entity.OrderExample;
import info.fanfou.dto.OrderDto;
import info.fanfou.util.BookStateHelper;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * author : chaoluo
 * date : 2015/8/25
 * depiction :
 */
@Service
public class OrderValidate {

    private static final Logger logger = LoggerFactory.getLogger(OrderValidate.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DBUtils dbUtils;

    @Resource
    private BookStateHelper bookStateHelper;

    public Boolean stateChangeValidate(List<Long> orderIds, OrderStatusDef nextOrderState) {
        switch (nextOrderState) {
            case CANCELED:
                return querySpecifiedOrderIdsStatusCount(orderIds, OrderStatusDef.UNCONFIRMED) == orderIds.size();
            case UNCONFIRMED:
                return Boolean.FALSE;
            case CONFIRMED:
                return querySpecifiedOrderIdsStatusCount(orderIds, OrderStatusDef.UNCONFIRMED) == orderIds.size();
            case PAID:
                return querySpecifiedOrderIdsStatusCount(orderIds, OrderStatusDef.CONFIRMED) == orderIds.size();
            default:
                logger.info("no such status {}", nextOrderState.getCodeState());
                return Boolean.FALSE;
        }

    }

    private int querySpecifiedOrderIdsStatusCount(List<Long> orderIds, OrderStatusDef orderStatus) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderStateEqualTo(orderStatus.getCodeState());
        criteria.andOrderIdIn(orderIds);
        return orderMapper.selectByExample(example).size();
    }

    public Boolean createOrderPreValidate(OrderDto orderDto) throws Exception {
        if (orderDto.getCreatedDatetime() == null) {
            return bookStateHelper.todayIsAvailable();
        } else {
            Date today = new Date();
            if (DateUtils.isSameDay(today, orderDto.getCreatedDatetime())) {
                return bookStateHelper.todayIsAvailable();
            }
        }
        return Boolean.TRUE;
    }
}
