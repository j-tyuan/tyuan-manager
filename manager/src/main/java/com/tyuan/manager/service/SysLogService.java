package com.tyuan.manager.service;

import com.github.pagehelper.PageInfo;
import com.tyuan.model.pojo.SysLog;
import com.tyuan.model.pojo.SysLogWithBLOBs;
import com.tyuan.model.vo.sys.SysLogTableVo;
import com.tyuan.model.vo.sys.SysParamTableVo;

/**
 * @author jiangguiqi@aliyun.com
 * @version 1.0
 * @date 2021/2/4 6:48 下午
 */
public interface SysLogService extends BaseService<SysLogWithBLOBs>  {



    PageInfo getByParams(SysLogTableVo param);

    /**
     * 保存异常
     * @param requestId
     * @param exception
     */
    void saveExceptionInfo(String requestId,String exception);
}
