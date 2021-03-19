package com.tyuan.manager.base.service;

import com.github.pagehelper.PageInfo;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.model.base.pojo.SysUser;
import com.tyuan.model.base.pojo.SysUserAvatar;
import com.tyuan.model.base.vo.sys.SysUserTableParamsVo;
import com.tyuan.model.base.vo.sys.UserAuthVo;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/6/29 16:33
 */
public interface SysUserAvatarService extends BaseService<SysUserAvatar> {

    /**
     * 上传用户头像
     *
     * @param multipartFile
     */
    Long updateAvatar(MultipartFile multipartFile);

    /**
     * 根据ID获取头像
     *
     * @param id
     * @return
     */
    String getAvatarById(Long id);

    /**
     * 获取头像列表
     *
     * @param ids
     * @return
     */
    List<SysUserAvatar> getByIds(List<Long> ids);
}
