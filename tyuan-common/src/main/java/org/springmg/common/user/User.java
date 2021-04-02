/**
 * @ClassName User
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/7/15 14:17
 */
package org.tyuan.common.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private int id;

    /**
     * 用户可用流量
     */
    private long useableFlow;

    private String name;

    private int userType;

    private String cookie;

}
