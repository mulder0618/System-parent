package com.mulder.aspect;

import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;
import com.mulder.base.MultipleDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;

/**
 * Created by mulder on 16/5/17.
 */
@Component
@Aspect
public class MultipleDataSourceAspectAdvice {

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.mulder.base.*..*(..))")
    private void daoDirect(){ }//定义一个切入点

    @Resource(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory;

    private void setDialectClass(String dialectClass){
        //获取当前mybatis拦截器列表
        List<Interceptor> mybatisInterceptors = sqlSessionFactory.getConfiguration().getInterceptors();
        Properties mybatisInterceptorProperties = new Properties();
        mybatisInterceptorProperties.put("dialectClass",dialectClass);
        mybatisInterceptors.get(0).setProperties(mybatisInterceptorProperties);
    }

    /**
     * 决定使用何种数据源
     * @param target
     */
    private void databaseDirect(String target){
        if(target.indexOf("oracle")!=-1){
            MultipleDataSource.setDataSourceKey("oracleDataSource");
            //动态修改分页拦截器
            this.setDialectClass("com.github.miemiedev.mybatis.paginator.dialect.OracleDialect");
        }
        else if(target.indexOf("mysql")!=-1){
            MultipleDataSource.setDataSourceKey("mysqlDataSource");
            //动态修改分页拦截器
            this.setDialectClass("com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect");
        }
    }

    @Around("daoDirect()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        String target = jp.getTarget().toString();
        this.databaseDirect(target);
        return jp.proceed();
    }
}