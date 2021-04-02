package org.tyuan.service.model.vo.sys;

import org.tyuan.service.model.vo.DataTableParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysLogTableVo extends DataTableParam {

    private String userName;

    private Long userId;

    private String method;

    private String title;

    private Integer type;

    private String requestId;

}
