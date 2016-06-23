package com.whh.zookeeper.register;

import com.whh.utils.ZkUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zk节点注册
 * Created by xuzhuo on 2016/6/23.
 */
public class ZkRegister {
    private static final Logger logger = LoggerFactory.getLogger(ZkRegister.class);
    private ZooKeeper zk;
    private String node;

    public ZkRegister(ZooKeeper zk, String node) {
        this.zk = zk;
        this.node = node;
    }

    public void registerWachter(Watcher watcher){
        try {
            ZkUtils.createZkNode(zk, node);
            logger.debug("设置节点监听");
            zk.getData(node, watcher, null);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        logger.info("asdfasf");
    }
}
