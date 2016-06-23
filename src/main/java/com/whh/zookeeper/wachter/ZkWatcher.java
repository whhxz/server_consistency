package com.whh.zookeeper.wachter;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zk监听节点
 * Created by xuzhuo on 2016/6/23.
 */
public class ZkWatcher implements Watcher{
    private static final Logger logger = LoggerFactory.getLogger(ZkWatcher.class);

    private String node;
    private ZooKeeper zk;

    public ZkWatcher(ZooKeeper zk, String node) {
        this.node = node;
        this.zk = zk;
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()){
            case NodeDataChanged:
                try {
                    // 重新更新监听节点
                    byte[] data = zk.getData(node, this, null);
                    logger.info("节点更新---->" + new String(data));
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}
