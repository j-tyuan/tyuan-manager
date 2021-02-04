/**
 * @ClassName RoleController
 * @Description 字典管理
 * @Author dev@tyuan.design
 * @Date 2020/6/29 19:03
 */
package com.tyuan.manager.web.controller;

import com.github.pagehelper.PageInfo;
import com.tyuan.manager.service.ServiceException;
import com.tyuan.manager.service.SysDictService;
import com.tyuan.manager.web.PermissionConstant;
import com.tyuan.manager.web.RouteConstant;
import com.tyuan.model.DictTypeEnum;
import com.tyuan.model.ResultData;
import com.tyuan.model.ResultTable;
import com.tyuan.model.pojo.SysDict;
import com.tyuan.model.vo.DeleteVo;
import com.tyuan.model.vo.sys.SysDictTableParamsVo;
import com.tyuan.model.vo.sys.SysDictVo;
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
    public ResultData add(@RequestBody @Validated SysDictVo k) throws ServiceException {

        service.add(k);
        return new ResultData();
    }

    @RequiresPermissions(PermissionConstant.SYS_DICT_EDIT)
    @PostMapping(RouteConstant.ROUTER_SYS_DICT_EDIT)
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
