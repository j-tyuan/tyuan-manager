package org.springmg.service.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class DeleteVo implements Serializable {

    List<Long> id;
}
