package io.github.mysar.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.MultipartConfigElement;


/**
 * 默认启动类
 * Application.java
 * @Author Im.Yan
 */
@SpringBootApplication
@EnableScheduling //通过@EnableScheduling注解开启对计划任务的支持
@MapperScan("io.github.mysar.blog.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

    }

//    @Bean
//    MultipartConfigElement multipartConfigElement() {
//
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        // 指定上传目录为 D:/data/
//        factory.setLocation("D:/data/");
//        return factory.createMultipartConfig();
//    }
}

/**
 * 在启动类中添加对mapper包扫描@MapperScan("io.github.mysar.blog.mapper")
 * 或者直接在Mapper类上面添加注解@Mapper,
 * 建议使用上面那种，不然每个mapper加个注解也挺麻烦的
 */

/**
 * 在springboot创建定时任务比较简单，只需2步：
    1.在程序的入口加上@EnableScheduling注解。
    2.在定时方法上加@Scheduled注解。
 *
 */
