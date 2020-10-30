package com.zz.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.base.Stopwatch;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.concurrent.TimeUnit;

/**
 *  @author by zz
 *  @date 2020/8/26
 */

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Around("execution(* com.zz.common.*(..))")
    public Object doAroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        String servletPath = httpServletRequest.getServletPath();

        Principal userPrincipal = httpServletRequest.getUserPrincipal();

        String appOrUserId = "ANONYMOUSUSER";

        if (userPrincipal != null) {
            appOrUserId = userPrincipal.getName();
        }

        ThreadContext.setAppId(appOrUserId);

        log.info("Enter method: [{}], client: [{}]", servletPath, appOrUserId);

        Stopwatch stopwatch = Stopwatch.createStarted();

        try {
            Object proceed = proceedingJoinPoint.proceed();

            log.info("Method [{}] processed succesfully, took [{}]ms, client:[{}]" , servletPath, stopwatch.elapsed(TimeUnit.MILLISECONDS), appOrUserId);

            return proceed;
        } catch (Throwable r) {
            log.error("");
            throw r;
        } finally {
            ThreadContext.setAppId(null);
        }

    }
}
