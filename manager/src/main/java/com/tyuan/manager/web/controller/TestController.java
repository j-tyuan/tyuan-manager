/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/3/14 5:03 下午
 */
package com.tyuan.manager.web.controller;

import com.tyuan.manager.web.RouteConstant;
import com.tyuan.model.ResultData;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.tyuan.model.cache.CacheConstant.TEST_VISIT;

@RestController
public class TestController {

    @Resource
    RedisTemplate redisTemplate;


    @GetMapping(RouteConstant.ROUTER_TEST_VISIT)
    public ResultData visit() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Integer visit = (Integer) valueOperations.get(TEST_VISIT);
        ResultData resultData = new ResultData();
        resultData.setData(visit);
        return resultData;
    }
}
