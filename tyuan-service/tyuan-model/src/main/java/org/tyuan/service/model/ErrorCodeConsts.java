/**
 * @ClassName ErrorCodeConsts
 * @Author dev@tyuan.design
 * @Date 2020/6/22 10:19
 */
/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.model;

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
