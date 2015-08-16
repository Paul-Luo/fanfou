package info.fanfou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author : chaoluo
 * date : 2015/8/16
 * depiction :
 */
@Controller
public class ApplicationController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
