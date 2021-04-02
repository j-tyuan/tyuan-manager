package org.springmg.service.model.vo.sys;

import org.springmg.service.model.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysParamTableVo extends DataTableParam {

    private String paramName;

    private String paramKey;

    private String paramVal;

    private String remarks;

}
