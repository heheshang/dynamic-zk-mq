package com.dynamic.zk.listener;

import com.dynamic.zk.event.ZkNodeEvent;
import com.dynamic.zk.mq.MQCustomConcurrentlyListener;
import com.dynamic.zk.mq.MQCustomConsumer;
import com.dynamic.zk.mq.MQCustomeOrderlyListener;
import com.dynamic.zk.mq.MQFactory;
import com.dynamic.zk.mq.MqConsumersThread;
import com.dynamic.zk.utils.FastJsonConvertUtil;
import com.dynamic.zk.vo.Subscribe;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 12:36
 */
@Log4j2
public class ChildrenCacheListener implements PathChildrenCacheListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(ChildrenCacheListener.class);


    private String namesrvAddr;

    private static Map<String/**/, String/**/> updateMQSubscribeMap = Maps.newHashMap();

    private CountDownLatch latch = new CountDownLatch(1);

    private ExecutorService executorService;

    private ApplicationEventPublisher publisher;

    public ChildrenCacheListener(String namesrvAddr, ExecutorService executorService, ApplicationEventPublisher publisher) {

        this.namesrvAddr = namesrvAddr;
        this.executorService = executorService;
        this.publisher = publisher;
    }

    @Override
    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {

        Subscribe subscribe = null;
        String key = "";
        String consumerId = "";
        MQCustomConsumer consumer = null;
        MessageListener listener;
        String groupName = "";
        String topic = "";
        String tag = "";

        switch (event.getType()) {
            case CHILD_ADDED:

                log.info("监听到节点增加事件,进行添加操作 path【{}】,data【{}】", event.getData().getPath(), new String(event.getData().getData()));
                subscribe = FastJsonConvertUtil.convertJSONToObject(new String(event.getData().getData()), Subscribe.class);

                assert subscribe != null;
                this.publisher.publishEvent(new ZkNodeEvent(subscribe,event.getType()));

                updateMQSubscribeMap.put(key, consumerId);
                break;

            case CHILD_UPDATED:
                log.info("监听到节点更新事件,进行更新操作 path【{}】,data【{}】", event.getData().getPath(), new String(event.getData().getData()));

                subscribe = FastJsonConvertUtil.convertJSONToObject(new String(event.getData().getData()), Subscribe.class);

                assert subscribe != null;
                this.publisher.publishEvent(new ZkNodeEvent(subscribe,event.getType()));
                if (subscribe.getStatus().equals("1")) {

                    key = subscribe.getProname() + "_" + subscribe.getId();
                    consumerId = DateFormatUtils.format(subscribe.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
                    MQFactory.getInstance().stopConsumer(consumerId);
                    groupName = subscribe.getGroupname();
                    topic = subscribe.getTopic();
                    tag = subscribe.getTag();
                    listener = subscribe.isOrderly() ? new MQCustomeOrderlyListener() : new MQCustomConcurrentlyListener();

                    consumer = MQFactory.getInstance().createConsumer(
                            consumerId, groupName, namesrvAddr, topic, tag, listener, this.setOptions(subscribe));
                    consumer.start();

                    updateMQSubscribeMap.put(key, consumerId);
                } else if (subscribe.getStatus().equals("2")) {
                    key = subscribe.getProname() + "_" + subscribe.getId();
                    consumerId = DateFormatUtils.format(subscribe.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
                    updateMQSubscribeMap.remove(key);
                    MQFactory.getInstance().stopConsumer(consumerId);
                }
                break;
            case CHILD_REMOVED:
                log.info("监听到节点删除事件,进行删除操作 path【{}】,data【{}】", event.getData().getPath(), new String(event.getData().getData()));
                subscribe = FastJsonConvertUtil.convertJSONToObject(new String(event.getData().getData()), Subscribe.class);

                assert subscribe != null;
                key = subscribe.getProname() + "_" + subscribe.getId();
                consumerId = DateFormatUtils.format(subscribe.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
                MQFactory.getInstance().stopConsumer(consumerId);
                updateMQSubscribeMap.remove(key);
                this.publisher.publishEvent(new ZkNodeEvent(subscribe,event.getType()));
                break;
            default:
                break;
        }
    }

    private Map<String, String> setOptions(Subscribe subscribe) {
        //设置消费者其它参数
        /**Consumer 启动后，默认从什么位置开始消费:默认CONSUME_FROM_LAST_OFFSET*/
        Map<String, String> options = Maps.newHashMap();
        //设置消费者其它参数
        /**Consumer 启动后，默认从什么位置开始消费:默认CONSUME_FROM_LAST_OFFSET*/
        options.put("consumeFromWhere", subscribe.getConsumeformwhere());
        /**消费线程池最小数量:默认10*/
        options.put("consumeThreadMin", subscribe.getConsumethreadmin());
        /**消费线程池最大数量:默认20*/
        options.put("consumeThreadMax", subscribe.getConsumethreadmax());
        /**拉消息本地队列缓存消息最大数：默认1000*/
        options.put("pullThresholdForQueue", subscribe.getPullthresholdforqueue());
        /**批量消费，一次消费多少条消息:默认1条*/
        options.put("consumeMessageBatchMaxSize", subscribe.getConsumemessagebatchmaxsize());
        /**批量拉消息，一次最多拉多少条,默认32条*/
        options.put("pullBatchSize", subscribe.getPullbatchsize());
        /**消息拉取线程每隔多久拉取一次,默认为0*/
        options.put("pullInterval", subscribe.getPullinterval());
        return options;
    }
}
