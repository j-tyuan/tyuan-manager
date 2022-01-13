/**
 * @ClassName RoleServiceImpl
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:58
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
package org.tyuan.service.application.service.manage.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.tyuan.service.application.service.manage.SysRoleUserService;
import org.tyuan.service.application.service.manage.SysUserService;
import org.tyuan.service.dao.mapper.SysRoleMapper;
import org.tyuan.service.dao.mapper.customize.manage.CSysUserRoleMapper;
import org.tyuan.service.data.model.*;
import org.tyuan.service.data.vo.sys.RoleUserTableParamsVo;
import org.tyuan.service.data.vo.sys.SysRoleUserVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static org.tyuan.service.data.cache.CacheConstant.SYS_USER_ROLE_CACHE;

@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Resource
    SysRoleMapper sysRoleMapper;

    @Resource
    CSysUserRoleMapper csysUserRoleMapper;

    @Resource
    SysUserService sysUserService;

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public PageInfo getUser(RoleUserTableParamsVo paramsVo) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(paramsVo.getRoleId());
        List<SysUserRole> roles = csysUserRoleMapper.selectByExample(example);
        List<String> longs = roles.stream().map(e -> e.getUserId()).collect(Collectors.toList());

        SysUserExample sysUserExample = sysUserService.getUserExampleByParams(paramsVo, null);
        List<SysUserExample.Criteria> criterias = sysUserExample.getOredCriteria();
        SysUserExample.Criteria criteria = criterias.get(0);
        if (paramsVo.getSearchType() == 1) {
            // 如果没有用户绑定此角色，直接返回空pageInfo对象
            if (CollectionUtils.isNotEmpty(longs)) {
                criteria.andIdIn(longs);
            } else {
                return new PageInfo();
            }
        } else if (CollectionUtils.isNotEmpty(longs) && paramsVo.getSearchType() == 2) {
            criteria.andIdNotIn(longs);
        }
        return sysUserService.getByExample(sysUserExample, paramsVo);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void bindUser(SysRoleUserVo sysRoleUserVo) {
        csysUserRoleMapper.batchInsert(sysRoleUserVo.getRoleId(), sysRoleUserVo.getUserIds());

    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Error.class}, isolation = Isolation.DEFAULT)
    public void unbindUser(SysRoleUserVo sysRoleUserVo) {
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRoleIdEqualTo(sysRoleUserVo.getRoleId()).andUserIdIn(sysRoleUserVo.getUserIds());
        csysUserRoleMapper.deleteByExample(example);

        List<String> userIds = sysRoleUserVo.getUserIds().stream().map(e -> SYS_USER_ROLE_CACHE + "::" + e).collect(Collectors.toList());
        redisTemplate.delete(userIds);
    }

    @Override
    public List<String> getRoleIdsByUserId(String uid) {
        return csysUserRoleMapper.getRoleIdsByUid(uid);
    }

    @Override
    @Cacheable(cacheNames = SYS_USER_ROLE_CACHE, key = "{#uid}")
    public List<SysRole> getRoleByUserId(String uid) {
        List<String> ids = this.getRoleIdsByUserId(uid);
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        SysRoleExample sysRoleExample = new SysRoleExample();
        sysRoleExample.createCriteria().andIdIn(ids);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        return sysRoles;
    }

}
