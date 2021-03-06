package com.mulder.demo.controller;

import com.mulder.demo.service.DemoService;
import com.mulder.utils.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
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
        List<Map> mysqlList = demoService.getMysqlList();
        System.out.println(mysqlList);
        mv.setViewName("/demo/demo");
        return mv;
    }

    @RequestMapping("/oracledemo")
    public ModelAndView oracleDemo(ModelAndView mv){
        //System.out.println(demoService.testOracleConnect());
        System.out.println(demoService.getOraclelList());
        mv.setViewName("/demo/demo");
        return mv;
    }


    // http://localhost:8080/mysqlpagedemo?pageIndex=1&pageSize=5
    @RequestMapping("/mysqlpagedemo")
    public ModelAndView mysqlpageDemo(Page page,ModelAndView mv){
        List<Map> pageList = demoService.getMysqlPagelList(page.getPageIndex(),page.getPageSize());
        mv.addObject("pageList",pageList);
        mv.setViewName("/demo/demo");
        return mv;
    }

    // http://localhost:8080/oraclepagedemo?pageIndex=1&pageSize=5
    @RequestMapping("/oraclepagedemo")
    public ModelAndView oraclepageDemo(Page page,ModelAndView mv){
        List<Map> pageList = demoService.getOraclePagelList(page.getPageIndex(),page.getPageSize());
        mv.addObject("pageList",pageList);
        mv.setViewName("/demo/demo");
        return mv;
    }


    @RequestMapping("/mysqlcachedemo")
    public ModelAndView mysqlOnecacheDemo(ModelAndView mv){
        System.out.println(demoService.getCacheResult());
        mv.setViewName("/demo/demo");
        return mv;
    }

    @RequestMapping("/mysqlinsertdemo")
    public ModelAndView mysqlInsertDemo(ModelAndView mv){
        System.out.println(demoService.setCode());
        mv.setViewName("/demo/demo");
        return mv;
    }

    @RequestMapping("/mysqltransactiondemo")
    public ModelAndView mysqlTransactionDemo(ModelAndView mv){
        demoService.testTransaction();
        mv.setViewName("/demo/demo");
        return mv;
    }

    @RequestMapping("/bootstrapdemo")
    public ModelAndView bootstrapDemo(ModelAndView mv){
        mv.setViewName("/demo/bootstrap");
        return mv;
    }

    public static void main(String[] args){
        List list = new ArrayList();
        Map map;
        for(int i=0;i<10;i++){
            map = new HashMap();
            map.put("title",i);
            list.add(map);
        }
        System.out.println(list.toString());
        //程序解释 如果map在for循环之外建立 map只有一个对象引用,list.add实际保存为数组方式  数组保存了对象的引用 map不在内部防止建立多个无用的引用节省栈空间
    }

}
