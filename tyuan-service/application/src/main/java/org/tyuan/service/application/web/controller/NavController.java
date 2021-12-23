/**
 * @ClassName MavMenuController
 * @Author dev@tyuan.design
 * @Date 2020/6/23 17:53
 * <p>
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
package org.tyuan.service.application.web.controller;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyuan.common.ITree;
import org.tyuan.common.utils.TreeUtils;
import org.tyuan.service.application.service.SysSourceService;
import org.tyuan.service.application.web.RouteConstant;
import org.tyuan.service.data.ResultData;
import org.tyuan.service.data.model.custom.CSysSource;
import org.tyuan.service.data.vo.sys.MenuDataItemVo;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class NavController {

    @Resource
    SysSourceService sysMenuService;

    @GetMapping(RouteConstant.ROUTER_SYS_NAV)
    public ResultData getNav() {
        List<CSysSource> list = sysMenuService.authorityFilter(sysMenuService.getAll());
        List<MenuDataItemVo> newList = Lists.newArrayList();
        list.forEach(e -> {
            MenuDataItemVo leftMenuVo = new MenuDataItemVo();
            leftMenuVo.setName(e.getSourceName());
            leftMenuVo.setPath(e.getSourceHref());
            leftMenuVo.setIcon(e.getSourceIcon());
            leftMenuVo.setSort(e.getSourceSort());
            leftMenuVo.setLeaf(e.getIsLeaf());
            leftMenuVo.setId(e.getId());
            leftMenuVo.setParentId(e.getParentId());
            newList.add(leftMenuVo);
        });

        List<ITree> leftMenuVos = TreeUtils.tree(newList, 0L);
        Collections.sort(leftMenuVos, Comparator.comparingLong(o -> o.getSort()));

        ResultData resultData = new ResultData();
        resultData.setData(leftMenuVos);
        resultData.setSuccess(true);
        return resultData;
    }
}
