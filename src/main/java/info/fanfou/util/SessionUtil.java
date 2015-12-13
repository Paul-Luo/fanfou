package info.fanfou.util;

import com.webex.clops.auth.model.ClopsProfile;
import com.webex.clops.oauth.authentication.OAuthAuthenticationToken;
import info.fanfou.db.dao.mapper.UserMapper;
import info.fanfou.db.entity.User;
import info.fanfou.db.entity.UserExample;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * author : chaoluo
 * date : 2015/8/20
 * depiction :
 */
@Component
public class SessionUtil {

    @Autowired
    private UserMapper userMapper;

    public User getLoginUser() {
        String userName = getUsername();
        String email = getEmailOfLoginUser();
        User user = getUserByUserName(userName);
        if (user == null) {
            saveUser(userName, email);
        } else if (StringUtils.isEmpty(user.getEmail())) {
            updateUser(userName, email);
        }
        return user;
    }

    public String getEmailOfLoginUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String email = ((ClopsProfile)((OAuthAuthenticationToken)securityContext.getAuthentication()).getUserProfile()).getEmail();
        return email;
    }

    public User saveUser(String userName) {
        User user = new User();
        user.setUserName(userName);
        userMapper.insertSelective(user);
        return user;
    }

    public User saveUser(String userName, String email) {
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        userMapper.insertSelective(user);
        return user;
    }

    public User updateUser(String userName, String email) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        User user = new User();
        user.setEmail(email);
        userMapper.updateByExampleSelective(user, userExample);
        return user;
    }



    public User getUserByUserName(String userName) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<User> userList = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }
    /**
     *
     * @return
     */
    public static String getUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext == null ? "" : securityContext.getAuthentication() == null ? "" : securityContext.getAuthentication().getName();
    }

    /**
     *
     * @param role
     * @return
     */
    public static boolean hasRole(String role) {
        boolean flag = false;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        @SuppressWarnings("unchecked")
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        flag = authorities.contains(new SimpleGrantedAuthority(role));
        return flag;
    }

    /**
     *
     * @param roles
     * @return
     */
    public static boolean hasRoles(String... roles) {
        boolean flag = true;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        @SuppressWarnings("unchecked")
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) securityContext.getAuthentication().getAuthorities();
        for (String role : roles) {
            flag = authorities.contains(new SimpleGrantedAuthority(role)) && flag;
            if (!flag) break;
        }
        return flag;
    }
}
