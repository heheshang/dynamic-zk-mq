package com.dynamic.zk.service;

import com.dynamic.zk.utils.FastJsonConvertUtil;
import com.dynamic.zk.vo.Subscribe;
import lombok.extern.log4j.Log4j2;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B>-<BR>
 * <B>中文类名：</B>--ZkFactory<BR>
 * <B>概要说明：</B>ZkFactory.java<BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @since 2018年09月27日 下午 3:28
 **/
@Component
@Log4j2
public class ZkNodeOperateService {

    @Autowired
    private CuratorFramework curatorFramework;


    public void createNode(String zkPath, String data) throws Exception {

        Stat stat = curatorFramework.checkExists().forPath(zkPath);
        if (stat == null) {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(zkPath, data.getBytes());
        } else {
            curatorFramework.setData().forPath(zkPath, data.getBytes());
        }

    }

    public void updateNode(String zkPath, String data) throws Exception {

        Stat stat = curatorFramework.checkExists().forPath(zkPath);
        if (stat == null) {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(zkPath, data.getBytes());
        } else {
            curatorFramework.setData().forPath(zkPath, data.getBytes());
        }
    }

    public void stopNode(String zkPath, String data) {


    }

    public void deleteNode(String zkPath) throws Exception {

        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath(zkPath);
    }

    public void deleteAllNode(String zkPath) throws Exception {

        curatorFramework.delete().deletingChildrenIfNeeded().forPath(zkPath);
        List<String> childNodes = curatorFramework.getChildren().forPath(zkPath);



    }

    public void stopAllNode(String zkPath) throws Exception {

        List<String> childNodes = curatorFramework.getChildren().forPath(zkPath);

        childNodes.forEach(s -> {

                    log.info("子节点为[{}]", s);

                    String nodePath = zkPath + "/" + s;


                    try {

                        byte[] data = curatorFramework.getData().forPath(nodePath);

                        Subscribe mq = FastJsonConvertUtil.convertJSONToObject(new String(data), Subscribe.class);

                        assert mq != null;
                        mq.setStatus("2");

                        curatorFramework.setData().forPath(nodePath, FastJsonConvertUtil.convertObjectToJSON(mq).getBytes());


                    } catch (Exception e) {
                        log.error("节点数据更新发生错误 节点为[{}]", nodePath);
                    }

                }
        );

    }

    public void startAllNode(String zkPath) throws Exception {

        List<String> childNodes = curatorFramework.getChildren().forPath(zkPath);

        childNodes.forEach(s -> {

                    log.info("子节点为[{}]", s);

                    String nodePath = zkPath + "/" + s;


                    try {

                        byte[] data = curatorFramework.getData().forPath(nodePath);

                        Subscribe mq = FastJsonConvertUtil.convertJSONToObject(new String(data), Subscribe.class);

                        assert mq != null;
                        mq.setStatus("1");

                        curatorFramework.setData().forPath(nodePath, FastJsonConvertUtil.convertObjectToJSON(mq).getBytes());


                    } catch (Exception e) {
                        log.error("节点数据更新发生错误 节点为[{}]", nodePath);
                    }

                }
        );
    }
}
