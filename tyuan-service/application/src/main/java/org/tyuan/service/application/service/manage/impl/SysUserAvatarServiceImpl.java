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
package org.tyuan.service.application.service.manage.impl;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.tyuan.service.application.service.manage.SysUserAvatarService;
import org.tyuan.service.common.UserInfoHolder;
import org.tyuan.service.dao.mapper.SysUserAvatarMapper;
import org.tyuan.service.data.model.SysUser;
import org.tyuan.service.data.model.SysUserAvatar;
import org.tyuan.service.data.model.SysUserAvatarExample;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

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
    public String updateAvatar(MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            String uid = UserInfoHolder.getUserId();
            SysUser record = new SysUser();
            record.setId(uid);
            String base64Str = Base64.getEncoder().encodeToString(bytes);
            String base64Encode = "data:" + multipartFile.getContentType() + ";base64," + base64Str;
            SysUserAvatar sysUserAvatar = new SysUserAvatar();
            sysUserAvatar.setUserAvatar(base64Encode);
            sysUserAvatar.setId(UUID.randomUUID().toString());
            sysUserAvatarMapper.insertSelective(sysUserAvatar);
            return sysUserAvatar.getId();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public String getAvatarById(String id) {
        SysUserAvatar sysUserAvatar = sysUserAvatarMapper.selectByPrimaryKey(id);
        if (null == sysUserAvatar) {
            return null;
        }
        return sysUserAvatar.getUserAvatar();
    }

    @Override
    public List<SysUserAvatar> getByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            logger.warn("【获取用户头像】 id 列表为空");
            return new ArrayList<>();
        }
        SysUserAvatarExample example = new SysUserAvatarExample();
        example.createCriteria().andIdIn(ids);
        return sysUserAvatarMapper.selectByExampleWithBLOBs(example);
    }

}
