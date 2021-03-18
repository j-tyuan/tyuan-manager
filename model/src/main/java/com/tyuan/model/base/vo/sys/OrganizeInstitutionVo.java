/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/2/5 11:39 上午
 */
package com.tyuan.model.base.vo.sys;

import com.tyuan.model.base.pojo.OrganizationInstitution;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class OrganizeInstitutionVo extends OrganizationInstitution implements Serializable {

    @NotNull(message = "机构编码不允许为空")
    private String instCode;

    @NotNull(message = "机构名称不允许为空")
    private String instName;


}
