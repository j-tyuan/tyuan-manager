package com.tyuan.model.base.pojo.custom;

import com.tyuan.common.ITree;
import com.tyuan.model.base.pojo.SysSource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CSysSource extends SysSource implements ITree {

    List<ITree> children;
}
