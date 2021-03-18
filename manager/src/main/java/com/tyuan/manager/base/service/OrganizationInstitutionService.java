/**
 * @ClassName SysDictService
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:57
 */
package com.tyuan.manager.base.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.tyuan.model.base.pojo.OrganizationInstitution;
import com.tyuan.model.base.pojo.custom.COrganizationInstitution;
import com.tyuan.model.base.vo.sys.OrganizeInstitutionTableVo;

import java.util.List;
import java.util.Map;

public interface OrganizationInstitutionService extends BaseService<OrganizationInstitution> {

    /**
     * @return
     */
    List<COrganizationInstitution> getAll();
}
