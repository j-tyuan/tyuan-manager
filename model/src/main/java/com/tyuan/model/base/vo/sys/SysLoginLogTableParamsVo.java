package com.tyuan.model.base.vo.sys;

import com.tyuan.model.base.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysLoginLogTableParamsVo extends DataTableParam {

    private Long userId;

    private String userNo;

    private String userName;


    private String account;

    private String loginDate;
}
