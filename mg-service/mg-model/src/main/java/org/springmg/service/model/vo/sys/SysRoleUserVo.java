package org.springmg.service.model.vo.sys;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SysRoleUserVo {

    private Long roleId;

    private List<Long> userIds;
}
