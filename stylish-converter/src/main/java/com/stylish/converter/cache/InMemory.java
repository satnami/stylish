package com.stylish.converter.cache;

import com.stylish.converter.ConverterApp;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.HashMap;

public class InMemory {

    private static InMemory instance;

    private JedisPool jedisPool;

    private InMemory() {
        jedisPool = new JedisPool(new JedisPoolConfig(), ConverterApp.getInstance().redis_host);
    }

    public static InMemory getInstance() {
        if (instance == null)
            instance = new InMemory();
        return instance;
    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            jedis.set(key, value);
            HashMap<String, String> hash = new HashMap<String, String>();
            hash.put(key, value);
            jedis.hmset(ConverterApp.getInstance().redis_key, hash);
        } catch (JedisConnectionException ex) {

        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            return jedis.hmget(ConverterApp.getInstance().redis_key, key).get(0);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void flush() {
        Jedis jedis = null;
        try {
            jedis = this.jedisPool.getResource();
            jedis.del(ConverterApp.getInstance().redis_key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
