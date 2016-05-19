package com.mulder.utils.cache;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by mulder on 16/5/13.
 */
public class RedisUtil {

    private static JedisPool jedisPool = null;
    private static RedisUtil redisUtil = null;

    private RedisUtil() {}

    public static RedisUtil getInstance(){
        if (jedisPool == null) {
            synchronized (RedisUtil.class) {
                if (jedisPool == null) {
                    ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"redis.xml"});
                    jedisPool = (JedisPool) ctx.getBean("jedisPool");
                    redisUtil = new RedisUtil();
                }
            }
        }
        return redisUtil;
    }

    public void setCacheString(String key,String val)
    {
        Jedis client = jedisPool.getResource();
        client.set(key,val);
        jedisPool.returnBrokenResource(client);
    }


    public String  getCacheString(String key)
    {  Jedis client = jedisPool.getResource();
        String result = client.get(key);
        jedisPool.returnBrokenResource(client);
        return  result;
    }

    public static void main(String[] args){
        RedisUtil redisUtil = RedisUtil.getInstance();
        redisUtil.setCacheString("demo","^_^");
        System.out.println(redisUtil.getCacheString("demo"));
    }
}
