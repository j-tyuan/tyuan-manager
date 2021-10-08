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
package org.tyuan.service.framework.config;

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
