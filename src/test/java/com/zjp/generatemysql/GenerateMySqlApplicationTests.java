package com.zjp.generatemysql;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class GenerateMySqlApplicationTests {

    @Test
    void contextLoads() {
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(GenerateMySqlApplicationTests.class.getName());
    }



}
