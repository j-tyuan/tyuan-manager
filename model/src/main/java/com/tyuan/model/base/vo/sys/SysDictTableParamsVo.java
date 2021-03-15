package com.tyuan.model.base.vo.sys;

import com.tyuan.model.base.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysDictTableParamsVo extends DataTableParam {

    private String label;

    private String type;

    private Long parentId;
}
