package com.lujieni.dbsharding.simple;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther lujieni
 * @Date 2020/7/8
 */
@SpringBootApplication(exclude = SpringBootConfiguration.class)
public class ShardingJdbcSimpleStarter {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcSimpleStarter.class,args);
    }
}
