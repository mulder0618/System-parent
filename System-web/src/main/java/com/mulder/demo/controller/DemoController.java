package com.mulder.demo.controller;

import com.mulder.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mulder on 16/5/16.
 */
@Controller
public class DemoController {

    @Autowired
    DemoService demoService;

    @RequestMapping("/redisdemo")
    public ModelAndView redisDemo(ModelAndView mv){
        demoService.setRedisString();
        System.out.println(demoService.getRedisString("test"));
        mv.setViewName("/demo/demo");
        return mv;
    }

    @RequestMapping("/mysqldemo")
    public ModelAndView mysqlDemo(ModelAndView mv){
        System.out.println(demoService.testMysqlConnect());
        mv.setViewName("/demo/demo");
        return mv;
    }

    @RequestMapping("/oracledemo")
    public ModelAndView oracleDemo(ModelAndView mv){
        System.out.println(demoService.testOracleConnect());
        mv.setViewName("/demo/demo");
        return mv;
    }
}
