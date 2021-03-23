/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/3/14 5:03 下午
 */
package com.tyuan.manager.base.web.controller;

import com.tyuan.manager.base.web.RouteConstant;
import com.tyuan.model.base.ResultData;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
