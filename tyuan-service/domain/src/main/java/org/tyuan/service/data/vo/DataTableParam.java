/**
 * @ClassName DataTableParam
 * @Author dev@tyuan.design
 * @Date 2020/6/19 15:37
 */
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
package org.tyuan.service.data.vo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DataTableParam implements Serializable {

    //开始页
    private int pageSize;

    //大小
    private int current;

    private int total;

    //开始时间
    private String from;

    //结束时间
    private String to;

    //开始时间-结束时间
    private List<Date> dateList;

    private Map filter;

    private Map sorter;

    public int getOffset() {
        return (current - 1) * pageSize;
    }
}
