package com.whh.redis.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;


/**
 * 获取消息
 * Created by xuzhuo on 2016/6/23.
 */
public class Subscriber extends JedisPubSub implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(Subscriber.class);

    private JedisPool jedisPool;
    private String channel;

    public Subscriber(JedisPool jedisPool, String channel) {
        this.jedisPool = jedisPool;
        this.channel = channel;
    }

    @Override
    public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
        logger.info("消息---->" + channel + "---->" + message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        super.onSubscribe(channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        super.onUnsubscribe(channel, subscribedChannels);
    }

    @Override
    public void run() {
        Jedis jedis = jedisPool.getResource();
        jedis.subscribe(this, channel);
    }
}
