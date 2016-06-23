package com.whh.redis;
import com.whh.redis.publish.Publisher;
import com.whh.redis.subscribe.Subscriber;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by xuzhuo on 2016/6/23.
 */
public class RedisTest {
    static String channel;
    static JedisPool jedisPool;

    @Before
    public void init(){
        jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
        channel = "channel_test";
    }

    @Test
    public void pub()throws Exception{
        Publisher publisher = new Publisher(jedisPool, channel);
        publisher.publish("hhhhhh");
    }

    @Test
    public void sub()throws Exception{
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Subscriber(jedisPool, channel));
            threads[i].start();
        }
        Thread.sleep(Long.MAX_VALUE);
    }
}
