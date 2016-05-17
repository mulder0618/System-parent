package com.mulder.mysql.mapper.demo;

import org.springframework.stereotype.Repository;

/**
 * Created by mulder on 16/5/16.
 */
@Repository("demoMysqlMapper")
public interface DemoMysqlMapper {

    int testCount();
}
