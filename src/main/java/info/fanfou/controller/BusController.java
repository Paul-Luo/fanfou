package info.fanfou.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import info.fanfou.db.custom.dao.FeedBackDao;
import info.fanfou.db.custom.dao.LineDao;
import info.fanfou.db.entity.Feedback;
import info.fanfou.service.BusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * author : lch
 * date : 2015/4/10
 * depiction :
 */
@Controller
public class BusController {

    private Logger log = LoggerFactory.getLogger(BusController.class);

    @Autowired
    private LineDao lineDao;

    @Resource
    private BusService busService;

    @RequestMapping(value = "/bus/view", method = RequestMethod.GET)
    public ModelAndView busView() {
        ModelAndView view = new ModelAndView("bus/bus");
        return view;
    }

    @RequestMapping("/line/{lineNumber}")
    @ResponseBody
    public List<Map<String, Object>> queryLinePath(@PathVariable Integer lineNumber) {
        log.info("line number is {}", lineNumber);
        return lineDao.selectLinePath(lineNumber);
    }

    @RequestMapping("/line/all")
    @ResponseBody
    public List<Map<String, Object>> queryAllLinePath() {
        return lineDao.selectAllLinePath();
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    @ResponseBody
    public Feedback addFeedBack(@RequestParam("content") String content) {
        return busService.saveFeedBack(content);
    }

}
