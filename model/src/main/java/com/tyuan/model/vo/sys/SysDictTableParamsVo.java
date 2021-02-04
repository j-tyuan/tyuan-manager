package com.tyuan.model.vo.sys;

import com.tyuan.model.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysDictTableParamsVo extends DataTableParam {

    private String label;

    private String type;

    private Long parentId;
}
