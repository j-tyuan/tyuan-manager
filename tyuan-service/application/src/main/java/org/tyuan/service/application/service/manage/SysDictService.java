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
package org.tyuan.service.application.service.manage;

import com.github.pagehelper.PageInfo;
import org.tyuan.service.data.model.SysDict;
import org.tyuan.service.data.vo.sys.SysDictTableParamsVo;

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
    SysDict getById(String id);

}
