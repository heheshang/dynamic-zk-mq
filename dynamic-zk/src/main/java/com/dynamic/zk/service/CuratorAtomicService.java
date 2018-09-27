package com.dynamic.zk.service;

import lombok.extern.log4j.Log4j2;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B>-<BR>
 * <B>中文类名：</B>--CuratorAtomicInteger<BR>
 * <B>概要说明：</B>CuratorAtomicInteger.java<BR>
 *
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @since 2018年09月21日 下午 5:43
 **/
@Component
@Log4j2
public class CuratorAtomicService {


    @Autowired
    private CuratorFramework cf;

    public int getAtomicIntger(String path) {

        DistributedAtomicInteger atomicIntger = new DistributedAtomicInteger(cf, "/"+path, new RetryNTimes(3, 1000));
        try {

            AtomicValue<Integer> value = atomicIntger.add(1);

            if (value.succeeded()) {
                return value.postValue();
            } else {
                return value.preValue();
            }

        } catch (Exception e) {
            log.error("获取分布式Id 失败");
        }
        return 0;
    }

}
