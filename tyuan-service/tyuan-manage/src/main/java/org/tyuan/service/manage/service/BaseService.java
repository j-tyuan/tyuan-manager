/**
 * @ClassName BaseService
 * @Author dev@tyuan.design
 * @Date 2020/6/19 15:35
 */
/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.manage.service;

import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.model.vo.DeleteVo;

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
