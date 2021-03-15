package com.tyuan.manager.base.cache;

import com.tyuan.model.base.cache.DataCache;
import com.tyuan.model.base.pojo.SysPermission;
import com.tyuan.model.base.pojo.SysSource;

import java.util.List;

public class LocalCache {

    /**
     * 菜单缓存数据
     */
    public static DataCache<List<SysSource>> SYS_SOURCE;


    /**
     * 权限缓存数据
     */
    public static DataCache<List<SysPermission>> SYS_PERMISSION;

}
