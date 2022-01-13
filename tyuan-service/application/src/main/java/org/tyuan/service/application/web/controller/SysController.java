/**
 * @ClassName SysController
 * @Author dev@tyuan.design
 * @Date 2020/6/22 11:08
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
package org.tyuan.service.application.web.controller;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.tyuan.service.application.cache.UserInfoCacheService;
import org.tyuan.service.application.service.manage.SysUserService;
import org.tyuan.service.data.ErrorCodeConsts;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.model.SysUser;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class SysController {

    @Autowired
    UserInfoCacheService userTokenCacheService;

    @Resource
    private UserInfoCacheService userInfoCacheService;

    @Resource
    private SysUserService sysUserService;


    @GetMapping("/api/currentUser")
    public ResultData getUserInfo(@ModelAttribute("userToken") String userToken) {
        Map user = (Map) userTokenCacheService.get(userToken);
        String userId = String.valueOf(user.get("id"));
        SysUser u = sysUserService.getById(userId);
        ResultData resultData = new ResultData();
        resultData.setData(u);
        resultData.setErrorCode(ErrorCodeConsts.SUCCESS);
        return resultData;
    }

    @GetMapping("/api/permission")
    public ResultData permission(@ModelAttribute("userToken") String userToken) {
        ResultData resultData = new ResultData();
        Map map = Maps.newHashMap();
        map.put("permission", userInfoCacheService.getPerm(userToken));
        map.put("role", userInfoCacheService.getRole(userToken));
        resultData.setData(map);
        resultData.setErrorCode(ErrorCodeConsts.SUCCESS);
        return resultData;
    }

}
