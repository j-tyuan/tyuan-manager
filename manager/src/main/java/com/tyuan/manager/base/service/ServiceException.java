/**
 * @ClassName ServiceException
 * @Author dev@tyuan.design
 * @Date 2020/6/19 15:39
 */
package com.tyuan.manager.base.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: dev@tyuan.design
 * @Description: 异常
 * @Date: Created in 上午11:27 18-1-4
 */
public class ServiceException extends Exception {

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

