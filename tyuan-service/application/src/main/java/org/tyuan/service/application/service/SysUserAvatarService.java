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
package org.tyuan.service.application.service;

import org.tyuan.service.dao.model.SysUserAvatar;
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