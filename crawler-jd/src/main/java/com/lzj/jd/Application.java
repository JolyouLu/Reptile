package com.lzj.jd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: LZJ
 * @Date: 2019/7/6 18:19
 * @Version 1.0
 */
@SpringBootApplication
//使用定时任务，需要先开启定时任务，需要添加注解
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
