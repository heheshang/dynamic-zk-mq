package com.dynamic.zk.mq;

import com.dynamic.zk.listener.MQCustomConcurrentlyListener;
import com.dynamic.zk.listener.MQCustomeOrderlyListener;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.log.ClientLogger;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.logging.InternalLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-28-上午 10:09
 */
public class MQFactory {

    private static class SingletonHolder {

        static final MQFactory instance = new MQFactory();
    }

    public static MQFactory getInstance() {

        return SingletonHolder.instance;
    }

    private static Map<String, MQCustomConsumer> consumers = Maps.newConcurrentMap();

    public MQCustomConsumer createConsumer(String consumerId,
                                           String groupName,
                                           String namesrvAddr,
                                           String topic,
                                           String tag,
                                           MessageListener mqListener,
                                           Map<String, String> options) {

        if (consumers.get(consumerId) != null) {
            return consumers.get(consumerId);
        }
        try {
            //创建Consumer实例、订阅、注册监听、配置参数、最后装入集合
            MQCustomConsumer consumer = new MQCustomConsumer(consumerId, groupName, namesrvAddr, topic, tag);
            consumer.subscribe(topic, tag);

            if (mqListener instanceof MQCustomConcurrentlyListener) {
                consumer.registerMessageListener((MQCustomConcurrentlyListener) mqListener);
            }
            if (mqListener instanceof MQCustomeOrderlyListener) {
                //★★★★ 添加消费策略
                consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueByHashAveragely());
                consumer.registerMessageListener((MQCustomeOrderlyListener) mqListener);
            }
            //设置消费者其它参数
            /**Consumer 启动后，默认从什么位置开始消费:默认CONSUME_FROM_LAST_OFFSET*/
            String consumeFromWhere = options.get("consumeFromWhere");
            /**消费线程池最小数量:默认10*/
            String consumeThreadMin = options.get("consumeThreadMin");
            /**消费线程池最大数量:默认20*/
            String consumeThreadMax = options.get("consumeThreadMax");
            /**拉消息本地队列缓存消息最大数：默认1000*/
            String pullThresholdForQueue = options.get("pullThresholdForQueue");
            /**批量消费，一次消费多少条消息:默认1条*/
            String consumeMessageBatchMaxSize = options.get("consumeMessageBatchMaxSize");
            /**批量拉消息，一次最多拉多少条,默认32条*/
            String pullBatchSize = options.get("pullBatchSize");
            /**消息拉取线程每隔多久拉取一次,默认为0*/
            String pullInterval = options.get("pullInterval");

            if (StringUtils.isNotBlank(consumeFromWhere)) {
                if (StringUtils.equals(consumeFromWhere, "CONSUME_FROM_LAST_OFFSET")) {
                    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
                } else if (StringUtils.equals(consumeFromWhere, "CONSUME_FROM_FIRST_OFFSET")) {
                    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
                }
            }
            if (StringUtils.isNotBlank(consumeThreadMin)) {
                consumer.setConsumeThreadMin(Integer.parseInt(consumeThreadMin));
            }
            if (StringUtils.isNotBlank(consumeThreadMax)) {
                consumer.setConsumeThreadMax(Integer.parseInt(consumeThreadMax));
            }
            if (StringUtils.isNotBlank(pullThresholdForQueue)) {
                consumer.setPullThresholdForQueue(Integer.parseInt(pullThresholdForQueue));
            }
            if (StringUtils.isNotBlank(consumeMessageBatchMaxSize)) {
                consumer.setConsumeMessageBatchMaxSize(Integer.parseInt(consumeMessageBatchMaxSize));
            }
            if (StringUtils.isNotBlank(pullBatchSize)) {
                consumer.setPullBatchSize(Integer.parseInt(pullBatchSize));
            }
            if (StringUtils.isNotBlank(pullInterval)) {
                consumer.setPullInterval(Integer.parseInt(pullInterval));
            }
            consumer.setMessageModel(MessageModel.CLUSTERING);
            //★★★★ 添加消费策略
//            consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragely());
            consumers.put(consumerId, consumer);
            return consumer;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public void stopConsumer(String consumerId) {

        if (consumers.get(consumerId) != null) {
            consumers.get(consumerId).shutdown();
            consumers.remove(consumerId);
        }
    }

    public void stopConsumers() {

        for (String consumerId : consumers.keySet()) {
            consumers.get(consumerId).shutdown();
        }
        consumers.clear();
    }

    class AllocateMessageQueueByHashAveragely extends AllocateMessageQueueAveragely {

        private final InternalLogger log = ClientLogger.getLog();

        @Override
        public String getName() {

            return super.getName() + "ByIDHash";
        }

        @Override
        public List<MessageQueue> allocate(String consumerGroup, String currentCID, List<MessageQueue> mqAll, List<String> cidAll) {
            //解析queue id
            char idChar = consumerGroup.charAt(consumerGroup.length() - "ConsumerGroup".length() - 1);

            int id = Integer.parseInt(idChar + "");

            List<MessageQueue> submq = new ArrayList<>();

            //根据queue id分配相应的MessageQueue
            for (MessageQueue queue : mqAll) {

                if (queue.getQueueId()==idChar||queue.getTopic().startsWith(MixAll.RETRY_GROUP_TOPIC_PREFIX)){

                    submq.add(queue);

                }
            }

            if (submq.size()==0){
                log.warn("allocate err:"+consumerGroup+","+currentCID+","+cidAll+","+mqAll);
            }

            return super.allocate(consumerGroup, currentCID, submq, cidAll);
        }
    }
}
