package com.dynamic.zk.event;

import com.dynamic.zk.vo.Subscribe;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.springframework.context.ApplicationEvent;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-28-下午 4:34
 */
public class ZkNodeEvent extends ApplicationEvent {

    private PathChildrenCacheEvent.Type type;

    private Subscribe subscribe;

    /**
     * Create a new ApplicationEvent.
     *
     * @param subscribe the object on which the event initially occurred (never {@code null})
     */
    public ZkNodeEvent(Subscribe subscribe, PathChildrenCacheEvent.Type type) {

        super(subscribe);
        this.subscribe = subscribe;
        this.type = type;
    }

    public String getSubStatus() {

        return subscribe.getStatus();
    }

    public String getTypeName(){
        return this.type.name();
    }

    public PathChildrenCacheEvent.Type getType() {

        return type;
    }

    public void setType(PathChildrenCacheEvent.Type type) {

        this.type = type;
    }

    public Subscribe getSubscribe() {

        return subscribe;
    }

    public void setSubscribe(Subscribe subscribe) {

        this.subscribe = subscribe;
    }
}
