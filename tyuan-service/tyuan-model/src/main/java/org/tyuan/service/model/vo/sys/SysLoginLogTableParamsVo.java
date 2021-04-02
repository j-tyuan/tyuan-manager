package org.tyuan.service.model.vo.sys;

import org.tyuan.service.model.vo.DataTableParam;
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
