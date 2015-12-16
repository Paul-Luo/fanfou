package info.fanfou.service;

import info.fanfou.db.entity.OrderDetail;
import info.fanfou.dto.OrderDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author : chaoluo
 * date : 2015/12/16
 * depiction :
 */
@Service
public class MailService {

    private Logger logger = LoggerFactory.getLogger(MailService.class);

    @Resource
    private JavaMailSenderImpl mailSender;

    @Resource
    private OrderService orderService;

    @Autowired
    private VelocityEngine velocityEngine;

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

    public String getTemplate() {
        Map<String, Map<String, Object>> sendList = getDuringThe7SendList();
        for (Map.Entry<String, Map<String, Object>> entry : sendList.entrySet()) {
            String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, "velocity/bill-template.vm", "UTF-8", entry.getValue());
            logger.info("mail text:", text);
            return text;
        }
        return "tes";
    }

    public Map<String, Map<String, Object>> getDuringThe7SendList() {
        List<OrderDto> orderDtos = orderService.queryDuringThe7DaysBeforeEffectOrder();
        return getSendList(orderDtos);
    }


    public Map<String, Map<String, Object>> getSendList(List<OrderDto> orderDtos) {
        Map<String, Map<String, Object>> sendList = new HashMap<>();
        for(OrderDto orderDto : orderDtos) {
            String email = orderDto.getEmail();
            Map<String, Object> params = sendList.get(email);
            if (params == null) {
                params = new HashMap<>();
            }

            String username = (String) params.get("username");
            if (username == null) {
                username = orderDto.getUserName();
                params.put("username", username);
            }

            List<OrderDetail> orderDetailsParam = (List<OrderDetail>) params.get("orderDetails");
            if (CollectionUtils.isEmpty(orderDetailsParam)) {
                orderDetailsParam = new ArrayList<>();
            }
            Float singleOrderTotal = new Float(0);
            List<OrderDetail> orderDetailList = orderDto.getOrderDetailList();
            for(OrderDetail orderDetail : orderDetailList) {
                orderDetail.setCreatedDatetime(orderDto.getCreatedDatetime());
                singleOrderTotal += orderDetail.getPrice() * orderDetail.getCount();
            }
            orderDetailsParam.addAll(orderDetailList);
            Float total = (Float) params.get("total");
            if (total == null) {
                total = new Float(0);
            }
            total += singleOrderTotal;


            params.put("total", total);
            params.put("orderDetails", orderDetailsParam);
            sendList.put(email, params);
        }
        return sendList;
    }



}
