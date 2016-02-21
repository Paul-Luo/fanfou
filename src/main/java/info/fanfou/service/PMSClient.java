package info.fanfou.service;

import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * author : chaoluo
 * date : 2016/2/21
 * depiction :
 */
@Service
public class PMSClient {

    private Logger logger = LoggerFactory.getLogger(PMSClient.class);

    @Value("${pms.api.url}")
    private String pmsAPIUrl;

    private static String SEND_MSG_RELATIVE_URL_FORMAT = "/rest/v1_0/notification/sendmessage/Email";


    @Value("${oauth_appId}")
    private String appKey;

    @Value("${oauth_appSecret}")
    private String appSecret;


    public void sendMsg(String toEmail, String content) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sender", "chaoluo@cisco.com");
        params.add("receiver", toEmail);
        params.add("content", content);
        params.add("subject", "Fanfou Bill");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Basic " + getBasic64Encode());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(pmsAPIUrl + SEND_MSG_RELATIVE_URL_FORMAT, HttpMethod.POST, entity, String.class);
        logger.info(result.toString());
    }

    private String getBasic64Encode() {
        return new String(Base64.encodeBase64((appKey + ":" + appSecret).getBytes()));
    }
}
