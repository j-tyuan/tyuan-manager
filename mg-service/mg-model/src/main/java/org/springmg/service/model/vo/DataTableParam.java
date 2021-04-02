/**
 * @ClassName DataTableParam
 * @Author dev@tyuan.design
 * @Date 2020/6/19 15:37
 */
package org.springmg.service.model.vo;


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
