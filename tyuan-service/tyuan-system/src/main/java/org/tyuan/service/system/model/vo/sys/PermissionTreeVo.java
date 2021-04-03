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
package org.tyuan.service.system.model.vo.sys;

import org.tyuan.service.system.model.pojo.SysPermission;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Setter
@Getter
public class PermissionTreeVo implements Serializable {

    private List<PermissionTreeVo> children;

    private Long id;

    private Long pid;

    /**
     * 排序
     */
    private Long sort;

    /**
     *
     */
    private String name;


    private String remarks;

    /**
     * 如果没有子节点返回null，注意这是因为前端需要
     *
     * @param pid
     * @param sysPerm
     * @return
     */
    public static List<PermissionTreeVo> format(Long pid, List<SysPermission> sysPerm) {
        List<PermissionTreeVo> formatList = new ArrayList<>();

        Iterator<SysPermission> iterator = sysPerm.iterator();
        while (iterator.hasNext()) {
            SysPermission item = iterator.next();
            if (item.getParentId().equals(pid)) {
                PermissionTreeVo permissionTreeVo = new PermissionTreeVo();
                permissionTreeVo.setName(item.getName());
                permissionTreeVo.setSort(item.getSort());
                permissionTreeVo.setPid(item.getParentId());
                permissionTreeVo.setId(item.getId());
                permissionTreeVo.setRemarks(item.getRemarks());

                formatList.add(permissionTreeVo);
                permissionTreeVo.setChildren(format(item.getId(), sysPerm));
            }
        }
        if (null == formatList || formatList.size() == 0) {
            return null;
        }
        return formatList;
    }
}
