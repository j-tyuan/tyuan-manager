/**
 * @ClassName RoleController
 * @Description 字典管理
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
 */
package com.tyuan.manager.web.controller;

import com.github.pagehelper.PageInfo;
import com.tyuan.manager.service.ServiceException;
import com.tyuan.manager.service.SysParamService;
import com.tyuan.manager.web.PermissionConstant;
import com.tyuan.manager.web.RouteConstant;
import com.tyuan.model.ResultData;
import com.tyuan.model.ResultTable;
import com.tyuan.model.pojo.SysParam;
import com.tyuan.model.vo.DeleteVo;
import com.tyuan.model.vo.sys.SysParamTableVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
public class SysParamController {


    @Resource
    SysParamService paramService;

    @RequiresPermissions(PermissionConstant.SYS_PARAM_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_PARAM)
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
    public ResultData add(@RequestBody SysParam k) throws ServiceException {

        paramService.add(k);
        return new ResultData();
    }

    @RequiresPermissions(PermissionConstant.SYS_PARAM_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_PARAM_EDIT)
    public ResultData edit(@RequestBody SysParam k) throws ServiceException {

        paramService.edit(k);
        return new ResultData();

    }

}
