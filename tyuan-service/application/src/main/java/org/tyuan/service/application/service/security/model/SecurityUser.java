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
package org.tyuan.service.application.service.security.model;

import com.beust.jcommander.internal.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.tyuan.service.application.service.security.system.SystemSecurityService;
import org.tyuan.service.data.model.SysUser;

import java.util.Collection;
import java.util.List;

public class SecurityUser extends SysUser {

    private static final long serialVersionUID = -797397440703066079L;

    private Collection<GrantedAuthority> authorities;
    private boolean enabled;
    private UserPrincipal userPrincipal;
    private SystemSecurityService systemSecurityService;

    public SecurityUser() {
        super();
    }

    public SecurityUser(String id) {
        super.setId(id);
    }

    public SecurityUser(SysUser user, boolean enabled, UserPrincipal userPrincipal, SystemSecurityService systemSecurityService) {
        this.setId(user.getId());
        this.setMobile(user.getMobile());
        this.setEnabled(enabled);
        this.setUserName(user.getUserName());
        this.setAuthority(user.getAuthority());
        this.setAdditionalInfo(user.getAdditionalInfo());
        this.setUserAccount(user.getUserAccount());
        this.userPrincipal = userPrincipal;
        this.systemSecurityService = systemSecurityService;
    }


    public Collection<GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = Lists.newArrayList();
            authorities.add(new SimpleGrantedAuthority(this.getAuthority()));
            List<String> permission = systemSecurityService.getAuthorities(getId());
            if (CollectionUtils.isNotEmpty(permission)) {
                permission.forEach(e -> {
                    this.authorities.add(new SimpleGrantedAuthority(e));
                });
            }

        }

        return this.authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public UserPrincipal getUserPrincipal() {
        return userPrincipal;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUserPrincipal(UserPrincipal userPrincipal) {
        this.userPrincipal = userPrincipal;
    }

    public void setSystemSecurityService(SystemSecurityService systemSecurityService) {
        this.systemSecurityService = systemSecurityService;
    }
}
