/**
 * @ClassName MavMenuController
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:53
 */
package org.tyuan.service.manage.web.controller;

import org.tyuan.common.ITree;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.common.utils.TreeUtils;
import org.tyuan.service.manage.annotation.Log;
import org.tyuan.service.manage.aop.LogAspect;
import org.tyuan.service.manage.web.PermissionConstant;
import org.tyuan.service.manage.web.RouteConstant;
import org.tyuan.service.manage.service.SysSourceService;
import org.tyuan.service.model.ResultData;
import org.tyuan.service.model.pojo.custom.CSysSource;
import org.tyuan.service.model.vo.DeleteVo;
import org.tyuan.service.model.vo.sys.SysUrlVo;
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
