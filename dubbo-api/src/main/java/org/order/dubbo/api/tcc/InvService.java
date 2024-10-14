package org.order.dubbo.api.tcc;


import io.seata.rm.tcc.api.BusinessActionContext;

public interface InvService {

    boolean prepare(BusinessActionContext actionContext, int a);


    boolean commit(BusinessActionContext actionContext);


    boolean rollback(BusinessActionContext actionContext);
}
