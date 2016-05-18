package demo;

import com.mulder.oracle.mapper.demo.DemoOracleMapper;

/**
 * Created by mulder on 16/5/18.
 */
public class TestDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("sfsf");
        Class<?> clazz = Class.forName("com.mulder.oracle.mapper.demo.DemoOracleMapper.DemoOracleMapper");

        DemoOracleMapper demo = (DemoOracleMapper) clazz.newInstance();
        demo.getClass().getPackage();

    }
}
