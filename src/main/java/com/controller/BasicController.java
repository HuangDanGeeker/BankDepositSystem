package com.controller;

import com.service.CustomerService;
import com.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuangDanGeeker on 2018/3/15.
 * Basic controller for test
 */

@Controller
public class BasicController {

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
        if(customerService.checkCustomer(userNo, userPasswd, userName, null)){
            result.put("loginStatus", "success");
            result.put("loginRole", "custm");
            result.put("userName", userName);
            result.put("userNo", userNo);
            return result;
        }else if (staffService.checkStaff(userNo, userPasswd, userName, null)){
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
        Integer registNo = -1;
        Map result = new HashMap<String, String>();
        if(registRole.equalsIgnoreCase("customer")){
            //已经存在相同用户名用户生日的用户账号
            if(customerService.checkCustomer(null, registName,null,registBirthday)){
                result.put("registStatus", "failure");
                result.put("reason", "the customer is already exist!");
                return result;
            }
            registNo = customerService.generateNo();
            customerService.regist(registName, registNo, registPasswd, registBirthday, registPhone, registAddress);
        }else if(registRole.equalsIgnoreCase("staff")){
            //已经存在相同柜员名柜员生日的柜员账号
            if(staffService.checkStaff(null, registName,null,registBirthday)){
                result.put("registStatus", "failure");
                result.put("reason", "the staff is already exist!");
                return result;
            }
            registNo = staffService.generateNo();
            staffService.regist(registName, registNo, registPasswd, registBirthday, registPhone);
        }

        result.put("registStatus", "success");
        result.put("registNo", registNo);
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
