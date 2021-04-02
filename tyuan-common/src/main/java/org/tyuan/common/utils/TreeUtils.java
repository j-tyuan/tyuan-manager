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
package org.tyuan.common.utils;

import com.google.common.collect.Lists;
import org.tyuan.common.ITree;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
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
            ITree item = e;
            if (e.getParentId().equals(pid)) {
                List<ITree> iTrees = tree(list, e.getId());
                if (CollectionUtils.isNotEmpty(iTrees)) {
                    Collections.sort(iTrees, Comparator.comparingLong(o -> o.getSort()));
                    item.setChildren(iTrees);
                }
                result.add(item);
            }
        });
        return result.size() == 0 ? null : result;
    }


}
