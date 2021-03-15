/**
 * @ClassName RoleController
 * @Description 字典管理
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
 */
package com.tyuan.manager.base.web.controller;

import com.github.pagehelper.PageInfo;
import com.tyuan.manager.base.annotation.Log;
import com.tyuan.manager.base.aop.LogAspect;
import com.tyuan.manager.base.service.ServiceException;
import com.tyuan.manager.base.service.SysDictService;
import com.tyuan.manager.base.web.PermissionConstant;
import com.tyuan.manager.base.web.RouteConstant;
import com.tyuan.model.base.DictTypeEnum;
import com.tyuan.model.base.ResultData;
import com.tyuan.model.base.ResultTable;
import com.tyuan.model.base.pojo.SysDict;
import com.tyuan.model.base.vo.DeleteVo;
import com.tyuan.model.base.vo.sys.SysDictTableParamsVo;
import com.tyuan.model.base.vo.sys.SysDictVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class SysDictController {


    @Resource
    SysDictService service;

    @RequiresPermissions(PermissionConstant.SYS_DICT_LIST)
    @PostMapping(RouteConstant.ROUTER_SYS_DICT)
    @Log(type = LogAspect.LogType.SELECT, value = "查看字典")
    public ResultTable list(@RequestBody SysDictTableParamsVo requestParam) {
        ResultTable table = new ResultTable();
        try {
            PageInfo<SysDict> pageInfo = service.getByParams(requestParam);
            table.setPageInfo(pageInfo);
            return table;
        } catch (Exception e) {
            return table;
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_DICT_DEL)
    @PostMapping(RouteConstant.ROUTER_SYS_DICT_DEL)
    @Log(type = LogAspect.LogType.DEL, value = "删除字典")
    public ResultData del(@RequestBody DeleteVo deleteVo) {
        try {
            service.del(deleteVo);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_DICT_ADD)
    @PostMapping(RouteConstant.ROUTER_SYS_DICT_ADD)
    @Log(type = LogAspect.LogType.ADD, value = "添加字典")
    public ResultData add(@RequestBody @Validated SysDictVo k) throws ServiceException {

        service.add(k);
        return new ResultData();
    }

    @RequiresPermissions(PermissionConstant.SYS_DICT_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_DICT_EDIT)
    @Log(type = LogAspect.LogType.EDIT, value = "修改字典")
    public ResultData edit(@RequestBody @Validated SysDictVo k) {
        try {
            service.edit(k);
            return new ResultData();
        } catch (ServiceException e) {
            return new ResultData()
                    .setErrorCode(e.getStatus())
                    .setErrorMessage(e.getMessage());
        }
    }

    @RequiresPermissions(PermissionConstant.SYS_DICT_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_DICT_GET_TYPES)
    public ResultData getTypes() {

        ResultData resultData = new ResultData();
        resultData.setData(DictTypeEnum.getToList());

        return resultData;
    }

    @RequiresPermissions(PermissionConstant.SYS_DICT_LIST)
    @GetMapping(RouteConstant.ROUTER_SYS_DICT_GET_BY_TYPE)
    public ResultData getByType(@PathVariable(value = "type") String type) {
        Map<String, List<SysDict>> maps = service.getByType(type);
        ResultData resultData = new ResultData();
        resultData.setData(maps);
        return resultData;
    }

}
