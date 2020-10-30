package zz.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author by zz
 * @date 2020/8/31
 */
@Slf4j
@Getter
public class QueryListQueueExecutor implements Runnable {


    public static BlockingQueue<Object> queue;
    // 总数据量
    private int count;

    // 分页查询时，每页查询数量
    private int pageSize = 0;
    private double totalPageCount;
    private int currentPageNumber = 0;

    private int threadCount = 1;
    private int finishThreadCount = 0;
    private int failThreadCount = 0;

    private boolean allFinished = false;
    String querySql = "";


    public QueryListQueueExecutor(String querySql, Map<String, String> params){
        this.finishThreadCount = 0;
        this.querySql = querySql;

    }

    private synchronized int getNextPage(){
        if (currentPageNumber > totalPageCount) {
            return currentPageNumber;
        }
        this.currentPageNumber++;
        return this.currentPageNumber;
    }

    private synchronized void threadFinish(){
        finishThreadCount++;

        if (finishThreadCount == threadCount) {
            this.allFinished = true;
            log.info("线程全部完成，耗时：" );
        }
    }


    public boolean init() {

        pageSize = 10000;

        this.allFinished = false;
        this.failThreadCount = 0;

        count  = 100;// 数据库 DAO.queryObject(sql, params) 查询

        double c = (double) count / pageSize;
        totalPageCount = Math.ceil(c);

        int maxThreadCount = 5;

        // 初始化线程
        threadCount = (int)totalPageCount / 5;
        if (threadCount == 0) {
            threadCount = 1;
        } else if (threadCount > maxThreadCount) {
            threadCount = maxThreadCount;
        }
        log.info("初始化成功，共：" + totalPageCount + "页，分" + threadCount + "个线程查询！");

        queue = new LinkedBlockingQueue<>(maxThreadCount);
        return true;

    }

    @Override
    public void run() {
        int cPage;
        while ((cPage = getNextPage()) <= totalPageCount) {
            if (this.failThreadCount > 0) {
                log.error("发现异常线程，此线程不继续进行");
                break;
            }

            int startRowNum = pageSize * (cPage - 1) + 1;
            int endRowNum = pageSize * cPage;
            Map tempMap = new HashMap<>();
            List list = null;// 后台数据库查询
            try {
                queue.put(list);  // put 方法可以阻塞，保证塞进队列
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.failThreadCount++;
                threadFinish();
                break;
            }
        }

        log.info("线程结束");
        threadFinish();

    }
}
