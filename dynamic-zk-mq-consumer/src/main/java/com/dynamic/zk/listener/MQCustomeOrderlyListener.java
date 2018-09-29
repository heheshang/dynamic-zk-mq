package com.dynamic.zk.listener;

import com.dynamic.zk.event.MqEvent;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class MQCustomeOrderlyListener implements MessageListenerOrderly {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {

        log.info("topic【{}】,tags【{}】 收到消息【{}】", msgs.get(0).getTopic(),msgs.get(0).getTags(),new String(msgs.get(0).getBody()));

        try {

            context.setAutoCommit(true);

            if (msgs.size() == 0) {
                return ConsumeOrderlyStatus.SUCCESS;
            }

            this.publisher.publishEvent(new MqEvent(msgs));

        } catch (Exception e) {

            e.printStackTrace();
            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
        }

        return ConsumeOrderlyStatus.SUCCESS;
    }
}
