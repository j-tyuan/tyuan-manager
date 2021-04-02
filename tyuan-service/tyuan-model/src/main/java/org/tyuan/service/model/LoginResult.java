package org.tyuan.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult {

    /**
     * 'ok' | 'error | disable'
     */
    private String status;

    /**
     * account | mobile
     */
    private String type;

    private String message;

    private int errorCode = ErrorCodeConsts.SUCCESS;
}
