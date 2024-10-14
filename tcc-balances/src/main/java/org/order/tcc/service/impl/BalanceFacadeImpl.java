package org.order.tcc.service.impl;

import io.seata.rm.tcc.api.BusinessActionContext;
import org.apache.dubbo.config.annotation.DubboService;
import org.order.dubbo.api.tcc.BalanceFacade;
import org.order.tcc.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class BalanceFacadeImpl implements BalanceFacade {

    @Autowired
    private BalanceService balanceService;

    @Override
    public void doBalance(BusinessActionContext actionContext, int a) {
        balanceService.prepare(actionContext,a);
    }
}
