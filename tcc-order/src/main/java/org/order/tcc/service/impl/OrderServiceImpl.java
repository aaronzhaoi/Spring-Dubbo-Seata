package org.order.tcc.service.impl;


import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.order.common.util.ResultHolder;
import org.order.dubbo.api.domain.UserDTO;
import org.order.dubbo.api.tcc.OrderService;
import org.order.tcc.persistent.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean prepare(BusinessActionContext actionContext, int a) {
        String xid =String.valueOf(Math.abs(new Random().nextInt()));
        // 生成订单
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(xid);
        userDTO.setUserName("zhangwu");
        orderMapper.insertOrder(userDTO);
        System.out.println("OrderService prepare, xid:" + RootContext.getXID() + ", a:" + a);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        System.out.println("OrderService commit, xid:" + xid + ", a:" + actionContext.getActionContext("a"));
        ResultHolder.setOrderResults(xid, "T");
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        System.out.println("OrderService rollback, xid:" + xid + ", a:" + actionContext.getActionContext("a"));
        ResultHolder.setOrderResults(xid, "R");
        return true;
    }
}
