package com.mulder.mysql.mapper.demo;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by mulder on 16/5/16.
 */
@Repository("demoMysqlMapper")
public interface DemoMysqlMapper {

    @Select("select count(1) from bank_code")
    int testCount();
}
