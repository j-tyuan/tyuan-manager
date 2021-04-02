package org.springmg.service.manage.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

/**
 * @Author: geyuqi
 * @DateTime: 2020/7/9 11:09
 * @Description: TODO
 */
public class JsonUtil {

    private static final Gson GSON = new GsonBuilder().serializeNulls().create();

    private JsonUtil() {
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    /**
     * json字符串转成对象
     *
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Type type) {
        return GSON.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     *
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Class<T> type) {
        return GSON.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     *
     * @param str
     * @return
     */
    public static JsonObject fromJson(String str) {
        return GSON.fromJson(str, JsonObject.class);
    }
}
