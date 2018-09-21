package com.dynamic.zk.event;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.ApplicationEvent;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 4:25
 */
public class RocketmqEvent extends ApplicationEvent {

    private DefaultMQPushConsumer consumer;

    private List<MessageExt> msgs;


    public RocketmqEvent(List<MessageExt> msgs, DefaultMQPushConsumer consumer) {

        super(msgs);
        this.consumer = consumer;
        this.msgs = msgs;
    }

    public String getMsg(int idx) {

        try {
            return new String(getMsgs().get(idx).getBody(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public String getMsg(int idx, String code) {

        try {
            return new String(getMsgs().get(idx).getBody(), code);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public DefaultMQPushConsumer getConsumer() {

        return consumer;
    }

    public void setConsumer(DefaultMQPushConsumer consumer) {

        this.consumer = consumer;
    }

    public MessageExt getMessageExt(int idx) {

        return getMsgs().get(idx);
    }


    public String getTopic(int idx) {

        return getMsgs().get(idx).getTopic();
    }


    public String getTag(int idx) {

        return getMsgs().get(idx).getTags();
    }


    public byte[] getBody(int idx) {

        return getMsgs().get(idx).getBody();
    }


    public String getKeys(int idx) {

        return getMsgs().get(idx).getKeys();
    }

    public List<MessageExt> getMsgs() {

        return msgs;
    }

    public void setMsgs(List<MessageExt> msgs) {

        this.msgs = msgs;
    }


}
