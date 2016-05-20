package com.mulder.base.aspect;

import com.mulder.base.Mapper;
import com.mulder.base.MultipleDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MemberSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.Policy;

/**
 * Created by mulder on 16/5/17.
 */
@Component
@Aspect
public class MapperAutoAspectAdvice {

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.mulder.base.BaseDao..*(..))")
    private void getMapperlocation() {
    }//定义一个切入点

    /**
     * 获取mapper物理位置
     * @param daoClass
     * @return
     */
    private String getMapperLocation(Class daoClass) {
        String packLocation = daoClass.getPackage().getName();
        Mapper mapper = (Mapper) daoClass.getAnnotation(Mapper.class);
        String mapperName = mapper.name();
        StringBuffer sb = new StringBuffer();
        sb.append(packLocation);
        sb.append(".");
        sb.append(mapperName);
        return sb.toString();
    }

    private Object[] getSqlID(Object[] args,String mapperLocation){
        String sqlID = args[0].toString();
        StringBuffer sb = new StringBuffer();
        sb.append(mapperLocation);
        sb.append(".");
        sb.append(sqlID);
        args[0] = sb.toString();
        return args;
    }


    @Around("getMapperlocation()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        String target = jp.getTarget().toString();
        Class daoClass = Class.forName(jp.getTarget().getClass().getName()) ;
        //获取目标mapper位置
        String mapperLocation = this.getMapperLocation(daoClass);
        //获取源调用方法参数值
        Object[] args = jp.getArgs();
        //设置最终调用sqlID
        return jp.proceed(this.getSqlID(args,mapperLocation));
    }
}