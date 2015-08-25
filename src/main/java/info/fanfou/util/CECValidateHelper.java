/**
 * Copyright © Cisco Systems, Inc.
 */
package info.fanfou.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.fanfou.db.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

/**
 * @author sanlli
 * @version 1.0
 */
@Component
public class CECValidateHelper implements Serializable {

    private static final long serialVersionUID = 1317333274203557756L;

    private static final Logger logger = LoggerFactory.getLogger(CECValidateHelper.class);

    @Value("${userinfo_url}")
    private String userInfoUrl;

    /**
     * Validate cecId by portal.
     * @author sanlli
     * @param cecId
     * @return
     */
    public User validateCecId(String cecId) {
        RestTemplate restTemplate = new RestTemplate();
        String jsonUserInfo = restTemplate.getForObject(userInfoUrl + cecId, String.class);
        User cecUser = mapCECUserInfo(jsonUserInfo, cecId);
        return cecUser;
    }

    /**
     * @author sanlli
     * @param jsonUserInfo
     * @return
     */
    private User mapCECUserInfo(String jsonUserInfo, String cecId)  {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonUserInfo);
            User cecUser = new User();
            cecUser.setUserName(cecId);
            return cecUser;
        } catch (Exception e) {
            // ignore
        }
        return null;
    }
}
