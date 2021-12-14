package org.tyuan.common.utils;

import org.slf4j.MDC;

public class MDCUtils {
    public static final String TRACE_ID = "traceId";

    public static void setTraceId(String logId) {
        put(TRACE_ID, logId);
    }

    public static void removeTraceId() {
        remove(TRACE_ID);
    }

    public static void put(String key, String value) {
        MDC.put(key, value);
    }

    public static void remove(String key) {
        MDC.remove(key);
    }

    public static void clear() {
        MDC.clear();
    }
}
