/**
 * @ClassName Host
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/7/16 13:24
 */
package com.tyuan.common.socket;

import io.netty.handler.codec.http.HttpMethod;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Host {

    private String host;

    private int port;

    private HttpMethod httpMethod;

}
