package com.dynamic.zk.listener;

import lombok.extern.log4j.Log4j2;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 12:36
 */
@Log4j2
public class ChildrenCacheListener implements PathChildrenCacheListener {

    @Override
    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {

        switch (event.getType()) {
            case CHILD_ADDED:
                log.info("CHILD_ADDED :" + event.getData().getPath());
                log.info("CHILD_ADDED :" + new String(event.getData().getData()));
                break;
            case CHILD_UPDATED:
                log.info("CHILD_UPDATED :" + event.getData().getPath());
                log.info("CHILD_UPDATED :" + new String(event.getData().getData()));
                break;
            case CHILD_REMOVED:
                log.info("CHILD_REMOVED :" + event.getData().getPath());
                log.info("CHILD_REMOVED :" + new String(event.getData().getData()));
                break;
            default:
                break;
        }
    }
}
