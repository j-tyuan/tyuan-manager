package com.tyuan.model.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DictTypeEnum {

    // 颜色
    COLOR("color", "颜色"),
    RAD_USER_CHECK_ATTRIBUTE("rad_user_check_attribute", "RADIUS用户配置属性"),
    RAD_GROUP_CHECK_ATTRIBUTE("rad_group_check_attribute", "RADIUS组配置属性"),
    RAD_GROUP_REPLY_ATTRIBUTE("rad_group_reply_attribute", "RADIUS组回复属性");

    private String type;

    private String description;

    DictTypeEnum(String type, String description) {

        this.type = type;
        this.description = description;
    }

    public String getType() {

        return type;
    }

    public String getDescription() {

        return description;
    }

    public static List<Map> getToList() {

        DictTypeEnum[] enums = DictTypeEnum.values();
        List arr = new ArrayList();

        for (DictTypeEnum item : enums) {

            Map map = new HashMap();
            map.put("type", item.getType());
            map.put("description", item.getDescription());
            arr.add(map);
        }

        return arr;
    }
}
