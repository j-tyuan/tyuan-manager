package com.tyuan.manager.base.service.impl;

import com.tyuan.dao.base.mapper.SysUserAvatarMapper;
import com.tyuan.manager.base.service.BaseService;
import com.tyuan.manager.base.service.SysUserAvatarService;
import com.tyuan.manager.base.utils.UserInfoHolder;
import com.tyuan.model.base.pojo.SysUser;
import com.tyuan.model.base.pojo.SysUserAvatar;
import com.tyuan.model.base.pojo.SysUserAvatarExample;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @Author: dev@tyuan.design
 * @DateTime: 2020/6/29 16:33
 */
@Service
public class SysUserAvatarServiceImpl implements SysUserAvatarService {

    @Resource
    private SysUserAvatarMapper sysUserAvatarMapper;

    Logger logger = LoggerFactory.getLogger(SysUserAvatarServiceImpl.class);


    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class}, isolation = Isolation.DEFAULT)
    public Long updateAvatar(MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            Long uid = UserInfoHolder.getUserId();
            SysUser record = new SysUser();
            record.setId(uid);
            String base64Str = Base64.getEncoder().encodeToString(bytes);
            String base64Encode = "data:" + multipartFile.getContentType() + ";base64," + base64Str;
            SysUserAvatar sysUserAvatar = new SysUserAvatar();
            sysUserAvatar.setUserAvatar(base64Encode);
            sysUserAvatarMapper.insertSelective(sysUserAvatar);
            return sysUserAvatar.getId();
        } catch (Exception e) {
            return -1L;
        }

    }

    @Override
    public String getAvatarById(Long id) {
        SysUserAvatar sysUserAvatar = sysUserAvatarMapper.selectByPrimaryKey(id);
        if (null == sysUserAvatar) {
            return null;
        }
        return sysUserAvatar.getUserAvatar();
    }

    @Override
    public List<SysUserAvatar> getByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            logger.warn("【获取用户头像】 id 列表为空");
            return new ArrayList<>();
        }
        SysUserAvatarExample example = new SysUserAvatarExample();
        example.createCriteria().andIdIn(ids);
        return sysUserAvatarMapper.selectByExampleWithBLOBs(example);
    }

}
