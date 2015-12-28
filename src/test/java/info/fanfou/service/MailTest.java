package info.fanfou.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.FileNotFoundException;

/**
 * author : chaoluo
 * date : 2015/12/15
 * depiction :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = {
        "classpath:config/spring/spring-prop.xml"
        ,"classpath:config/spring/spring-mail.xml"})
public class MailTest {

    @Resource
    JavaMailSender mailSender;

    @Test
    public void testMail() throws FileNotFoundException, MessagingException {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom("Hello");
        smm.setTo("234750677@qq.com");
        smm.setSubject("Hello world");
        smm.setText("Hello world via spring mail sender");
        // 发送邮件
        mailSender.send(smm);

    }
}
