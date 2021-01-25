package zz.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author by zz
 * @date 2021/1/12
 */
public class FutrueTest {
    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("……3s 后，异步任务执行了");
        });
        future.get();
        System.out.println("主线任务执行了");
    }
}
