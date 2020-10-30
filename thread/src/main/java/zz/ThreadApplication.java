package zz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author by zz
 * @date 2020/8/29
 */
@SpringBootApplication
@EnableSwagger2
public class ThreadApplication {

    public static void main(String []args) {

        SpringApplication.run(ThreadApplication.class);

    }

}
