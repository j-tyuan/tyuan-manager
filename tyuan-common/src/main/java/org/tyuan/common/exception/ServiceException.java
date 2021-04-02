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
package org.tyuan.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: dev@tyuan.design
 * @Description: 异常
 * @Date: Created in 上午11:27 18-1-4
 */
public class ServiceException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(this.getMessage());

    private Integer status;

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Integer getStatus() {
        super.fillInStackTrace();
        return status;
    }

    public ServiceException(Integer status, String msg) {
        super(msg);
        this.status = status;
        logger.warn("msg:{},status:{}", msg, status);
    }
}
