/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/2/5 11:39 上午
 */
package org.springmg.service.model.vo.sys;

import org.springmg.service.model.pojo.OrganizationInstitution;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrganizeInstitutionVo extends OrganizationInstitution implements Serializable {

    @NotNull(message = "机构编码不允许为空")
    private String instCode;

    @NotNull(message = "机构名称不允许为空")
    private String instName;


}
