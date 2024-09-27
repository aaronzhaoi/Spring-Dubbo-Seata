package org.order.point.controller;


import org.order.common.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/point")
public class PointController {

    @GetMapping(value = "/test")
    public String test() {
        return "this is point-service";
    }

    @PostMapping("addPoint")
    public String addOrder(@RequestBody Order order) {
        return "order name 222" + order.getProductionName();
    }
}
