package com.mulder.demo.controller;

import com.mulder.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mulder on 16/5/16.
 */
@Controller
public class DemoController {

    @Autowired
    DemoService demoService;

    @RequestMapping("/redisdemo")
    public void redisDemo(){
        demoService.setRedisString();
        System.out.println(demoService.getRedisString("test"));
    }
}
