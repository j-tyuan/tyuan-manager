package com.tyuan.manager.utils;

public class GenerateUtil {

    /**
     * 生产提数任务编码
     *
     * @return
     */
    public static String generateBatchCode() {

        String oriCode = "batch-";
        return oriCode + System.currentTimeMillis();
    }
}
