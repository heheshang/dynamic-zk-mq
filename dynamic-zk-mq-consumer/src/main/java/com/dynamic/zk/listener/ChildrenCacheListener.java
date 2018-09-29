package com.dynamic.zk.listener;

import com.dynamic.zk.event.ZkNodeEvent;
import com.dynamic.zk.utils.FastJsonConvertUtil;
import com.dynamic.zk.vo.Subscribe;
import lombok.extern.log4j.Log4j2;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 12:36
 */
@Log4j2
@Component
public class ChildrenCacheListener implements PathChildrenCacheListener {


    private CountDownLatch latch = new CountDownLatch(1);


    private ApplicationEventPublisher publisher;

    @Autowired
    public ChildrenCacheListener(ApplicationEventPublisher publisher) {

        this.publisher = publisher;
    }

    @Override
    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) {

        Subscribe subscribe = null;

        switch (event.getType()) {
            case CHILD_ADDED:

                log.info("监听到节点增加事件,进行添加操作 path【{}】,data【{}】", event.getData().getPath(), new String(event.getData().getData()));
                subscribe = FastJsonConvertUtil.convertJSONToObject(new String(event.getData().getData()), Subscribe.class);

                assert subscribe != null;
                try {

                    this.publisher.publishEvent(new ZkNodeEvent(subscribe, event.getType()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case CHILD_UPDATED:

                log.info("监听到节点更新事件,进行更新操作 path【{}】,data【{}】", event.getData().getPath(), new String(event.getData().getData()));

                subscribe = FastJsonConvertUtil.convertJSONToObject(new String(event.getData().getData()), Subscribe.class);

                assert subscribe != null;
                this.publisher.publishEvent(new ZkNodeEvent(subscribe, event.getType()));

                break;
            case CHILD_REMOVED:

                log.info("监听到节点删除事件,进行删除操作 path【{}】,data【{}】", event.getData().getPath(), new String(event.getData().getData()));
                subscribe = FastJsonConvertUtil.convertJSONToObject(new String(event.getData().getData()), Subscribe.class);

                assert subscribe != null;
                this.publisher.publishEvent(new ZkNodeEvent(subscribe, event.getType()));

                break;
            default:
                break;
        }
    }


}
