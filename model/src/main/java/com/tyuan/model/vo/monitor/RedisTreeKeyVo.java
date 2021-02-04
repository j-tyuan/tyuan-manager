package com.tyuan.model.vo.monitor;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RedisTreeKeyVo {

    String name;

    String path;

    List<RedisTreeKeyVo> children = new ArrayList<>();

    public RedisTreeKeyVo(String name) {
        this.name = name;
    }

    private void setName() {
    }
}
