/**
 * @ClassName SysMenuServiceImpl
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:54
 * <p>
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
 * <p>
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
 * <p>
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
 * <p>
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
 * <p>
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
 * <p>
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
package org.tyuan.service.application.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.tyuan.common.exception.ServiceException;
import org.tyuan.service.application.service.SysPermissionService;
import org.tyuan.service.application.service.SysSourceService;
import org.tyuan.service.application.service.security.model.SecurityUser;
import org.tyuan.service.common.UserInfoHolder;
import org.tyuan.service.dao.mapper.customize.CSysSourceMapper;
import org.tyuan.service.data.ErrorCodeConsts;
import org.tyuan.service.data.model.SysPermission;
import org.tyuan.service.data.model.SysSource;
import org.tyuan.service.data.model.SysSourceExample;
import org.tyuan.service.data.model.custom.CSysSource;
import org.tyuan.service.data.security.Authority;
import org.tyuan.service.data.vo.DeleteVo;

import javax.annotation.Resource;
import java.util.*;

import static org.tyuan.service.data.cache.CacheConstant.SYS_SOURCE_CACHE;

@Service
public class SysSourceServiceImpl implements SysSourceService {

    @Resource
    CSysSourceMapper csysSourceMapper;

    @Autowired
    SysPermissionService sysPermissionService;

    @Override
    @Cacheable(cacheNames = SYS_SOURCE_CACHE, key = "'ALL'")
    public List<CSysSource> getAll() {
        return csysSourceMapper.getAll();
    }


    @Override
    @CacheEvict(cacheNames = SYS_SOURCE_CACHE, allEntries = true)
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void add(SysSource sysSource) throws ServiceException {
        String userName = UserInfoHolder.getUserName();
        sysSource.setUpdateBy(userName);
        sysSource.setCreateBy(userName);
        csysSourceMapper.insertSelective(sysSource);
    }

    @Override
    @CacheEvict(cacheNames = SYS_SOURCE_CACHE, allEntries = true)
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void edit(SysSource sysSource) throws ServiceException {
        Long id = sysSource.getId();

        SysSource val = csysSourceMapper.selectByPrimaryKey(id);
        if (null == val) {
            throw new ServiceException(ErrorCodeConsts.ERROR, "未找到数据，修改失败");
        }

        sysSource.setUpdateBy(UserInfoHolder.getUserName());
        sysSource.setUpdateTime(new Date());

        csysSourceMapper.updateByPrimaryKeySelective(sysSource);

    }

    @Override
    @CacheEvict(cacheNames = SYS_SOURCE_CACHE,allEntries = true)
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void del(DeleteVo deleteVo) throws ServiceException {
        List<Long> ids = deleteVo.getId();
        ids.forEach(id -> {
            csysSourceMapper.deleteByPrimaryKey(id);
        });

    }

    @Override
    public SysSource getById(Long id) {
        return csysSourceMapper.selectByPrimaryKey(id);
    }

    /**
     * 权限过滤
     *
     * @return
     */
    @Override
    public List<CSysSource> authorityFilter(List<CSysSource> list) {
        List<CSysSource> newList = Lists.newArrayList();
        List<SysPermission> permissions = sysPermissionService.getAll();
        Iterator<CSysSource> sysSourceIterator = list.iterator();

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
        SecurityUser principal = (SecurityUser) securityContext.getAuthentication().getPrincipal();

        while (sysSourceIterator.hasNext()) {
            CSysSource sysSource = sysSourceIterator.next();
            Iterator<SysPermission> permIterator = permissions.iterator();
            while (permIterator.hasNext()) {
                SysPermission sysPower = permIterator.next();
                if (sysPower.getId().equals(sysSource.getPermissionId())) {
                    String permission = sysPower.getPermission();

                    if (Authority.parse(principal.getAuthority()) == Authority.SYS_ADMIN) {
                        newList.add(sysSource);
                        continue;
                    }

                    Optional<? extends GrantedAuthority> first = authorities.stream().filter(e -> e.getAuthority().equalsIgnoreCase(permission)).findFirst();
                    if (null != first) {
                        newList.add(sysSource);
                    }
                }
            }
        }
        return newList;
    }

    @Override
    public List<SysSource> getByParentId(long parentId) {
        SysSourceExample example = new SysSourceExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<SysSource> sources = csysSourceMapper.selectByExample(example);
        return sources;
    }
}
