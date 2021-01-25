package zz.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author by zz
 * @date 2021/1/12
 */
public class CompletableFutureTest {


    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        CompletableFutureTest test = new CompletableFutureTest();
        // 结果集
        List<String> list = new ArrayList<>();

        List<Integer> taskList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 全流式处理转换成CompletableFuture[]
        CompletableFuture[] cfs = taskList.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> test.calc(integer))
                        .thenApply(h->Integer.toString(h))
                        .whenComplete((s, e) -> {
                            System.out.println("任务"+s+"完成!result="+s+"，异常 e="+e+","+new Date());
                            list.add(s);
                        })
                ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(cfs).join();

        System.out.println("list="+list+",耗时="+(System.currentTimeMillis()-start));
    }


    public int calc(Integer i) {
        try {
            if (i == 1) {
                //任务1耗时3秒
                Thread.sleep(3000);
            } else if (i == 5) {
                //任务5耗时5秒
                Thread.sleep(5000);
            } else {
                //其它任务耗时1秒
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }



    public void completableFutrueFunctionTest() throws ExecutionException, InterruptedException {
        //无返回值
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync无返回值");
        });

        future1.get();

        //有返回值
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync有返回值");
            return "111";
        });

        String s = future2.get();
    }


}
