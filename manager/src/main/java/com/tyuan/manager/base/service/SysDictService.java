/**
 * @ClassName SysDictService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
 */
package com.tyuan.manager.base.service;

import com.github.pagehelper.PageInfo;
import com.tyuan.model.base.pojo.SysDict;
import com.tyuan.model.base.vo.sys.SysDictTableParamsVo;

import java.util.List;
import java.util.Map;

public interface SysDictService extends BaseService<SysDict> {

    PageInfo getByParams(SysDictTableParamsVo param);

    /**
     * 根据label 获取数据
     *
     * @param label
     * @return
     */
    SysDict getByLabel(String label);

    /**
     * 根据字典类型获取数据，如果type为null 或者 "" 则返回所有
     *
     * @param type
     * @return key -> 类型，value 字典
     */
    Map<String, List<SysDict>> getByType(String type);

    /**
     *
     * @return
     */
    List<SysDict> getAll();

    /**
     *
     * @param id
     * @return
     */
    SysDict getById(Long id);

}
