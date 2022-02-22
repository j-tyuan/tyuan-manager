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
package org.tyuan.service.application.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyuan.service.application.cache.UserInfoCacheService;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.SysParamConsts;
import org.tyuan.service.data.cache.CacheConstant;

import javax.annotation.Resource;

/**
 * 全局 系统管理
 *
 * @author guiqijiang
 */
@RestController
public class SystemController {

    Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    UserInfoCacheService userTokenCacheService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 获取全局水印
     *
     * @return
     */
    @GetMapping("/api/watermark")
    public ResultData watermark() {
        logger.info("获取全局水印");
        HashOperations operations = redisTemplate.opsForHash();
        Object o = operations.get(CacheConstant.SYS_PARAM_MAP, SysParamConsts.SYS_WATER_MARK);
        return new ResultData().setData(o);
    }

}
