package com.zz.common.config;

import java.util.concurrent.Executor;

import com.zz.common.async.ContextCopyingDecorator;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
 * @author by zz
 * @date 2020/8/30
 */
@Configuration
public class AsyncConfig {

    public Executor asyncExecutor() {

        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();

        threadPoolExecutor.setTaskDecorator(new ContextCopyingDecorator());
        threadPoolExecutor.setCorePoolSize(3);
        threadPoolExecutor.setMaxPoolSize(5);
        threadPoolExecutor.setQueueCapacity(100);
        threadPoolExecutor.setWaitForTasksToCompleteOnShutdown(true);

        threadPoolExecutor.setThreadNamePrefix("CommenAsyncThread-");
        threadPoolExecutor.initialize();


        return threadPoolExecutor;
    }
}
