package com.mulder.mysql.mapper.demo;

import com.mulder.base.mybatiscache.LoggingRedisCache;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by mulder on 16/5/16.
 */
@Repository("demoMysqlMapper")
@CacheNamespace(implementation = LoggingRedisCache.class,size = 512)
public interface DemoMysqlMapper {

    @Select("select count(1) from bank_code")
    int testCount();

    @Select("select * from bank_code")
    List<Map> testList();

    @Select("select bankname from bank_code where id=#{bankId}")
    @Options(useCache = true, flushCache = false, timeout = 10000)
    String tesCache(int bnakId);

    @Insert("insert into bank_code (bankname,bankcode) values(#{bankName},#{bankCode})")
    int testInsert();
}
