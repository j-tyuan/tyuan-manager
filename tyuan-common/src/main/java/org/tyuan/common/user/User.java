/**
 * @ClassName User
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/7/15 14:17
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
package org.tyuan.common.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private int id;

    /**
     * 用户可用流量
     */
    private long useableFlow;

    private String name;

    private int userType;

    private String cookie;

}
