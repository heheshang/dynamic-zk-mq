package com.dynamic.zk.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-下午 4:51
 */
@Configuration
@EnableConfigurationProperties(value = {CuratorProperties.class})
public class ZkAutoConfiguration {

    @Autowired
    private CuratorProperties curatorProperties;


    private static final Logger log = LoggerFactory.getLogger(RocketmqAutoConfiguration.class);

    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(curatorProperties.getBaseSleepTimeMs(), curatorProperties.getMaxRetries());
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(curatorProperties.getConnectString())
                .sessionTimeoutMs(curatorProperties.getSessionOuttime())
                .connectionTimeoutMs(curatorProperties.getConnectionTimeoutMs())
                .retryPolicy(retryPolicy)
                .build();

        return curatorFramework;

    }


}
