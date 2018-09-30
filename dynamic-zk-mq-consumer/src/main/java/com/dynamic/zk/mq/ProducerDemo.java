package com.dynamic.zk.mq;

import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 4:29
 */
@RestController
@Log4j2
public class ProducerDemo {

    @Autowired
    private DefaultMQProducer defaultProducer;

    @Autowired
    private TransactionMQProducer transactionProducer;

    private int i = 0;

    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    public void sendMsg() {

        Message msg = new Message("TopicTest1", // topic
                "TagA", // tag
                "OrderID00" + i, // key
                ("Hello zebra mq" + i).getBytes());// body
        try {
            defaultProducer.send(msg, new SendCallback() {

                @Override
                public void onSuccess(SendResult sendResult) {

                    System.out.println(sendResult);
                    // TODO 发送成功处理
                }

                @Override
                public void onException(Throwable e) {

                    System.out.println(e);
                    // TODO 发送失败处理
                }
            });
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/sendTransactionMsg", method = RequestMethod.GET)
    public String sendTransactionMsg() {

        SendResult sendResult = null;
        try {
            // 构造消息
            Message msg = new Message("TopicTestTransaction", // topic
                    "TagTransaction", // tag
                    "OrderID001", // key
                    ("Hello zebra mq").getBytes());// body

            // 发送事务消息，LocalTransactionExecute的executeLocalTransactionBranch方法中执行本地逻辑
            sendResult = transactionProducer.sendMessageInTransaction(msg,
                    null);
            System.out.println(sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult.toString();
    }

    @RequestMapping(value = "/sendMsgOrder", method = RequestMethod.GET)
    public void sendMsgOrder() {

        Message msg = new Message("TopicTest", // topic
                "Tag20", // tag
                "consumer_20" + i, // key
                ("Hello  mq" + i).getBytes());// body
        try {
            defaultProducer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {

                    log.info("MessageQueue {} msgId{} ,msg{}" , arg, msg.getTransactionId(),new String(msg.getBody()));
                    int index = ((Integer) arg) % mqs.size();
                    return mqs.get(index);
                }
            }, i);// i==arg
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/sendMsg1", method = RequestMethod.GET)
    public void sendMsg1() {

        Message msg = new Message("TopicTest", // topic
                "Tag20", // tag
                "OrderID00" + i, // key
                ("Hello zebra mq" + i).getBytes());// body
        try {
            defaultProducer.send(msg, new SendCallback() {

                @Override
                public void onSuccess(SendResult sendResult) {

                    System.out.println(sendResult);
                    // TODO 发送成功处理
                }

                @Override
                public void onException(Throwable e) {

                    System.out.println(e);
                    // TODO 发送失败处理
                }
            });
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
