package com.tyuan.manager.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoCacheService {

    @Resource
    RedisTemplate redisTemplate;

    /**
     * 缓存权限
     *
     * @param key
     * @param value
     * @return
     */
    public Object putPerm(String key, Object value, long exp) {
        key = "MANAGE:USER:" + key + ":PERM";
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, exp, TimeUnit.SECONDS);
        return value;
    }

    /**
     * 缓存角色
     *
     * @param key
     * @param value
     * @return
     */
    public Object putRole(String key, Object value, long exp) {
        key = "MANAGE:USER:" + key + ":ROLE";
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, exp, TimeUnit.SECONDS);
        return value;
    }


    /**
     * 缓存Token
     *
     * @param userId
     * @param token
     * @param value
     * @return
     */
    public Object put(Long userId, String token, Object value, long exp) {
        String key = "MANAGE:USER:" + token + ":INFO";
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, exp, TimeUnit.SECONDS);

        // 用于踢出登陆
        key = "MANAGE:USER:" + userId;
        redisTemplate.opsForValue().set(key, token);
        redisTemplate.expire(key, exp, TimeUnit.SECONDS);

        return value;
    }

    /**
     * 获取权限
     *
     * @param key
     * @return
     */
    public Object getPerm(String key) {

        key = "MANAGE:USER:" + key + ":PERM";
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取角色
     *
     * @param key
     * @return
     */
    public Object getRole(String key) {
        key = "MANAGE:USER:" + key + ":ROLE";
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取用户
     *
     * @param id 用户ID
     * @return
     */
    public Object getTokenById(Long id) {
        String key = "MANAGE:USER:" + id;
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 获取用户
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        key = "MANAGE:USER:" + key + ":INFO";
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 退出登陆
     *
     * @param userId
     */
    public void clear(Long userId) {
        String token = (String) getTokenById(userId);
        if (StringUtils.isBlank(token)) {
            return;
        }
        String key = "MANAGE:USER:" + token + ":INFO";
        redisTemplate.delete(key);

        key = "MANAGE:USER:" + token + ":ROLE";
        redisTemplate.delete(key);

        key = "MANAGE:USER:" + token + ":PERM";
        redisTemplate.delete(key);

        key = "MANAGE:USER:" + userId;
        redisTemplate.delete(key);
    }

    /**
     * 留言
     *
     * @param userId
     * @param message
     */
    public void leaveMessage(long userId, Object message) {
        String token = (String) getTokenById(userId);
        if (StringUtils.isBlank(token)) {
            return;
        }
        String key = "MANAGE:USER:" + token + ":MESSAGE";
        redisTemplate.opsForValue().set(key, message);
        clear(userId);
    }

    /**
     * 留言
     *
     * @param token
     */
    public String readeLeaveMessage(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        String key = "MANAGE:USER:" + token + ":MESSAGE";
        String message = (String) redisTemplate.opsForValue().get(key);
        redisTemplate.delete(key);
        return message;
    }
}
