package org.tyuan.service.manage.web.controller;


import org.tyuan.service.manage.service.MonitorRedisService;
import org.tyuan.service.manage.web.PermissionConstant;
import org.tyuan.service.manage.web.RouteConstant;
import org.tyuan.service.model.ResultData;
import org.tyuan.service.model.vo.monitor.RedisTreeKeyVo;
import org.tyuan.service.model.vo.monitor.RedisValueVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
