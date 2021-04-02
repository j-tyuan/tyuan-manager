package org.springmg.service.manage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author: geyuqi
 * @DateTime: 2020/7/6 14:26
 * @Description: TODO
 */
@Configuration
public class ThreadPoolConfig {

    @Value("${config.threadPool.batchTask.corePoolSize}")
    private Integer batchTaskCorePoolSize;

    @Value("${config.threadPool.batchTask.maxPoolSize}")
    private Integer batchTaskMaxPoolSize;

    @Value("${config.threadPool.batchTask.queueCapacity}")
    private Integer batchTaskQueueCapacity;


    /**
     * 批量插入原始数据线程池
     *
     * @return
     */
    @Bean(name = "batchInsertOriginRecord")
    public ThreadPoolTaskExecutor batchInsertOriginRecord() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setQueueCapacity(100);

        // 阻塞策略
        executor.setRejectedExecutionHandler((r, executor1) -> {
            try {
                executor1.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(200);
        executor.setKeepAliveSeconds(300);
        return executor;
    }

}
