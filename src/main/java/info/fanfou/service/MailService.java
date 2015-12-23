package info.fanfou.service;

import info.fanfou.db.entity.OrderDetail;
import info.fanfou.dto.OrderDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
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

    public void sendDuringThe7DaysBill() throws FileNotFoundException, MessagingException {
        Map<String, Map<String, Object>> sendList = getDuringThe7SendList();
        sendMails(sendList);
    }

    public void sendConfirmedStateBill() throws FileNotFoundException, MessagingException {
        Map<String, Map<String, Object>> sendList = getConfirmedSendList();
        sendMails(sendList);
    }

    public Map<String, Map<String, Object>> getDuringThe7SendList() {
        List<OrderDto> orderDtos = orderService.queryDuringThe7DaysBeforeEffectOrder();
        return getSendList(orderDtos);
    }

    public Map<String, Map<String, Object>> getConfirmedSendList() {
        List<OrderDto> orderDtos = orderService.queryConfirmedOrders();
        return getSendList(orderDtos);
    }

    protected void sendMails(Map<String, Map<String, Object>> sendList) throws FileNotFoundException, MessagingException {
        if (MapUtils.isEmpty(sendList)) {
            return;
        }
        for (Map.Entry<String, Map<String, Object>> entry : sendList.entrySet()) {
            sendToUser(entry.getKey(), entry.getValue());
        }
    }

    public void sendToUser(String toEmail, Map<String, Object> context) throws MessagingException, FileNotFoundException {
        logger.info("send email to {}", toEmail);
        context.put("date", new DateTool());
        context.put("number", new NumberTool());
        context.put("math", new MathTool());
        String htmlText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/bill-template.vm", "UTF-8", context);
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper smm = new MimeMessageHelper(msg, true);
        smm.setFrom("Fanfou");
        smm.setTo(toEmail);
        smm.setSubject("Fanfou Bill");
        smm.setText(htmlText, true);
        File payFile = ResourceUtils.getFile("classpath:velocity/pay.jpg");
        smm.addInline("pay", payFile);
        // send email
        mailSender.send(msg);
    }


    protected Map<String, Map<String, Object>> getSendList(List<OrderDto> orderDtos) {
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
