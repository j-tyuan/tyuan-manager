package com.tyuan.model.base.pojo.custom;


import com.tyuan.common.ITree;
import com.tyuan.model.base.pojo.OrganizationInstitution;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class COrganizationInstitution extends OrganizationInstitution implements ITree {

    /**
     * 子节点
     */
    List<ITree> children;
}
