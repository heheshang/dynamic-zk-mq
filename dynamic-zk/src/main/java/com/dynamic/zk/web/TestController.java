package com.dynamic.zk.web;

import com.dynamic.zk.mybatis.domain.DynamicMq;
import com.dynamic.zk.mybatis.mapper.DynamicMqMapper;
import com.dynamic.zk.utils.FastJsonConvertUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 1:37
 */
@Controller
public class TestController {

    @Autowired
    private CuratorFramework curatorFramework;

    @Autowired
    private DynamicMqMapper mqMapper;

    @Value("${zookeeper.basePath}")
    private String basePath;


    @RequestMapping("/index")
    public String index() {

        return "index";
    }

    @RequestMapping("/add")
    public void add() throws Exception {

        DynamicMq mq = new DynamicMq();
        mq.setProname("测试");
        mq.setUrl("www.123.com ");
        mq.setTopic("TopicTest2");
        mq.setTag("TagB");
        mq.setGroupname("TestGroup");
        mq.setConsumeformwhere("");
        mq.setConsumethreadmin("10");
        mq.setConsumethreadmax("10");
        mq.setPullthresholdforqueue("");
        mq.setConsumemessagebatchmaxsize("");
        mq.setPullbatchsize("");
        mq.setPullinterval("");
        mq.setBusinesskey("123");
        mq.setStatus("1");
        mq.setCreateTime(new Date());
        mq.setUpdateTime(new Date());


//        mqMapper.insert(mq);
        String path = basePath + "/" + mq.getGroupname() + ":" + mq.getTopic() + ":" + mq.getTag() + ":" + mq.getBusinesskey();
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (stat == null) {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, FastJsonConvertUtil.convertObjectToJSON(mq).getBytes());
        }else {
            curatorFramework.setData().forPath(path, FastJsonConvertUtil.convertObjectToJSON(mq).getBytes());
        }

    }

    @RequestMapping("/update")
    public void update() throws Exception {

        DynamicMq mq = new DynamicMq();
        mq.setProname("测试");
        mq.setUrl("www.123.com ");
        mq.setTopic("TopicTest2");
        mq.setTag("TagB");
        mq.setGroupname("TestGroup");
        mq.setConsumeformwhere("");
        mq.setConsumethreadmin("10");
        mq.setConsumethreadmax("1000");
        mq.setPullthresholdforqueue("");
        mq.setConsumemessagebatchmaxsize("");
        mq.setPullbatchsize("");
        mq.setPullinterval("");
        mq.setBusinesskey("123");
        mq.setStatus("1");
        mq.setCreateTime(new Date());
        mq.setUpdateTime(new Date());

        String path = basePath + "/" + mq.getGroupname() + ":" + mq.getTopic() + ":" + mq.getTag() + ":" + mq.getBusinesskey();

        Stat stat = curatorFramework.checkExists().forPath(path);
        if (stat == null) {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, FastJsonConvertUtil.convertObjectToJSON(mq).getBytes());
        }else {
            curatorFramework.setData().forPath(path, FastJsonConvertUtil.convertObjectToJSON(mq).getBytes());
        }
    }

    @RequestMapping("/delete")
    public void delete() throws Exception {

        curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath(basePath);
    }
}
