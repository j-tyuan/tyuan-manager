package com.tyuan.dao.base.customize;

import com.tyuan.dao.base.mapper.SysSourceMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.Map;

public interface CSysSourceMapper extends SysSourceMapper, ICacheInfo {


    /**
     * 统计条数及获取最后一个更新的时间
     *
     * @return
     */
    @Select("select update_date, b.c total " +
            " from sys_source, " +
            "  (select count(*) c from sys_source) as b " +
            " order by update_date desc " +
            " limit 1;")
    @Results({
            @Result(column = "update_date", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "total", property = "total", jdbcType = JdbcType.INTEGER)
    })
    Map getLastUpdateAndCount();
}
