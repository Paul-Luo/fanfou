package info.fanfou.util;

import info.fanfou.db.custom.mapper.DBUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * author : chaoluo
 * date : 2015/8/27
 * depiction :
 */
@Component
public class BookStateHelper {

    @Autowired
    private DBUtils dbUtils;

    public Long disableDateTime = null;


    public Boolean todayIsAvailable() {
        if (disableDateTime == null) {
            return Boolean.TRUE;
        } else {
            Date now = new Date();
            Date disableDate = new Date(disableDateTime);
            return !DateUtils.isSameDay(now, disableDate);
        }
    }

    public void setTodayAvailable() {
        this.disableDateTime = null;
    }

    public void setTodayDisable() {
        this.disableDateTime = System.currentTimeMillis();
    }
}
