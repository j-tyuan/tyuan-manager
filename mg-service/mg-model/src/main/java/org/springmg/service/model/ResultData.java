/**
 * @ClassName ResultData
 * @Author dev@tyuan.design
 * @Date 2020/6/22 10:18
 */
package org.springmg.service.model;

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

