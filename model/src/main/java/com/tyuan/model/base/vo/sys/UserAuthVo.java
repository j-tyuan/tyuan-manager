package com.tyuan.model.base.vo.sys;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class UserAuthVo implements Serializable {

    private List<Long> roleIds;

    private List<Long> permissionIds;
}
