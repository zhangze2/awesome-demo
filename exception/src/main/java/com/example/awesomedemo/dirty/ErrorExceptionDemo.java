package com.example.awesomedemo.dirty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author  zz
 */
@Slf4j
public class ErrorExceptionDemo {

    private void readFile() throws IOException {
        Files.readAllLines(Paths.get("a_file"));
    }

    @GetMapping("wrong1")
    public void wrong1(){
        try {
            this.readFile();
        } catch (IOException e) {
            //原始异常信息丢失： 出了问题不知道 IOException 具体是哪里引起的
            throw new RuntimeException("系统忙请稍后再试");
        }
    }

    @GetMapping("wrong2")
    public void wrong2(){
        try {
            this.readFile();
        } catch (IOException e) {
            //只保留了异常消息，栈没有记录： 只知道文件读取错误的文件名，至于为什么读取错误、是不存在还是没权限，完全不知道
            log.error("文件读取错误, {}", e.getMessage());
            throw new RuntimeException("系统忙请稍后再试");
            // [12:57:19.746] [http-nio-45678-exec-1] [ERROR] [.g.t.c.e.d.HandleExceptionController:35  ] - 文件读取错误, a_file
        }
    }

    @GetMapping("right")
    public void right(){
        try {
            this.readFile();
        } catch (IOException e) {
            log.error("文件读取错误", e);
            throw new RuntimeException("系统忙请稍后再试");
        }

    }
}
