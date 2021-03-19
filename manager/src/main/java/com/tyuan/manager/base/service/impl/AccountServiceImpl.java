/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/3/14 1:54 下午
 */
package com.tyuan.manager.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.tyuan.common.exception.ServiceException;
import com.tyuan.dao.base.mapper.SysUserMapper;
import com.tyuan.dao.base.mapper.SysUserWebLayoutMapper;
import com.tyuan.manager.base.service.AccountService;
import com.tyuan.manager.base.service.SysUserAvatarService;
import com.tyuan.manager.base.utils.UserInfoHolder;
import com.tyuan.model.base.ErrorCodeConsts;
import com.tyuan.model.base.pojo.SysUser;
import com.tyuan.model.base.pojo.SysUserWebLayout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    SysUserWebLayoutMapper sysUserWebLayoutMapper;

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysUserAvatarService sysUserAvatarService;

    @Override
    public void customLayout(Map customLayoutVo) {
        String structure = JSONObject.toJSONString(customLayoutVo);
        Long userId = UserInfoHolder.getUserId();
        SysUserWebLayout sysUserWebLayout = sysUserWebLayoutMapper.selectByPrimaryKey(userId);
        if (null == sysUserWebLayout) {
            sysUserWebLayout = new SysUserWebLayout();
            sysUserWebLayout.setUserId(userId);
            sysUserWebLayout.setLayoutStructure(structure);
            sysUserWebLayoutMapper.insertSelective(sysUserWebLayout);
            return;
        }
        sysUserWebLayout.setLayoutStructure(structure);
        sysUserWebLayoutMapper.updateByPrimaryKeySelective(sysUserWebLayout);
        return;
    }

    @Override
    public void setting(SysUser sysUser) {
        SysUser newUserInfo = new SysUser();
        Long userId = UserInfoHolder.getUserId();
        newUserInfo.setId(userId);
        newUserInfo.setEmail(sysUser.getEmail());
        newUserInfo.setMobile(sysUser.getPhone());
        newUserInfo.setPhone(sysUser.getPhone());
        newUserInfo.setRemarks(sysUser.getRemarks());
        sysUserMapper.updateByPrimaryKeySelective(newUserInfo);
        return;
    }

    @Override
    public Map account() {
        Long uid = UserInfoHolder.getUserId();
        SysUserWebLayout layout = sysUserWebLayoutMapper.selectByPrimaryKey(uid);
        Map map = Maps.newHashMap();
        String layStr = layout.getLayoutStructure();
        map.put("layout", JSONObject.parse(layStr));

        SysUser sysUser = sysUserMapper.selectByPrimaryKey(uid);
        sysUser.setPassword(null);
        Map userMap = (Map) JSONObject.toJSON(sysUser);
        String avatar = sysUserAvatarService.getAvatarById(sysUser.getAvatarId());
        userMap.put("avatar", avatar);
        map.put("account", userMap);

        return map;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Error.class}, isolation = Isolation.DEFAULT)
    public void accountAvatar(MultipartFile multipartFile) throws ServiceException {
        try {
            Long uid = UserInfoHolder.getUserId();
            SysUser record = new SysUser();
            record.setId(uid);
            Long id = sysUserAvatarService.updateAvatar(multipartFile);
            record.setAvatarId(id);
            sysUserMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ErrorCodeConsts.ERROR, "头像更新失败");
        }

    }
}
