package com.mulder.demo.service;

import com.mulder.mysql.mapper.demo.DemoMysqlDao;
import com.mulder.oracle.mapper.demo.DemoOracleDao;
import com.mulder.utils.cache.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        return  demoMysqlDao.selectOne("testCount");
    }

    public int testOracleConnect(){
        return  demoOracleDao.selectOne("testCount",null);
    }

    public List<Map> testMysqlList(){
        return demoMysqlDao.selectList("testList");
    }

    public List<Map> testOraclelList(){
        return demoOracleDao.selectList("testList");
    }

    public List<Map> testMysqlPagelList(int pageIndex,int pageSize){
        List<Map> pageList = demoMysqlDao.selectPageList("testList",pageIndex,pageSize);
        return pageList;
    }

    public List<Map> testOraclePagelList(int pageIndex,int pageSize){
        List<Map> pageList = demoOracleDao.selectPageList("testList",pageIndex,pageSize);
        return pageList;
    }

}
