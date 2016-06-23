package com.whh.utils;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * zkUtils
 * Created by xuzhuo on 2016/6/23.
 */
public class ZkUtils {
    private static final Logger logger = LoggerFactory.getLogger(ZkUtils.class);

    /**
     * 创建zk节点
     * @param zk
     * @param nodePath
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void createZkNode(ZooKeeper zk, String nodePath) throws KeeperException, InterruptedException {
        logger.info("开始创建节点--->");
        String[] nodePaths = nodePath.split("/");
        StringBuilder sb = new StringBuilder();

        for (String path : nodePaths) {
            if (StringUtils.isEmpty(path)){
                continue;
            }
            sb.append("/").append(path);
            if (zk.exists(sb.toString(), true) == null){
                logger.info("create node --->" + sb.toString());
                zk.create(sb.toString(), path.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        }
    }
}
