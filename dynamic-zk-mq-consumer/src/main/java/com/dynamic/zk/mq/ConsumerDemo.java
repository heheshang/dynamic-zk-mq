package com.dynamic.zk.mq;

import com.dynamic.zk.event.MqEvent;
import com.dynamic.zk.event.RocketmqEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 4:38
 */
@Component
@Log4j2
public class ConsumerDemo {

    @EventListener(condition = "#event.msgs[0].topic=='TopicTest1' && #event.msgs[0].tags=='TagA'")
    public void rocketmqMsgListen(RocketmqEvent event) {
//      DefaultMQPushConsumer consumer = event.getConsumer();
        try {
            log.info("com.guosen.client.controller.consumerDemo监听到一个消息达到：" + event.getMsgs().get(0).getMsgId());
            // TODO 进行业务处理
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventListener(condition = "#event.msgs[0].topic=='TopicTestTransaction' && #event.msgs[0].tags=='TagTransaction'")
    public void rocketmqTransactionMsgListen(RocketmqEvent event) {
//      DefaultMQPushConsumer consumer = event.getConsumer();
        try {
           log.info("rocketmqTransactionMsgListen监听到一个消息达到：" + event.getMsgs().get(0).getMsgId());
            // TODO 进行业务处理
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventListener(condition = "#event.msgs[0].topic=='TopicTest' && #event.msgs[0].tags.startsWith('TagB')")
    public void rocketmqMsgListenB(RocketmqEvent event) {
//      DefaultMQPushConsumer consumer = event.getConsumer();
        try {
            log.info("rocketmqMsgListenB 监听到一个消息达到：" + event.getMsgs().get(0).getMsgId());
            // TODO 进行业务处理
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventListener(condition = "#event.msgs[0].topic=='TopicTest' && #event.msgs[0].tags=='Tag20' ")
    public void rocketmqMsgListenC(MqEvent event) {
        try {
            log.info("rocketmqMsgListenB 监听到一个消息达到：" + event.getMsgs().get(0).getMsgId()+"  "+ event.getKeys(0));
            // TODO 进行业务处理
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

