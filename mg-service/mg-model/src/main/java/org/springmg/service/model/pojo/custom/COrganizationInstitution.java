package org.springmg.service.model.pojo.custom;


import org.springmg.common.ITree;
import org.springmg.service.model.pojo.OrganizationInstitution;
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
