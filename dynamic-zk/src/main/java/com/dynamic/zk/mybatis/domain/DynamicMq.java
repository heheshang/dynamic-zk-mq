package com.dynamic.zk.mybatis.domain;

import java.util.Date;
import lombok.Data;

/**
* Created by Mybatis Generator 2018/09/19
*/
@Data
public class DynamicMq {
    private Integer id;

    /** 系统名称*/
    private String proname;

    /** url*/
    private String url;

    /** 订阅主题*/
    private String topic;

    /** 订阅标签*/
    private String tag;

    /** 组名*/
    private String groupname;

    /** 消费者来源*/
    private String consumeformwhere;

    /** 消费者最小线程数*/
    private String consumethreadmin;

    /** 消费者最大线程数*/
    private String consumethreadmax;

    /** 拉取队列名称*/
    private String pullthresholdforqueue;

    /** 批量消费队列消息最大条数*/
    private String consumemessagebatchmaxsize;

    /** 拉取消费队列消息最大条数*/
    private String pullbatchsize;

    /** 拉取消费队列时间间隔*/
    private String pullinterval;

    /** 业务key*/
    private String businesskey;

    /** 状态 */
    private String status;

    /** 创建时间*/
    private Date createTime;

    /** 更新时间*/
    private Date updateTime;
}