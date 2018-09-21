package com.dynamic.zk.config;

import com.dynamic.zk.listener.ChildrenCacheListener;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 4:51
 */
@Configuration
@EnableConfigurationProperties(CuratorProperties.class)
public class ZkAutoConfiguration {

    @Autowired
    private CuratorProperties curatorProperties;

    private static final Logger log = LoggerFactory.getLogger(RocketmqAutoConfiguration.class);

    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(curatorProperties.getBaseSleepTimeMs(), curatorProperties.getMaxRetries());
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(curatorProperties.getConnectString())
                .sessionTimeoutMs(curatorProperties.getSessionOuttime())
                .connectionTimeoutMs(curatorProperties.getConnectionTimeoutMs())
                .retryPolicy(retryPolicy)
                .build();

        return curatorFramework;

    }

    @Bean
    public PathChildrenCache pathChildrenCache(CuratorFramework cf) {
        //建立一个PathChildrenCache缓存,第三个参数为是否接受节点数据内容 如果为false则不接受
        PathChildrenCache pathChildrenCache = new PathChildrenCache(cf, curatorProperties.getBasePath(), true);
        // 在初始化的时候就进行缓存监听
        try {
            pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        } catch (Exception e) {
            log.error("初始化进行缓存监听失败");
            e.printStackTrace();
        }
        pathChildrenCache.getListenable().addListener(new ChildrenCacheListener());

        return pathChildrenCache;
    }
}
