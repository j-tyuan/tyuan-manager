/**
 * @ClassName MavMenuController
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:53
 */
package com.tyuan.manager.base.web.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.tyuan.common.ITree;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.common.utils.TreeUtils;
import com.tyuan.manager.base.annotation.Log;
import com.tyuan.manager.base.aop.LogAspect;
import com.tyuan.manager.base.cache.LocalCache;
import com.tyuan.manager.base.web.PermissionConstant;
import com.tyuan.manager.base.web.RouteConstant;
import com.tyuan.manager.base.service.SysSourceService;
import com.tyuan.model.base.ErrorCodeConsts;
import com.tyuan.model.base.ResultData;
import com.tyuan.model.base.ResultTable;
import com.tyuan.model.base.pojo.SysSource;
import com.tyuan.model.base.pojo.custom.CSysSource;
import com.tyuan.model.base.vo.DeleteVo;
import com.tyuan.model.base.vo.sys.SysSourceTableParamsVo;
import com.tyuan.model.base.vo.sys.SysUrlVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class SysSourceController {

    @Resource
    SysSourceService sysSourceService;

    @RequiresPermissions(PermissionConstant.SYS_SOURCE_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_SOURCE)
    public ResultData getAll() {
        ResultData resultData = new ResultData();
        List<CSysSource> list = sysSourceService.getAll();
        List<ITree> newList = TreeUtils.tree(list, 0L);
        // 排序
        Collections.sort(newList, Comparator.comparingLong(o -> o.getSort()));
        resultData.setData(newList);
        return resultData;
    }

    @RequiresPermissions(PermissionConstant.SYS_SOURCE_DEL)
    @PostMapping(RouteConstant.ROUTER_SYS_SOURCE_DEL)
    @Log(type = LogAspect.LogType.DEL, value = "删除资源")
    public ResultData del(@RequestBody DeleteVo deleteVo) {
        try {
            sysSourceService.del(deleteVo);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_SOURCE_ADD)
    @PostMapping(RouteConstant.ROUTER_SYS_SOURCE_ADD)
    @Log(type = LogAspect.LogType.ADD, value = "添加资源")
    public ResultData add(@RequestBody @Validated SysUrlVo k) {
        try {
            sysSourceService.add(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_SOURCE_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_SOURCE_EDIT)
    @Log(type = LogAspect.LogType.EDIT, value = "修改资源")
    public ResultData edit(@RequestBody @Validated SysUrlVo k) {
        try {
            sysSourceService.edit(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

}
