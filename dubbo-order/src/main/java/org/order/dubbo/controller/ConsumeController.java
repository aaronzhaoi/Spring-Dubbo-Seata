package org.order.dubbo.controller;


import org.order.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ConsumeController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/dubbo")
    public String placeOrder() {
        return "consumer : " + userService.placeOrder("zhang san");
    }

}