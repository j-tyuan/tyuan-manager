package com.tyuan.model.base.vo.sys;

import com.tyuan.model.base.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysLogTableVo extends DataTableParam {

    private String userName;

    private Long userId;

    private String method;

    private String title;

    private Integer type;

    private String requestId;

}
