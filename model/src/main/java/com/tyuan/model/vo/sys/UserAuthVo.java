package com.tyuan.model.vo.sys;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class UserAuthVo {

    private List<Long> roleIds;

    private List<Long> permissionIds;
}
