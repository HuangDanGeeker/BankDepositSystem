package com.controller;

import com.service.CommonService;
import com.service.CustomerService;
import com.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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

    @Resource
    private CommonService commonService;
    @Resource
    private CustomerService customerService;
    @Resource
    private StaffService staffService;



    @RequestMapping("/basic")
    public String basic(){
        return "basic";
    }

    @RequestMapping("/loginpage")
    public String loginPage(){
        System.out.println("loginPage");
        return "login";
    }

    @RequestMapping("/login/{userName}/{userNo}/{userPasswd}")
    @ResponseBody
    public Map<Object, String> login(@PathVariable String userName, @PathVariable String userNo, @PathVariable String userPasswd){
        System.out.println("login");
        System.out.println("userName " + userName);
        System.out.println("userPasswd " + userPasswd);
        // return cookie as 'remember me ' request
        Map result = new HashMap<String, String>();
        if(customerService.cheakCustomer(userName, userPasswd, userPasswd)){
            result.put("loginStatus", "success");
            result.put("loginRole", "custm");
            result.put("userName", userName);
            result.put("userNo", userNo);
            return result;
        }else if (staffService.checkStaff(userName, userPasswd, userPasswd)){
            result.put("loginStatus", "success");
            result.put("loginRole", "staff");
            result.put("userName", userName);
            result.put("userNo", userNo);
            return result;
        }
        result.put("loginStatus", "failed");
        return result;
    }

    @RequestMapping("/regist/{registName}/{registRole}/{registPasswd}/{registBirthday}/{registPhone}/{registAddress}")
    @ResponseBody
    public Map<String, String> login(@PathVariable String registName, @PathVariable String registRole,@PathVariable String registPasswd, @PathVariable String registBirthday, @PathVariable String registPhone, @PathVariable String registAddress){
        System.out.println("regist");
        System.out.println("registName " + registName);
        System.out.println("registRole" + registRole);
        System.out.println("registPasswd " + registPasswd);
        System.out.println("registBirthday " + registBirthday);
        System.out.println("registPhone " + registPhone);
        System.out.println("registAddress " + registAddress);
        if(registRole.equalsIgnoreCase("custm")){
            customerService.regist(registName, customerService.generateNo(), registPasswd, registBirthday, registPhone, registAddress);
        }else if(registRole.equalsIgnoreCase("staff")){
            staffService.regist(registName, staffService.generateNo(), registPasswd, registBirthday, registPhone);
        }
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
