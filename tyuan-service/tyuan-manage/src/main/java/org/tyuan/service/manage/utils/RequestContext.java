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
package org.tyuan.service.manage.utils;

import org.tyuan.common.utils.Tools;

public class RequestContext {

    private static final ThreadLocal<Context> LOCAL_CONTEXT = new ThreadLocal<>();

    public static void initContext() {
        Context context = new Context();
        context.setRequestId(Tools.generateUUID());
        context.setStartTime(System.currentTimeMillis());
        LOCAL_CONTEXT.set(context);
    }

    public static void setContext(Context context) {
        LOCAL_CONTEXT.set(context);
    }

    public static String getRequestId() {
        return LOCAL_CONTEXT.get().getRequestId();
    }

    public static long getStartTime() {
        return LOCAL_CONTEXT.get().getStartTime();
    }

    public static Context get() {
        return LOCAL_CONTEXT.get();
    }

    public static void remove() {
        LOCAL_CONTEXT.remove();
    }

    public static class Context {

        private String requestId;

        private long startTime;

        private long endTime;

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

    }
}
