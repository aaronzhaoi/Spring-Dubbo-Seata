/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package org.aaron.app.hoper.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;



public class TaskExecutorConfiguration {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;

    private static final int MAX_POOL_SIZE = CPU_COUNT * 2 + 1;

    private static final int QUEUE_CAPACITY = 800;

    @Bean(name = "workExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.afterPropertiesSet();
        taskExecutor.setThreadNamePrefix("PERFORMANCE-THREAD-");
        taskExecutor.setThreadGroupName("PERFORMANCE-POOL-");
        taskExecutor.initialize();
        return taskExecutor;
    }


}
