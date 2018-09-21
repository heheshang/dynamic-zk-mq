package com.dynamic.zk.web;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 1:37
 */
@Controller
public class TestController {

    @Autowired
    private CuratorFramework curatorFramework;


    @Value("${zookeeper.basePath}")
    private String basePath;


    @RequestMapping("/index")
    public String index() {

        return "index";
    }

    @RequestMapping("/add")
    public void add() throws Exception {

        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(basePath + "/c1", "c1add内容".getBytes());
    }

    @RequestMapping("/update")
    public void update() throws Exception {

        curatorFramework.getData().forPath(basePath + "/c1");
        curatorFramework.setData().forPath(basePath + "/c1", "c1update内容".getBytes());
    }

    @RequestMapping("/delete")
    public void delete() throws Exception {

        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath(basePath);
    }
}
