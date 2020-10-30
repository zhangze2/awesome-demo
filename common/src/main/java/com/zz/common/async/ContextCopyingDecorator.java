package com.zz.common.async;

import com.sun.istack.NotNull;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author by zz
 * @date 2020/8/30
 */
public class ContextCopyingDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(@NotNull Runnable runnable) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();



        return () -> {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            runnable.run();
        };
    }
}
