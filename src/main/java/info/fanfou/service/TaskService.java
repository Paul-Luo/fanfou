package info.fanfou.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * author : chaoluo
 * date : 2015/12/21
 * depiction :
 */
@Service
public class TaskService {

    private Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Scheduled(cron = "0/5 * * * * ?")
    public void sendBill() {
        logger.info("begin to send email!");

//        SimpleMailMessage smm = new SimpleMailMessage();
//        smm.setFrom("Hello");
//        smm.setTo("234750677@qq.com");
//        smm.setSubject("Hello world");
//        smm.setText("Hello world via spring mail sender");
//        // 发送邮件
//        mailSender.send(smm);
    }
}
