/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/3/17 1:52 下午
 */
package com.tyuan.common;

import java.io.Serializable;
import java.util.List;

public interface ITree extends Serializable {

    /**
     * id
     *
     * @return
     */
    Long getId();

    /**
     * parentId
     *
     * @return
     */
    Long getParentId();

    List<ITree> getChildren();

    void setChildren(List<ITree> trees);
}