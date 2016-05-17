package com.mulder.demo.service;

import com.mulder.mysql.mapper.demo.DemoMysqlDao;
import com.mulder.oracle.mapper.demo.DemoOracleDao;
import com.mulder.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by mulder on 16/5/16.
 */
@Service("demoService")
public class DemoService {

    @Resource(name = "demoMysqlDao")
    DemoMysqlDao demoMysqlDao;

    @Resource(name = "demoOracleDao")
    DemoOracleDao demoOracleDao;

    public void setRedisString(){
        RedisUtil.getInstance().setCacheString("test","^_^");
    }

    public String getRedisString(String key){
        return RedisUtil.getInstance().getCacheString(key);
    }

    public int testMysqlConnect(){
        return demoMysqlDao.selectOne("com.mulder.mysql.mapper.demo.DemoMysqlMapper.testCount",null);
    }

    public int testOracleConnect(){
        return demoOracleDao.selectOne("com.mulder.oracle.mapper.demo.DemoOracleMapper.testCount",null);
    }
}
