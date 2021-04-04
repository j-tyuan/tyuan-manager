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

import org.tyuan.service.system.mapper.SysSourceMapper;
import org.tyuan.service.system.model.pojo.custom.CSysSource;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

public interface CSysSourceMapper extends SysSourceMapper, ICacheInfo {


    /**
     * 统计条数及获取最后一个更新的时间
     *
     * @return
     */
    @Override
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

    /**
     * 获取所有资源
     */
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_source
     *
     * @mbggenerated
     */
    @Select({
            "select",
            "id, parent_id, name, sort, href, target, icon, is_leaf, ",
            "is_show, permission_id, remarks, del_flag",
            "from sys_source"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "parent_id", property = "parentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sort", property = "sort", jdbcType = JdbcType.DECIMAL),
            @Result(column = "href", property = "href", jdbcType = JdbcType.VARCHAR),
            @Result(column = "target", property = "target", jdbcType = JdbcType.VARCHAR),
            @Result(column = "icon", property = "icon", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_leaf", property = "isLeaf", jdbcType = JdbcType.BIT),
            @Result(column = "is_show", property = "isShow", jdbcType = JdbcType.BIT),
            @Result(column = "permission_id", property = "permissionId", jdbcType = JdbcType.BIGINT),
            @Result(column = "remarks", property = "remarks", jdbcType = JdbcType.VARCHAR),
            @Result(column = "del_flag", property = "delFlag", jdbcType = JdbcType.BIT)
    })
    List<CSysSource> getAll();
}