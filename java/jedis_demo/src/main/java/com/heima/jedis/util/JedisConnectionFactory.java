package com.heima.jedis.util;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        // 配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最多8个连接
        jedisPoolConfig.setMaxTotal(8);
        // 最多空闲连接
        jedisPoolConfig.setMaxIdle(8);
        // 最小空闲连接
        jedisPoolConfig.setMinIdle(0);
        // 等待时长
        jedisPoolConfig.setMaxWaitMillis(1000);


        // 创建连接池对象
        jedisPool = new JedisPool(jedisPoolConfig,"192.168.150.128",
                6379,1000, "123321");


    }
    // 每次调用此方法就能拿到一个Jedis对象
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
