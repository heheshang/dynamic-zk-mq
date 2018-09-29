package com.dynamic.zk.web;

import com.dynamic.zk.service.CuratorAtomicService;
import com.dynamic.zk.service.ZkNodeOperateService;
import com.dynamic.zk.utils.FastJsonConvertUtil;
import com.dynamic.zk.vo.Subscribe;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    private CuratorAtomicService curatorAtomicInteger;

    @Autowired
    private ZkNodeOperateService zkNodeOperateService;

    @Value("${zookeeper.basePath}")
    private String basePath;


    @RequestMapping("/index")
    public String index() {

        return "index";
    }

    @RequestMapping("/add")
    public void add() throws Exception {

        Subscribe mq = new Subscribe();

        mq.setProname("test");
        int id = curatorAtomicInteger.getAtomicIntger(mq.getProname());
        mq.setId(id);
        mq.setUrl("www.123.com ");
        mq.setTopic("TopicTest");
        mq.setTag("Tag"+id);
        mq.setGroupname("TestGroup");
        mq.setConsumeformwhere("CONSUME_FROM_LAST_OFFSET");
        mq.setConsumethreadmin("10");
        mq.setConsumethreadmax("10");
        mq.setPullthresholdforqueue("");
        mq.setConsumemessagebatchmaxsize("");
        mq.setPullbatchsize("");
        mq.setPullinterval("");
        mq.setBusinesskey("consumer_"+id);
        mq.setStatus("0");
        mq.setCreateTime(new Date());
        mq.setUpdateTime(new Date());


        String path = basePath + "/" + mq.getGroupname() + "_" + mq.getId();
        zkNodeOperateService.createNode(path, FastJsonConvertUtil.convertObjectToJSON(mq));

    }

    @RequestMapping("/update")
    public void update() throws Exception {

        Subscribe mq = new Subscribe();
        mq.setProname("test");
        mq.setUrl("www.123.com ");
        mq.setTopic("TopicTest2");
        mq.setId(3);
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

        String path = basePath + "/" + mq.getGroupname() + "_" + mq.getId();

        zkNodeOperateService.updateNode(path, FastJsonConvertUtil.convertObjectToJSON(mq));
    }

    @RequestMapping("/delete/{path}")
    public void delete(@PathVariable("path") String path) throws Exception {

        zkNodeOperateService.deleteNode(basePath + "/" + path);
    }

    @RequestMapping("/delete/all")
    public void deleteAll() throws Exception {

        zkNodeOperateService.deleteAllNode(basePath);
    }

    @RequestMapping("/stop/all")
    public void stopAll() throws Exception {

        zkNodeOperateService.stopAllNode(basePath);
    }

    @RequestMapping("/start/all")
    public void startAll() throws Exception {

        zkNodeOperateService.startAllNode(basePath);
    }
}
