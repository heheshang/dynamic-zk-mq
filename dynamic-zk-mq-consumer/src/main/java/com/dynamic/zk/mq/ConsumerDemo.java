package com.dynamic.zk.mq;

import com.dynamic.zk.event.MqEvent;
import com.dynamic.zk.event.RocketmqEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 4:38
 */
@Component
public class ConsumerDemo {

    @EventListener(condition = "#event.msgs[0].topic=='TopicTest1' && #event.msgs[0].tags=='TagA'")
    public void rocketmqMsgListen(RocketmqEvent event) {
//      DefaultMQPushConsumer consumer = event.getConsumer();
        try {
            System.out.println("com.guosen.client.controller.consumerDemo监听到一个消息达到：" + event.getMsgs().get(0).getMsgId());
            // TODO 进行业务处理
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventListener(condition = "#event.msgs[0].topic=='TopicTest2' && #event.msgs[0].tags=='TagB'")
    public void rocketmqMsgListenB(RocketmqEvent event) {
//      DefaultMQPushConsumer consumer = event.getConsumer();
        try {
            System.out.println("rocketmqMsgListenB 监听到一个消息达到：" + event.getMsgs().get(0).getMsgId());
            // TODO 进行业务处理
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventListener(condition = "#event.msgs[0].topic=='TopicTest2' && #event.msgs[0].tags=='TagB'")
    public void rocketmqMsgListenC(MqEvent event) {
        try {
            System.out.println("rocketmqMsgListenB 监听到一个消息达到：" + event.getMsgs().get(0).getMsgId());
            // TODO 进行业务处理
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

