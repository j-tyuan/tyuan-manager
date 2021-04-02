package org.tyuan.service.model.pojo.custom;

import org.tyuan.common.ITree;
import org.tyuan.service.model.pojo.SysSource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CSysSource extends SysSource implements ITree {

    List<ITree> children;
}
