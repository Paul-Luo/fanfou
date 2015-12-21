package info.fanfou.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import java.io.File;

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

    @Resource
    private JavaMailSenderImpl mailSender;

    @Test
    public void testSendBill() throws Exception {

    }

    @Test
    public void testGetTemplate() throws Exception {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper smm = new MimeMessageHelper(msg, true);
        smm.setFrom("Fanfou");
        smm.setTo("234750677@qq.com");
        smm.setSubject("Fanfou Bill");
        smm.setText(mailService.getTemplate(), true);
        File payFile = ResourceUtils.getFile("classpath:velocity/pay.jpg");
        smm.addInline("pay", payFile);
        // 发送邮件
        mailSender.send(msg);


        System.out.print(mailService.getTemplate());

    }
}