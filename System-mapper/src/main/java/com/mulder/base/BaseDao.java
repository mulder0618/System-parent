package com.mulder.base;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mulder.utils.cache.RedisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Around;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by mulder on 16/5/16.
 */
public class BaseDao extends SqlSessionDaoSupport implements Serializable {


    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Autowired
    protected SqlSessionFactoryBean sqlSessionFactory;

    /**
     * 自动分页处理器
     * @param sqlId
     * @param sqlParameter
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    protected List<?> getPageList( String sqlId,
                                  Object sqlParameter, int pageIndex, int pageSize) throws Exception {
        SqlSession session = null;
        try {
            SqlSessionFactory sessionFactory = sqlSessionFactory.getObject();
            session = SqlSessionUtils.getSqlSession(sessionFactory);
            if (pageIndex <= 0) {
                pageIndex = 1;
            }
            if (pageSize <= 0) {
                pageSize = 10;
            }
            PageBounds pageBounds = new PageBounds(pageIndex, pageSize);
            return session.selectList(sqlId,
                    sqlParameter, pageBounds);
        } finally {
            session.close();
        }
    }

    /**
     * 查询无参单个结果集
     * @param sqlID
     * @param <T>
     * @return
     */
    public <T>T selectOne(String sqlID) {
        return this.getSqlSession().selectOne(sqlID);
    }

    /**
     * 查询有参单个结果集
     * @param sqlID
     * @param queryObject
     * @param <T>
     * @return
     */
    public <T>T selectOne(String sqlID, Object queryObject) {
        return this.getSqlSession().selectOne(sqlID,queryObject);
    }


    /**
     * 查询无参列表
     * @param sqlID
     * @param <T>
     * @return
     */
    public <T>T selectList(String sqlID) {
        return (T) this.getSqlSession().selectList(sqlID);
    }

    /**
     * 查询有参列表
     * @param sqlID
     * @param <T>
     * @return
     */
    public <T>T selectList(String sqlID, Object queryObject) {
        return (T) this.getSqlSession().selectList(sqlID,queryObject);
    }


    /**
     * 插入数据
     * @param sqlID
     * @param values
     * @return
     */
    public int insert(String sqlID,Object values){
        return this.getSqlSession().insert(sqlID,values);
    }

    /**
     * 更新数据
     * @param sqlID
     * @param values
     * @return
     */
    public int update(String sqlID,Object values){
        return this.getSqlSession().update(sqlID,values);
    }

    /**
     * 查询有参分页
     * @param sqlID
     * @param queryObject
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageList<Map> selectPageList(String sqlID, Object queryObject,int pageIndex,int pageSize) {
        PageList<Map> pageList = null;
        try {
            pageList = (PageList<Map>) getPageList(sqlID, queryObject, pageIndex, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageList;
    }

    /**
     * 查询无参分页
     * @param sqlID
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageList<Map> selectPageList(String sqlID,int pageIndex,int pageSize) {
        PageList<Map> pageList = null;
        try {
            pageList = (PageList<Map>) getPageList(sqlID, null, pageIndex, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageList;
    }

}
