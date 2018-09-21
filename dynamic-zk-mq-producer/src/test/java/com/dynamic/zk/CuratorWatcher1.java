package com.dynamic.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-上午 10:52
 */
public class CuratorWatcher1 {


        /** zookeeper地址 */
//        static final String CONNECT_ADDR = "192.168.1.171:2181,192.168.1.172:2181,192.168.1.173:2181";
        static final String CONNECT_ADDR = "localhost:2181";
        /** session超时时间 */
        static final int SESSION_OUTTIME = 5000;//ms

        static final String PARENT_PATH = "/super";

        public static void main (String[]args) throws Exception {

            //1 重试策略：初试时间为1s 重试10次
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
            //2 通过工厂创建连接
            CuratorFramework cf = CuratorFrameworkFactory.builder()
                    .connectString(CONNECT_ADDR)
                    .sessionTimeoutMs(SESSION_OUTTIME)
                    .retryPolicy(retryPolicy)
                    .build();

            //3 建立连接
            cf.start();


            //4 建立一个cache缓存
            final NodeCache cache = new NodeCache(cf, "/super", false);
            cache.start(true);
            cache.getListenable().addListener(new NodeCacheListener() {
                /**
                 * <B>方法名称：</B>nodeChanged<BR>
                 * <B>概要说明：</B>触发事件为创建节点和更新节点，在删除节点的时候并不触发此操作。<BR>
                 * @see org.apache.curator.framework.recipes.cache.NodeCacheListener#nodeChanged()
                 */
                @Override
                public void nodeChanged() throws Exception {

                    System.out.println("路径为：" + cache.getCurrentData().getPath());
                    System.out.println("数据为：" + new String(cache.getCurrentData().getData()));
                    System.out.println("状态为：" + cache.getCurrentData().getStat());
                    System.out.println("---------------------------------------");
                }
            });


            //4 建立一个PathChildrenCache缓存,第三个参数为是否接受节点数据内容 如果为false则不接受
            PathChildrenCache pathChildrenCache = new PathChildrenCache(cf, PARENT_PATH, true);
            //5 在初始化的时候就进行缓存监听
            pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                /**
                 * <B>方法名称：</B>监听子节点变更<BR>
                 * <B>概要说明：</B>新建、修改、删除<BR>
                 * @see org.apache.curator.framework.recipes.cache.PathChildrenCacheListener#childEvent(org.apache.curator.framework.CuratorFramework, org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent)
                 */
                @Override
                public void childEvent(CuratorFramework cf, PathChildrenCacheEvent event) throws Exception {
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
            });
            Thread.sleep(1000);
//            cf.create().forPath("/super", "123".getBytes());

            Thread.sleep(1000);
            cf.setData().forPath("/super", "456".getBytes());
            cf.getData().forPath("/super");
            Thread.sleep(1000);
            cf.delete().forPath("/super");

            Thread.sleep(Integer.MAX_VALUE);
        }
    }
