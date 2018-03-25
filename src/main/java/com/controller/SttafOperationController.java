package com.controller;

import com.bean.CreditCard;
import com.bean.StaffOperRecord;
import com.service.CreditAccountService;
import com.service.CustomerService;
import com.service.StaffOperationService;
import com.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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
    @Resource
    private StaffService staffService;

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
    @RequestMapping("/generateCreditCardNo")
    @ResponseBody
    public String generateCreditCardNo(){
        System.out.println("generateCreditCardNo");
        return creditCardService.generateCreditCardNo();
    }

    @RequestMapping("/deposit/{custmNo}/{creditCardNum}/{nums}/{type}/{dueTime}")
    @ResponseBody
    public Map<String,String> deposit(@PathVariable String custmNo, @PathVariable String creditCardNum, @PathVariable Integer nums, @PathVariable Integer type,@PathVariable String dueTime){
        System.out.println("deposit");
        System.out.println("cutmNo " + custmNo);
        System.out.println("creditCardNum " + creditCardNum);
        System.out.println("nums " + nums);
        System.out.println("type " + type);
        System.out.println("dueTime " + dueTime);
        Map result = new HashMap<String, String>();

        //活期&&定期存储的到期时间处理
        if(type == 1){
            if(!cheackDueTime(LocalDate.parse(dueTime))){
                result.put("status", "failure");
                result.put("reason", "invaild Time : dueTime is prior to today");
                return result;
            }
            creditCardService.deposit(custmNo, creditCardNum, nums, dueTime);
        }else{
            int monthScape = 0;
            int yearScape = 0;
            switch(dueTime){
                case "1":monthScape=3;break;
                case "2":monthScape=6;break;
                case "3":yearScape=1;break;
                case "4":yearScape=2;break;
                case "5":yearScape=3;break;
            }
            LocalDate date = LocalDate.now();
            int month = date.getMonth().getValue();
            month = month + monthScape;
            int year = yearScape + date.getYear() + month/12;
            LocalDate endDate = LocalDate.of(year, (month % 12 + 1), date.getDayOfMonth());
            System.out.println(endDate.toString());
            if(!cheackDueTime(endDate)){
                result.put("status", "failure");
                result.put("reason", "invaild Time : dueTime is prior to today");
                return result;
            }
            creditCardService.deposit(custmNo, creditCardNum, nums, endDate.toString());
        }


        result.put("status", "success");
        return result;
    }


    private boolean cheackDueTime(LocalDate dueTime){
        LocalDate now = LocalDate.now();
        if(dueTime.isBefore(now)){
            return false;
        }
        return true;
    }


    @RequestMapping("/queryCustmIntrest/{custmNo}")
    @ResponseBody
    public Map<String, Object> queryCustmIntrest(@PathVariable("custmNo") String custmNo){
        System.out.println("queryCustmIntrest");
        System.out.println("custmNo " + custmNo);
        Map<String, Object> result = new HashMap<>(5,5);
        //检查用户账号存在情况
        if(!customerService.checkCustomer(custmNo)){
            result.put("status", "error");
            result.put("reason", "the custmNo is invalid");
            return result;
        }

        List<CreditCard> list = creditCardService.queryCreditCardRecord(custmNo);
        result.put("status", "success");
        result.put("list", list);
        result.put("count", list.size());
        return result;

    }

    @RequestMapping("/queryStaffOperRecord/{staffNo}")
    @ResponseBody
    public Map<String, Object> queryStaffOperRecord(@PathVariable("staffNo") String staffNo){
        System.out.println("queryCustmIntrest");
        System.out.println("staffNo " + staffNo);
        Map<String, Object> result = new HashMap<>(5,5);
        //检查用户账号存在情况
        if(!staffService.checkStaff(staffNo)){
            result.put("status", "error");
            result.put("reason", "the staffNo is invalid");
            return result;
        }
        List<StaffOperRecord> list = staffOperationService.queryRecord(staffNo);
        result.put("status", "success");
        result.put("list", list);
        result.put("count", list.size());
        return result;

    }

}
