package com.mulder.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Around;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mulder on 16/5/16.
 */
public class BaseDao extends SqlSessionDaoSupport {

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 查询单个结果集
     * @param sqlID
     * @param queryObject
     * @param <T>
     * @return
     */
    public <T>T selectOne(String sqlID, Object queryObject){
        return this.getSqlSession().selectOne(sqlID,queryObject);
    }




}
