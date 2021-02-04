package com.tyuan.model.vo.sys;

import com.tyuan.model.pojo.SysPermission;
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
