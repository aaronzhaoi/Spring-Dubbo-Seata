package org.order.tcc.service;


import io.seata.rm.tcc.api.BusinessActionContext;

public interface BalanceService {

    boolean prepare(BusinessActionContext actionContext, int a);


    boolean commit(BusinessActionContext actionContext);


    boolean rollback(BusinessActionContext actionContext);
}

