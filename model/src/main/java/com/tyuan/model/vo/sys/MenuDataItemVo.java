/**
 * @ClassName LeftMenuVo
 * @Author dev@tyuan.design
 * @Date 2020/6/23 18:18
 */
package com.tyuan.model.vo.sys;

import com.tyuan.model.pojo.SysSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
public class MenuDataItemVo implements Serializable {

    private List<MenuDataItemVo> children;

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

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Long sort;


    /**
     * 是否叶子节点
     */
    private boolean isLeaf;

    /**
     * 如果没有子节点返回null，注意这是因为前端需要
     *
     * @param pid
     * @param wbSysUrls
     * @return
     */
    public static List<MenuDataItemVo> sysMenuToLeftMenuVo(Long pid, List<SysSource> wbSysUrls, boolean isShowNullParent) {
        List<MenuDataItemVo> leftMenuVos = new ArrayList<>();

        Iterator<SysSource> iterator = wbSysUrls.iterator();
        while (iterator.hasNext()) {
            SysSource sysSource = iterator.next();
            if (sysSource.getParentId().equals(pid)) {
                MenuDataItemVo leftMenuVo = new MenuDataItemVo();
                leftMenuVo.setName(sysSource.getName());
                leftMenuVo.setPath(sysSource.getHref());
                leftMenuVo.setIcon(sysSource.getIcon());
                leftMenuVo.setSort(sysSource.getSort());
                leftMenuVo.setLeaf(sysSource.getIsLeaf());

                if (!sysSource.getIsLeaf()) {
                    List<MenuDataItemVo> childs = sysMenuToLeftMenuVo(sysSource.getId(), wbSysUrls, isShowNullParent);
                    //如果是父节点，且下面没有子节点，则不显示
                    if (!isShowNullParent && CollectionUtils.isEmpty(childs)) {
                        continue;
                    }
                    Collections.sort(childs, Comparator.comparingLong(o -> o.getSort()));
                    leftMenuVo.setChildren(childs);
                }

                leftMenuVos.add(leftMenuVo);
            }
        }
        if (null == leftMenuVos || leftMenuVos.size() == 0) {
            return null;
        }
        return leftMenuVos;
    }
}
