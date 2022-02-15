package com.zjp.generatemysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjp.generatemysql.mapper")
public class GenerateMySqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerateMySqlApplication.class, args);
        System.out.println("接口测试  http://localhost:8081/swagger-ui.html");
    }

}
