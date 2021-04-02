package org.springmg.service.model.vo.monitor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RedisValueVo {

    private String type;

    private Object value;

    private Long expire;

}
