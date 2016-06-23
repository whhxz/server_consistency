package com.whh.zookeeper;
import com.whh.zookeeper.register.ZkRegister;
import com.whh.zookeeper.wachter.ZkWatcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;
/**
 * 节点测试
 * Created by xuzhuo on 2016/6/23.
 */
public class ZkTest {
    static ZooKeeper zk;
    static String node;

    @Before
    public void init()throws Exception{
        zk = new ZooKeeper("localhost:2181", 3000, null);
        node = "/order_app/com.feiniu.zookeeper/DictConfig";
    }

    @Test
    public void register()throws Exception{
        for (int i = 0; i < 5; i++) {
            ZkRegister zkRegister = new ZkRegister(zk, node);
            ZkWatcher zkWatcher = new ZkWatcher(zk, node);
            zkRegister.registerWachter(zkWatcher);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void changeDate()throws Exception{
        zk.setData(node, "hhhh".getBytes(), -1);
    }
}
