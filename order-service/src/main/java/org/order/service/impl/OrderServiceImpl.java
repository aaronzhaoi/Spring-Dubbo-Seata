package org.order.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.order.persistent.order.mapper.OrderMapper;
import org.order.service.OrderSeataService;
import org.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderSeataService orderSeataService;

    @Override
    public String getOrderName(int OrderId) {
        return orderMapper.getOrderName(OrderId);
    }

    @Override
    @GlobalTransactional
    public void doTcc() {
        orderSeataService.prepare(null,1);
    }
}
