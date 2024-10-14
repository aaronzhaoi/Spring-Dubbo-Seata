package org.order.dubbo;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.order.dubbo.persistent.dao")
@EnableDubbo
public class DubboInvApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboInvApplication.class, args);
    }

}
