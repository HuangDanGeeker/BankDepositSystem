package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

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

    @RequestMapping("/loginpage")
    public String loginPage(){
        System.out.println("loginPage");
        return "login";
    }

    @RequestMapping("/login/{userName}/{userPasswd}")
    @ResponseBody
    public String login(@PathVariable String userName, @PathVariable String userPasswd){
        System.out.println("login");
        System.out.println("userName" + userName);
        System.out.println("userPasswd" + userPasswd);
        return "{'ssss':'2'}";
    }

    @RequestMapping("/depositpage")
    public String depositPage(){
        return "deposit";
    }


    @RequestMapping(value="/delete/{userId}/{friendId}/{fullDelete}", method=RequestMethod.GET)
    @ResponseBody
    public String deleteFriend(@PathVariable String userId, @PathVariable String friendId, @PathVariable String fullDelete){
        System.out.println("=====> deleteFriend");
        System.out.println("userID " + userId);
        System.out.println("friendId " + friendId);
        System.out.println("fullDelete " + fullDelete);

        return "{\"result\":1}";
    }

}
