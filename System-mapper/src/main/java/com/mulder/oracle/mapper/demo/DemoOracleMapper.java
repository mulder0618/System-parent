package com.mulder.oracle.mapper.demo;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by mulder on 16/5/17.
 */
@Repository("demoOracleMapper")
public interface DemoOracleMapper {

    @Select("select count(1) from ORDER_LIST t")
    int testCount();
}
