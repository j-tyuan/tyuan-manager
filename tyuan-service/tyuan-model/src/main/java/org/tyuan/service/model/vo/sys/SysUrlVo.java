package org.tyuan.service.model.vo.sys;

import org.tyuan.service.model.pojo.SysSource;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysUrlVo extends SysSource implements Serializable {

    @NotEmpty(message = "资源名称不能为空")
    private String name;

    private Long parentId = 0L;
}
