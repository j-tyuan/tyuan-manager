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
package org.tyuan.service.system.mapper.customize;

import org.tyuan.service.system.mapper.SysParamMapper;
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
