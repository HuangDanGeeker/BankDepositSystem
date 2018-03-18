package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuangDanGeeker on 2018/3/18.
 * controller : dealing with staff operation request
 */
@Controller
@RequestMapping("/staff")
public class SttafOperationController {

    @RequestMapping("/createcreditcard/{custmNo}/{creditCardNum}")
    @ResponseBody
    public Map<String,String> createcreditcard(@PathVariable String custmNo, @PathVariable String creditCardNum){
        System.out.println("createcreditcard");
        System.out.println("cutmNo " + custmNo);
        System.out.println("creditCardNum " + creditCardNum);

        Map result = new HashMap<String, String>();
        result.put("status", "success");
        return result;
    }

    @RequestMapping("/deposit/{custmNo}/{creditCardNum}/{nums}/{type}/{dueTime}")
    @ResponseBody
    public Map<String,String> deposit(@PathVariable String custmNo, @PathVariable String creditCardNum, @PathVariable Integer nums, @PathVariable Integer type,@PathVariable String dueTime){
        System.out.println("deposit");
        System.out.println("cutmNo " + custmNo);
        System.out.println("creditCardNum " + creditCardNum);
        System.out.println("nums " + nums);

        System.out.println("type " + type);
        LocalDate date = LocalDate.now();
        int month = Integer.valueOf(date.getMonth().toString());
        month = month + Integer.valueOf(dueTime);
        int year = date.getYear() + month/12;
        LocalDate endDate = LocalDate.of(year, (month % 12 + 1), date.getDayOfMonth());
        System.out.println("dueTime " + year +" "+ (month % 12 + 1) +" "+ date.getDayOfMonth());

        Map result = new HashMap<String, String>();
        result.put("status", "success");
        return result;
    }



}
