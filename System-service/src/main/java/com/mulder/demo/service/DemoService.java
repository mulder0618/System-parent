package com.mulder.demo.service;

import com.mulder.mysql.mapper.demo.DemoMysqlDao;
import com.mulder.oracle.mapper.demo.DemoOracleDao;
import com.mulder.utils.cache.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
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
        new RedisUtil().setCache("test","^_^");
    }

    public String getRedisString(String key){
        return new RedisUtil().getCache(key);
    }

    public int getMysqlConnect(){
        return  demoMysqlDao.selectOne("testCount");
    }

    public int getOracleConnect(){
        return  demoOracleDao.selectOne("testCount",null);
    }

    public List<Map> getMysqlList(){
        return demoMysqlDao.selectList("testList");
    }

    public List<Map> getOraclelList(){
        return demoOracleDao.selectList("testList");
    }

    public List<Map> getMysqlPagelList(int pageIndex,int pageSize){
        List<Map> pageList = demoMysqlDao.selectPageList("testList",pageIndex,pageSize);
        return pageList;
    }

    public List<Map> getOraclePagelList(int pageIndex,int pageSize){
        List<Map> pageList = demoOracleDao.selectPageList("testList",pageIndex,pageSize);
        return pageList;
    }

    public String getCacheResult(){
        Map param = new HashMap();
        param.put("bankId",1);
        String cacheResut = demoMysqlDao.selectOne("tesCache",param);
        return cacheResut;
    }

    public int setCode(){
        Map values = new HashMap();
        values.put("bankName","test1");
        values.put("bankCode","90");
        return demoMysqlDao.insert("testInsert",values);
    }

    @Transactional
    public void testTransaction(){
        Map values = new HashMap();
        values.put("bankName","test1");
        values.put("bankCode","90");
        demoMysqlDao.insert("testInsert",values);
        Map valuesRollback = new HashMap();
        valuesRollback.put("bankName","test1");
        values.put("bankCode","91");
        //valuesRollback.put("bankCode","46546464646ghfdgdfgdsfsdfsdfsdfsdfsdfsdfsdfdfs64");
        demoMysqlDao.insert("testInsert",valuesRollback);
        int a = 3/0;
    }

}
