package com.tyuan.manager.service;

import com.tyuan.model.vo.monitor.RedisTreeKeyVo;
import com.tyuan.model.vo.monitor.RedisValueVo;

import java.util.List;

public interface MonitorRedisService {

    /**
     * 获取树形结构的redisKey
     *
     * @return
     */
    List<RedisTreeKeyVo> getTreeKey();

    /**
     * 根据key获取数据
     *
     * @param key
     * @return
     */
    RedisValueVo getValue(String key);
}
