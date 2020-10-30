package com.zz.common.aop;

/**
 * @author by zz
 * @date 2020/8/26
 */
public class ThreadContext {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static String getAppId() {
        return threadLocal.get();
    }

    public static void setAppId(String appId) {
        threadLocal.set(appId);
    }
}
