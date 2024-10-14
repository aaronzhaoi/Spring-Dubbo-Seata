package org.order.balance;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDubbo
@MapperScan("org.order.balance.persistent.dao")
public class DubboBalanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboBalanceApplication.class, args);
    }

}
