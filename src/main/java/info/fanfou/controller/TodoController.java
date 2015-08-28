package info.fanfou.controller;

import info.fanfou.util.BookStateHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * author : chaoluo
 * date : 2015/8/24
 * depiction :
 */
@Controller
@RequestMapping("/todo")
public class TodoController {

    @Resource
    private BookStateHelper bookStateHelper;

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView toDoView() {
        ModelAndView view = new ModelAndView("todo/todo");
        view.addObject("checked", bookStateHelper.todayIsAvailable());
        return view;
    }
}
