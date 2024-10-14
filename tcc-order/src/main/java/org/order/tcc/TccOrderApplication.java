package org.order.tcc;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.order.tcc.persistent")
@EnableDubbo
@EnableTransactionManagement
public class TccOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccOrderApplication.class, args);
    }

}
