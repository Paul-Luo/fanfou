package info.fanfou.service;

import info.fanfou.db.dao.mapper.FeedbackMapper;
import info.fanfou.db.entity.Feedback;
import info.fanfou.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author : chaoluo
 * date : 2015/8/25
 * depiction :
 */
@Service
public class BusService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Resource
    private SessionUtil sessionUtil;

    public Feedback saveFeedBack(String content) {
        Feedback feedback = new Feedback();
        feedback.setUserId(sessionUtil.getLoginUser().getUserId());
        feedback.setContent(content);
        feedbackMapper.insertSelective(feedback);
        return feedback;
    }
}
