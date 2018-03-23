package com.controller;

import com.service.CreditAccountService;
import com.service.CustomerService;
import com.service.StaffOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuangDanGeeker on 2018/3/18.
 * controller : dealing with staff operation request
 */
@Controller
@RequestMapping("/staff")
public class SttafOperationController {

    @Resource
    private StaffOperationService staffOperationService;
    @Resource
    private CreditAccountService creditCardService;
    @Resource
    private CustomerService customerService;

    @RequestMapping("/createcreditcard/{custmNo}/{cardNo}")
    @ResponseBody
    public Map<String,String> createcreditcard(@PathVariable String custmNo, @PathVariable String cardNo){
        System.out.println("createcreditcard");
        System.out.println("cutmNo " + custmNo);
        System.out.println("creditCardNum " + cardNo);

        creditCardService.createCreditCard(custmNo, cardNo);

        Map result = new HashMap<String, String>();
        result.put("status", "success");
        return result;
    }
    @RequestMapping("/queryCustmNo")
    @ResponseBody
    public String queryCustmNo(){
        System.out.println("queryCustmNo");
        return String.valueOf(customerService.generateNo());

    }

    @RequestMapping("/deposit/{custmNo}/{creditCardNum}/{nums}/{type}/{dueTime}")
    @ResponseBody
    public Map<String,String> deposit(@PathVariable String custmNo, @PathVariable String creditCardNum, @PathVariable Integer nums, @PathVariable Integer type,@PathVariable String dueTime){
        System.out.println("deposit");
        System.out.println("cutmNo " + custmNo);
        System.out.println("creditCardNum " + creditCardNum);
        System.out.println("nums " + nums);
        System.out.println("type " + type);
        if(type == 1){
            LocalDate date = LocalDate.now();
            int month = Integer.valueOf(date.getMonth().toString());
            month = month + Integer.valueOf(dueTime);
            int year = date.getYear() + month/12;
            LocalDate endDate = LocalDate.of(year, (month % 12 + 1), date.getDayOfMonth());
            System.out.println("dueTime " + year +" "+ (month % 12 + 1) +" "+ date.getDayOfMonth());
            String realDueTime = "";    //TODO 确定日期的格式
            creditCardService.deposit(custmNo, creditCardNum, nums, realDueTime);
        }else{
            creditCardService.deposit(custmNo, creditCardNum, nums, dueTime);
        }

        Map result = new HashMap<String, String>();
        result.put("status", "success");
        return result;
    }



}
