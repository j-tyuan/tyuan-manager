/**
 * @InterFaceName SysMenuService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:53
 */
package org.springmg.service.manage.service;

import org.springmg.service.model.pojo.SysSource;
import org.springmg.service.model.pojo.custom.CSysSource;

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
