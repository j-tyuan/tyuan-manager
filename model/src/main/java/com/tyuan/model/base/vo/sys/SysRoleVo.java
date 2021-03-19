package com.tyuan.model.base.vo.sys;

import com.tyuan.model.base.pojo.SysRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class SysRoleVo extends SysRole implements Serializable {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.name
     *
     * @mbggenerated
     */
    @NotEmpty(message = "名称不允许为空")
    @Pattern(regexp = "^[a-z]*$", message = "只允许英文字母")
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.en_name
     *
     * @mbggenerated
     */
    @NotEmpty(message = "名称不允许为空")
    private String name;


    private List<Long> permissionIds;
}