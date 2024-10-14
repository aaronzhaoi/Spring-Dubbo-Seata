package org.order.dubbo.api.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;

public interface BalanceFacade {

    void doBalance(BusinessActionContext actionContext, int a);

}

