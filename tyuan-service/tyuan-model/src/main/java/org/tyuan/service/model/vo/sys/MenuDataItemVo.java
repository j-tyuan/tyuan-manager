/**
 * @ClassName LeftMenuVo
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:18
 */
package org.tyuan.service.model.vo.sys;

import org.tyuan.common.ITree;
import org.tyuan.service.model.pojo.SysSource;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
public class MenuDataItemVo extends SysSource implements Serializable, ITree {

    private List<ITree> children;

    /**
     * @name 在菜单中隐藏子节点
     */
    private boolean hideChildrenInMenu;

    /**
     * @name 在菜单中隐藏自己和子节点
     */
    private boolean hideInMenu;

    /**
     * String or bool
     *
     * @name 自定义菜单的国际化 key
     */
    private Object locale = false;

    /**
     * @name 用于标定选中的值，默认是 path
     */
    private String key;

    /**
     * @name disable 菜单选项
     */
    private boolean disabled;

    /**
     * @name 路径
     */
    private String path;

    /**
     * @name 自定义父节点
     * @description 当此节点被选中的时候也会选中 parentKeys 的节点
     */
    private String[] parentKeys;

    /**
     * @name 隐藏自己，并且将子节点提升到与自己平级
     */
    private boolean flatMenu;
}
