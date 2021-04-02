package org.springmg.service.manage.cache;

import org.springmg.service.model.pojo.SysPermission;
import org.springmg.service.model.pojo.custom.COrganizationInstitution;
import org.springmg.service.model.pojo.custom.CSysSource;
import org.springmg.service.model.cache.DataCache;

import java.util.List;

public class LocalCache {

    /**
     * 菜单缓存数据
     */
    public static DataCache<List<CSysSource>> SYS_SOURCE;

    /**
     * 权限缓存数据
     */
    public static DataCache<List<SysPermission>> SYS_PERMISSION;

    /**
     * 机构缓存数据
     */
    public static DataCache<List<COrganizationInstitution>> SYS_INSTITUTION = new DataCache<>();

}
