/**
 * @ClassName SysDictService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
 */
package org.springmg.service.manage.service;

import org.springmg.service.model.pojo.OrganizationInstitution;
import org.springmg.service.model.pojo.custom.COrganizationInstitution;

import java.util.List;

public interface OrganizationInstitutionService extends BaseService<OrganizationInstitution> {

    /**
     * @return
     */
    List<COrganizationInstitution> getAll();
}
