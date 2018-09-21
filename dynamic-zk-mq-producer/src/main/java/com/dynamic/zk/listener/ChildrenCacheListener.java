package com.dynamic.zk.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 12:36
 */
public class ChildrenCacheListener implements PathChildrenCacheListener {

    @Override
    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
        switch (event.getType()) {
            case CHILD_ADDED:
                System.out.println("CHILD_ADDED :" + event.getData().getPath());
                System.out.println("CHILD_ADDED :" + new String(event.getData().getData()));
                break;
            case CHILD_UPDATED:
                System.out.println("CHILD_UPDATED :" + event.getData().getPath());
                System.out.println("CHILD_UPDATED :" + new String(event.getData().getData()));
                break;
            case CHILD_REMOVED:
                System.out.println("CHILD_REMOVED :" + event.getData().getPath());
                System.out.println("CHILD_REMOVED :" + new String(event.getData().getData()));
                break;
            default:
                break;
        }
    }
}
