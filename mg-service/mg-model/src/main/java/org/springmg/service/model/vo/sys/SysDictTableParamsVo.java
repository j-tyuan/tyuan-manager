package org.springmg.service.model.vo.sys;

import org.springmg.service.model.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysDictTableParamsVo extends DataTableParam {

    private String label;

    private String type;

    private Long parentId;
}
