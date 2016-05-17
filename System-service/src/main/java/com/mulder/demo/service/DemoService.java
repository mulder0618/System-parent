package com.mulder.demo.service;

import com.mulder.demo.mapper.DemoDao;
import com.mulder.demo.mapper.DemoMapper;
import com.mulder.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mulder on 16/5/16.
 */
@Service("demoService")
public class DemoService {

    @Autowired
    DemoMapper demoMapper;

    @Autowired
    DemoDao demoDao;

    public void setRedisString(){
        RedisUtil.getInstance().setCacheString("test","^_^");
    }

    public String getRedisString(String key){
        return RedisUtil.getInstance().getCacheString(key);
    }

    public int testMysqlConnect(){
        return demoDao.test();
        //return demoMapper.testCount();
    }
}
