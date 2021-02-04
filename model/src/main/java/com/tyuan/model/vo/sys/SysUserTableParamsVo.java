package com.tyuan.model.vo.sys;

import com.tyuan.model.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysUserTableParamsVo extends DataTableParam {

    private String name;

    private String account;

    private String phone;

    private String loginDate;
}
