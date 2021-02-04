package com.tyuan.model.vo.sys;

import com.tyuan.model.pojo.SysDict;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Setter
@Getter
public class SysDictVo extends SysDict {

    @NotEmpty(message = "值不许为空")
    private String value;

    @NotEmpty(message = "标签不允许为空")
    private String label;


}
