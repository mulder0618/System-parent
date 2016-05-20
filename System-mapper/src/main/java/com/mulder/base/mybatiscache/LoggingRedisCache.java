package com.mulder.base.mybatiscache;

import org.apache.ibatis.cache.decorators.LoggingCache;

/**
 * Created by mulder on 16/5/20.
 */
public class LoggingRedisCache extends LoggingCache {

    public LoggingRedisCache(String id) {
        super(new RedisCache(id));
    }

}