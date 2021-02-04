/**
 * @ClassName UserInfoHolder
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:08
 */
package com.tyuan.manager.utils;

import java.util.Map;

public class UserInfoHolder {

    private static final ThreadLocal<Map> THREAD_LOCAL = new ThreadLocal();


    public static String getUserName() {
        Map map = THREAD_LOCAL.get();
        if (null == map) {
            return null;
        }
        return (String) map.get("account");
    }

    public static Long getUserId() {
        Map map = THREAD_LOCAL.get();
        if (null == map) {
            return null;
        }
        return (Long) map.get("id");
    }

    public static void put(Map map) {

        THREAD_LOCAL.set(map);
    }

    public static void clear() {

        THREAD_LOCAL.remove();
    }

}
