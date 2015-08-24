package info.fanfou.db.custom.mapper;

import info.fanfou.dto.OrderDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author : chaoluo
 * date : 2015/8/24
 * depiction :
 */
public interface OrderExMapper {

    List<OrderDto> queryUserOrder(@Param("userId") Long userId);
}
