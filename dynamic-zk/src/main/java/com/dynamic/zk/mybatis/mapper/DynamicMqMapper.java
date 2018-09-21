package com.dynamic.zk.mybatis.mapper;

import com.dynamic.zk.mybatis.domain.DynamicMq;
import com.dynamic.zk.mybatis.domain.DynamicMqExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Created by Mybatis Generator 2018/09/19
*/
public interface DynamicMqMapper{
    long countByExample(DynamicMqExample example);

    int deleteByExample(DynamicMqExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DynamicMq record);

    int insertSelective(DynamicMq record);

    List<DynamicMq> selectByExample(DynamicMqExample example);

    DynamicMq selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DynamicMq record, @Param("example") DynamicMqExample example);

    int updateByExample(@Param("record") DynamicMq record, @Param("example") DynamicMqExample example);

    int updateByPrimaryKeySelective(DynamicMq record);

    int updateByPrimaryKey(DynamicMq record);
}