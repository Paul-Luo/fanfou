package info.fanfou.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * author : chaoluo
 * date : 2015/12/21
 * depiction :
 */
@RequestMapping("/bill")
public class BillController {

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView toDoView() {
        ModelAndView view = new ModelAndView("bill/bill");
//        view.addObject("checked", bookStateHelper.todayIsAvailable());
        return view;
    }

}
