package org.order.dubbo.controller;


import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.order.dubbo.api.facade.UserProvideFacade;
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

    @Reference
    private UserProvideFacade userProvideFacade;

    @GetMapping(value = "/dubbo")
    public String testHello() {
        return "consumer : " + userProvideFacade.queryUser("zhang san");
    }


    @GetMapping(value = "/dubbo2")
    public String testHello2() {
        return "consumer : " + userService.getMsg("zhang san");
    }

}