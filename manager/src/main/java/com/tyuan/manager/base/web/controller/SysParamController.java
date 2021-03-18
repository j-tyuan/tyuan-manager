/**
 * @ClassName RoleController
 * @Description 字典管理
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
 */
package com.tyuan.manager.base.web.controller;

import com.github.pagehelper.PageInfo;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.manager.base.annotation.Log;
import com.tyuan.manager.base.aop.LogAspect;
import com.tyuan.manager.base.service.SysParamService;
import com.tyuan.manager.base.web.PermissionConstant;
import com.tyuan.manager.base.web.RouteConstant;
import com.tyuan.model.base.ResultData;
import com.tyuan.model.base.ResultTable;
import com.tyuan.model.base.vo.DeleteVo;
import com.tyuan.model.base.vo.sys.SysParamTableVo;
import com.tyuan.model.base.vo.sys.SysParamVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class SysParamController {


    @Resource
    SysParamService paramService;

    @RequiresPermissions(PermissionConstant.SYS_PARAM_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_PARAM)
    @Log(type = LogAspect.LogType.SELECT, value = "查看系统参数")
    public ResultTable list(@RequestBody SysParamTableVo param) {

        ResultTable resultData = new ResultTable();
        try {
            PageInfo pageInfo = paramService.getByParams(param);
            resultData.setPageInfo(pageInfo);
            return resultData;
        } catch (Exception e) {
            return resultData;
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_PARAM_DEL)
    @PostMapping(RouteConstant.ROUTER_SYS_PARAM_DEL)
    @Log(type = LogAspect.LogType.DEL, value = "删除系统参数")
    public ResultData del(@RequestBody DeleteVo deleteVo) {
        try {
            paramService.del(deleteVo);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_PARAM_ADD)
    @PostMapping(RouteConstant.ROUTER_SYS_PARAM_ADD)
    @Log(type = LogAspect.LogType.ADD, value = "添加系统参数")
    public ResultData add(@RequestBody SysParamVo k) throws ServiceException {

        paramService.add(k);
        return new ResultData();
    }

    @RequiresPermissions(PermissionConstant.SYS_PARAM_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_PARAM_EDIT)
    @Log(type = LogAspect.LogType.EDIT, value = "修改系统参数")
    public ResultData edit(@RequestBody SysParamVo k) throws ServiceException {

        paramService.edit(k);
        return new ResultData();

    }

}
