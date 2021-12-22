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
package org.tyuan.service.data;

import lombok.Data;

/**
 * @author jiangguiqi@aliyun.com
 * @version 1.0
 * @date 2021/12/22 1:46 下午
 */
@Data
public class LogTransport {

    public LogTransport(Long duration, Exception exception) {
        this.duration = duration;
        this.exception = exception;
    }

    private Long duration;

    private Exception exception;
}
