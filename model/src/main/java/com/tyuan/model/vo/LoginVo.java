/**
 * @ClassName LoginVo
 * @Author dev@tyuan.design
 * @Date 2020/6/23 14:44
 */
package com.tyuan.model.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginVo {

    private String username;

    private String password;

    private String mobile;

    private String captcha;

    //account  mobile
    private String type;

    private boolean autoLogin;
}
