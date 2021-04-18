/**
 * @ClassName ResultData
 * @Author dev@tyuan.design
 * @Date 2020/6/22 10:18
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
package org.tyuan.service.system.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author dev@tyuan.design
 */
public class ResultData<T> implements Serializable {

    private int errorCode = ErrorCodeConsts.SUCCESS;

    private Object errorMessage = null;

    private T data = null;

    private boolean success = true;

    public int getErrorCode() {
        return errorCode;
    }

    public ResultData setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public ResultData setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultData setData(T data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}

