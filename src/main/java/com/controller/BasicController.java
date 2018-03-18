package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
/**
 * Created by HuangDanGeeker on 2018/3/15.
 * Basic controller for test
 */
@Controller
public class BasicController {

    private static Logger logger = Logger.getLogger(BasicController.class);

    @RequestMapping("/basic")
    public String basic(){
        return "basic";
    }

    @RequestMapping("/loginpage")
    public String loginPage(){
        System.out.println("loginPage");
        return "login";
    }

    @RequestMapping("/login/{userName}/{userPasswd}")
    @ResponseBody
    public Map<Object, String> login(@PathVariable String userName, @PathVariable String userPasswd){
        System.out.println("login");
        System.out.println("userName " + userName);
        System.out.println("userPasswd " + userPasswd);
        // return cookie as 'remember me ' request
        Map result = new HashMap<String, String>();
        result.put("loginStatus", "success");
        result.put("loginRole", "staff");
        return result;
    }

    @RequestMapping("/regist/{registName}/{registPasswd}/{registBirthday}/{registPhone}/{registAddress}")
    @ResponseBody
    public Map<String, String> login(@PathVariable String registName, @PathVariable String registPasswd, @PathVariable String registBirthday, @PathVariable String registPhone, @PathVariable String registAddress){
        System.out.println("regist");

        Map result = new HashMap<String, String>();
        result.put("registStatus", "success");
        return result;
    }

    @RequestMapping("/main/{type}")
    public String mainpage(@PathVariable String type) {
        if (type.equalsIgnoreCase("staff")) {
            return "main_staff";
        } else if (type.equalsIgnoreCase("custm")){
            return "main_custm";
        }
        return "loginpage";
    }

    @RequestMapping("/depositpage")
    public String depositPage(){
        return "deposit";
    }




}
