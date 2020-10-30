package zz.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author by zz
 * @date 2020/8/31
 */
public class SyncThreadManager {
    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void submit(Runnable actionTread) {
        threadPool.submit(actionTread);
    }
}
