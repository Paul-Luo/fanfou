package info.fanfou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * author : chaoluo
 * date : 2015/8/16
 * depiction :
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @RequestMapping("/order")
    public String goToOrder() {
        return "redirect:/order";
    }


    @RequestMapping("/todo")
    public String goToShop() {
        return "redirect:/todo";
    }

    @RequestMapping("/bus")
    public String goToBus() {
        return "redirect:/bus";
    }

}
