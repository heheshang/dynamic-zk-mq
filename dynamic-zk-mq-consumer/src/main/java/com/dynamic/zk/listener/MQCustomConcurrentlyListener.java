package com.dynamic.zk.listener;

import com.dynamic.zk.event.MqEvent;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-28-上午 10:51
 */
@Component
@Log4j2
public class MQCustomConcurrentlyListener implements MessageListenerConcurrently {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

        log.info("topic【{}】,tags【{}】 收到消息【{}】", msgs.get(0).getTopic(),msgs.get(0).getTags(),new String(msgs.get(0).getBody()));

        try {

            if (msgs.size() == 0) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

            this.publisher.publishEvent(new MqEvent(msgs));

        } catch (Exception e) {
            e.printStackTrace();
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
