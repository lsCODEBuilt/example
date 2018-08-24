package com.ls.commom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Redis Service
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key,Object value){

        boolean result = false;
        try {
            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            result = true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param key
     * @param value
     * @param expireTime
     */
    public void set(final byte[] key,final byte[] value,final long expireTime){
       redisTemplate.execute(new RedisCallback() {
           @Override
           public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
               redisConnection.set(key,value);
               if(expireTime > 0){
                   redisConnection.expire(key,expireTime);
               }
               return 1L;
           }
       });
    }

    /**
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(final String key,Object value,final long expireTime){
        boolean result = false;
        try {
            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            if(expireTime > 0){
                redisTemplate.expire(key,expireTime, TimeUnit.SECONDS);
            }

            result = true ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * del key
     * @param key
     */
    public void remove(final String key){
        if(exists(key)){
            redisTemplate.delete(key);
        }
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.del(key.getBytes());
                return 1L;
            }
        });
    }
    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }



}
