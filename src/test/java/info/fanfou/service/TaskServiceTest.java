package info.fanfou.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimeZone;

/**
 * author : chaoluo
 * date : 2015/12/23
 * depiction :
 */
public class TaskServiceTest {

    private Logger logger = LoggerFactory.getLogger(TaskServiceTest.class);

    @Test
    public void getTimezone() {
//        TimeZone.getTimeZone("GMT+8");
        TimeZone zone = TimeZone.getDefault();
        logger.debug(zone.getDisplayName());
        logger.debug(zone.getID());
    }

}