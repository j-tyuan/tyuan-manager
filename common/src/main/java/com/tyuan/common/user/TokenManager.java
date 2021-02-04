/**
 * @InterFaceName TokenManager
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/10/31 10:36
 */
package com.tyuan.common.user;

/**
 * token管理
 *
 * @param <T>
 */
public interface TokenManager<T> {

    /**
     * 创建token
     *
     * @param t
     * @return
     */
    String createToken(T t);

    /**
     * 查找Token
     *
     * @param token
     * @return
     */
    T findByToken(String token);


    /**
     * 删除Token
     *
     * @param token
     * @return T or entity
     */
    T deleteToken(String token);

    /**
     * token验证
     *
     * @param token
     */
    void auth(String token) throws AuthorizationException;
}
