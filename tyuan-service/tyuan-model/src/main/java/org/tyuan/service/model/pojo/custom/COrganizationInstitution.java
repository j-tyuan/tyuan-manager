package org.tyuan.service.model.pojo.custom;


import org.tyuan.common.ITree;
import org.tyuan.service.model.pojo.OrganizationInstitution;
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
