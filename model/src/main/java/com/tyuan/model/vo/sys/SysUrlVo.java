package com.tyuan.model.vo.sys;

import com.tyuan.model.pojo.SysSource;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
public class SysUrlVo extends SysSource {

    @NotEmpty(message = "资源名称不能为空")
    private String name;

    private Long parentId = 0L;
}
