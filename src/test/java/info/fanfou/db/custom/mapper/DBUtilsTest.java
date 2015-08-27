package info.fanfou.db.custom.mapper;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * author : chaoluo
 * date : 2015/8/27
 * depiction :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = {"classpath:config/spring/local-spring-file.xml"})
public class DBUtilsTest {

    @Autowired
    private DBUtils dbUtils;

    @Test
    public void testSelectNow() throws Exception {
        Date date = dbUtils.selectNow();
        System.out.print(date.getTime());
    }
}