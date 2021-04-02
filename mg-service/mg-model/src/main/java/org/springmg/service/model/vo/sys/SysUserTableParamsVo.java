package org.springmg.service.model.vo.sys;

import org.springmg.service.model.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysUserTableParamsVo extends DataTableParam {

    private String userNo;

    private String name;

    private String account;

    private String phone;

    private String loginDate;

    private Long instId;
}
