package com.mulder.demo.mapper;

import com.mulder.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by mulder on 16/5/16.
 */
@Repository("demoDao")
public class DemoDao extends BaseDao {

    public int test(){
        return this.selectOne("testCount",null);
    }
}
