package com.dynamic.zk.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-09-18-上午 11:35
 */
@ConfigurationProperties(CuratorProperties.PREFIX)
@Data
public class CuratorProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorProperties.class);

    public static final String PREFIX = "zookeeper";

    private String connectString;

    private int sessionOuttime;

    private int connectionTimeoutMs;

    private int baseSleepTimeMs;

    private int maxRetries;

    private String basePath;





}
