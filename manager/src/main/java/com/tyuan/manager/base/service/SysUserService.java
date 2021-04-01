package com.tyuan.manager.base.service;

import com.github.pagehelper.PageInfo;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.model.base.pojo.SysRole;
import com.tyuan.model.base.pojo.SysUser;
import com.tyuan.model.base.pojo.SysUserExample;
import com.tyuan.model.base.vo.DataTableParam;
import com.tyuan.model.base.vo.sys.SysUserTableParamsVo;
import com.tyuan.model.base.vo.sys.SysUserVo;
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
public interface SysUserService extends BaseService<SysUserVo> {


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


    /**
     * 根据入参获取 SysUserExample
     * 使用场景：通用SysUserExample
     *
     * @param param
     * @return
     */
    SysUserExample getUserExampleByParams(SysUserTableParamsVo param);

    /**
     * 获取用户列表
     * 使用场景：用户管理前端列表
     *
     * @param param
     * @return
     */
    PageInfo getByParams(SysUserTableParamsVo param);

    /**
     * 获取用户列表
     * 使用场景：用户管理前端列表、其它模块
     *
     * @param sysUserExample
     * @param param
     * @return
     */
    PageInfo getByExample(SysUserExample sysUserExample, DataTableParam param);

    /**
     * 绑定用户
     *
     * @param sysUser
     * @throws ServiceException
     */
    @Override
    void add(SysUserVo sysUser) throws ServiceException;

    /**
     * 修改用户
     *
     * @param sysUser
     * @throws ServiceException
     */
    @Override
    void edit(SysUserVo sysUser) throws ServiceException;

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

    /**
     * 根据用户ID 获取全部的角色信息
     *
     * @param uid
     * @return
     */
    List<Long> getRoleIdsByUserId(Long uid);

    /**
     * 根据用户ID 获取全部的角色信息
     *
     * @param uid
     * @return
     */
    List<SysRole> getRoleByUserId(Long uid);
}
