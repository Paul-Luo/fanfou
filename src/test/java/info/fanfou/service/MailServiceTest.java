package info.fanfou.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

/**
 * author : chaoluo
 * date : 2015/12/16
 * depiction :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = {
        "classpath:spring-servlet.xml"
        , "classpath:config/spring/spring-content.xml"})
@WebAppConfiguration
public class MailServiceTest {

    @Resource
    private MailService mailService;

    @Value("${pms.api.url}")
    private String pmsAPIUrl;

    @Test
    public void testSendBill() throws Exception {

    }

    @Test
    public void testSendConfirmedStateBill() throws Exception {
        Map<String, Map<String, Object>> sendList = mailService.getConfirmedSendList();
        for (Map.Entry<String, Map<String, Object>> entry : sendList.entrySet()) {
            mailService.sendToUserByPMS("234750677@qq.com", entry.getValue());
        }
//        mailService.sendConfirmedStateBill();
    }

    @Test
    public void testPMS() {
        String relativeUrl = "/rest/v1_0/notification/sendmail";
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("sender", "fanfou");
        params.add("to", "chaoluo@cisco.com");
        params.add("content", "this is a test email");
        params.add("subject", "test");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Basic ZmFuZm91X2tleTpjNmM3NTFkZDI3MWU0MzE1YWIyZmU4NzAzYjBlNjZjMA==");


        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(pmsAPIUrl + relativeUrl, HttpMethod.POST, entity, String.class);
        System.out.println(result.toString());

    }



}