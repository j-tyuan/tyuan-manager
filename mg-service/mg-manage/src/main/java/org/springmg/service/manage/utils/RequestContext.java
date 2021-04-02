/**
 * @version 1.0
 * @author jiangguiqi@aliyun.com
 * @date 2021/2/5 12:05 下午
 */
package org.springmg.service.manage.utils;

import org.springmg.common.utils.Tools;

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
