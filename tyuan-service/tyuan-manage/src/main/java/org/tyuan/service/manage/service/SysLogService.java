package org.tyuan.service.manage.service;

import com.github.pagehelper.PageInfo;
import org.tyuan.service.model.pojo.SysLogWithBLOBs;
import org.tyuan.service.model.vo.sys.SysLogTableVo;

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
