package info.fanfou.db.custom.mapper;

import info.fanfou.constants.OrderStateDef;
import info.fanfou.dto.OrderDto;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * author : chaoluo
 * date : 2015/12/21
 * depiction :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = {
        "classpath:spring-servlet.xml"
        , "classpath:config/spring/spring-content.xml"})
@WebAppConfiguration
public class OrderExMapperTest {

    @Autowired
    private OrderExMapper orderExMapper;

    @Test
    public void testQueryOrdersByState() throws Exception {
        List<OrderDto> orderDtoList = orderExMapper.queryOrdersByState(OrderStateDef.CONFIRMED.getCodeState());
        StringUtils.join(orderDtoList, ",");
    }
}