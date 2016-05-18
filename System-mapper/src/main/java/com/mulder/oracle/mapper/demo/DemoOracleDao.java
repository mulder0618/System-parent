package com.mulder.oracle.mapper.demo;

import com.mulder.base.BaseDao;
import com.mulder.base.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by mulder on 16/5/16.
 */
@Repository("demoOracleDao")
@Mapper(name="DemoOracleMapper")
public  class DemoOracleDao extends BaseDao {

    //可以实现自定义方法
}
