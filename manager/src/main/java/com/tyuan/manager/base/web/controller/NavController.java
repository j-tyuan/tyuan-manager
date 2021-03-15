/**
 * @ClassName MavMenuController
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:53
 */
package com.tyuan.manager.base.web.controller;

import com.tyuan.manager.base.cache.LocalCache;
import com.tyuan.manager.base.service.SysSourceService;
import com.tyuan.manager.base.web.RouteConstant;
import com.tyuan.model.base.ResultData;
import com.tyuan.model.base.pojo.SysSource;
import com.tyuan.model.base.vo.sys.MenuDataItemVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class NavController {

    @Resource
    SysSourceService sysMenuService;

    @GetMapping(RouteConstant.ROUTER_SYS_NAV)
    public ResultData getNav() {

        List<SysSource> list = sysMenuService.authority(LocalCache.SYS_SOURCE.getData());
        List<MenuDataItemVo> leftMenuVos = MenuDataItemVo.sysMenuToLeftMenuVo(0L, list, false);
        Collections.sort(leftMenuVos, Comparator.comparingLong(o -> o.getSort()));
        ResultData resultData = new ResultData();
        resultData.setData(leftMenuVos);
        resultData.setSuccess(true);
        return resultData;
    }
}
