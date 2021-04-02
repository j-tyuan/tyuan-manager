package org.springmg.service.model.pojo.custom;

import org.springmg.common.ITree;
import org.springmg.service.model.pojo.SysSource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CSysSource extends SysSource implements ITree {

    List<ITree> children;
}
