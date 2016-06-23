package com.whh.redis.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis发布消息
 * Created by xuzhuo on 2016/6/23.
 */
public class Publisher {
    private static final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private JedisPool jedisPool;
    private String channel;

    public Publisher(JedisPool jedisPool, String channel) {
        this.jedisPool = jedisPool;
        this.channel = channel;
    }

    // 发布消息
    public void publish(String msg){
        Jedis jedis = jedisPool.getResource();
        logger.debug("发布消息---->" + channel + "--->" + msg);
        jedis.publish(channel, msg);
    }
}
