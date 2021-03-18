/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/3/14 1:54 下午
 */
package com.tyuan.manager.base.service;

import com.tyuan.common.exception.ServiceException;
import com.tyuan.model.base.pojo.SysUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface AccountService {

    /**
     * 用户自定义布局
     *
     * @param customLayoutVo
     */
    void customLayout(Map customLayoutVo);

    /**
     * 账号备注
     *
     * @param sysUser
     */
    void setting(SysUser sysUser);

    /**
     * 获取账号信息
     * @return
     */
    Map account();

    /**
     * 修改头像
     * @param multipartFile
     */
    void accountPhoto(MultipartFile multipartFile) throws ServiceException;
}
