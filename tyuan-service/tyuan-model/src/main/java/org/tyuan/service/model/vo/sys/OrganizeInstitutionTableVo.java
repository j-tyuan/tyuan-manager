package org.tyuan.service.model.vo.sys;

import org.tyuan.service.model.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrganizeInstitutionTableVo extends DataTableParam {


    private String instCode;

    private String instName;

    private Integer instType;

    private Integer instStatus;

}
