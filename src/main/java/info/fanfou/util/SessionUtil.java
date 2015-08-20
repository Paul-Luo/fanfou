package info.fanfou.util;

import info.fanfou.db.entity.User;

/**
 * author : chaoluo
 * date : 2015/8/20
 * depiction :
 */
public class SessionUtil {

    public static String getLoginUserName() {
        return "";
    }

    public static User getLoginUser() {
        User user = new User();
        user.setUserId(new Long(1));
        return user;
    }
}
