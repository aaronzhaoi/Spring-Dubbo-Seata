package org.order.tcc;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("org.order.tcc.persistent.dao")
@EnableDubbo
@EnableTransactionManagement
public class TccBalancesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccBalancesApplication.class, args);
    }

}
