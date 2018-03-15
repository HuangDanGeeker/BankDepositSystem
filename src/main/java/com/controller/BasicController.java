package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by HuangDanGeeker on 2018/3/15.
 * Basic controller for test
 */
@Controller
public class BasicController {

    @RequestMapping("/basic")
    public String basic(){
        return "basic";
    }

}
