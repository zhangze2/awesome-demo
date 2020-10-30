package zz.demo;

import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by zz
 * @date 2020/8/29
 */
@RequestMapping(path = "/thread-local")
@RestController
public class ThreadLocalDemo {

    private static final ThreadLocal currentUser = ThreadLocal.withInitial(() -> null);


    @GetMapping(path = "/")
    public Map wrong(@RequestParam("userId") Integer userId) {

        String before = Thread.currentThread().getName() + ":" + currentUser.get();

        currentUser.set(userId);

        String after = Thread.currentThread().getName() + ":" + currentUser.get();

        //汇总输出两次查询结果
        Map result = new HashMap();
        result.put("before", before);
        result.put("after", after);
        return result;
    }

    public Map right(@RequestParam("userId") Integer userId) {

        try {
            String before = Thread.currentThread().getName() + ":" + currentUser.get();

            currentUser.set(userId);

            String after = Thread.currentThread().getName() + ":" + currentUser.get();

            Map<String, String> result = new HashMap<>();

            result.put("before", before);
            result.put("after", after);
        } finally {
            currentUser.remove();
        }


        return null;
    }
}
