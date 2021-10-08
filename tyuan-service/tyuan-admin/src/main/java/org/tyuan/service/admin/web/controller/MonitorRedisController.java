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
package org.tyuan.service.admin.web.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tyuan.service.system.model.ResultData;
import org.tyuan.service.admin.web.PermissionConstant;
import org.tyuan.service.admin.web.RouteConstant;
import org.tyuan.service.system.model.vo.monitor.RedisTreeKeyVo;
import org.tyuan.service.system.model.vo.monitor.RedisValueVo;
import org.tyuan.service.system.service.MonitorRedisService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MonitorRedisController {

    @Resource
    MonitorRedisService monitorRedisService;

    @RequiresPermissions(PermissionConstant.MONITOR_REDIS_KEYS)
    @GetMapping(RouteConstant.ROUTER_MONITOR_REDIS_KEYS)
    public ResultData getRedisTreeKey() {

        ResultData resultData = new ResultData();
        List<RedisTreeKeyVo> redisTreeKeyVoList = monitorRedisService.getTreeKey();
        resultData.setData(redisTreeKeyVoList);

        return resultData;
    }

    @RequiresPermissions(PermissionConstant.MONITOR_REDIS_GET_VALUE)
    @GetMapping(RouteConstant.ROUTER_MONITOR_REDIS_GET_VALUE)
    public ResultData getValue(@RequestParam("key") String key) {

        ResultData resultData = new ResultData();
        RedisValueVo valueVo = monitorRedisService.getValue(key);
        resultData.setData(valueVo);

        return resultData;
    }
}
