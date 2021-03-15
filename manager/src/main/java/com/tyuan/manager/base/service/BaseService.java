/**
 * @ClassName BaseService
 * @Author dev@tyuan.design
 * @Date 2020/6/19 15:35
 */
package com.tyuan.manager.base.service;

import com.tyuan.model.base.vo.DeleteVo;

/**
 * Created by guiqijiang on 2/29/20.
 */
public interface BaseService<T> {

    /**
     * 添加
     *
     * @param t
     * @throws ServiceException 业务异常
     */
    default void add(T t) throws ServiceException {
    }


    /**
     * 修改
     *
     * @param t
     * @throws ServiceException 业务异常
     */
    default void edit(T t) throws ServiceException {
    }

    /**
     * 删除
     *
     * @param deleteVo
     * @throws ServiceException 业务异常
     */
    default void del(DeleteVo deleteVo) throws ServiceException {
    }
}
