package com.dynamic.zk.config;

import com.dynamic.zk.listener.ChildrenCacheListener;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.async.AsyncCuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-上午 11:35
 */
@Configuration
public class CuratorConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorConfig.class);

    @Value("${zookeeper.connectString}")
    private String connectString;

    @Value("${zookeeper.sessionOuttime}")
    private int sessionOuttime;

    @Value("${zookeeper.connectionTimeoutMs}")
    private int connectionTimeoutMs;

    @Value("${zookeeper.baseSleepTimeMs}")
    private int baseSleepTimeMs;

    @Value("${zookeeper.maxRetries}")
    private int maxRetries;

    @Value("${zookeeper.basePath}")
    private String basePath;

    @PostConstruct
    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionOuttime)
                .connectionTimeoutMs(connectionTimeoutMs)
                .retryPolicy(retryPolicy)
                .build();

        return curatorFramework;

    }

    @Bean
    PathChildrenCache pathChildrenCache(CuratorFramework cf) {
        //建立一个PathChildrenCache缓存,第三个参数为是否接受节点数据内容 如果为false则不接受
        PathChildrenCache pathChildrenCache = new PathChildrenCache(cf, basePath, true);
        // 在初始化的时候就进行缓存监听
        try {
            pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        } catch (Exception e) {
            LOGGER.error("初始化进行缓存监听失败");
            e.printStackTrace();
        }
        pathChildrenCache.getListenable().addListener(new ChildrenCacheListener());

        return pathChildrenCache;
    }
}
