package com.tyuan.dao.base.customize;

import java.util.List;
import java.util.Map;

public interface ICacheInfo<T> {

    /**
     * 获取缓存信息
     *
     * @return
     */
    Map getLastUpdateAndCount();
}

