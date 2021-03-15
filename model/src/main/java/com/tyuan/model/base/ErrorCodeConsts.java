/**
 * @ClassName ErrorCodeConsts
 * @Author dev@tyuan.design
 * @Date 2020/6/22 10:19
 */
package com.tyuan.model.base;

public class ErrorCodeConsts {

    /**
     * 请求成功
     */
    public static final int SUCCESS = -1;

    /**
     * 错误
     */
    public static final int ERROR = 500;

    /**
     * 数据已经存在
     */
    public static final int ERROR_EXIST = 1001;

    /**
     * 数据不存在
     */
    public static final int ERROR_NOT_EXIST = 1003;

    /**
     * 验证码错误
     */
    public static final int ERROR_VERIFICATION_CODE = 1002;

    /**
     * 需要重新登陆
     */
    public static final int ERROR_LOGIN = 2001;

    /**
     * 无权限
     */
    public static final int ERROR_NO_PERMISSION = 2002;


    /**
     * 信息不全
     */
    public static final int ERROR_INFO_LACK = 3001;


    public static final int ERROR_PARAM = 4001;
}
