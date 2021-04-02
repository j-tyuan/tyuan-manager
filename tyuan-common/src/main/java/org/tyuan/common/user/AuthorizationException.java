package org.tyuan.common.user;

/**
 * @ClassName user.AuthorizationException
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/10/31 12:29
 */

public class AuthorizationException extends Exception {

    private int code;

    public AuthorizationException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public AuthorizationException(int code) {
        super("-");
        this.code = code;
    }


    public int getCode() {
        return code;
    }
}
