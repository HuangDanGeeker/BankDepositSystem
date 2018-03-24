package com.controller;

import com.bean.Customer;
import com.bean.StaffOperRecord;
import com.service.CustomerService;
import com.sun.tracing.dtrace.ProviderAttributes;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangDanGeeker on 2018/3/18.
 * Controller for common and ural request
 */
@Controller
public class CommonController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("/checkUser/{userName}")
    @ResponseBody
    public Map<String, Object> checkUser(@PathVariable String userName){
        System.out.println("checkUser");
        Map<String, Object> result = new HashMap<String, Object>();
        List<String> list = customerService.checkCustomer(userName);
        result.put("resultList", list);
        result.put("count", list.size());
        return result;
    }

    @RequestMapping("/checkUser/{userName}/{userNo}")
    @ResponseBody
    public Map<String, Object> checkUserWithUserNo(@PathVariable String userName, @PathVariable("userNo") String userNo){
        System.out.println("checkUserWithUserNo");
        System.out.println("userName " + userName);
        System.out.println("userNo " + userNo);
        Map<String, Object> result = new HashMap<String, Object>();
        List<Customer> list = customerService.queryCustomer(userNo, userName);
        // we assume that it always get one and only one result
        result.put("custm", list.get(0).reFormat());
        result.put("count", list.size());
        return result;
    }
}
