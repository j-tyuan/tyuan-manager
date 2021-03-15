package com.tyuan.model.base.vo.sys;

import com.tyuan.model.base.pojo.SysSource;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Getter
@Setter
public class SysUrlVo extends SysSource implements Serializable {

    @NotEmpty(message = "资源名称不能为空")
    private String name;

    private Long parentId = 0L;
}
