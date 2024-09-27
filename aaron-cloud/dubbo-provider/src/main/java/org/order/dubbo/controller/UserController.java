package org.order.dubbo.controller;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

    @Value("${my.name:haha}")
    private String myName;

    @GetMapping("/get")
    public String get(String msg) {
        return "hi, " + msg + ", my name is " + myName;
    }

}
