package com.tyuan.model;

import com.github.pagehelper.PageInfo;
import lombok.Getter;

@Getter
public class ResultTable {
    private Object data;

    private int current;

    private int pageSize;

    private boolean success;

    private long total;

    private int errorCode = ErrorCodeConsts.SUCCESS;

    public void setPageInfo(PageInfo pageInfo) {
        data = pageInfo.getList();
        current = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        success = true;
        total = pageInfo.getTotal();
    }
}
