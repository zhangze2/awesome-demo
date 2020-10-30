package zz.demo;

import java.util.Map;

/**
 * @author by zz
 * @date 2020/8/31
 */
public abstract class AbstractTaFileCreateTemplate {


    public boolean buildNetvalueFile(Map<String, String> params) {

        QueryListQueueExecutor executor = new QueryListQueueExecutor("sql", params);
        executor.init();

        int threadCount = executor.getThreadCount();
        while (threadCount > 0 ){
            SyncThreadManager.submit(executor);
            threadCount--;
        }



        return true;

    }

}
