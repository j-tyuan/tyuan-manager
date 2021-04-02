package org.springmg.service.model.vo.sys;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleUserTableParamsVo extends SysUserTableParamsVo {

    private Long roleId;

    // 查询类型：1.已绑定用户 2.未绑定用户
    private int searchType;

}
