package org.tyuan.service.manage.service;

import org.tyuan.service.model.pojo.SysUserAvatar;
import org.springframework.web.multipart.MultipartFile;

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
