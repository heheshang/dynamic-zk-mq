package com.dynamic.zk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @date 2018/09/18 下午 2:38
 * @since v1.0
 **/
@SpringBootApplication
@MapperScan("com.dynamic.zk.mybatis.mapper")
public class DynamicZkApplication {

    public static void main(String[] args) {

        SpringApplication.run(DynamicZkApplication.class, args);
    }
}
