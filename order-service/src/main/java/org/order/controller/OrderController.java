package org.order.controller;


import org.order.common.Order;
import org.order.feignClient.PointServiceClient;
import org.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
@RefreshScope
public class OrderController {


    @Autowired
    private PointServiceClient pointServiceClient;

    @Value("${aaron.test}")
    private String nacostest;

    @Autowired
    private OrderService orderService;


    @GetMapping(value = "/test")
    public String test() {
        return nacostest;
    }


    @PostMapping("addOrder")
    public String addOrder() {
        Order order = new Order();
        order.setId("123");
        order.setProductionName("轰20");
        String res = pointServiceClient.addPoint(order);
        return res;
    }

    @GetMapping(value = "/getOrderName")
    public String getOrderName() {
        return orderService.getOrderName(123);
    }
}
