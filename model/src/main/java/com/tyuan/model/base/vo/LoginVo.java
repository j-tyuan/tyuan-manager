/**
 * @ClassName LoginVo
 * @Author dev@tyuan.design
 * @Date 2020/6/23 14:44
 */
package com.tyuan.model.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LoginVo implements Serializable {

    private String username;

    private String password;

    private String mobile;

    private String captcha;

    //account  mobile
    private String type;

    private boolean autoLogin;
}
