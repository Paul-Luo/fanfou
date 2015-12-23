package info.fanfou.db.custom.mapper;

import info.fanfou.dto.OrderDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * author : chaoluo
 * date : 2015/8/24
 * depiction :
 */
public interface OrderExMapper {

    List<OrderDto> queryUserOrder(@Param("userId") Long userId);

    List<OrderDto> queryTodayOrderByExcludeState(@Param("orderState") String orderState, @Param("createdDate")String createdDate);

    List<OrderDto> queryOrdersByStatuesAndTime(@Param("orderStatus") List<String> orderStatus, @Param("createdDate")String createdDate);

    List<OrderDto> queryOrderByStateAndTimeRange(@Param("orderState") String orderState, @Param("beginDate")String beginDate, @Param("endDate")String endDate);

    List<OrderDto> queryOrdersByState(@Param("orderState") String orderState);
}
