/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/3/17 1:29 下午
 */
package com.tyuan.common.utils;

import com.google.common.collect.Lists;
import com.tyuan.common.ITree;

import java.util.List;

public class TreeUtils {

    /**
     * 获取节点子ID列表
     *
     * @param data
     * @param id
     * @return
     */
    public static List getChild(List<? extends ITree> data, final Long id) {
        List ids = Lists.newArrayList();
        for (ITree iTree : data) {
            Long pid = iTree.getParentId();
            long newId = id;
            if (pid == newId) {
                ids.add(iTree);
                newId = iTree.getId();
            }
            List<ITree> children = iTree.getChildren();
            if (null != children) {
                ids.addAll(getChild(children, newId));
            }
        }
        return ids;
    }

    /**
     * 树形结构
     *
     * @param list
     * @param pid
     * @return
     */
    public static List<ITree> tree(List<? extends ITree> list, Long pid) {
        List<ITree> result = Lists.newArrayList();
        list.forEach(e -> {
            ITree abstractTree = e;
            if (e.getParentId() == pid) {
                abstractTree.setChildren(tree(list, e.getId()));
                result.add(abstractTree);
            }
        });
        return result.size() == 0 ? null : result;
    }


}
