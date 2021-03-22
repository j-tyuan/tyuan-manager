/**
 * @ClassName MavMenuController
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:53
 */
package com.tyuan.manager.base.web.controller;

import com.google.common.collect.Lists;
import com.tyuan.common.ITree;
import com.tyuan.common.utils.TreeUtils;
import com.tyuan.manager.base.cache.LocalCache;
import com.tyuan.manager.base.service.SysSourceService;
import com.tyuan.manager.base.web.RouteConstant;
import com.tyuan.model.base.ResultData;
import com.tyuan.model.base.pojo.custom.CSysSource;
import com.tyuan.model.base.vo.sys.MenuDataItemVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

        List<CSysSource> list = sysMenuService.authorityFilter(LocalCache.SYS_SOURCE.getData());
        List<MenuDataItemVo> newList = Lists.newArrayList();
        list.forEach(e -> {
            MenuDataItemVo leftMenuVo = new MenuDataItemVo();
            leftMenuVo.setName(e.getName());
            leftMenuVo.setPath(e.getHref());
            leftMenuVo.setIcon(e.getIcon());
            leftMenuVo.setSort(e.getSort());
            leftMenuVo.setIsLeaf(e.getIsLeaf());
            leftMenuVo.setId(e.getId());
            leftMenuVo.setParentId(e.getParentId());
            newList.add(leftMenuVo);
        });

        List<ITree> leftMenuVos = TreeUtils.tree(newList, 0L);
        Collections.sort(leftMenuVos, Comparator.comparingLong(o -> o.getSort()));

        ResultData resultData = new ResultData();
        resultData.setData(leftMenuVos);
        resultData.setSuccess(true);
        return resultData;
    }
}
