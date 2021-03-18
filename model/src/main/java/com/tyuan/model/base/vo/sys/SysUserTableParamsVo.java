package com.tyuan.model.base.vo.sys;

import com.tyuan.model.base.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysUserTableParamsVo extends DataTableParam {

    private String name;

    private String account;

    private String phone;

    private String loginDate;

    private Long instId;
}
