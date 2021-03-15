package com.tyuan.manager.base.service;

import com.github.pagehelper.PageInfo;
import com.tyuan.model.base.pojo.SysLogWithBLOBs;
import com.tyuan.model.base.vo.sys.SysLogTableVo;

/**
 * @author jiangguiqi@aliyun.com
 * @version 1.0
 * @date 2021/2/4 6:48 下午
 */
public interface SysLogService extends BaseService<SysLogWithBLOBs> {


    PageInfo getByParams(SysLogTableVo param);


    /**
     *
     * @param requestId
     */
    void updateByRequestId(String requestId, SysLogWithBLOBs sysLogWithBLOBs);

}
