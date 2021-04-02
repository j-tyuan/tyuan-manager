/**
 * @EnumName HttpMethod
 * @Description TODO
 * @Author dev@tyuan.design
 * @Date 2019/7/11 10:23
 */
/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.common.http;

import java.util.HashMap;
import java.util.Map;

public enum HttpMethod {
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE;

    private static final Map<String, HttpMethod> MAPPINGS = new HashMap(8);

    private HttpMethod() {
    }

    public static HttpMethod resolve(String method) {
        return method != null ? MAPPINGS.get(method) : null;
    }

    public boolean matches(String method) {
        return this == resolve(method);
    }

    static {
        HttpMethod[] var0 = values();
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2) {
            HttpMethod httpMethod = var0[var2];
            MAPPINGS.put(httpMethod.name(), httpMethod);
        }

    }
}
