package com.tyuan.manager.cache;

import com.tyuan.model.cache.DataCache;
import com.tyuan.model.pojo.SysPermission;
import com.tyuan.model.pojo.SysSource;

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
