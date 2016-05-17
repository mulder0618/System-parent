package com.mulder.aspect;

import com.mulder.base.MultipleDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by mulder on 16/5/17.
 */
@Component
@Aspect
public class MultipleDataSourceAspectAdvice {

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.mulder.base.*..*(..))")
    private void daoDirect(){ }//定义一个切入点

    /**
     * 决定使用何种数据源
     * @param target
     */
    private void databaseDirect(String target){
        if(target.indexOf("oracle")!=-1){
            MultipleDataSource.setDataSourceKey("oracleDataSource");
        }
        else  if(target.indexOf("mysql")!=-1){
            MultipleDataSource.setDataSourceKey("mysqlDataSource");
        }
    }

    @Around("daoDirect()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        String target = jp.getTarget().toString();
        this.databaseDirect(target);
        return jp.proceed();
    }
}