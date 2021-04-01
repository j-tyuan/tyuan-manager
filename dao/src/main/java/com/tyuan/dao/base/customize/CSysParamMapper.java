package com.tyuan.dao.base.customize;

import com.tyuan.dao.base.mapper.SysParamMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.Map;

public interface CSysParamMapper extends SysParamMapper, ICacheInfo {


    /**
     * 统计条数及获取最后一个更新的时间
     *
     * @return
     */
    @Override
    @Select("select update_date, b.c total " +
            " from sys_param, " +
            "  (select count(*) c from sys_param) as b " +
            " order by update_date desc " +
            " limit 1;")
    @Results({
            @Result(column = "update_date", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "total", property = "total", jdbcType = JdbcType.INTEGER)
    })
    Map getLastUpdateAndCount();


}
