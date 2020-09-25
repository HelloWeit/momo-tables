package cn.weit.tables;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author weitong
 */
@SpringBootApplication
@MapperScan("cn.weit.tables.dao")
public class MoMoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoMoApplication.class, args);
    }

}
