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

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyuan.common.ResultData;
import org.tyuan.service.admin.web.RouteConstant;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    RedisTemplate redisTemplate;


    @GetMapping(RouteConstant.ROUTER_TEST_VISIT)
    public ResultData visit() {
        ResultData resultData = new ResultData();
        resultData.setData(0);
        return resultData;
    }
}
