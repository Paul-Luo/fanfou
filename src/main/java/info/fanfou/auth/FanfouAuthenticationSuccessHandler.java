package info.fanfou.auth;

import com.webex.clops.auth.model.ClopsProfile;
import com.webex.clops.auth.sso.ClopsAuthenticationSuccessHandler;
import com.webex.clops.oauth.authentication.OAuthAuthenticationToken;
import com.webex.clops.oauth.profile.UserProfile;
import info.fanfou.db.dao.mapper.UserMapper;
import info.fanfou.db.entity.User;
import info.fanfou.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author : chaoluo
 * date : 2015/12/13
 * depiction :
 */
public class FanfouAuthenticationSuccessHandler extends ClopsAuthenticationSuccessHandler {

    @Resource
    private SessionUtil sessionUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        OAuthAuthenticationToken token = (OAuthAuthenticationToken) authentication;
        ClopsProfile userProfile = (ClopsProfile)token.getUserProfile();
        String userName = token.getName();
        String email = userProfile.getEmail();
        User user = sessionUtil.getUserByUserName(userName);
        if (user == null) {
            sessionUtil.saveUser(userName, email);
        } else if (StringUtils.isEmpty(user.getEmail())) {
            sessionUtil.updateUser(userName, email);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

    public void setSessionUtil(SessionUtil sessionUtil) {
        this.sessionUtil = sessionUtil;
    }
}
