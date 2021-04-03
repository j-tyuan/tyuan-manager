/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.framework.task;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tyuan.common.utils.TreeUtils;
import org.tyuan.service.framework.cache.LocalCache;
import org.tyuan.service.system.mapper.SysPermissionMapper;
import org.tyuan.service.system.mapper.customize.*;
import org.tyuan.service.system.model.cache.Cache;
import org.tyuan.service.system.model.cache.CacheConstant;
import org.tyuan.service.system.model.cache.DataCache;
import org.tyuan.service.system.model.pojo.SysDict;
import org.tyuan.service.system.model.pojo.SysParam;
import org.tyuan.service.system.model.pojo.SysPermission;
import org.tyuan.service.system.model.pojo.custom.COrganizationInstitution;
import org.tyuan.service.system.model.pojo.custom.CSysSource;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@Component
public class SysScheduledTask {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    CSysSourceMapper cSysSourceMapper;

    @Resource
    SysPermissionMapper sysPermissionMapper;

    @Resource
    COrganizationInstitutionMapper cOrganizeInstitutionMapper;

    @Resource
    CSysDictMapper csysDictMapper;

    @Resource
    CSysParamMapper cSysParamMapper;


    @PostConstruct
    public void init() {
        // 加载菜单
        refreshMenu();

        // 加载权限
        initPermission();

        // 加载字典
        refreshDict();

        //刷新参数
        refreshParam();
    }

    /**
     * 初始化权限数据
     */
    public void initPermission() {

        DataCache cache = new DataCache();
        List<SysPermission> list = sysPermissionMapper.selectByExample(null);
        cache.setData(list);
        LocalCache.SYS_PERMISSION = cache;
    }

    /**
     * 菜单本地缓存
     */
    @Scheduled(cron = "${scheduled.menu.refresh.cron}")
    public void refreshMenu() {
        checkLoadCache(LocalCache.SYS_SOURCE, cSysSourceMapper, (cache) -> {
            List<CSysSource> sysSources = cSysSourceMapper.getAll();
            cache.setData(sysSources);
            LocalCache.SYS_SOURCE = cache;
        });
    }

    /**
     * 机构本地缓存
     */
    @Scheduled(cron = "${scheduled.institution.refresh.cron}")
    public void refreshInstitution() {
        checkLoadCache(LocalCache.SYS_INSTITUTION, cOrganizeInstitutionMapper, (cache) -> {
            List<COrganizationInstitution> sysInstitutions = cOrganizeInstitutionMapper.getAll();
            List list = TreeUtils.tree(sysInstitutions, 0L);
            cache.setData(list);
            LocalCache.SYS_INSTITUTION = cache;
        });
    }

    /**
     * 字典缓存
     */
    @Scheduled(cron = "${scheduled.dict.refresh.cron}")
    public void refreshDict() {

        BiFunction biFunction = isUpdate(CacheConstant.SYS_DICT_INFO, csysDictMapper);
        if (biFunction == null) {
            return;
        }

        List<SysDict> dicts = csysDictMapper.selectByExample(null);
        if (CollectionUtils.isEmpty(dicts)) {
            return;
        }

        Map dictMap = Maps.newHashMap();
        for (SysDict item : dicts) {

            List arr = (List) dictMap.getOrDefault(item.getType(), Lists.newArrayList());
            Map var = Maps.newHashMap();
            var.put("type", item.getType());
            var.put("label", item.getLabel());
            var.put("value", item.getValue());
            var.put("parentId", item.getParentId());
            arr.add(var);

            dictMap.put(item.getType(), arr);
        }

        biFunction.apply(CacheConstant.SYS_DICT_MAP, dictMap);
    }


    /**
     * 参数缓存
     */
    @Scheduled(cron = "${scheduled.param.refresh.cron}")
    public void refreshParam() {

        BiFunction biFunction = isUpdate(CacheConstant.SYS_PARAM_INFO, cSysParamMapper);
        if (biFunction == null) {
            return;
        }

        List<SysParam> params = cSysParamMapper.selectByExampleWithBLOBs(null);
        Map valMap = Maps.newHashMap();
        for (SysParam item : params) {

            valMap.put(item.getParamKey(), item.getParamVal());
        }

        biFunction.apply(CacheConstant.SYS_PARAM_MAP, valMap);
    }

    private void checkLoadCache(DataCache updateBO, ICacheInfo iCacheInfo, Consumer<DataCache> call) {
        if (updateBO == null) {
            updateBO = new DataCache<>();
        }
        Map map = iCacheInfo.getLastUpdateAndCount();
        if (MapUtils.isEmpty(map)) {
            return;
        }
        Long total = (Long) map.get("total");
        if (null == total || total == 0) {
            return;
        }
        Long time = ((Date) map.get("updateDate")).getTime();
        if (updateBO.complete(time, total)) {
            return;
        }
        DataCache cache = new DataCache();
        cache.setTotal(total);
        cache.setUpdateTime(time);
        call.accept(cache);
    }


    /**
     * 检查缓存 是否需要更新
     *
     * @param key
     * @param iCacheInfo
     * @return
     */
    private BiFunction isUpdate(String key, ICacheInfo iCacheInfo) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Cache cache = (Cache) valueOperations.get(key);
        if (null == cache) {
            cache = new Cache();
        }

        Map map = iCacheInfo.getLastUpdateAndCount();
        if (map == null || cache.complete(map)) {
            return null;
        }

        Cache finalCache = cache;
        return (BiFunction<String, Map, Boolean>) (k, v) -> {
            // 更新数据
            HashOperations hashOperations = redisTemplate.opsForHash();
            hashOperations.putAll(k, v);
            // 删除 去除的信息
            Set<String> stringSet = hashOperations.keys(k);
            for (Iterator it = v.keySet().iterator(); it.hasNext(); ) {
                String newKey = String.valueOf(it.next());
                stringSet.removeIf(item -> item.equals(newKey));
            }
            if (stringSet.size() != 0) {
                String[] s = new String[stringSet.size()];
                hashOperations.delete(k, stringSet.toArray(s));
            }
            // 更新信息
            finalCache.updateByMap(map);
            valueOperations.set(key, finalCache);
            return true;
        };
    }


}
