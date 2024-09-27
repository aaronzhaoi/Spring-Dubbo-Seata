package org.order.feignClient;

import org.order.common.Order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "point-service")
public interface PointServiceClient {

    @PostMapping(value = "/point/addPoint")
    String addPoint(@RequestBody Order order);
}
