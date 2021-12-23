/**
 * Copyright © 2016-2021 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.data.exception;

public class TyuanException extends Exception {

    private static final long serialVersionUID = 1L;

    private TyuanErrorCode errorCode;

    public TyuanException() {
        super();
    }

    public TyuanException(TyuanErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public TyuanException(String message, TyuanErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TyuanException(String message, Throwable cause, TyuanErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public TyuanException(Throwable cause, TyuanErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public TyuanErrorCode getErrorCode() {
        return errorCode;
    }

}