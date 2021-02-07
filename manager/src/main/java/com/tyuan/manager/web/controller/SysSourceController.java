/**
 * @ClassName MavMenuController
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:53
 */
package com.tyuan.manager.web.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.tyuan.manager.annotation.Log;
import com.tyuan.manager.aop.LogAspect;
import com.tyuan.manager.service.ServiceException;
import com.tyuan.manager.web.PermissionConstant;
import com.tyuan.manager.web.RouteConstant;
import com.tyuan.manager.service.SysSourceService;
import com.tyuan.model.ErrorCodeConsts;
import com.tyuan.model.ResultData;
import com.tyuan.model.ResultTable;
import com.tyuan.model.pojo.SysSource;
import com.tyuan.model.vo.DeleteVo;
import com.tyuan.model.vo.sys.SysSourceTableParamsVo;
import com.tyuan.model.vo.sys.SysUrlVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SysSourceController {

    @Resource
    SysSourceService sysSourceService;


    @RequiresPermissions(PermissionConstant.SYS_SOURCE_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_SOURCE)
    @Log(type = LogAspect.LogType.SELECT, value = "查看资源列表")
    public ResultTable list(@RequestBody SysSourceTableParamsVo requestParam) {
        ResultTable table = new ResultTable();
        try {
            PageInfo pageInfo = sysSourceService.getByParams(requestParam);
            table.setPageInfo(pageInfo);
            return table;
        } catch (Exception e) {
            return table;
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_SOURCE_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_SOURCE)
    public ResultData get(@RequestParam(value = "parentId", required = false) Long parentId,
                          @RequestParam(value = "id", required = false) Long id) {

        ResultData resultData = new ResultData();
        resultData.setErrorCode(ErrorCodeConsts.SUCCESS);
        List<SysSource> sourcesList = Lists.newArrayList();
        if (null != parentId) {
            sourcesList = sysSourceService.getByParentId(parentId);
        } else {
            SysSource sysSource = sysSourceService.getById(id);
            sourcesList.add(sysSource);
        }
        resultData.setData(sourcesList);

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
