/**
 * @ClassName RoleService
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
package org.tyuan.service.system.service;

import com.github.pagehelper.PageInfo;
import org.tyuan.service.system.model.pojo.SysRole;
import org.tyuan.service.system.model.vo.sys.SysRoleTableParamsVo;
import org.tyuan.service.system.model.vo.sys.SysRoleVo;

import java.util.List;

public interface SysRoleService extends BaseService<SysRoleVo> {

    PageInfo<SysRole> getByParams(SysRoleTableParamsVo param);

    /**
     * 是否包涵角色
     *
     * @return
     */
    boolean hasRoleById(Long id);

    /**
     * @param roleId
     * @return
     */
    List<Long> getAuthIdByRoleId(Long roleId);

    /**
     * @param id
     * @return
     */
    SysRole getById(Long id);

    /**
     * @return
     */
    List<SysRole> getAll();

}
