/**
 * @InterFaceName SysMenuService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:53
 */
package com.tyuan.manager.base.service;

import com.github.pagehelper.PageInfo;
import com.tyuan.model.base.pojo.SysSource;
import com.tyuan.model.base.pojo.custom.CSysSource;
import com.tyuan.model.base.vo.sys.SysSourceTableParamsVo;

import java.util.List;

public interface SysSourceService extends BaseService<SysSource> {

    /**
     * 鉴权
     *
     * @param list
     * @return
     */
    List<CSysSource> authorityFilter(List<CSysSource> list);

    /**
     * 根据pid获取数据
     *
     * @param parentId
     * @return
     */
    List<SysSource> getByParentId(long parentId);

    /**
     * @return
     */
    List<CSysSource> getAll();

    /**
     * @param id
     * @return
     */
    SysSource getById(Long id);
}
