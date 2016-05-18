package com.mulder.mysql.mapper.demo;

import com.mulder.base.BaseDao;
import com.mulder.base.Mapper;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Repository;

/**
 * Created by mulder on 16/5/16.
 */
@Repository("demoMysqlDao")
@Mapper(name="DemoMysqlMapper")
public class DemoMysqlDao extends BaseDao {


    //此处可以实现自定也方法
}
