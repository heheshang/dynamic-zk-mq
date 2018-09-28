package com.dynamic.zk.mq;

import com.dynamic.zk.event.MqEvent;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-28-上午 10:50
 */
@Component
public class MQCustomeOrderlyListener implements MessageListenerOrderly {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
        this.publisher.publishEvent(new MqEvent(msgs));
        return null;
    }
}
