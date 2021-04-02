/**
 * @ClassName SysDictService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
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
