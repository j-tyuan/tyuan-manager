package org.springmg.service.model.vo.sys;

import org.springmg.service.model.pojo.SysDict;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SysDictVo extends SysDict implements Serializable {

    @NotEmpty(message = "值不许为空")
    private String value;

    @NotEmpty(message = "标签不允许为空")
    private String label;


}
