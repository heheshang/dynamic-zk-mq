package com.dynamic.zk.mq;

import com.dynamic.zk.listener.MQCustomConcurrentlyListener;
import com.dynamic.zk.listener.MQCustomeOrderlyListener;
import lombok.Data;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListener;

import java.util.List;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-28-上午 10:11
 */
@Data
public class MQCustomConsumer extends DefaultMQPushConsumer {

    private String consumerId;

    private String topic;

    private String tags;

    public MQCustomConsumer(String consumerId, String groupName, String namesrvAddr, String topic, String tags) {

        super(groupName);
        super.setInstanceName(consumerId);
        this.setNamesrvAddr(namesrvAddr);
        this.consumerId = consumerId;
        this.topic = topic;
        this.tags = tags;
    }

    public void registerMessageListener(List<MessageListener> mqListeners) {

        for (MessageListener mqListener : mqListeners) {
            if (mqListener instanceof MQCustomConcurrentlyListener) {
                super.registerMessageListener((MQCustomConcurrentlyListener) mqListener);
            }
            if (mqListener instanceof MQCustomeOrderlyListener) {
                super.registerMessageListener((MQCustomeOrderlyListener) mqListener);
            }

        }
    }
}
