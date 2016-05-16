package com.mulder.demo.service;

import com.mulder.utils.RedisUtil;
import org.springframework.stereotype.Service;

/**
 * Created by mulder on 16/5/16.
 */
@Service("demoService")
public class DemoService {

    public void setRedisString(){
        RedisUtil.getInstance().setCacheString("test","^_^");
    }

    public String getRedisString(String key){
        return RedisUtil.getInstance().getCacheString(key);
    }
}
