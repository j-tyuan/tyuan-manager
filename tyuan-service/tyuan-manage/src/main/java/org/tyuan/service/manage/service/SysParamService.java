/**
 * @ClassName SysDictService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
 */
package org.tyuan.service.manage.service;

import com.github.pagehelper.PageInfo;
import org.tyuan.service.model.pojo.SysParam;
import org.tyuan.service.model.vo.sys.SysParamTableVo;

import java.util.List;

public interface SysParamService extends BaseService<SysParam> {

    PageInfo getByParams(SysParamTableVo param);

    /**
     * 根据key获取数据
     *
     * @param key
     * @return
     */
    SysParam getByKey(String key);

    /**
     *
     * @return
     */
    List<SysParam> getAll();

    /**
     *
     */
    void updateCache();
}
