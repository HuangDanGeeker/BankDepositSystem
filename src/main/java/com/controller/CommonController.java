package com.controller;

import com.bean.StaffOperRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/checkUser/{userName}")
    @ResponseBody
    public Map<Object, Object> checkUser(@PathVariable String userName){
        System.out.println("checkUser");

        Map<Object, Object> result = new HashMap<Object, Object>();
        List<String> list = new ArrayList<String>();
        result.put("resultList", list);
        result.put("count", list.size());
        result.put("count", 0);
        return result;
    }
}
