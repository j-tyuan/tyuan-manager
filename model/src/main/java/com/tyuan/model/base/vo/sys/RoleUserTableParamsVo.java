package com.tyuan.model.base.vo.sys;

import com.tyuan.model.base.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleUserTableParamsVo extends SysUserTableParamsVo {

    private Long roleId;

    // 查询类型：1.已绑定用户 2.未绑定用户
    private int searchType;

}
