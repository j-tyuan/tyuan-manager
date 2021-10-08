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
package org.tyuan.service.system.service;

import com.github.pagehelper.PageInfo;
import org.tyuan.service.system.model.pojo.SysLoginLog;
import org.tyuan.service.system.model.pojo.SysUser;
import org.tyuan.service.system.model.vo.sys.SysLoginLogTableParamsVo;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author jiangguiqi@aliyun.com
 * @version 1.0
 * @date 2021/3/23 1:50 下午
 */
public interface SysLoginLogService extends BaseService<SysLoginLog> {

    PageInfo getByParams(SysLoginLogTableParamsVo param);

    /**
     * 记录登陆日志
     * @param sysUser
     * @param request
     */
    void add(SysUser sysUser, HttpServletRequest request);
}
