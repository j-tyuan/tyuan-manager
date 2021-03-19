package com.tyuan.manager.base.service;

import com.github.pagehelper.PageInfo;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.model.base.pojo.SysUser;
import com.tyuan.model.base.vo.sys.SysUserTableParamsVo;
import com.tyuan.model.base.vo.sys.UserAuthVo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/6/29 16:33
 */
public interface SysUserService extends BaseService<SysUser> {


    enum USER_TYPE {

        ORDINARY(0),
        SYS(1);

        private int type;

        private USER_TYPE(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }

    PageInfo getByParams(SysUserTableParamsVo param);

    /**
     * 根据手机号或者用户名获取用户信息
     * 如果两个都为null返回所有用户
     * 日后有大量用户，可以考虑使用 getByParams
     *
     * @param name  用户
     * @param phone 手机号
     * @return
     */
    List<SysUser> getAll(String name, String phone);

    /**
     * 绑定用户
     *
     * @param sysUser
     * @throws ServiceException
     */
    void add(SysUser sysUser) throws ServiceException;

    /**
     * 修改用户
     *
     * @param sysUser
     * @throws ServiceException
     */
    void edit(SysUser sysUser) throws ServiceException;

    /**
     * 根据账号获取用户信息
     *
     * @param account
     * @return
     */
    SysUser getByAccount(String account);

    /**
     * 更新用户登陆信息
     *
     * @param request
     * @param userId
     */
    void updateUserLoginInfo(HttpServletRequest request, long userId);

    /**
     * 更新用户角色
     *
     * @param vo
     */
    void editAuth(Long userId, UserAuthVo vo) throws ServiceException;

    /**
     * 启用或禁用用户
     *
     * @param userId
     * @param disable
     */
    void disable(Long userId, Integer disable) throws ServiceException;

    /**
     * @param id
     * @return
     */
    SysUser getById(Long id);

    /**
     * 海选用户
     *
     * @param value
     * @return
     */
    List fetch(String value);

    /**
     * 用户数据后置处理
     *
     * @param users
     * @return
     */
    List<Map> userPostProcessor(List<SysUser> users);
}
