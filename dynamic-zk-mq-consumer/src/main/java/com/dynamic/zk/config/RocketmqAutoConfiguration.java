package com.dynamic.zk.config;

import com.dynamic.zk.event.RocketmqEvent;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 4:12
 */
@Configuration
@EnableConfigurationProperties(RocketmqProperties.class)
@ConditionalOnProperty(prefix = RocketmqProperties.PREFIX, value = "namesrvAddr")
public class RocketmqAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RocketmqAutoConfiguration.class);


    @Autowired
    private RocketmqProperties properties;

    @Autowired
    private ApplicationEventPublisher publisher;

    private static boolean isFirstSub = true;

    private static long startTime = System.currentTimeMillis();

    private AtomicInteger transactionIndex = new AtomicInteger(0);

    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * 初始化向rocketmq发送普通消息的生产者
     */
    @Bean(initMethod = "start")
    @ConditionalOnProperty(prefix = RocketmqProperties.PREFIX, value = "producerInstanceName")
    public DefaultMQProducer defaultProducer() throws MQClientException {
        /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        DefaultMQProducer producer = new DefaultMQProducer(properties.getProducerGroupName());
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.setInstanceName(properties.getProducerInstanceName());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
//        producer.start();
        log.info("RocketMq defaultProducer Started.");
        return producer;
    }

    /**
     * 初始化向rocketmq发送事务消息的生产者
     */
    @Bean(initMethod = "start")
    @ConditionalOnProperty(prefix = RocketmqProperties.PREFIX, value = "producerTranInstanceName")
    public TransactionMQProducer transactionProducer() throws MQClientException {
        /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        TransactionMQProducer producer = new TransactionMQProducer(properties.getTransactionProducerGroupName());
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.setInstanceName(properties.getProducerTranInstanceName());
        producer.setRetryTimesWhenSendAsyncFailed(10);

        // 事务回查监听器
        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {

                int value = transactionIndex.getAndIncrement();
                log.info("事务回查监听器 executeLocalTransaction value[{}]", value);
                int status = value % 3;
                localTrans.put(msg.getTransactionId(), status);
                log.info("事务回查监听器 executeLocalTransaction status[{}]", status);
                return LocalTransactionState.UNKNOW;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {

                Integer status = localTrans.get(msg.getTransactionId());
                log.info("事务回查监听器 checkLocalTransaction status[{}]", status);
                if (null != status) {
                    switch (status) {
                        case 0:
                            log.info("业务处理未知");
                            return LocalTransactionState.UNKNOW;
                        case 1:
                            log.info("业务处理完成提交消息");
                            return LocalTransactionState.COMMIT_MESSAGE;
                        case 2:
                            log.info("业务处理异常回滚消息");
                            return LocalTransactionState.ROLLBACK_MESSAGE;
                    }
                }
                log.info("业务处理完成提交消息");
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
//        producer.setCheckThreadPoolMinSize(2);
//        // 事务回查最大并发数
//        producer.setCheckThreadPoolMaxSize(2);
//        // 队列数
//        producer.setCheckRequestHoldMax(2000);

        // TODO 由于社区版本的服务器阉割调了消息回查的功能，所以这个地方没有意义
        // TransactionCheckListener transactionCheckListener = new
        // TransactionCheckListenerImpl();
        // producer.setTransactionCheckListener(transactionCheckListener);

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
//        producer.start();

        log.info("RocketMq TransactionMQProducer Started.");
        return producer;
    }

    /**
     * 初始化rocketmq消息监听方式的消费者
     */
    @Bean(initMethod = "start")
    @ConditionalOnProperty(prefix = RocketmqProperties.PREFIX, value = "consumerInstanceName")
    public DefaultMQPushConsumer pushConsumer() throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(properties.getConsumerGroupName());
        consumer.setNamesrvAddr(properties.getNamesrvAddr());
        consumer.setInstanceName(properties.getConsumerInstanceName());
        if (properties.isConsumerBroadcasting()) {
            consumer.setMessageModel(MessageModel.BROADCASTING);
        }else {
            consumer.setMessageModel(MessageModel.CLUSTERING);
        }
        consumer.setConsumeMessageBatchMaxSize(
                properties.getConsumerBatchMaxSize() == 0 ? 1 : properties.getConsumerBatchMaxSize());// 设置批量消费，以提升消费吞吐量，默认是1
        /**
         * 订阅指定topic下tags
         */
        List<String> subscribeList = properties.getSubscribe();
        for (String sunscribe : subscribeList) {
            log.info("订阅主题topic[{}]", sunscribe.split(":")[0]);
            log.info("订阅标签tag[{}]", sunscribe.split(":")[1]);
            consumer.subscribe(sunscribe.split(":")[0], sunscribe.split(":")[1]);
        }
        if (properties.isEnableOrderConsumer()) {
            consumer.registerMessageListener((List<MessageExt> msgs, ConsumeOrderlyContext context) -> {
                try {

                    context.setAutoCommit(true);
                    msgs = filter(msgs);

                    if (msgs.size() == 0) {
                        return ConsumeOrderlyStatus.SUCCESS;
                    }

                    this.publisher.publishEvent(new RocketmqEvent(msgs, consumer));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                // 如果没有return success，consumer会重复消费此信息，直到success。
                return ConsumeOrderlyStatus.SUCCESS;
            });
        } else {
            consumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {
                try {
                    msgs = filter(msgs);

                    if (msgs.size() == 0) {
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    this.publisher.publishEvent(new RocketmqEvent(msgs, consumer));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                // 如果没有return success，consumer会重复消费此信息，直到success。
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
        }
        /*new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);// 延迟5秒再启动，主要是等待spring事件监听相关程序初始化完成，否则，回出现对RocketMQ的消息进行消费后立即发布消息到达的事件，然而此事件的监听程序还未初始化，从而造成消息的丢失
                    *//**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         *//*
                    try {
                        consumer.start();
                    } catch (Exception e) {
                        log.info("RocketMq pushConsumer Start failure!!!.");
                        log.error(e.getMessage(), e);
                    }
                    log.info("RocketMq pushConsumer Started.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();*/

        return consumer;
    }




    private List<MessageExt> filter(List<MessageExt> msgs) {

        if (isFirstSub && !properties.isEnableHisConsumer()) {
            List<MessageExt> list = new ArrayList<>();
            for (MessageExt item : msgs) {
                if (startTime - item.getBornTimestamp() < 0) {
                    list.add(item);
                }
            }
            msgs = list;
        }
        if (isFirstSub && msgs.size() > 0) {
            isFirstSub = false;
        }
        return msgs;
    }


}

