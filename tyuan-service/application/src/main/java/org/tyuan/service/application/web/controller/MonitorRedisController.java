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


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tyuan.service.application.service.MonitorRedisService;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.vo.monitor.RedisTreeKeyVo;
import org.tyuan.service.data.vo.monitor.RedisValueVo;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/monitor")
public class MonitorRedisController {

    @Resource
    MonitorRedisService monitorRedisService;

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','monitor:redis:keys')")
    @GetMapping("/redis/keys")
    public ResultData getRedisTreeKey() {

        ResultData resultData = new ResultData();
        List<RedisTreeKeyVo> redisTreeKeyVoList = monitorRedisService.getTreeKey();
        resultData.setData(redisTreeKeyVoList);

        return resultData;
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN','monitor:redis:getval')")
    @GetMapping("/redis/getVal")
    public ResultData getValue(@RequestParam("key") String key) {

        ResultData resultData = new ResultData();
        RedisValueVo valueVo = monitorRedisService.getValue(key);
        resultData.setData(valueVo);

        return resultData;
    }
}
