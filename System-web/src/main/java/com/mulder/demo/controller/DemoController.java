package com.mulder.demo.controller;

import com.mulder.demo.service.DemoService;
import com.mulder.utils.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
        //System.out.println(demoService.testMysqlConnect());
        List<Map> mysqlList = demoService.testMysqlList();
        System.out.println(mysqlList);
        mv.setViewName("/demo/demo");
        return mv;
    }

    @RequestMapping("/oracledemo")
    public ModelAndView oracleDemo(ModelAndView mv){
        //System.out.println(demoService.testOracleConnect());
        System.out.println(demoService.testOraclelList());
        mv.setViewName("/demo/demo");
        return mv;
    }


    // http://localhost:8080/mysqlpagedemo?pageIndex=1&pageSize=5
    @RequestMapping("/mysqlpagedemo")
    public ModelAndView mysqlpageDemo(Page page,ModelAndView mv){
        List<Map> pageList = demoService.testMysqlPagelList(page.getPageIndex(),page.getPageSize());
        mv.addObject("pageList",pageList);
        mv.setViewName("/demo/demo");
        return mv;
    }

    // http://localhost:8080/oraclepagedemo?pageIndex=1&pageSize=5
    @RequestMapping("/oraclepagedemo")
    public ModelAndView oraclepageDemo(Page page,ModelAndView mv){
        List<Map> pageList = demoService.testOraclePagelList(page.getPageIndex(),page.getPageSize());
        mv.addObject("pageList",pageList);
        mv.setViewName("/demo/demo");
        return mv;
    }

}
