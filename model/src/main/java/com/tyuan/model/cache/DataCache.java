package com.tyuan.model.cache;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataCache<T> extends Cache {

    private T data;
}
