package com.tyuan.manager.base.web.controller;


import com.tyuan.manager.base.service.MonitorRedisService;
import com.tyuan.manager.base.web.PermissionConstant;
import com.tyuan.model.base.ResultData;
import com.tyuan.model.base.vo.monitor.RedisTreeKeyVo;
import com.tyuan.model.base.vo.monitor.RedisValueVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.tyuan.manager.base.web.RouteConstant.ROUTER_MONITOR_REDIS_GET_VALUE;
import static com.tyuan.manager.base.web.RouteConstant.ROUTER_MONITOR_REDIS_KEYS;

@RestController
public class MonitorRedisController {

    @Resource
    MonitorRedisService monitorRedisService;

    @RequiresPermissions(PermissionConstant.MONITOR_REDIS_KEYS)
    @GetMapping(ROUTER_MONITOR_REDIS_KEYS)
    public ResultData getRedisTreeKey() {

        ResultData resultData = new ResultData();
        List<RedisTreeKeyVo> redisTreeKeyVoList = monitorRedisService.getTreeKey();
        resultData.setData(redisTreeKeyVoList);

        return resultData;
    }

    @RequiresPermissions(PermissionConstant.MONITOR_REDIS_GET_VALUE)
    @GetMapping(ROUTER_MONITOR_REDIS_GET_VALUE)
    public ResultData getValue(@RequestParam("key") String key) {

        ResultData resultData = new ResultData();
        RedisValueVo valueVo = monitorRedisService.getValue(key);
        resultData.setData(valueVo);

        return resultData;
    }
}