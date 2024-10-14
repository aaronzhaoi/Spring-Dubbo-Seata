package org.order.tcc.service;


import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.order.dubbo.api.tcc.BalanceFacade;
import org.order.dubbo.api.tcc.InvService;
import org.order.dubbo.api.tcc.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @DubboReference
    private BalanceFacade balanceFacade;

    @DubboReference
    private InvService invService;

    @Autowired
    private OrderService orderService;


    /**
     * 发起分布式事务
     *
     * @return string string
     */
    @GlobalTransactional
    public String doTransactionCommit() {

        orderService.prepare(null, 1);
        balanceFacade.doBalance(null, 2);
        invService.prepare(null, 3);
        return RootContext.getXID();
    }

    @GlobalTransactional
    public String doTransactionRollback() {
        orderService.prepare(null, 1);
        balanceFacade.doBalance(null, 2);
        invService.prepare(null, 3);
        throw new RuntimeException("transaction rollback");
    }

}