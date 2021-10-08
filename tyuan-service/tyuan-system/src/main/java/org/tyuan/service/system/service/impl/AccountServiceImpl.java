/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.system.mapper.SysUserMapper;
import org.tyuan.service.system.mapper.SysUserWebLayoutMapper;
import org.tyuan.service.system.service.AccountService;
import org.tyuan.service.system.service.SysUserAvatarService;
import org.tyuan.common.utils.UserInfoHolder;
import org.tyuan.service.system.model.ErrorCodeConsts;
import org.tyuan.service.system.model.pojo.SysUser;
import org.tyuan.service.system.model.pojo.SysUserWebLayout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
        newUserInfo.setUserEmail(sysUser.getUserEmail());
        newUserInfo.setMobile(sysUser.getUserPhone());
        newUserInfo.setUserPhone(sysUser.getUserPhone());
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
        sysUser.setUserPwd(null);
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
